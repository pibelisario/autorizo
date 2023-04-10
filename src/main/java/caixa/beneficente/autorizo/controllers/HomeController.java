package caixa.beneficente.autorizo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

    @GetMapping("/log")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/login");
        return mv;
    }

}
