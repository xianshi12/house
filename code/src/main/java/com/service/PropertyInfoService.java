package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.PropertyInfoEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.PropertyInfoVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.PropertyInfoView;


/**
 * 房源信息
 *
 * @author 
 * @email 
 * 
 */
public interface PropertyInfoService extends IService<PropertyInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<PropertyInfoVO> selectListVO(Wrapper<PropertyInfoEntity> wrapper);
   	
   	PropertyInfoVO selectVO(@Param("ew") Wrapper<PropertyInfoEntity> wrapper);
   	
   	List<PropertyInfoView> selectListView(Wrapper<PropertyInfoEntity> wrapper);
   	
   	PropertyInfoView selectView(@Param("ew") Wrapper<PropertyInfoEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<PropertyInfoEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<PropertyInfoEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<PropertyInfoEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<PropertyInfoEntity> wrapper);



}

