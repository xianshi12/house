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


import com.dao.PropertyCommentDao;
import com.entity.PropertyCommentEntity;
import com.service.PropertyCommentService;
import com.entity.vo.PropertyCommentVO;
import com.entity.view.PropertyCommentView;

@Service("propertyCommentService")
public class PropertyCommentServiceImpl extends ServiceImpl<PropertyCommentDao, PropertyCommentEntity> implements PropertyCommentService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<PropertyCommentEntity> page = this.selectPage(
                new Query<PropertyCommentEntity>(params).getPage(),
                new EntityWrapper<PropertyCommentEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<PropertyCommentEntity> wrapper) {
		  Page<PropertyCommentView> page =new Query<PropertyCommentView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<PropertyCommentVO> selectListVO(Wrapper<PropertyCommentEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public PropertyCommentVO selectVO(Wrapper<PropertyCommentEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<PropertyCommentView> selectListView(Wrapper<PropertyCommentEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public PropertyCommentView selectView(Wrapper<PropertyCommentEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
