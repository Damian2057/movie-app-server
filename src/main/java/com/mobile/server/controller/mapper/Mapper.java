package com.mobile.server.controller.mapper;

import com.mobile.server.controller.dto.UserDto;
import com.mobile.server.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    public static List<UserDto> mapUsers(List<User> users) {
        return users.stream().map(user ->
                new UserDto(user.getId(),
                        user.getUsername(),
                        user.getFavoriteGenres(),
                        user.getFavoriteMovies(),
                        user.getReminderMovies()))
                .collect(Collectors.toList());
    }

    public static UserDto mapUser(User user) {
        return new UserDto(user.getId(),
                user.getUsername(),
                user.getFavoriteGenres(),
                user.getFavoriteMovies(),
                user.getReminderMovies());
    }

}
