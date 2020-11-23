package com.grap;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
import com.grap.dao.IRecipeDAO;
import com.grap.dao.ISearchDAO;
import com.grap.dto.ProfileDTO;
import com.grap.dto.RecipeDTO;
import com.grap.service.IRecipeService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public ModelAndView home() {
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
    public ModelAndView test() {
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

    @GetMapping(value = "/login")
    public String loginRequest(Model model){
        model.addAttribute("emailLogin");
        return "login";
    }

    @GetMapping(value = "/signup")
    public ModelAndView signUpRequest(){
        return new ModelAndView();
    }

    @GetMapping(value = "/index")
    public ModelAndView index(){
        return new ModelAndView();
    }


    @MessageMapping("/profile-update")
    @SendTo("/topic/messages")
    public ProfileDTO updateProfile(String email, String firstName, String lastName) throws Exception {

        Firestore db = FirestoreClient.getFirestore();

        JSONObject obj = new JSONObject(email);
        System.out.println("controller dto: " + obj.getString("email"));

        ProfileDTO docData = new ProfileDTO();
        docData.setEmail(obj.getString("email"));
        docData.setFirstName(obj.getString("firstName"));
        docData.setLastName(obj.getString("lastName"));

        UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(obj.getString("email"));
        String uid = userRecord.getUid();

        ApiFuture<WriteResult> future = db.collection("Users").document(uid).set(docData);
        System.out.println("Update time : " + future.get().getUpdateTime());

        return new ProfileDTO();
    }
}