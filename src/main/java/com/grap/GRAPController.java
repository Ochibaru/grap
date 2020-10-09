package com.grap;

import com.grap.dao.IRecipeDAO;
import com.grap.dto.RecipeDTO;
import com.grap.service.IRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GRAPController{

    @Autowired
    private IRecipeService recipeService;
    @Autowired
    private IRecipeDAO recipeDAO;

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
        // Needs code
//            RecipeDTO recipe = new RecipeDTO();
//            recipe.setLabel("Pasta");
//            String lbl = recipe.getLabel();
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
    public String create1() {

        return "home";
    }
}