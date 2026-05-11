package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ViewingReservationEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ViewingReservationVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ViewingReservationView;


/**
 * 看房预约
 *
 * @author 
 * @email 
 * 
 */
public interface ViewingReservationService extends IService<ViewingReservationEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ViewingReservationVO> selectListVO(Wrapper<ViewingReservationEntity> wrapper);
   	
   	ViewingReservationVO selectVO(@Param("ew") Wrapper<ViewingReservationEntity> wrapper);
   	
   	List<ViewingReservationView> selectListView(Wrapper<ViewingReservationEntity> wrapper);
   	
   	ViewingReservationView selectView(@Param("ew") Wrapper<ViewingReservationEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ViewingReservationEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<ViewingReservationEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<ViewingReservationEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<ViewingReservationEntity> wrapper);



}

