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

import com.entity.IntentDepositPaymentEntity;
import com.entity.view.IntentDepositPaymentView;

import com.service.IntentDepositPaymentService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 意向金支付
 * 后端接口
 * @author 
 * @email 
 * 
 */
@RestController
@RequestMapping({"/intentDepositPayment", "/yixiangjinzhifu"})
public class IntentDepositPaymentController {
    @Autowired
    private IntentDepositPaymentService intentDepositPaymentService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,IntentDepositPaymentEntity yixiangjinzhifu,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("developer")) {
			yixiangjinzhifu.setDeveloperCode((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("user")) {
			yixiangjinzhifu.setAccount((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<IntentDepositPaymentEntity> ew = new EntityWrapper<IntentDepositPaymentEntity>();


        //查询结果
		PageUtils page = intentDepositPaymentService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yixiangjinzhifu), params), params));
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
    public R list(@RequestParam Map<String, Object> params,IntentDepositPaymentEntity yixiangjinzhifu, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<IntentDepositPaymentEntity> ew = new EntityWrapper<IntentDepositPaymentEntity>();

        //查询结果
		PageUtils page = intentDepositPaymentService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yixiangjinzhifu), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( IntentDepositPaymentEntity yixiangjinzhifu){
       	EntityWrapper<IntentDepositPaymentEntity> ew = new EntityWrapper<IntentDepositPaymentEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yixiangjinzhifu, "intent_deposit_payment")); 
        return R.ok().put("data", intentDepositPaymentService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(IntentDepositPaymentEntity yixiangjinzhifu){
        EntityWrapper< IntentDepositPaymentEntity> ew = new EntityWrapper< IntentDepositPaymentEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yixiangjinzhifu, "intent_deposit_payment")); 
		IntentDepositPaymentView yixiangjinzhifuView =  intentDepositPaymentService.selectView(ew);
		return R.ok("查询意向金支付成功").put("data", yixiangjinzhifuView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        IntentDepositPaymentEntity yixiangjinzhifu = intentDepositPaymentService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(yixiangjinzhifu,deSens);
        return R.ok().put("data", yixiangjinzhifu);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        IntentDepositPaymentEntity yixiangjinzhifu = intentDepositPaymentService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(yixiangjinzhifu,deSens);
        return R.ok().put("data", yixiangjinzhifu);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody IntentDepositPaymentEntity yixiangjinzhifu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yixiangjinzhifu);
        intentDepositPaymentService.insert(yixiangjinzhifu);
        return R.ok().put("data",yixiangjinzhifu.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody IntentDepositPaymentEntity yixiangjinzhifu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yixiangjinzhifu);
        intentDepositPaymentService.insert(yixiangjinzhifu);
        return R.ok().put("data",yixiangjinzhifu.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody IntentDepositPaymentEntity yixiangjinzhifu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yixiangjinzhifu);
        //全部更新
        intentDepositPaymentService.updateById(yixiangjinzhifu);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        intentDepositPaymentService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
