package com.entity.vo;

import com.entity.DeveloperEntity;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

public class DeveloperVO implements Serializable {
    private static final long serialVersionUID = 1L;

    public DeveloperVO() { }
    public DeveloperVO(DeveloperEntity t) {
        try { BeanUtils.copyProperties(this, t); }
        catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
    }
}
