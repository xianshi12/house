package com.dao;

import com.entity.PropertyInfoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.PropertyInfoVO;
import com.entity.view.PropertyInfoView;


/**
 * 房源信息
 * 
 * @author 
 * @email 
 * 
 */
public interface PropertyInfoDao extends BaseMapper<PropertyInfoEntity> {
	
	List<PropertyInfoVO> selectListVO(@Param("ew") Wrapper<PropertyInfoEntity> wrapper);
	
	PropertyInfoVO selectVO(@Param("ew") Wrapper<PropertyInfoEntity> wrapper);
	
	List<PropertyInfoView> selectListView(@Param("ew") Wrapper<PropertyInfoEntity> wrapper);

	List<PropertyInfoView> selectListView(Pagination page,@Param("ew") Wrapper<PropertyInfoEntity> wrapper);

	
	PropertyInfoView selectView(@Param("ew") Wrapper<PropertyInfoEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<PropertyInfoEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<PropertyInfoEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<PropertyInfoEntity> wrapper);



}
