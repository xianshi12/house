package com.dao;

import com.entity.IntentDepositPaymentEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.IntentDepositPaymentVO;
import com.entity.view.IntentDepositPaymentView;


/**
 * 意向金支付
 * 
 * @author 
 * @email 
 * 
 */
public interface IntentDepositPaymentDao extends BaseMapper<IntentDepositPaymentEntity> {
	
	List<IntentDepositPaymentVO> selectListVO(@Param("ew") Wrapper<IntentDepositPaymentEntity> wrapper);
	
	IntentDepositPaymentVO selectVO(@Param("ew") Wrapper<IntentDepositPaymentEntity> wrapper);
	
	List<IntentDepositPaymentView> selectListView(@Param("ew") Wrapper<IntentDepositPaymentEntity> wrapper);

	List<IntentDepositPaymentView> selectListView(Pagination page,@Param("ew") Wrapper<IntentDepositPaymentEntity> wrapper);

	
	IntentDepositPaymentView selectView(@Param("ew") Wrapper<IntentDepositPaymentEntity> wrapper);
	

}
