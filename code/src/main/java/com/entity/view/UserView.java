package com.entity.view;

import com.entity.UserEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

@TableName("user")
public class UserView extends UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public UserView() { }
    public UserView(UserEntity t) {
        try { BeanUtils.copyProperties(this, t); }
        catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
    }
}
