package org.example.movieapp_finalproject.dto;


import lombok.Data;
import java.util.List;

@Data
public class MovieResponse {
    private List<MovieDTO> results;
}
