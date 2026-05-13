package com.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${jwt.access-token-expiration}")
	private Long accessTokenExpiration;

	@Value("${jwt.refresh-token-expiration}")
	private Long refreshTokenExpiration;

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
		String accessToken = jwtUtils.generateAccessToken(userid, username, tableName, role);
		String refreshToken = jwtUtils.generateRefreshToken(userid, username, tableName, role);
		Map<String, Object> tokenInfo = buildTokenInfo(userid, username, tableName, role);
		cacheRefreshToken(refreshToken, tokenInfo);

		Map<String, String> result = new HashMap<>();
		result.put("token", accessToken);
		result.put("refreshToken", refreshToken);
		return result;
	}

	@Override
	public TokenEntity getTokenEntity(String token) {
		if (!jwtUtils.validateToken(token) || !jwtUtils.isAccessToken(token)) {
			return null;
		}
		io.jsonwebtoken.Claims claims = jwtUtils.parseToken(token);
		TokenEntity entity = new TokenEntity();
		entity.setUserid(Long.valueOf(claims.get("userId").toString()));
		entity.setUsername((String) claims.get("username"));
		entity.setTablename((String) claims.get("tableName"));
		entity.setRole((String) claims.get("role"));
		entity.setToken(token);
		return entity;
	}

	@Override
	public Map<String, String> refreshToken(String refreshToken) {
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

		String accessToken = jwtUtils.generateAccessToken(userId, username, tableName, role);
		Map<String, String> result = new HashMap<>();
		result.put("token", accessToken);
		result.put("refreshToken", refreshToken);
		return result;
	}

	private Map<String, Object> buildTokenInfo(Long userid, String username, String tableName, String role) {
		Map<String, Object> tokenInfo = new HashMap<>();
		tokenInfo.put("userId", userid);
		tokenInfo.put("username", username);
		tokenInfo.put("tableName", tableName);
		tokenInfo.put("role", role);
		return tokenInfo;
	}

	private void cacheRefreshToken(String refreshToken, Map<String, Object> tokenInfo) {
		redisUtils.set(REFRESH_PREFIX + refreshToken, tokenInfo, refreshTokenExpiration, TimeUnit.MILLISECONDS);
	}
}
