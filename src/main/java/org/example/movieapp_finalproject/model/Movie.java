package org.example.movieapp_finalproject.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Movie {
    @Id
    private Long id;
    private String title;
    private String overview;
    private String posterPath;
    private Double voteAverage;
    @Column(nullable = true) // Make releaseDate nullable
    private LocalDate releaseDate;
}
