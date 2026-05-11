package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.IntentDepositPaymentDao;
import com.entity.IntentDepositPaymentEntity;
import com.service.IntentDepositPaymentService;
import com.entity.vo.IntentDepositPaymentVO;
import com.entity.view.IntentDepositPaymentView;

@Service("intentDepositPaymentService")
public class IntentDepositPaymentServiceImpl extends ServiceImpl<IntentDepositPaymentDao, IntentDepositPaymentEntity> implements IntentDepositPaymentService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<IntentDepositPaymentEntity> page = this.selectPage(
                new Query<IntentDepositPaymentEntity>(params).getPage(),
                new EntityWrapper<IntentDepositPaymentEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<IntentDepositPaymentEntity> wrapper) {
		  Page<IntentDepositPaymentView> page =new Query<IntentDepositPaymentView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<IntentDepositPaymentVO> selectListVO(Wrapper<IntentDepositPaymentEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public IntentDepositPaymentVO selectVO(Wrapper<IntentDepositPaymentEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<IntentDepositPaymentView> selectListView(Wrapper<IntentDepositPaymentEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public IntentDepositPaymentView selectView(Wrapper<IntentDepositPaymentEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
