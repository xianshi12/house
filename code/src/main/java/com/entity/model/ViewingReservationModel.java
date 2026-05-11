package com.entity.model;

import com.entity.ViewingReservationEntity;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

public class ViewingReservationModel implements Serializable {
    private static final long serialVersionUID = 1L;

    public ViewingReservationModel() { }
    public ViewingReservationModel(ViewingReservationEntity t) {
        try { BeanUtils.copyProperties(this, t); }
        catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
    }
}
