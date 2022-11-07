package com.mobile.server.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "movie.api")
@Getter
@Setter
public class MovieApiProperties {
    private String url;
    private String key;
    private String img;
}
