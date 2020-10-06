package com.grap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}