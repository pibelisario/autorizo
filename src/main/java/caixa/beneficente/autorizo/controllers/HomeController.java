package caixa.beneficente.autorizo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/login");
        return mv;
    }

    @RequestMapping("/login")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/login");
        return mv;
    }

    @GetMapping("/pesquisar")
    public ModelAndView pesquisaAssociado() {
        ModelAndView mv = new ModelAndView("associado/pesquisar");
        // mv.addObject("associados", associadoService.findAll());
        return mv;
    }

}
