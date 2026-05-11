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

import com.entity.NewsTypeEntity;
import com.entity.view.NewsTypeView;

import com.service.NewsTypeService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 售房资讯分类
 * 后端接口
 * @author 
 * @email 
 * 
 */
@RestController
@RequestMapping({"/newsType", "/newstype"})
public class NewsTypeController {
    @Autowired
    private NewsTypeService newsTypeService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,NewsTypeEntity newstype,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<NewsTypeEntity> ew = new EntityWrapper<NewsTypeEntity>();


        //查询结果
		PageUtils page = newsTypeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, newstype), params), params));
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
    public R list(@RequestParam Map<String, Object> params,NewsTypeEntity newstype, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<NewsTypeEntity> ew = new EntityWrapper<NewsTypeEntity>();

        //查询结果
		PageUtils page = newsTypeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, newstype), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( NewsTypeEntity newstype){
       	EntityWrapper<NewsTypeEntity> ew = new EntityWrapper<NewsTypeEntity>();
      	ew.allEq(MPUtil.allEQMapPre( newstype, "newsType")); 
        return R.ok().put("data", newsTypeService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(NewsTypeEntity newstype){
        EntityWrapper< NewsTypeEntity> ew = new EntityWrapper< NewsTypeEntity>();
 		ew.allEq(MPUtil.allEQMapPre( newstype, "newsType")); 
		NewsTypeView newstypeView =  newsTypeService.selectView(ew);
		return R.ok("查询售房资讯分类成功").put("data", newstypeView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        NewsTypeEntity newstype = newsTypeService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(newstype,deSens);
        return R.ok().put("data", newstype);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        NewsTypeEntity newstype = newsTypeService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(newstype,deSens);
        return R.ok().put("data", newstype);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody NewsTypeEntity newstype, HttpServletRequest request){
        //ValidatorUtils.validateEntity(newstype);
        newsTypeService.insert(newstype);
        return R.ok().put("data",newstype.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody NewsTypeEntity newstype, HttpServletRequest request){
        //ValidatorUtils.validateEntity(newstype);
        newsTypeService.insert(newstype);
        return R.ok().put("data",newstype.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        NewsTypeEntity newstype = newsTypeService.selectOne(new EntityWrapper<NewsTypeEntity>().eq("", username));
        return R.ok().put("data", newstype);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody NewsTypeEntity newstype, HttpServletRequest request){
        //ValidatorUtils.validateEntity(newstype);
        //全部更新
        newsTypeService.updateById(newstype);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        newsTypeService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,NewsTypeEntity newstype, HttpServletRequest request,String pre){
        EntityWrapper<NewsTypeEntity> ew = new EntityWrapper<NewsTypeEntity>();
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

		PageUtils page = newsTypeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, newstype), params), params));
        return R.ok().put("data", page);
    }








}
