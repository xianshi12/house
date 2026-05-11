package com.entity.model;

import com.entity.DeveloperEntity;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

public class DeveloperModel implements Serializable {
    private static final long serialVersionUID = 1L;

    public DeveloperModel() { }
    public DeveloperModel(DeveloperEntity t) {
        try { BeanUtils.copyProperties(this, t); }
        catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
    }
}
