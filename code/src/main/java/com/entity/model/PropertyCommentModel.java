package com.entity.model;

import com.entity.PropertyCommentEntity;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

public class PropertyCommentModel implements Serializable {
    private static final long serialVersionUID = 1L;

    public PropertyCommentModel() { }
    public PropertyCommentModel(PropertyCommentEntity t) {
        try { BeanUtils.copyProperties(this, t); }
        catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
    }
}
