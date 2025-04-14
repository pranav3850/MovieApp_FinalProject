package org.example.movieapp_finalproject.controller;

import org.example.movieapp_finalproject.dto.MovieDTO;
import org.example.movieapp_finalproject.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/favorites")
public class FavoriteController {
    private final MovieService movieService;

    public FavoriteController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public String favorites(Model model) {
        model.addAttribute("favorites", movieService.getAllFavorites());
        return "favorites";
    }

    @PostMapping("/add")
    public String addFavorite(@ModelAttribute MovieDTO movieDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Invalid movie data");
            return "redirect:/";
        }

        try {
            movieService.addFavorite(movieDTO);
            redirectAttributes.addFlashAttribute("success", "Added to favorites!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error: " + e.getMessage());
        }

        return "redirect:/movie/" + movieDTO.getId();
    }

    @PostMapping("/remove/{id}")
    public String removeFavorite(@PathVariable Long id,
                                 RedirectAttributes redirectAttributes) {
        try {
            movieService.removeFavorite(id);
            redirectAttributes.addFlashAttribute("success", "Removed from favorites!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error removing favorite: " + e.getMessage());
        }
        return "redirect:/favorites"; // Or wherever you want to redirect
    }
}