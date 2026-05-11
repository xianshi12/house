package com.entity.model;

import com.entity.IntentDepositPaymentEntity;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

public class IntentDepositPaymentModel implements Serializable {
    private static final long serialVersionUID = 1L;

    public IntentDepositPaymentModel() { }
    public IntentDepositPaymentModel(IntentDepositPaymentEntity t) {
        try { BeanUtils.copyProperties(this, t); }
        catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
    }
}
