package com.mobile.server.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Getter
@Setter
public class Genre {
    private Integer id;
    private String name;
}
