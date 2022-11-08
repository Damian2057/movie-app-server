package com.mobile.server.validation;

import org.apache.commons.lang3.ObjectUtils;

public class CheckNull {
    public static boolean containNull(Object clazz) {
        return ObjectUtils.allNull(clazz.getClass().getDeclaredFields());
    }
}
