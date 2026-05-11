package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DeveloperEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DeveloperVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DeveloperView;


/**
 * 开发商
 *
 * @author 
 * @email 
 * 
 */
public interface DeveloperService extends IService<DeveloperEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DeveloperVO> selectListVO(Wrapper<DeveloperEntity> wrapper);
   	
   	DeveloperVO selectVO(@Param("ew") Wrapper<DeveloperEntity> wrapper);
   	
   	List<DeveloperView> selectListView(Wrapper<DeveloperEntity> wrapper);
   	
   	DeveloperView selectView(@Param("ew") Wrapper<DeveloperEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DeveloperEntity> wrapper);

   	

}

