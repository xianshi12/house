package com.dao;

import com.entity.PropertyCommentEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.PropertyCommentVO;
import com.entity.view.PropertyCommentView;


/**
 * 房源信息评论表
 * 
 * @author 
 * @email 
 * 
 */
public interface PropertyCommentDao extends BaseMapper<PropertyCommentEntity> {
	
	List<PropertyCommentVO> selectListVO(@Param("ew") Wrapper<PropertyCommentEntity> wrapper);
	
	PropertyCommentVO selectVO(@Param("ew") Wrapper<PropertyCommentEntity> wrapper);
	
	List<PropertyCommentView> selectListView(@Param("ew") Wrapper<PropertyCommentEntity> wrapper);

	List<PropertyCommentView> selectListView(Pagination page,@Param("ew") Wrapper<PropertyCommentEntity> wrapper);

	
	PropertyCommentView selectView(@Param("ew") Wrapper<PropertyCommentEntity> wrapper);
	

}
