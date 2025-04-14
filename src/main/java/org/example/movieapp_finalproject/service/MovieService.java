package org.example.movieapp_finalproject.service;

import lombok.RequiredArgsConstructor;
import org.example.movieapp_finalproject.dto.MovieDTO;
import org.example.movieapp_finalproject.model.Movie;
import org.example.movieapp_finalproject.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository, RestTemplate restTemplate) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllFavorites() {
        return movieRepository.findAll();
    }

    public void addFavorite(MovieDTO movieDTO) {
        // Validate input
        if (movieDTO == null) {
            throw new IllegalArgumentException("MovieDTO cannot be null");
        }

        Movie movie = new Movie();
        movie.setId(movieDTO.getId());
        movie.setTitle(movieDTO.getTitle());
        movie.setOverview(movieDTO.getOverview());
        movie.setPosterPath(movieDTO.getPosterPath());

        // Handle null releaseDate
        if (movieDTO.getReleaseDate() != null && !movieDTO.getReleaseDate().isEmpty()) {
            movie.setReleaseDate(LocalDate.parse(movieDTO.getReleaseDate()));
        } else {
            movie.setReleaseDate(null); // or set a default date
        }

        movie.setVoteAverage(movieDTO.getVoteAverage());
        movieRepository.save(movie);
    }

    public void removeFavorite(Long movieId) {
        movieRepository.deleteById(movieId);
    }

    public boolean isFavorite(Long movieId) {
        return movieRepository.existsById(movieId);
    }
}