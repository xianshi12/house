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


import com.dao.DeveloperDao;
import com.entity.DeveloperEntity;
import com.service.DeveloperService;
import com.entity.vo.DeveloperVO;
import com.entity.view.DeveloperView;

@Service("developerService")
public class DeveloperServiceImpl extends ServiceImpl<DeveloperDao, DeveloperEntity> implements DeveloperService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DeveloperEntity> page = this.selectPage(
                new Query<DeveloperEntity>(params).getPage(),
                new EntityWrapper<DeveloperEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DeveloperEntity> wrapper) {
		  Page<DeveloperView> page =new Query<DeveloperView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DeveloperVO> selectListVO(Wrapper<DeveloperEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DeveloperVO selectVO(Wrapper<DeveloperEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DeveloperView> selectListView(Wrapper<DeveloperEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DeveloperView selectView(Wrapper<DeveloperEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
