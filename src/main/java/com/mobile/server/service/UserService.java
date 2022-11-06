package com.mobile.server.service;

import com.mobile.server.model.Role;
import com.mobile.server.model.User;

import java.util.Collection;
import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User>getUsers();

    Collection<Role> getRoles();
}
