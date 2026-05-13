package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.utils.ValidatorUtils;
import com.utils.DeSensUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.PropertyCommentEntity;
import com.entity.view.PropertyCommentView;
import com.entity.UserEntity;

import com.service.DeveloperService;
import com.service.PropertyInfoService;
import com.service.PropertyCommentService;
import com.service.TokenService;
import com.service.UserService;
import com.service.UsersService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import com.utils.PropertyCommentDisplayUtils;
import java.io.IOException;

/**
 * 房源信息评论表
 * 后端接口
 * @author 
 * @email 
 * 
 */
@RestController
@RequestMapping({"/propertyComment", "/discussfangyuanxinxi"})
public class PropertyCommentController {
    @Autowired
    private PropertyCommentService propertyCommentService;
    @Autowired
    private UserService userService;
    @Autowired
    private PropertyInfoService propertyInfoService;
    @Autowired
    private DeveloperService developerService;
    @Autowired
    private UsersService usersService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,PropertyCommentEntity propertyComment,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<PropertyCommentEntity> ew = new EntityWrapper<PropertyCommentEntity>();


        //查询结果
		PageUtils page = propertyCommentService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, propertyComment), params), params));
        PropertyCommentDisplayUtils.fillPage(page, userService, propertyInfoService, developerService, usersService);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,PropertyCommentEntity propertyComment, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<PropertyCommentEntity> ew = new EntityWrapper<PropertyCommentEntity>();

        //查询结果
		PageUtils page = propertyCommentService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, propertyComment), params), params));
        PropertyCommentDisplayUtils.fillPage(page, userService, propertyInfoService, developerService, usersService);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( PropertyCommentEntity propertyComment){
       	EntityWrapper<PropertyCommentEntity> ew = new EntityWrapper<PropertyCommentEntity>();
      	ew.allEq(MPUtil.allEQMapPre( propertyComment, "property_comment")); 
        return R.ok().put("data", PropertyCommentDisplayUtils.fillList(propertyCommentService.selectListView(ew), userService, propertyInfoService, developerService, usersService));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(PropertyCommentEntity propertyComment){
        EntityWrapper< PropertyCommentEntity> ew = new EntityWrapper< PropertyCommentEntity>();
		ew.allEq(MPUtil.allEQMapPre( propertyComment, "property_comment")); 
		PropertyCommentView propertyCommentView =  propertyCommentService.selectView(ew);
		PropertyCommentDisplayUtils.fill(propertyCommentView, userService, propertyInfoService, developerService, usersService);
		return R.ok("查询房源信息评论表成功").put("data", propertyCommentView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        PropertyCommentEntity propertyComment = propertyCommentService.selectById(id);
        PropertyCommentDisplayUtils.fill(propertyComment, userService, propertyInfoService, developerService, usersService);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(propertyComment,deSens);
        return R.ok().put("data", propertyComment);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        PropertyCommentEntity propertyComment = propertyCommentService.selectById(id);
        PropertyCommentDisplayUtils.fill(propertyComment, userService, propertyInfoService, developerService, usersService);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(propertyComment,deSens);
        return R.ok().put("data", propertyComment);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PropertyCommentEntity propertyComment, HttpServletRequest request){
        //ValidatorUtils.validateEntity(propertyComment);
        fillCurrentCommentUser(propertyComment, request);
        propertyCommentService.insert(propertyComment);
        return R.ok().put("data",propertyComment.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody PropertyCommentEntity propertyComment, HttpServletRequest request){
        //ValidatorUtils.validateEntity(propertyComment);
        fillCurrentCommentUser(propertyComment, request);
        propertyCommentService.insert(propertyComment);
        return R.ok().put("data",propertyComment.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        PropertyCommentEntity propertyComment = propertyCommentService.selectOne(new EntityWrapper<PropertyCommentEntity>().eq("", username));
        return R.ok().put("data", propertyComment);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody PropertyCommentEntity propertyComment, HttpServletRequest request){
        //ValidatorUtils.validateEntity(propertyComment);
        //全部更新
        propertyCommentService.updateById(propertyComment);
        return R.ok();
    }

    private void fillCurrentCommentUser(PropertyCommentEntity propertyComment, HttpServletRequest request) {
        Object userIdObj = request.getSession().getAttribute("userId");
        if (!(userIdObj instanceof Long)) {
            return;
        }
        Long userId = (Long) userIdObj;
        propertyComment.setUserid(userId);
        UserEntity user = userService.selectById(userId);
        if (user == null) {
            return;
        }
        if (StringUtils.isNotBlank(user.getAvatar())) {
            propertyComment.setAvatarurl(user.getAvatar());
        }
        if (StringUtils.isNotBlank(user.getFullName())) {
            propertyComment.setNickname(user.getFullName());
        } else if (StringUtils.isNotBlank(user.getAccount())) {
            propertyComment.setNickname(user.getAccount());
        }
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        propertyCommentService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,PropertyCommentEntity propertyComment, HttpServletRequest request,String pre){
        EntityWrapper<PropertyCommentEntity> ew = new EntityWrapper<PropertyCommentEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        // 组装参数
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");

		PageUtils page = propertyCommentService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, propertyComment), params), params));
        return R.ok().put("data", page);
    }








}
