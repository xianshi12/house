package com.entity.vo;

import com.entity.PropertyCommentEntity;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

public class PropertyCommentVO implements Serializable {
    private static final long serialVersionUID = 1L;

    public PropertyCommentVO() { }
    public PropertyCommentVO(PropertyCommentEntity t) {
        try { BeanUtils.copyProperties(this, t); }
        catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
    }
}
