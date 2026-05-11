package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.IntentDepositPaymentEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.IntentDepositPaymentVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.IntentDepositPaymentView;


/**
 * 意向金支付
 *
 * @author 
 * @email 
 * 
 */
public interface IntentDepositPaymentService extends IService<IntentDepositPaymentEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<IntentDepositPaymentVO> selectListVO(Wrapper<IntentDepositPaymentEntity> wrapper);
   	
   	IntentDepositPaymentVO selectVO(@Param("ew") Wrapper<IntentDepositPaymentEntity> wrapper);
   	
   	List<IntentDepositPaymentView> selectListView(Wrapper<IntentDepositPaymentEntity> wrapper);
   	
   	IntentDepositPaymentView selectView(@Param("ew") Wrapper<IntentDepositPaymentEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<IntentDepositPaymentEntity> wrapper);

   	

}

