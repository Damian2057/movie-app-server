package com.mobile.server.service;

import com.mobile.server.exception.types.ApiExceptions;
import com.mobile.server.model.Genre;
import com.mobile.server.model.Movie;
import com.mobile.server.model.Role;
import com.mobile.server.model.User;
import com.mobile.server.repository.RoleRepository;
import com.mobile.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
    }

    @Override
    public User registerUser(User user) {
        if(userRepository.findByUsername(user.getUsername()) != null) {
            throw new ApiExceptions.LogicException("The username is not unique");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getRoles().isEmpty()) {
            user.setRoles(Set.of(roleRepository.findByName("ROLE_USER")));
        }
        log.info("Saving new user {} to the database", user.getUsername());
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.setRoles(Set.of(role));
        userRepository.save(user);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    public Collection<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<User> addGenreToUser(User user, Genre genre) {
        log.info("Adding genre {} to user {}", genre.getName(), user.getUsername());
        Optional<User> savedUser = userRepository.findById(user.getId());
        savedUser.ifPresent(user1 -> {
            user1.addGenre(genre);
            userRepository.save(user1);
        });
        return savedUser;
    }

    @Override
    public Optional<User> removeGenreFromUser(User user, Genre genre) {
        log.info("Removing genre {} from user {}", genre.getName(), user.getUsername());
        Optional<User> savedUser = userRepository.findById(user.getId());
        savedUser.ifPresent(user1 -> {
            user1.removeGenre(genre);
            userRepository.save(user1);
        });
        return savedUser;
    }

    @Override
    public Optional<User> addMovieToUser(User user, Movie movie) {
        log.info("Adding Movie {} to user {}", movie.getTitle(), user.getUsername());
        Optional<User> savedUser = userRepository.findById(user.getId());
        savedUser.ifPresent(user1 -> {
            user1.addMovie(movie);
            userRepository.save(user1);
        });
        return savedUser;
    }

    @Override
    public Optional<User> addGenreListToUser(User user, List<Genre> genres) {
        log.info("Adding genre {} to user {}", genres, user.getUsername());
        Optional<User> savedUser = userRepository.findById(user.getId());
        savedUser.ifPresent(user1 -> {
            user1.addGenreList(genres);
            userRepository.save(user1);
        });
        return savedUser;
    }

    @Override
    public Optional<User> removeMovieFromUser(User user, Movie movie) {
        log.info("Removing movie {} from user {}", movie.getTitle(), user.getUsername());
        Optional<User> savedUser = userRepository.findById(user.getId());
        savedUser.ifPresent(user1 -> {
            user1.removeMovie(movie);
            userRepository.save(user1);
        });
        return savedUser;
    }

}
