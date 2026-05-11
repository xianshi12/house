package com.entity.view;

import com.entity.NewsTypeEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

@TableName("news_type")
public class NewsTypeView extends NewsTypeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public NewsTypeView() { }
    public NewsTypeView(NewsTypeEntity t) {
        try { BeanUtils.copyProperties(this, t); }
        catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
    }
}
