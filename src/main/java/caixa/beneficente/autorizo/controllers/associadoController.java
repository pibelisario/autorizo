package caixa.beneficente.autorizo.controllers;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class associadoController {

    @GetMapping("/pesquisar")
    public ModelAndView pesquisaAssociado(){
        ModelAndView mv = new ModelAndView("/pesquisaAssociado/index");
        return mv;
    }
}
