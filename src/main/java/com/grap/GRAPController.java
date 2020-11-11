package com.grap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;

@Controller
public class GRAPController{

    private ServletContext servletContext;
    public GRAPController(ServletContext servletContext) {
        this.servletContext = servletContext;
    }


    @GetMapping(value = "/home")
    public ModelAndView home() {
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
        public String create() {
            return "home";
        }


    // Login form
    @RequestMapping("/login")
    @ResponseBody
    public String login() {
        return "login";
    }

    @GetMapping(value = "/login")
    public String loginRequest(Model model){
        model.addAttribute("emailLogin");
        return "login";
    }

    @PostMapping("/login")
    public String createLogin() {
        return "signup";
    }

    @RequestMapping("/login/signup")
    public String signup(){
        return "signup";
    }

}
