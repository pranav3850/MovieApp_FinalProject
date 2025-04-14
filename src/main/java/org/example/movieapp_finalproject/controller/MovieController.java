package org.example.movieapp_finalproject.controller;

import org.example.movieapp_finalproject.dto.MovieDTO;
import org.example.movieapp_finalproject.service.MovieService;
import org.example.movieapp_finalproject.service.TmdbService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MovieController {
    private final TmdbService tmdbService;
    private final MovieService movieService;

    public MovieController(TmdbService tmdbService, MovieService movieService) {
        this.tmdbService = tmdbService;
        this.movieService = movieService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("movies", tmdbService.getTrendingMovies());
        return "index";
    }

    @GetMapping("/movie/{id}")
    public String movieDetails(@PathVariable Long id, Model model) {
        MovieDTO movie = tmdbService.getMovieDetails(id);
        model.addAttribute("movie", movie);
        model.addAttribute("isFavorite", movieService.isFavorite(id));
        return "movie-details";
    }

    @GetMapping("/search")
    public String search(@RequestParam String query, Model model) {
        model.addAttribute("movies", tmdbService.searchMovies(query));
        model.addAttribute("query", query);
        return "index";
    }
}