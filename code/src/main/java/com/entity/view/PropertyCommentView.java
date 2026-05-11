package com.entity.view;

import com.entity.PropertyCommentEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

@TableName("property_comment")
public class PropertyCommentView extends PropertyCommentEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public PropertyCommentView() { }
    public PropertyCommentView(PropertyCommentEntity t) {
        try { BeanUtils.copyProperties(this, t); }
        catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
    }
}
