package com.grap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GRAPController{

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
            }
            catch (Exception e){
            // this should throw an error, not print stack trace
            e.printStackTrace();
            }
            return modelAndView;
            }

    @PostMapping("/home")
        public String create1() {

            return "home";
        }


    // Login form
    @RequestMapping("/login")
    @ResponseBody
    public String login() {
        return "login";
    }

    // Login form with error
    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

}
