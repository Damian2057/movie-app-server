package com.mobile.server.model;

import com.mobile.server.exception.types.ApiExceptions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    private String emailAddress;
    private String password;
    private Collection<Role> roles = new ArrayList<>();
    private Collection<Genre> favoriteGenres = new ArrayList<>();
    private Collection<Movie> favoriteMovies = new ArrayList<>();
    private Collection<Movie> reminderMovies = new ArrayList<>();

    private User(User user) {
        this.id = user.id;
        this.username = user.username;
        this.emailAddress = user.emailAddress;
        this.password = user.password;
        this.roles = user.roles;
        this.favoriteGenres = user.favoriteGenres;
        this.favoriteMovies = user.favoriteMovies;
        this.reminderMovies = user.reminderMovies;
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
            user.favoriteGenres = genres;
            return this;
        }

        public UserBuilder setFavouriteMovies(Collection<Movie> movies) {
            user.favoriteMovies = movies;
            return this;
        }

        public UserBuilder setReminderMovies(Collection<Movie> movies) {
            user.reminderMovies = movies;
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
