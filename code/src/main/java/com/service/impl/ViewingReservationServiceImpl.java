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


import com.dao.ViewingReservationDao;
import com.entity.ViewingReservationEntity;
import com.service.ViewingReservationService;
import com.entity.vo.ViewingReservationVO;
import com.entity.view.ViewingReservationView;

@Service("viewingReservationService")
public class ViewingReservationServiceImpl extends ServiceImpl<ViewingReservationDao, ViewingReservationEntity> implements ViewingReservationService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ViewingReservationEntity> page = this.selectPage(
                new Query<ViewingReservationEntity>(params).getPage(),
                new EntityWrapper<ViewingReservationEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ViewingReservationEntity> wrapper) {
		  Page<ViewingReservationView> page =new Query<ViewingReservationView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<ViewingReservationVO> selectListVO(Wrapper<ViewingReservationEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ViewingReservationVO selectVO(Wrapper<ViewingReservationEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ViewingReservationView> selectListView(Wrapper<ViewingReservationEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ViewingReservationView selectView(Wrapper<ViewingReservationEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<ViewingReservationEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<ViewingReservationEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<ViewingReservationEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
