package com.entity.vo;

import com.entity.NewsTypeEntity;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

public class NewsTypeVO implements Serializable {
    private static final long serialVersionUID = 1L;

    public NewsTypeVO() { }
    public NewsTypeVO(NewsTypeEntity t) {
        try { BeanUtils.copyProperties(this, t); }
        catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
    }
}
