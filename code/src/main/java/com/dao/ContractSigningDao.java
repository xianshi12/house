package com.dao;

import com.entity.ContractSigningEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ContractSigningVO;
import com.entity.view.ContractSigningView;


/**
 * 合同签订
 * 
 * @author 
 * @email 
 * 
 */
public interface ContractSigningDao extends BaseMapper<ContractSigningEntity> {
	
	List<ContractSigningVO> selectListVO(@Param("ew") Wrapper<ContractSigningEntity> wrapper);
	
	ContractSigningVO selectVO(@Param("ew") Wrapper<ContractSigningEntity> wrapper);
	
	List<ContractSigningView> selectListView(@Param("ew") Wrapper<ContractSigningEntity> wrapper);

	List<ContractSigningView> selectListView(Pagination page,@Param("ew") Wrapper<ContractSigningEntity> wrapper);

	
	ContractSigningView selectView(@Param("ew") Wrapper<ContractSigningEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<ContractSigningEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<ContractSigningEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<ContractSigningEntity> wrapper);



}
