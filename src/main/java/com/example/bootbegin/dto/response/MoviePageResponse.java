package com.example.bootbegin.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MoviePageResponse {
    private List<MovieResponse> movies;
    private int totalElements;
    private int totalPages;
    private int pageNumber;
    private int pageSize;

}
