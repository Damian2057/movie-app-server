package com.mobile.server;

import com.mobile.server.exception.types.ApiExceptions;
import com.mobile.server.model.Role;
import com.mobile.server.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			if(userService.getRoles().isEmpty()) {
				userService.saveRole(new Role("ROLE_USER"));
				userService.saveRole(new Role("ROLE_ADMIN"));
			}
		};
	}
}
