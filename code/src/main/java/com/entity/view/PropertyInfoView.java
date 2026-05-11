package com.entity.view;

import com.entity.PropertyInfoEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

@TableName("property_info")
public class PropertyInfoView extends PropertyInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public PropertyInfoView() { }
    public PropertyInfoView(PropertyInfoEntity t) {
        try { BeanUtils.copyProperties(this, t); }
        catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
    }
}
