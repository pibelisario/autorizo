package caixa.beneficente.autorizo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import caixa.beneficente.autorizo.models.Compra;
import caixa.beneficente.autorizo.repositories.CompraRepository;
import caixa.beneficente.autorizo.services.AssociadoService;

@RestController
public class CompraController {

    @Autowired
    CompraRepository compraRepository;
    @Autowired
    AssociadoService associadoService;

    @GetMapping("compras/{id}")
    public ModelAndView compras(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("associado/compras");
        mv.addObject("associado", associadoService.findById(id));
        return mv;
    }

    @GetMapping("lancarCompra/{id}")
    public ModelAndView lancarCompra(@RequestParam("valor") String valor, @PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
        System.out.println(valor + " - " + id);
        return compras(id);
    }

}
