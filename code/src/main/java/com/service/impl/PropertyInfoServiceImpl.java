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


import com.dao.PropertyInfoDao;
import com.entity.PropertyInfoEntity;
import com.service.PropertyInfoService;
import com.entity.vo.PropertyInfoVO;
import com.entity.view.PropertyInfoView;

@Service("propertyInfoService")
public class PropertyInfoServiceImpl extends ServiceImpl<PropertyInfoDao, PropertyInfoEntity> implements PropertyInfoService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<PropertyInfoEntity> page = this.selectPage(
                new Query<PropertyInfoEntity>(params).getPage(),
                new EntityWrapper<PropertyInfoEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<PropertyInfoEntity> wrapper) {
		  Page<PropertyInfoView> page =new Query<PropertyInfoView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<PropertyInfoVO> selectListVO(Wrapper<PropertyInfoEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public PropertyInfoVO selectVO(Wrapper<PropertyInfoEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<PropertyInfoView> selectListView(Wrapper<PropertyInfoEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public PropertyInfoView selectView(Wrapper<PropertyInfoEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<PropertyInfoEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<PropertyInfoEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<PropertyInfoEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
