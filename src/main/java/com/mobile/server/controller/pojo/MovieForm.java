package com.mobile.server.controller.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class MovieForm {
    private String page;
    private List<MovieFormDto> results;
    private String total_pages;
    private String total_results;
}
