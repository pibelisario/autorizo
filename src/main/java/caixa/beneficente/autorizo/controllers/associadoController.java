package caixa.beneficente.autorizo.controllers;


import caixa.beneficente.autorizo.services.AssociadoService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class associadoController {

    AssociadoService associadoService;

    public associadoController(AssociadoService associadoService) {
        this.associadoService = associadoService;
    }

    @GetMapping("/pesquisar")
    public ModelAndView pesquisaAssociado(){
        ModelAndView mv = new ModelAndView("/associado/index");
//        mv.addObject("associados", associadoService.findAll());
        return mv;
    }

    @GetMapping("/listarTodos")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView("/associado/listaAssociados");
        mv.addObject("associados", associadoService.findAll());
        return mv;
    }

    @PostMapping("buscarRg")
    public ModelAndView buscarRg(@RequestParam("rg")String rg){
        ModelAndView mv = new ModelAndView("/associado/index");
        mv.addObject("associados", associadoService.findByRg(rg));
        return mv;
    }

    @PostMapping("buscarNome")
    public ModelAndView buscarNome(@RequestParam("nome")String nome){
        ModelAndView mv = new ModelAndView("/associado/index");
        mv.addObject("associados", associadoService.findByNome(nome));
        return mv;
    }

    @PostMapping("buscarCpf")
    public ModelAndView buscarCpf(@RequestParam("cpf")String cpf){
        ModelAndView mv = new ModelAndView("/associado/index");
        mv.addObject("associados", associadoService.findByCpf(cpf));
        return mv;
    }
}
