package com.utils;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.PropertyInfoEntity;
import com.service.PropertyInfoService;

public final class PropertyImageSyncUtils {
    private PropertyImageSyncUtils() {
    }

    public static void syncPage(PageUtils page, PropertyInfoService propertyInfoService) {
        if (page == null || page.getList() == null) {
            return;
        }
        for (Object item : page.getList()) {
            sync(item, propertyInfoService);
        }
    }

    public static <T> List<T> syncList(List<T> list, PropertyInfoService propertyInfoService) {
        if (list == null) {
            return list;
        }
        for (T item : list) {
            sync(item, propertyInfoService);
        }
        return list;
    }

    public static <T> T sync(T target, PropertyInfoService propertyInfoService) {
        if (target == null || propertyInfoService == null) {
            return target;
        }
        String propertyName = readString(target, "getPropertyName", "getFangwumingcheng");
        if (StringUtils.isBlank(propertyName)) {
            return target;
        }
        EntityWrapper<PropertyInfoEntity> ew = new EntityWrapper<PropertyInfoEntity>();
        ew.eq("property_name", propertyName);
        String developerCode = readString(target, "getDeveloperCode", "getKaifashanghao");
        if (StringUtils.isNotBlank(developerCode)) {
            ew.eq("developer_code", developerCode);
        }
        ew.last("limit 1");
        PropertyInfoEntity propertyInfo = propertyInfoService.selectOne(ew);
        if (propertyInfo != null && StringUtils.isNotBlank(propertyInfo.getPropertyImage())) {
            writeString(target, propertyInfo.getPropertyImage(), "setPropertyImage", "setFangwutupian");
        }
        return target;
    }

    private static String readString(Object target, String... methodNames) {
        for (String methodName : methodNames) {
            try {
                Method method = target.getClass().getMethod(methodName);
                Object value = method.invoke(target);
                if (value != null && StringUtils.isNotBlank(String.valueOf(value))) {
                    return String.valueOf(value);
                }
            } catch (Exception ignored) {
            }
        }
        return "";
    }

    private static void writeString(Object target, String value, String... methodNames) {
        for (String methodName : methodNames) {
            try {
                Method method = target.getClass().getMethod(methodName, String.class);
                method.invoke(target, value);
            } catch (Exception ignored) {
            }
        }
    }
}
