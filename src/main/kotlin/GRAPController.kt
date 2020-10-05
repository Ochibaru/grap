package com.grap

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GRAPController {

    @Autowired
    private IProfileService profileService;
    @Autowired
    private ISearchDAO searchDAO;

    @GetMapping(value="/home")
    public ModelAndView start() throws Exception{
        val modelAndView = ModelAndView()
        try {

        }
        catch (Exception e){
            // this should throw an error, not print stack trace
            e.printStackTrace();
        }
        return modelAndView;
    }
}