package com.entity.view;

import com.entity.IntentDepositPaymentEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

@TableName("intent_deposit_payment")
public class IntentDepositPaymentView extends IntentDepositPaymentEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public IntentDepositPaymentView() { }
    public IntentDepositPaymentView(IntentDepositPaymentEntity t) {
        try { BeanUtils.copyProperties(this, t); }
        catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
    }
}
