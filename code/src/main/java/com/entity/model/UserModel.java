package com.entity.model;

import com.entity.UserEntity;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    public UserModel() { }
    public UserModel(UserEntity t) {
        try { BeanUtils.copyProperties(this, t); }
        catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
    }
}
