package org.example.movieapp_finalproject.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MovieDTO {
    private Long id;
    private String title;
    private String overview;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("vote_average")
    private Double voteAverage;

    public LocalDate getReleaseDateAsLocalDate() {
        if (releaseDate == null || releaseDate.isEmpty()) {
            return null;
        }
        return LocalDate.parse(releaseDate);
    }
}
