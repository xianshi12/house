package com.entity.vo;

import com.entity.PropertyInfoEntity;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

public class PropertyInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    public PropertyInfoVO() { }
    public PropertyInfoVO(PropertyInfoEntity t) {
        try { BeanUtils.copyProperties(this, t); }
        catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
    }
}
