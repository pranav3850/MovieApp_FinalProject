package org.example.movieapp_finalproject.service;

import org.example.movieapp_finalproject.dto.MovieDTO;
import org.example.movieapp_finalproject.dto.MovieResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class TmdbService {
    private final RestTemplate restTemplate;

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.api.base-url}")
    private String baseUrl;

    public TmdbService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<MovieDTO> getTrendingMovies() {
        String url = baseUrl + "/trending/movie/week?api_key=" + apiKey;
        MovieResponse response = restTemplate.getForObject(url, MovieResponse.class);
        return response != null ? response.getResults() : List.of();
    }

    public MovieDTO getMovieDetails(Long movieId) {
        String url = baseUrl + "/movie/" + movieId + "?api_key=" + apiKey;
        return restTemplate.getForObject(url, MovieDTO.class);
    }

    public List<MovieDTO> searchMovies(String query) {
        String url = baseUrl + "/search/movie?api_key=" + apiKey + "&query=" + query;
        MovieResponse response = restTemplate.getForObject(url, MovieResponse.class);
        return response != null ? response.getResults() : List.of();
    }
}