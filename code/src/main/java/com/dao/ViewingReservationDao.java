package com.dao;

import com.entity.ViewingReservationEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ViewingReservationVO;
import com.entity.view.ViewingReservationView;


/**
 * 看房预约
 * 
 * @author 
 * @email 
 * 
 */
public interface ViewingReservationDao extends BaseMapper<ViewingReservationEntity> {
	
	List<ViewingReservationVO> selectListVO(@Param("ew") Wrapper<ViewingReservationEntity> wrapper);
	
	ViewingReservationVO selectVO(@Param("ew") Wrapper<ViewingReservationEntity> wrapper);
	
	List<ViewingReservationView> selectListView(@Param("ew") Wrapper<ViewingReservationEntity> wrapper);

	List<ViewingReservationView> selectListView(Pagination page,@Param("ew") Wrapper<ViewingReservationEntity> wrapper);

	
	ViewingReservationView selectView(@Param("ew") Wrapper<ViewingReservationEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<ViewingReservationEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<ViewingReservationEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<ViewingReservationEntity> wrapper);



}
