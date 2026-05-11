package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.PropertyCommentEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.PropertyCommentVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.PropertyCommentView;


/**
 * 房源信息评论表
 *
 * @author 
 * @email 
 * 
 */
public interface PropertyCommentService extends IService<PropertyCommentEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<PropertyCommentVO> selectListVO(Wrapper<PropertyCommentEntity> wrapper);
   	
   	PropertyCommentVO selectVO(@Param("ew") Wrapper<PropertyCommentEntity> wrapper);
   	
   	List<PropertyCommentView> selectListView(Wrapper<PropertyCommentEntity> wrapper);
   	
   	PropertyCommentView selectView(@Param("ew") Wrapper<PropertyCommentEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<PropertyCommentEntity> wrapper);

   	

}

