package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ContractSigningEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ContractSigningVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ContractSigningView;


/**
 * 合同签订
 *
 * @author 
 * @email 
 * 
 */
public interface ContractSigningService extends IService<ContractSigningEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ContractSigningVO> selectListVO(Wrapper<ContractSigningEntity> wrapper);
   	
   	ContractSigningVO selectVO(@Param("ew") Wrapper<ContractSigningEntity> wrapper);
   	
   	List<ContractSigningView> selectListView(Wrapper<ContractSigningEntity> wrapper);
   	
   	ContractSigningView selectView(@Param("ew") Wrapper<ContractSigningEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ContractSigningEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<ContractSigningEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<ContractSigningEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<ContractSigningEntity> wrapper);



}

