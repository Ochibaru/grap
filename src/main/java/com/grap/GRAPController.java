package com.grap;

import com.grap.dao.IPantryDAO;
import com.grap.dao.IRecipeDAO;
import com.grap.dao.ISearchDAO;
import com.grap.dao.PantryDAO;
import com.grap.dto.PantryDTO;
import com.grap.dto.RecipeDTO;
import com.grap.service.FirebaseService;
import com.grap.service.IRecipeService;
import com.grap.service.PantryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    @Autowired
    private IPantryDAO pantryDAO;
    @Autowired
    private FirebaseService firebaseService;
    @Autowired
    private PantryService pantryService;

    /**
     @Autowired
     private IProfileService profileService;
     @Autowired
     private ISearchDAO searchDAO;
     **/

    @GetMapping("/")
    public String displaySchedule(@RequestParam(value = "countryCode", required = false) String countryCode, Model model, @CookieValue(value = "uid", required = false) String uid) throws Exception {

        try {
            // if (countryCode == null) { countryCode = "US"; }
            List<RecipeDTO> recipes = recipeService.fetchRecipes();
            model.addAttribute("recipes", recipes);
            model.addAttribute("uid", uid);
            return "recipes";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @GetMapping(value = "/home")
    public ModelAndView home() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Iterable<RecipeDTO> recipes = recipeService.fetchRecipes();
            modelAndView.setViewName("home");
            modelAndView.addObject("recipes", recipes);
        }
        catch (Exception e){
        // This should throw an error, not print stack trace
        e.printStackTrace();
        modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    @PostMapping("/home")
    public String create() {
        return "home";
    }

    @GetMapping(value = "/test")
    public ModelAndView test() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Iterable<RecipeDTO> recipes = recipeService.fetchRecipes();
            modelAndView.setViewName("test");
            modelAndView.addObject("recipes", recipes);
        }
        catch (Exception e){
            // This should throw an error, not print stack trace
            e.printStackTrace();
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    // Pantry Attempt
    @GetMapping(value = "/pantry")
    public ModelAndView pantry(@RequestParam(value="pantries", required=false, defaultValue="") String pantries) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Iterable<PantryDTO> pantry = pantryDAO.fetch(pantries);
//            List<PantryDTO> pantry = pantryService.fetch(firebaseService.getUser(uid).getEmail());
            modelAndView.setViewName("pantry");
            modelAndView.addObject("pantry", pantry);
        }
        catch (Exception e){
            // This should throw an error, not print stack trace
            e.printStackTrace();
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    // Reference: https://dzone.com/articles/how-to-use-cookies-in-spring-boot
    @GetMapping("/set-uid")
    public String setCookie(HttpServletResponse response, @RequestParam(value = "uid") String uid) {
        Cookie cookie = new Cookie("uid", uid);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/RJPantry";
    }

    // Reference: https://attacomsian.com/blog/cookies-spring-boot#deleting-cookie
    @GetMapping("/unset-uid")
    public String unsetCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("uid", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return "redirect:/";
    }

    // Pantry Attempt
    @GetMapping(value = "/RJPantry")
    public String fetchPantry(@CookieValue(value="uid", required=false) String uid, Model model) {
        try {
            if (uid == null) {
                System.out.println("No UID cookie found. User is not logged in.");
                return "login";
            }

            System.out.println("User is logged in. Fetching favorites.");
            List<PantryDTO> pantries = pantryService.fetchAll(firebaseService.getUser(uid).getEmail());
            model.addAttribute("pantries", pantries);
            model.addAttribute("uid", uid);
            return "RJPantry";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/home/topics")
    public ModelAndView topics(@RequestParam(value="searchTerm", required=false, defaultValue="") String searchTerm) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Iterable<RecipeDTO> topics = recipeService.fetchRecipes();
            modelAndView.setViewName("topics");
            modelAndView.addObject("topics", topics);
        } catch (Exception  e) {
            e.printStackTrace();
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    @RequestMapping("/home/topics/recipes")
    public ModelAndView recipes(@RequestParam(value="searchTerm", required=false, defaultValue="") String searchTerm) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Iterable<RecipeDTO> spoonRecipes = recipeService.fetchSpoonRecipes();
            modelAndView.setViewName("recipes");
            modelAndView.addObject("spoonRecipes", spoonRecipes);

            Iterable<RecipeDTO> topics = recipeService.fetchRecipes();
            modelAndView.addObject("topics", topics);
        } catch (Exception  e) {
            e.printStackTrace();
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    // Search for recipes
    @RequestMapping("/searchRecipes")
    public ModelAndView searchRecipes(@RequestParam(value="searchTerm", required=false, defaultValue="") String searchTerm) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Iterable<RecipeDTO> searchResults =  searchDAO.fetch(searchTerm);
            modelAndView.setViewName("searchRecipes");
            modelAndView.addObject("searchResults", searchResults);
            // Set off and error if movies = 0
        } catch (Exception  e) {
            e.printStackTrace();
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    @RequestMapping(value={"/searchAutocomplete", "home/*", "home/topics/*"})
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