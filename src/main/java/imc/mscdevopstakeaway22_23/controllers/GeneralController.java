package imc.mscdevopstakeaway22_23.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class GeneralController {
    @RequestMapping(path="/Home")
    public ModelAndView home(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Home");
        return mav;
    }
}
