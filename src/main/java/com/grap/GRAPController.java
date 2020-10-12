package com.grap;

import com.grap.dao.IRecipeDAO;
import com.grap.dao.ISearchDAO;
import com.grap.dto.RecipeDTO;
import com.grap.service.IRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GRAPController{

    @Autowired
    private IRecipeService recipeService;
    @Autowired
    private IRecipeDAO recipeDAO;
    @Autowired
    private ISearchDAO searchDAO;

/**
 @Autowired
 private IProfileService profileService;
 @Autowired
 private ISearchDAO searchDAO;
 **/

    @GetMapping(value = "/home")
    public ModelAndView home() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Iterable<RecipeDTO> recipes = recipeService.fetchRecipes();
            modelAndView.setViewName("home");
            modelAndView.addObject("recipes", recipes);
        }
        catch (Exception e){
        // this should throw an error, not print stack trace
        e.printStackTrace();
        modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    @PostMapping("/home")
    public String create() {
        return "home";
    }

    // search for recipes
    @RequestMapping("/searchRecipes")
    public ModelAndView searchRecipes(@RequestParam(value="searchTerm", required=false, defaultValue="") String searchTerm) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Iterable<RecipeDTO> searchResults =  searchDAO.fetch(searchTerm);
            modelAndView.setViewName("searchRecipes");
            modelAndView.addObject("searchResults", searchResults);
            // set off and error if movies = 0
        } catch (Exception  e) {
            e.printStackTrace();
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    @RequestMapping("/searchAutocomplete")
    @ResponseBody
    public List<String> searchAutocomplete(@RequestParam(value="term", required=false, defaultValue="") String term) {
        List<String> suggestions = new ArrayList<String>();
        try {
            Iterable<RecipeDTO> searchResults =  searchDAO.fetch(term);
            for(RecipeDTO recipe : searchResults) {
                suggestions.add(recipe.getLabel());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return suggestions;
    }
}