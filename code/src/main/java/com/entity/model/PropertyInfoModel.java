package com.entity.model;

import com.entity.PropertyInfoEntity;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

public class PropertyInfoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    public PropertyInfoModel() { }
    public PropertyInfoModel(PropertyInfoEntity t) {
        try { BeanUtils.copyProperties(this, t); }
        catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
    }
}
