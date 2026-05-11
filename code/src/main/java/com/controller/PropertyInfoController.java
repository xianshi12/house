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
import com.utils.UserBasedCollaborativeFiltering;

import com.entity.PropertyInfoEntity;
import com.entity.view.PropertyInfoView;

import com.service.PropertyInfoService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import com.utils.AliasUtils;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;
import com.utils.RedisUtils;
import java.util.concurrent.TimeUnit;

/**
 * 房源信息
 * 后端接口
 * @author 
 * @email 
 * 
 */
@RestController
@RequestMapping({"/propertyInfo", "/fangyuanxinxi"})
public class PropertyInfoController {
    @Autowired
    private PropertyInfoService propertyInfoService;

    @Autowired
    private StoreupService storeupService;

    @Autowired
    private RedisUtils redisUtils;





    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,PropertyInfoEntity fangyuanxinxi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("developer")) {
			fangyuanxinxi.setDeveloperCode((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<PropertyInfoEntity> ew = new EntityWrapper<PropertyInfoEntity>();


        //查询结果
		PageUtils page = propertyInfoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fangyuanxinxi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,PropertyInfoEntity fangyuanxinxi, 
		HttpServletRequest request){
        // 尝试从Redis缓存获取
        //设置查询条件
        EntityWrapper<PropertyInfoEntity> ew = new EntityWrapper<PropertyInfoEntity>();

        //查询结果
		PageUtils page = propertyInfoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fangyuanxinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        // 缓存到Redis(1小时)
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( PropertyInfoEntity fangyuanxinxi){
       	EntityWrapper<PropertyInfoEntity> ew = new EntityWrapper<PropertyInfoEntity>();
      	ew.allEq(MPUtil.allEQMapPre( fangyuanxinxi, "property_info")); 
        return R.ok().put("data", propertyInfoService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(PropertyInfoEntity fangyuanxinxi){
        EntityWrapper< PropertyInfoEntity> ew = new EntityWrapper< PropertyInfoEntity>();
 		ew.allEq(MPUtil.allEQMapPre( fangyuanxinxi, "property_info")); 
		PropertyInfoView fangyuanxinxiView =  propertyInfoService.selectView(ew);
		return R.ok("查询房源信息成功").put("data", fangyuanxinxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        PropertyInfoEntity fangyuanxinxi = propertyInfoService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(fangyuanxinxi,deSens);
        return R.ok().put("data", fangyuanxinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        // 尝试从Redis缓存获取
        String cacheKey = "fangyuanxinxi:detail:" + id;
        Object cached = redisUtils.get(cacheKey);
        if(cached != null) {
            return R.ok().put("data", cached);
        }
        PropertyInfoEntity fangyuanxinxi = propertyInfoService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(fangyuanxinxi,deSens);
        // 缓存到Redis(长期有效)
        redisUtils.set(cacheKey, fangyuanxinxi);
        return R.ok().put("data", fangyuanxinxi);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PropertyInfoEntity fangyuanxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(fangyuanxinxi);
        propertyInfoService.insert(fangyuanxinxi);
        // 清除该房源缓存
        redisUtils.delete("fangyuanxinxi:detail:" + fangyuanxinxi.getId());
        return R.ok().put("data",fangyuanxinxi.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody PropertyInfoEntity fangyuanxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(fangyuanxinxi);
        propertyInfoService.insert(fangyuanxinxi);
        // 清除该房源缓存
        redisUtils.delete("fangyuanxinxi:detail:" + fangyuanxinxi.getId());
        return R.ok().put("data",fangyuanxinxi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody PropertyInfoEntity fangyuanxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(fangyuanxinxi);
        //全部更新
        propertyInfoService.updateById(fangyuanxinxi);
        // 清除该房源缓存
        redisUtils.delete("fangyuanxinxi:detail:" + fangyuanxinxi.getId());
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        propertyInfoService.deleteBatchIds(Arrays.asList(ids));
        // 清除缓存
        for(Long id : ids) {
            redisUtils.delete("fangyuanxinxi:detail:" + id);
        }
        return R.ok();
    }
    
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,PropertyInfoEntity fangyuanxinxi, HttpServletRequest request,String pre){
        EntityWrapper<PropertyInfoEntity> ew = new EntityWrapper<PropertyInfoEntity>();
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

		PageUtils page = propertyInfoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fangyuanxinxi), params), params));
        return R.ok().put("data", page);
    }


    /**
     * 协同算法（基于用户收藏的协同算法）
     */
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,PropertyInfoEntity fangyuanxinxi, HttpServletRequest request){
        String userId = request.getSession().getAttribute("userId").toString();
        Integer limit = params.get("limit")==null?10:Integer.parseInt(params.get("limit").toString());
        List<StoreupEntity> storeups = storeupService.selectList(new EntityWrapper<StoreupEntity>().eq("type", 1).eq("tablename", "property_info"));
        // 创建协同过滤对象
        UserBasedCollaborativeFiltering filter = new UserBasedCollaborativeFiltering();

        // 为指定用户推荐物品
        String targetUser = userId;
        int numRecommendations = limit;
        List<String> recommendations = filter.recommendItems(storeups, "userid", "refid", userId, numRecommendations);

        // 输出推荐结果
        System.out.println("Recommendations for " + targetUser + ":");
        for (String item : recommendations) {
            System.out.println(item);
        }

        EntityWrapper<PropertyInfoEntity> ew = new EntityWrapper<PropertyInfoEntity>();
        ew.in("id", String.join(",", recommendations));
        if(recommendations!=null && recommendations.size()>0 && recommendations.size()>0) {
            ew.last("order by FIELD(id, "+"'"+String.join("','", recommendations)+"'"+")");
        }

        // 根据协同结果查询结果并返回
        PageUtils page = propertyInfoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fangyuanxinxi), params), params));;
        List<PropertyInfoEntity> pageList = (List<PropertyInfoEntity>)page.getList();
        if(recommendations!=null && recommendations.size()>0 && pageList.size()<limit) {
            int toAddNum = limit-pageList.size();
            ew = new EntityWrapper<PropertyInfoEntity>();
            ew.notIn("id", recommendations);
            ew.orderBy("id", false);
            ew.last("limit "+toAddNum);
            pageList.addAll(propertyInfoService.selectList(ew));
        } else if(pageList.size()>limit) {
            pageList = pageList.subList(0, limit);
        }
        page.setList(pageList);

        return R.ok().put("data", page);
    }




    /**
     * （按值统计）
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName,HttpServletRequest request) throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_fangyuanxinxi_" + xColumnName + "_" + yColumnName + "_timeType.json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        //构建查询统计条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", AliasUtils.column(xColumnName));
        params.put("yColumn", AliasUtils.column(yColumnName));
        EntityWrapper<PropertyInfoEntity> ew = new EntityWrapper<PropertyInfoEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
                                                    if(tableName.equals("developer")) {
            ew.eq("developer_code", (String)request.getSession().getAttribute("username"));
        }
                                        //获取结果
        List<Map<String, Object>> result = propertyInfoService.selectValue(params, ew);
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
        java.nio.file.Path path = java.nio.file.Paths.get("value_fangyuanxinxi_" + xColumnName + "_" + yColumnNameMul + "_timeType.json");
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
        EntityWrapper<PropertyInfoEntity> ew = new EntityWrapper<PropertyInfoEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("developer")) {
            ew.eq("developer_code", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", AliasUtils.column(yColumnNames[i]));
            List<Map<String, Object>> result = propertyInfoService.selectValue(params, ew);
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
        java.nio.file.Path path = java.nio.file.Paths.get("value_fangyuanxinxi_" + xColumnName + "_" + yColumnName + "_"+timeStatType+".json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", AliasUtils.column(xColumnName));
        params.put("yColumn", AliasUtils.column(yColumnName));
        params.put("timeStatType", timeStatType);
        //构建查询统计条件
        EntityWrapper<PropertyInfoEntity> ew = new EntityWrapper<PropertyInfoEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("developer")) {
            ew.eq("developer_code", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = propertyInfoService.selectTimeStatValue(params, ew);
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
        java.nio.file.Path path = java.nio.file.Paths.get("value_fangyuanxinxi_" + xColumnName + "_" + yColumnNameMul + "_" + timeStatType + ".json");
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
        EntityWrapper<PropertyInfoEntity> ew = new EntityWrapper<PropertyInfoEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("developer")) {
            ew.eq("developer_code", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", AliasUtils.column(yColumnNames[i]));
            List<Map<String, Object>> result = propertyInfoService.selectTimeStatValue(params, ew);
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
        java.nio.file.Path path = java.nio.file.Paths.get("group_fangyuanxinxi_" + columnName + "_timeType.json");
        if(java.nio.file.Files.exists(path)){
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("column", AliasUtils.column(columnName));
        //构建查询统计条件
        EntityWrapper<PropertyInfoEntity> ew = new EntityWrapper<PropertyInfoEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("developer")) {
            ew.eq("developer_code", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = propertyInfoService.selectGroup(params, ew);
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
    public R count(@RequestParam Map<String, Object> params,PropertyInfoEntity fangyuanxinxi, HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("developer")) {
            fangyuanxinxi.setDeveloperCode((String)request.getSession().getAttribute("username"));
        }
        EntityWrapper<PropertyInfoEntity> ew = new EntityWrapper<PropertyInfoEntity>();
        int count = propertyInfoService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fangyuanxinxi), params), params));
        return R.ok().put("data", count);
    }

}
