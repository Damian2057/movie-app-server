package com.mobile.server.model;

import com.mobile.server.exception.ApiExceptions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private String username;
    @Indexed(unique = true)
    private String emailAddress;
    private String password;
    private Collection<Role> roles = new ArrayList<>();
    private Collection<Genre> genres = new ArrayList<>();
    private Collection<Movie> memorizedMovies = new ArrayList<>();

    private User(User user) {
        this.id = user.id;
        this.username = user.username;
        this.emailAddress = user.emailAddress;
        this.password = user.password;
        this.roles = user.roles;
        this.genres = user.genres;
        this.memorizedMovies = user.memorizedMovies;
    }

    public static final class UserBuilder {
        private final User user = new User();

        public UserBuilder newInstance() {
            return new UserBuilder();
        }

        public UserBuilder setId(String id) {
            user.id = id;
            return this;
        }

        public UserBuilder setNickName(String nickName) {
            user.username = nickName;
            return this;
        }

        public UserBuilder setEmailAddress(String emailAddress) {
            user.emailAddress = emailAddress;
            return this;
        }

        public UserBuilder setPassword(String password) {
            user.password = password;
            return this;
        }

        public UserBuilder setRoles(Collection<Role> roles) {
            user.roles = roles;
            return this;
        }

        public UserBuilder setGenres(Collection<Genre> genres) {
            user.genres = genres;
            return this;
        }

        public UserBuilder setMemorizedMovies(Collection<Movie> movies) {
            user.memorizedMovies = movies;
            return this;
        }

        public User build() {
            if(user.username.isBlank() || user.password.isBlank() || user.emailAddress.isBlank()) {
                throw new ApiExceptions.ParameterException("Required options are not completed");
            }
            return new User(user);
        }
    }

}
