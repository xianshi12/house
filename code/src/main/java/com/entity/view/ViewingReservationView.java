package com.entity.view;

import com.entity.ViewingReservationEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

@TableName("viewing_reservation")
public class ViewingReservationView extends ViewingReservationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public ViewingReservationView() { }
    public ViewingReservationView(ViewingReservationEntity t) {
        try { BeanUtils.copyProperties(this, t); }
        catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
    }
}
