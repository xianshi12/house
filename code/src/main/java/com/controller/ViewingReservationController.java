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

import com.entity.ViewingReservationEntity;
import com.entity.view.ViewingReservationView;

import com.service.ViewingReservationService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import com.utils.AliasUtils;
import java.io.IOException;

/**
 * 看房预约
 * 后端接口
 * @author 
 * @email 
 * 
 */
@RestController
@RequestMapping({"/viewingReservation", "/kanfangyuyue"})
public class ViewingReservationController {
    @Autowired
    private ViewingReservationService viewingReservationService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ViewingReservationEntity kanfangyuyue,
		HttpServletRequest request){
		String tableName = request.getAttribute("tableName").toString();
		if(tableName.equals("developer")) {
			kanfangyuyue.setDeveloperCode((String)request.getAttribute("username"));
		}
		if(tableName.equals("user")) {
			kanfangyuyue.setAccount((String)request.getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<ViewingReservationEntity> ew = new EntityWrapper<ViewingReservationEntity>();


        //查询结果
		PageUtils page = viewingReservationService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kanfangyuyue), params), params));
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
    public R list(@RequestParam Map<String, Object> params,ViewingReservationEntity kanfangyuyue, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<ViewingReservationEntity> ew = new EntityWrapper<ViewingReservationEntity>();

        //查询结果
		PageUtils page = viewingReservationService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kanfangyuyue), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ViewingReservationEntity kanfangyuyue){
       	EntityWrapper<ViewingReservationEntity> ew = new EntityWrapper<ViewingReservationEntity>();
      	ew.allEq(MPUtil.allEQMapPre( kanfangyuyue, "viewing_reservation")); 
        return R.ok().put("data", viewingReservationService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ViewingReservationEntity kanfangyuyue){
        EntityWrapper< ViewingReservationEntity> ew = new EntityWrapper< ViewingReservationEntity>();
 		ew.allEq(MPUtil.allEQMapPre( kanfangyuyue, "viewing_reservation")); 
		ViewingReservationView kanfangyuyueView =  viewingReservationService.selectView(ew);
		return R.ok("查询看房预约成功").put("data", kanfangyuyueView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ViewingReservationEntity kanfangyuyue = viewingReservationService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(kanfangyuyue,deSens);
        return R.ok().put("data", kanfangyuyue);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ViewingReservationEntity kanfangyuyue = viewingReservationService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(kanfangyuyue,deSens);
        return R.ok().put("data", kanfangyuyue);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ViewingReservationEntity kanfangyuyue, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kanfangyuyue);
        viewingReservationService.insert(kanfangyuyue);
        return R.ok().put("data",kanfangyuyue.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ViewingReservationEntity kanfangyuyue, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kanfangyuyue);
        viewingReservationService.insert(kanfangyuyue);
        return R.ok().put("data",kanfangyuyue.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ViewingReservationEntity kanfangyuyue, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kanfangyuyue);
        //全部更新
        viewingReservationService.updateById(kanfangyuyue);
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<ViewingReservationEntity> list = new ArrayList<ViewingReservationEntity>();
        for(Long id : ids) {
            ViewingReservationEntity kanfangyuyue = viewingReservationService.selectById(id);
            kanfangyuyue.setAuditStatus(sfsh);
            kanfangyuyue.setAuditReply(shhf);
            list.add(kanfangyuyue);
        }
        viewingReservationService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        viewingReservationService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    






    /**
     * （按值统计）
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName,HttpServletRequest request) throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_kanfangyuyue_" + xColumnName + "_" + yColumnName + "_timeType.json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        //构建查询统计条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", AliasUtils.column(xColumnName));
        params.put("yColumn", AliasUtils.column(yColumnName));
        EntityWrapper<ViewingReservationEntity> ew = new EntityWrapper<ViewingReservationEntity>();
        String tableName = request.getAttribute("tableName").toString();
                                        if(tableName.equals("developer")) {
            ew.eq("developer_code", (String)request.getAttribute("username"));
        }
                        if(tableName.equals("user")) {
            ew.eq("account", (String)request.getAttribute("username"));
        }
                                    //获取结果
        List<Map<String, Object>> result = viewingReservationService.selectValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        Collections.sort(result, (map1, map2) -> {
            // 假设 total 总是存在并且是数值类型
            Number total1 = (Number) map1.get("total");
            Number total2 = (Number) map2.get("total");
            if(total1==null)
            {
                total1 = 0;
            }
            if(total2==null)
            {
                total2 = 0;
            }
            return Double.compare(total2.doubleValue(), total1.doubleValue());
        });
        return R.ok().put("data", result);
    }
    
    /**
     * （按值统计(多)）
     */
    @RequestMapping("/valueMul/{xColumnName}")
    public R valueMul(@PathVariable("xColumnName") String xColumnName,@RequestParam String yColumnNameMul,HttpServletRequest request)  throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_kanfangyuyue_" + xColumnName + "_" + yColumnNameMul + "_timeType.json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", AliasUtils.column(xColumnName));
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //构建查询统计条件
        EntityWrapper<ViewingReservationEntity> ew = new EntityWrapper<ViewingReservationEntity>();
        String tableName = request.getAttribute("tableName").toString();
        if(tableName.equals("developer")) {
            ew.eq("developer_code", (String)request.getAttribute("username"));
        }
        if(tableName.equals("user")) {
            ew.eq("account", (String)request.getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", AliasUtils.column(yColumnNames[i]));
            List<Map<String, Object>> result = viewingReservationService.selectValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }
    
    /**
     * （按值统计）时间统计类型
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}/{timeStatType}")
    public R valueDay(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,HttpServletRequest request) throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_kanfangyuyue_" + xColumnName + "_" + yColumnName + "_"+timeStatType+".json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", AliasUtils.column(xColumnName));
        params.put("yColumn", AliasUtils.column(yColumnName));
        params.put("timeStatType", timeStatType);
        //构建查询统计条件
        EntityWrapper<ViewingReservationEntity> ew = new EntityWrapper<ViewingReservationEntity>();
        String tableName = request.getAttribute("tableName").toString();
        if(tableName.equals("developer")) {
            ew.eq("developer_code", (String)request.getAttribute("username"));
        }
        if(tableName.equals("user")) {
            ew.eq("account", (String)request.getAttribute("username"));
        }
        List<Map<String, Object>> result = viewingReservationService.selectTimeStatValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }
    
    /**
     * （按值统计）时间统计类型(多)
     */
    @RequestMapping("/valueMul/{xColumnName}/{timeStatType}")
    public R valueMulDay(@PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,@RequestParam String yColumnNameMul,HttpServletRequest request) throws IOException
    {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_kanfangyuyue_" + xColumnName + "_" + yColumnNameMul + "_" + timeStatType + ".json");
        if (java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", AliasUtils.column(xColumnName));
        params.put("timeStatType", timeStatType);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //构建查询统计条件
        EntityWrapper<ViewingReservationEntity> ew = new EntityWrapper<ViewingReservationEntity>();
        String tableName = request.getAttribute("tableName").toString();
        if(tableName.equals("developer")) {
            ew.eq("developer_code", (String)request.getAttribute("username"));
        }
        if(tableName.equals("user")) {
            ew.eq("account", (String)request.getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", AliasUtils.column(yColumnNames[i]));
            List<Map<String, Object>> result = viewingReservationService.selectTimeStatValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }
    
    /**
     * 分组统计
     */
    @RequestMapping("/group/{columnName}")
    public R group(@PathVariable("columnName") String columnName,HttpServletRequest request) throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("group_kanfangyuyue_" + columnName + "_timeType.json");
        if(java.nio.file.Files.exists(path)){
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("column", AliasUtils.column(columnName));
        //构建查询统计条件
        EntityWrapper<ViewingReservationEntity> ew = new EntityWrapper<ViewingReservationEntity>();
        String tableName = request.getAttribute("tableName").toString();
        if(tableName.equals("developer")) {
            ew.eq("developer_code", (String)request.getAttribute("username"));
        }
        if(tableName.equals("user")) {
            ew.eq("account", (String)request.getAttribute("username"));
        }
        List<Map<String, Object>> result = viewingReservationService.selectGroup(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
            AliasUtils.putAlias(m, columnName, AliasUtils.column(columnName));
        }
        return R.ok().put("data", result);
    }    




    /**
     * 总数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params,ViewingReservationEntity kanfangyuyue, HttpServletRequest request){
        String tableName = request.getAttribute("tableName").toString();
        if(tableName.equals("developer")) {
            kanfangyuyue.setDeveloperCode((String)request.getAttribute("username"));
        }
        if(tableName.equals("user")) {
            kanfangyuyue.setAccount((String)request.getAttribute("username"));
        }
        EntityWrapper<ViewingReservationEntity> ew = new EntityWrapper<ViewingReservationEntity>();
        int count = viewingReservationService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kanfangyuyue), params), params));
        return R.ok().put("data", count);
    }

}
