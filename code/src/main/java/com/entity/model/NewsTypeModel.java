package com.entity.model;

import com.entity.NewsTypeEntity;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

public class NewsTypeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    public NewsTypeModel() { }
    public NewsTypeModel(NewsTypeEntity t) {
        try { BeanUtils.copyProperties(this, t); }
        catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
    }
}
