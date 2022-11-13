package com.mobile.server.controller.pojo;

import lombok.Data;

@Data
public class UserLoginRequestModel {
    private String username;
    private String password;
}
