package com.entity.view;

import com.entity.HouseTypeEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

@TableName("house_type")
public class HouseTypeView extends HouseTypeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public HouseTypeView() { }
    public HouseTypeView(HouseTypeEntity t) {
        try { BeanUtils.copyProperties(this, t); }
        catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
    }
}
