package com.mobile.server.validation;

import com.mobile.server.exception.types.ApiExceptions;
import com.mobile.server.model.User;
import org.apache.commons.lang3.ObjectUtils;

import java.util.regex.Pattern;

public class Check {

    private static String regexPattern = "^(.+)@(\\S+)$";
    public static boolean containNull(Object clazz) {
        return ObjectUtils.allNull(clazz.getClass().getDeclaredFields());
    }

    public static void validateUser(User user) {
        if (user.getUsername().length() < 5) {
            throw new ApiExceptions.ParameterException("user's nickname cannot be shorter than 5 characters");
        }
        if (user.getPassword().length() < 8) {
            throw new ApiExceptions.ParameterException("user's password cannot be shorter than 8 characters");
        }
        if (!Pattern.compile(regexPattern).matcher(user.getEmailAddress()).matches()) {
            throw new ApiExceptions.ParameterException("The email does not match the email pattern: username@domain.com");
        }
    }
}
