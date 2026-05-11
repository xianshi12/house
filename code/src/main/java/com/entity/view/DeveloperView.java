package com.entity.view;

import com.entity.DeveloperEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

@TableName("developer")
public class DeveloperView extends DeveloperEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public DeveloperView() { }
    public DeveloperView(DeveloperEntity t) {
        try { BeanUtils.copyProperties(this, t); }
        catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
    }
}
