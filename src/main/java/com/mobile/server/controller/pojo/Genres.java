package com.mobile.server.controller.pojo;

import com.mobile.server.model.Genre;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class Genres {
    private List<Genre> genres;
}
