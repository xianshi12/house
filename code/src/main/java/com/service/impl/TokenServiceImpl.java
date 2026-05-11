package com.service.impl;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.TokenDao;
import com.entity.TokenEntity;
import com.service.TokenService;
import com.utils.JwtUtils;
import com.utils.PageUtils;
import com.utils.Query;
import com.utils.RedisUtils;


/**
 * token
 */
@Service("tokenService")
public class TokenServiceImpl extends ServiceImpl<TokenDao, TokenEntity> implements TokenService {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private RedisUtils redisUtils;

	private static final String TOKEN_PREFIX = "token:";
	private static final String REFRESH_PREFIX = "refresh:";

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<TokenEntity> page = this.selectPage(
                new Query<TokenEntity>(params).getPage(),
                new EntityWrapper<TokenEntity>()
        );
        return new PageUtils(page);
	}

	@Override
	public List<TokenEntity> selectListView(Wrapper<TokenEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public PageUtils queryPage(Map<String, Object> params,
			Wrapper<TokenEntity> wrapper) {
		 Page<TokenEntity> page =new Query<TokenEntity>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
	}

	@Override
	public Map<String, String> generateToken(Long userid, String username, String tableName, String role) {
		// 数据库 token 字段是 varchar(200)，JWT 访问令牌会超过长度限制。
		// 权限拦截器按 token 表/Redis 查会话，所以访问令牌保持为短随机串更兼容现有库表。
		String accessToken = UUID.randomUUID().toString().replace("-", "");
		// 生成刷新令牌(7天)
		String refreshToken = jwtUtils.generateRefreshToken(userid, username, tableName, role);

		// accessToken缓存到Redis(30分钟)
		String accessKey = TOKEN_PREFIX + accessToken;
		Map<String, Object> tokenInfo = new HashMap<>();
		tokenInfo.put("userId", userid);
		tokenInfo.put("username", username);
		tokenInfo.put("tableName", tableName);
		tokenInfo.put("role", role);
		redisUtils.set(accessKey, tokenInfo, 30, TimeUnit.MINUTES);

		// refreshToken缓存到Redis(7天)
		String refreshKey = REFRESH_PREFIX + refreshToken;
		redisUtils.set(refreshKey, tokenInfo, 7, TimeUnit.DAYS);

		// 同步保存到DB(保持兼容性)
		TokenEntity tokenEntity = this.selectOne(new EntityWrapper<TokenEntity>().eq("userid", userid).eq("role", role));
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY, 1);
		if(tokenEntity != null) {
			tokenEntity.setToken(accessToken);
			tokenEntity.setExpiratedtime(cal.getTime());
			this.updateById(tokenEntity);
		} else {
			this.insert(new TokenEntity(userid, username, tableName, role, accessToken, cal.getTime()));
		}

		Map<String, String> result = new HashMap<>();
		result.put("token", accessToken);
		result.put("refreshToken", refreshToken);
		return result;
	}

	@Override
	public TokenEntity getTokenEntity(String token) {
		// 优先从Redis获取
		String accessKey = TOKEN_PREFIX + token;
		Object cached = redisUtils.get(accessKey);
		if (cached != null) {
			@SuppressWarnings("unchecked")
			Map<String, Object> tokenInfo = (Map<String, Object>) cached;
			TokenEntity entity = new TokenEntity();
			entity.setUserid(Long.valueOf(tokenInfo.get("userId").toString()));
			entity.setUsername((String) tokenInfo.get("username"));
			entity.setTablename((String) tokenInfo.get("tableName"));
			entity.setRole((String) tokenInfo.get("role"));
			entity.setToken(token);
			entity.setExpiratedtime(new Date(System.currentTimeMillis() + 30 * 60 * 1000));
			return entity;
		}

		// Redis未命中，回退DB查询
		TokenEntity tokenEntity = this.selectOne(new EntityWrapper<TokenEntity>().eq("token", token));
		if(tokenEntity == null || tokenEntity.getExpiratedtime().getTime() < new Date().getTime()) {
			return null;
		}
		return tokenEntity;
	}

	@Override
	public Map<String, String> refreshToken(String refreshToken) {
		// 验证刷新令牌
		if (!jwtUtils.validateToken(refreshToken) || !jwtUtils.isRefreshToken(refreshToken)) {
			return null;
		}

		String refreshKey = REFRESH_PREFIX + refreshToken;
		Object cached = redisUtils.get(refreshKey);
		if (cached == null) {
			return null;
		}

		@SuppressWarnings("unchecked")
		Map<String, Object> tokenInfo = (Map<String, Object>) cached;
		Long userId = Long.valueOf(tokenInfo.get("userId").toString());
		String username = (String) tokenInfo.get("username");
		String tableName = (String) tokenInfo.get("tableName");
		String role = (String) tokenInfo.get("role");

		// 删除旧的refreshToken
		redisUtils.delete(refreshKey);

		// 生成新的令牌对
		return generateToken(userId, username, tableName, role);
	}
}
