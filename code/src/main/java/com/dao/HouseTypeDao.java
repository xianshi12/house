package com.dao;

import com.entity.HouseTypeEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.HouseTypeVO;
import com.entity.view.HouseTypeView;


/**
 * 房屋类型
 * 
 * @author 
 * @email 
 * 
 */
public interface HouseTypeDao extends BaseMapper<HouseTypeEntity> {
	
	List<HouseTypeVO> selectListVO(@Param("ew") Wrapper<HouseTypeEntity> wrapper);
	
	HouseTypeVO selectVO(@Param("ew") Wrapper<HouseTypeEntity> wrapper);
	
	List<HouseTypeView> selectListView(@Param("ew") Wrapper<HouseTypeEntity> wrapper);

	List<HouseTypeView> selectListView(Pagination page,@Param("ew") Wrapper<HouseTypeEntity> wrapper);

	
	HouseTypeView selectView(@Param("ew") Wrapper<HouseTypeEntity> wrapper);
	

}
