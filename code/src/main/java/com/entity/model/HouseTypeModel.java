package com.entity.model;

import com.entity.HouseTypeEntity;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

public class HouseTypeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    public HouseTypeModel() { }
    public HouseTypeModel(HouseTypeEntity t) {
        try { BeanUtils.copyProperties(this, t); }
        catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
    }
}
