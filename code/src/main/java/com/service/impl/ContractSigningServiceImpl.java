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


import com.dao.ContractSigningDao;
import com.entity.ContractSigningEntity;
import com.service.ContractSigningService;
import com.entity.vo.ContractSigningVO;
import com.entity.view.ContractSigningView;

@Service("contractSigningService")
public class ContractSigningServiceImpl extends ServiceImpl<ContractSigningDao, ContractSigningEntity> implements ContractSigningService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ContractSigningEntity> page = this.selectPage(
                new Query<ContractSigningEntity>(params).getPage(),
                new EntityWrapper<ContractSigningEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ContractSigningEntity> wrapper) {
		  Page<ContractSigningView> page =new Query<ContractSigningView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<ContractSigningVO> selectListVO(Wrapper<ContractSigningEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ContractSigningVO selectVO(Wrapper<ContractSigningEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ContractSigningView> selectListView(Wrapper<ContractSigningEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ContractSigningView selectView(Wrapper<ContractSigningEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<ContractSigningEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<ContractSigningEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<ContractSigningEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
