package com.mobile.server.service;

import com.mobile.server.model.Role;
import com.mobile.server.model.User;

import java.util.Collection;
import java.util.List;

/**
 * The Service interface responsible for the operation related to the users
 */
public interface UserService {

    /**
     * User registration and add it to the database
     * @param user currently registered user
     * @return saved user from the database
     */
    User registerUser(User user);

    /**
     * saving a new role in the system
     * @param role currently saved user
     * @return saved role from the database
     */
    Role saveRole(Role role);

    /**
     * @param username user's nickname
     * @param roleName added to the user
     */
    void addRoleToUser(String username, String roleName);

    /**
     * @param username user's nickname
     * @return user found by nick
     */
    User getUser(String username);

    /**
     * @return list of all users
     */
    List<User>getUsers();

    /**
     * @return list of all users
     */
    Collection<Role> getRoles();
}
