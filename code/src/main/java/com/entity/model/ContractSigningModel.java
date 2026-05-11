package com.entity.model;

import com.entity.ContractSigningEntity;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

public class ContractSigningModel implements Serializable {
    private static final long serialVersionUID = 1L;

    public ContractSigningModel() { }
    public ContractSigningModel(ContractSigningEntity t) {
        try { BeanUtils.copyProperties(this, t); }
        catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
    }
}
