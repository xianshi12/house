package com.dao;

import com.entity.DeveloperEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DeveloperVO;
import com.entity.view.DeveloperView;


/**
 * 开发商
 * 
 * @author 
 * @email 
 * 
 */
public interface DeveloperDao extends BaseMapper<DeveloperEntity> {
	
	List<DeveloperVO> selectListVO(@Param("ew") Wrapper<DeveloperEntity> wrapper);
	
	DeveloperVO selectVO(@Param("ew") Wrapper<DeveloperEntity> wrapper);
	
	List<DeveloperView> selectListView(@Param("ew") Wrapper<DeveloperEntity> wrapper);

	List<DeveloperView> selectListView(Pagination page,@Param("ew") Wrapper<DeveloperEntity> wrapper);

	
	DeveloperView selectView(@Param("ew") Wrapper<DeveloperEntity> wrapper);
	

}
