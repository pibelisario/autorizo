package caixa.beneficente.autorizo.controllers;


import caixa.beneficente.autorizo.models.Associado;
import caixa.beneficente.autorizo.services.AssociadoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class AssociadoController {

    AssociadoService associadoService;

    public AssociadoController(AssociadoService associadoService) {
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
        List<Associado> associados = associadoService.findByRg(rg);
        if (associados.isEmpty()){
            mv.addObject("mensagem", "Nenhum registro encontrando para o RG: " +rg);
        } else {
            mv.addObject("associados", associados);
        }
        return mv;
    }

    @PostMapping("buscarNome")
    public ModelAndView buscarNome(@RequestParam("nome")String nome){
        ModelAndView mv = new ModelAndView("/associado/index");
        List<Associado> associados = associadoService.findByNome(nome);
        if (associados.isEmpty()){
            mv.addObject("mensagem", "Nenhum registo encontrado para o NOME: " +nome);
        } else {
            mv.addObject("associados", associados);
        }
        return mv;
    }

    @PostMapping("buscarCpf")
    public ModelAndView buscarCpf(@RequestParam("cpf")String cpf){
        ModelAndView mv = new ModelAndView("/associado/index");
        List<Associado> associados = associadoService.findByCpf(cpf);
        if (associados.isEmpty()){
            mv.addObject("mensagem", "Nenhum registro encontrado para o CPF: " +cpf);
        } else {
            mv.addObject("associados", associados);
        }
        return mv;
    }
}
