package caixa.beneficente.autorizo.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import caixa.beneficente.autorizo.repositories.CompraRepository;
import caixa.beneficente.autorizo.services.AssociadoService;
import caixa.beneficente.autorizo.services.CompraService;

@RestController
public class CompraController {

    @Autowired
    CompraRepository compraRepository;
    @Autowired
    AssociadoService associadoService;
    @Autowired
    CompraService compraService;

    @GetMapping("compras/{id}")
    public ModelAndView compras(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("associado/compras");
        mv.addObject("associado", associadoService.findById(id));
        mv.addObject("compras", compraRepository.findByCompraId(id));
        return mv;
    }

    @GetMapping("lancarCompra/{id}")
    public ModelAndView lancarCompra(@RequestParam("valor") String valor, @PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
        compraService.salvar(valor, id);
        return compras(id);
    }

    @GetMapping("/download")
    public HttpEntity<byte[]> download(@RequestParam("id") Long id) throws IOException {

        compraService.gerarRelatorio(id);

        byte[] arquivo = Files.readAllBytes(Paths.get("C:\\Workspace\\autorizo\\RelatorioVendas.pdf"));

        org.springframework.http.HttpHeaders httpHeaders = new org.springframework.http.HttpHeaders();

        httpHeaders.add("Content-Disposition",
                "attachment;filename=\""
                        + associadoService.findById(id).getNome().replace(" ", "") + ".pdf\"");

        HttpEntity<byte[]> entity = new HttpEntity<byte[]>(arquivo, httpHeaders);

        System.out.println("Passei aqui");

        return entity;
    }

}
