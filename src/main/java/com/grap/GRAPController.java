package com.grap;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
import com.grap.service.FirebaseService;
import com.grap.dao.IPantryDAO;
import com.grap.dao.IRecipeDAO;
import com.grap.dao.ISearchDAO;
import com.grap.dto.PantryDTO;
import com.grap.dto.ProfileDTO;
import com.grap.dao.IProfileDAO;
import com.grap.dto.RecipeDTO;
import com.grap.service.ProfileService;
import com.grap.service.IRecipeService;
import com.grap.service.PantryService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
    @Autowired
    private ProfileService profileService;
    @Autowired
    private IProfileDAO profileDAO;

    // Home pages for signed in and public users
    @GetMapping("/")
    public String home(@RequestParam(value = "countryCode", required = false) String countryCode, Model model, @CookieValue(value = "uid", required = false) String uid) throws Exception {
        try {
            // if (countryCode == null) { countryCode = "US"; }
            List<RecipeDTO> recipes = recipeService.fetchRecipes();
            model.addAttribute("recipes", recipes);
            model.addAttribute("uid", uid);
            return "home";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

//    @GetMapping(value = "/userProfile")
//    public String userProfileUpdate(@CookieValue(value="uid", required=false) String uid, Model model){
//        model.addAttribute("emailLogin");
//        model.addAttribute("uid", uid);
//        return "userProfile";
//    }

    @GetMapping(value = "/userHome")
    public String userHome(@CookieValue(value="uid", required=false) String uid, Model model) throws Exception {
        try {
            if (uid == null) {
                System.out.println("No UID cookie found. User is not logged in.");
                return "login";
            }
            List<PantryDTO> pantries = pantryService.fetchAll(firebaseService.getUser(uid).getEmail());
            model.addAttribute("pantries", pantries);
            model.addAttribute("uid", uid);
            return "userHome";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
    // < ------------------------------------------------------------------------------------ >

    // Signed in Users Pantry Page and Save and Delete Functions
    @GetMapping(value = "/userHome/pantry")
    public String fetchPantry(@CookieValue(value="uid", required=false) String uid, Model model) {
        try {
            System.out.println("User is logged in. Fetching favorites.");
            List<PantryDTO> pantries = pantryService.fetchAll(firebaseService.getUser(uid).getEmail());
            model.addAttribute("pantries", pantries);
            model.addAttribute("uid", uid);
            return "pantry";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @PostMapping("/userHome/pantry/saveCategory")
    public String savePantry(HttpServletRequest request, @CookieValue(value = "uid", required = false) String uid) {
        if (uid == null) {
            return "login";
        }

        String pantryId = request.getParameter("pantryId");

        PantryDTO pantry = new PantryDTO();
        pantry.setId(request.getParameter("pantryId"));
        pantry.setName(request.getParameter("name"));
        pantry.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        pantry.setMeasurement(Double.valueOf(request.getParameter("measurement")));

        try {
            pantryService.saveCategory(pantry, firebaseService.getUser(uid).getEmail(), pantryId);
        } catch (FirebaseAuthException | ExecutionException | InterruptedException e) {
            return "error";
        }
        return "redirect:/userHome/pantry";
    }

    @PostMapping("/userHome/pantry/deleteCategory")
    public String deletePantryItem(@RequestParam(value = "pantryId") String pantryId, @CookieValue(value = "uid") String uid) {
        try {
            pantryService.deleteCategory(firebaseService.getUser(uid).getEmail(), pantryId);
        } catch (FirebaseAuthException | ExecutionException | InterruptedException e) {
            return "error";
        }
        return "redirect:/userHome/pantry";
    }
    // < ------------------------------------------------------------------------------------ >

    // Sets the Users UID in the login.js file
    // Reference: https://dzone.com/articles/how-to-use-cookies-in-spring-boot
    @GetMapping("/set-uid")
    public String setCookie(HttpServletResponse response, @RequestParam(value = "uid") String uid) {
        Cookie cookie = new Cookie("uid", uid);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/userHome";
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
    // < ------------------------------------------------------------------------------------ >

    // Signed in Users Recipes topic Page and Specific Recipes
    @RequestMapping("/userHome/topics")
    public ModelAndView topics(@RequestParam(value="searchTerm", required=false, defaultValue="") String searchTerm, @CookieValue(value="uid", required=false) String uid, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Iterable<RecipeDTO> topics = recipeService.fetchRecipes();
            modelAndView.setViewName("topics");
            modelAndView.addObject("topics", topics);
            model.addAttribute("uid", uid);
        } catch (Exception  e) {
            e.printStackTrace();
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    @RequestMapping("/userHome/topics/recipes")
    public ModelAndView recipes(@RequestParam(value="searchTerm", required=false, defaultValue="") String searchTerm, @CookieValue(value="uid", required=false) String uid, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Iterable<RecipeDTO> spoonRecipes = recipeService.fetchSpoonRecipes();
            modelAndView.setViewName("recipes");
            modelAndView.addObject("spoonRecipes", spoonRecipes);

            Iterable<RecipeDTO> topics = recipeService.fetchRecipes();
            modelAndView.addObject("topics", topics);
            model.addAttribute("uid", uid);
        } catch (Exception  e) {
            e.printStackTrace();
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }
    // < ------------------------------------------------------------------------------------ >

    // Topics and Recipes Pages for public users
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
    // < ------------------------------------------------------------------------------------ >

    // Search for recipes and Autocomplete
    @RequestMapping("/searchRecipes")
    public ModelAndView searchRecipes(@RequestParam(value="searchTerm", required=false, defaultValue="") String searchTerm, @CookieValue(value="uid", required=false) String uid, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Iterable<RecipeDTO> searchResults =  searchDAO.fetch(searchTerm);
            modelAndView.setViewName("searchRecipes");
            modelAndView.addObject("searchResults", searchResults);
            model.addAttribute("uid", uid);
            // Set off and error if movies = 0
        } catch (Exception  e) {
            e.printStackTrace();
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    @RequestMapping(value={"/searchAutocomplete", "userHome/*", "userHome/topics/*"})
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
    // < ------------------------------------------------------------------------------------ >

    @RequestMapping(value = "/login")
    public ModelAndView searchNavBar(@RequestParam(value="searchItem", required=false, defaultValue="") String searchItem, @CookieValue(value="uid", required=false) String uid, Model model){
        ModelAndView mV = new ModelAndView();
        try {
            Iterable<RecipeDTO> searchResults =  searchDAO.fetch(searchItem);
            mV.setViewName("searchRecipes");
            mV.addObject("searchResults", searchResults);
            model.addAttribute("uid", uid);
            // Set off and error if movies = 0
        } catch (Exception  e) {
            e.printStackTrace();
            mV.setViewName("error");
        }
        return mV;
    }
    @GetMapping(value = "/login")
    public String loginRequest(@CookieValue(value="uid", required=false) String uid, Model model){
        model.addAttribute("emailLogin");
        model.addAttribute("uid", uid);
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

//    @MessageMapping("/profile-update")
//    @SendTo("/topic/messages")
//    public ProfileDTO updateProfile(String email, String firstName, String lastName) throws Exception {
//
//        Firestore db = FirestoreClient.getFirestore();
//
//        JSONObject obj = new JSONObject(email);
//        System.out.println("controller dto: " + obj.getString("email"));
//
//        ProfileDTO docData = new ProfileDTO();
//        docData.setEmail(obj.getString("email"));
//        docData.setFirstName(obj.getString("firstName"));
//        docData.setLastName(obj.getString("lastName"));
//
//        UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(obj.getString("email"));
//        String uid = userRecord.getUid();
//
//        ApiFuture<WriteResult> future = db.collection("Users").document(uid).set(docData);
//        System.out.println("Update time : " + future.get().getUpdateTime());
//
//        return new ProfileDTO();
//
//    }
//
//    @GetMapping(value = "/profile")
//    public String userProfile (@CookieValue(value = "uid", required = false) String uid, Model model) throws
//            Exception {
//        try {
//            if (uid == null) {
//                System.out.println("No UID cookie found. User is not logged in.");
//                return "login";
//            }
//            List<PantryDTO> pantries = pantryService.fetchAll(firebaseService.getUser(uid).getEmail());
//            model.addAttribute("pantries", pantries);
//            model.addAttribute("uid", uid);
//            return "profile";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "error";
//        }
//    }

    // < ------------------------------------------------------------------------------------ >

    // Profile page prompted after initial sign in
    @GetMapping(value = "/profile")
    public String profile (HttpServletRequest request, @CookieValue(value = "uid", required = false) String uid, Model model) throws
            Exception {
        if (uid == null) {
            return "login";
        }

        ProfileDTO profiles = new ProfileDTO();
        profiles.setFirstName(firebaseService.getUser(uid).getDisplayName());
        profiles.setLastName(request.getParameter("lastName"));
        profiles.setEmail(firebaseService.getUser(uid).getEmail());
        profiles.setPhoneNumber(request.getParameter("phoneNumber"));
        profiles.setAddress(request.getParameter("address"));
        profiles.setUsername(request.getParameter("username"));

        try {
            if (uid == null) {
                System.out.println("No UID cookie found. User is not logged in.");
                return "login";
            }
            profileService.saveProfile(profiles, firebaseService.getUser(uid).getEmail(), firebaseService.getUser(uid).getDisplayName());

            List<ProfileDTO> profile = profileService.fetch(firebaseService.getUser(uid).getEmail());
            model.addAttribute("profile", profile);
            model.addAttribute("uid", uid);
            return "profile";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @PostMapping("/profile/saveProfile")
    public String saveProfile(HttpServletRequest request, @CookieValue(value = "uid", required = false) String uid) throws FirebaseAuthException {
        if (uid == null) {
            return "login";
        }

        ProfileDTO profiles = new ProfileDTO();
        profiles.setFirstName(request.getParameter("firstName"));
        profiles.setLastName(request.getParameter("lastName"));
        profiles.setEmail(request.getParameter("email"));
        profiles.setPhoneNumber(request.getParameter("phoneNumber"));
        profiles.setAddress(request.getParameter("address"));
        profiles.setUsername(request.getParameter("username"));

        try {
            profileService.saveProfile(profiles, firebaseService.getUser(uid).getEmail(), firebaseService.getUser(uid).getDisplayName());
        } catch (FirebaseAuthException | ExecutionException | InterruptedException e) {
            return "error";
        }
        return "redirect:/userHome";
    }

    //    @GetMapping(value = "/home")
//    public ModelAndView home() {
//        ModelAndView modelAndView = new ModelAndView();
//        try {
//            Iterable<RecipeDTO> recipes = recipeService.fetchRecipes();
//            modelAndView.setViewName("home");
//            modelAndView.addObject("recipes", recipes);
//        }
//        catch (Exception e){
//            // This should throw an error, not print stack trace
//            e.printStackTrace();
//            modelAndView.setViewName("error");
//        }
//        return modelAndView;
//    }
//
//    @PostMapping("/home")
//    public String create() {
//        return "home";
//    }

//    @GetMapping(value = "/test")
//    public ModelAndView test(@CookieValue(value="uid", required=false) String uid, Model model) throws Exception {
//        ModelAndView modelAndView = new ModelAndView();
//        try {
//            Iterable<RecipeDTO> recipes = recipeService.fetchRecipes();
//            modelAndView.setViewName("test");
//            modelAndView.addObject("recipes", recipes);
//            model.addAttribute("uid", uid);
//        }
//        catch (Exception e){
//            // This should throw an error, not print stack trace
//            e.printStackTrace();
//            modelAndView.setViewName("error");
//        }
//        return modelAndView;
//    }

//    // Pantry Attempt
//    @GetMapping(value = "/pantry")
//    public ModelAndView pantry(@RequestParam(value="pantries", required=false, defaultValue="") String pantries, @CookieValue(value="uid", required=false) String uid, Model model) throws Exception {
//        ModelAndView modelAndView = new ModelAndView();
//        try {
//            Iterable<PantryDTO> pantry = pantryDAO.fetch(pantries);
////            List<PantryDTO> pantry = pantryService.fetch(firebaseService.getUser(uid).getEmail());
//            modelAndView.setViewName("pantry");
//            modelAndView.addObject("pantry", pantry);
//            model.addAttribute("uid", uid);
//        }
//        catch (Exception e){
//            // This should throw an error, not print stack trace
//            e.printStackTrace();
//            modelAndView.setViewName("error");
//        }
//        return modelAndView;
//    }

//    @RequestMapping("/pantry")
//    public ModelAndView pantry(@RequestParam(value="searchTerm", required=false, defaultValue="") String searchTerm) {
//        ModelAndView modelAndViewPantry = new ModelAndView();
//        try {
//            Iterable<RecipeDTO> topics = recipeService.fetchRecipes();
//            modelAndViewPantry.setViewName("pantry");
//            modelAndViewPantry.addObject("pantry", topics);
//        } catch (Exception  e) {
//            e.printStackTrace();
//            modelAndViewPantry.setViewName("error");
//        }
//        return modelAndViewPantry;
//    }
}