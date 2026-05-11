package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.NewsTypeEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.NewsTypeVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.NewsTypeView;


/**
 * 售房资讯分类
 *
 * @author 
 * @email 
 * 
 */
public interface NewsTypeService extends IService<NewsTypeEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<NewsTypeVO> selectListVO(Wrapper<NewsTypeEntity> wrapper);
   	
   	NewsTypeVO selectVO(@Param("ew") Wrapper<NewsTypeEntity> wrapper);
   	
   	List<NewsTypeView> selectListView(Wrapper<NewsTypeEntity> wrapper);
   	
   	NewsTypeView selectView(@Param("ew") Wrapper<NewsTypeEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<NewsTypeEntity> wrapper);

   	

}

