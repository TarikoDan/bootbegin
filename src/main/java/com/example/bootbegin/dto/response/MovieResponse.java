package com.example.bootbegin.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieResponse {
    private int id;
    private String title;
    private int duration;
    private String directorName;
}
