package com.entity.view;

import com.entity.ContractSigningEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

@TableName("contract_signing")
public class ContractSigningView extends ContractSigningEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public ContractSigningView() { }
    public ContractSigningView(ContractSigningEntity t) {
        try { BeanUtils.copyProperties(this, t); }
        catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
    }
}
