package com.dao;

import com.entity.NewsTypeEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.NewsTypeVO;
import com.entity.view.NewsTypeView;


/**
 * 售房资讯分类
 * 
 * @author 
 * @email 
 * 
 */
public interface NewsTypeDao extends BaseMapper<NewsTypeEntity> {
	
	List<NewsTypeVO> selectListVO(@Param("ew") Wrapper<NewsTypeEntity> wrapper);
	
	NewsTypeVO selectVO(@Param("ew") Wrapper<NewsTypeEntity> wrapper);
	
	List<NewsTypeView> selectListView(@Param("ew") Wrapper<NewsTypeEntity> wrapper);

	List<NewsTypeView> selectListView(Pagination page,@Param("ew") Wrapper<NewsTypeEntity> wrapper);

	
	NewsTypeView selectView(@Param("ew") Wrapper<NewsTypeEntity> wrapper);
	

}
