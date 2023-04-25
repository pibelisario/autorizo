package caixa.beneficente.autorizo.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import caixa.beneficente.autorizo.models.Compra;
import caixa.beneficente.autorizo.repositories.CompraRepository;
import caixa.beneficente.autorizo.services.AssociadoService;
import caixa.beneficente.autorizo.services.CompraService;
import caixa.beneficente.autorizo.util.RelatorioMensal;

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
        mv.addObject("totalCompras", compraService.calcularTotal(id));
        return mv;
    }

    @PostMapping("lancarCompra/{id}")
    public ModelAndView lancarCompra(@RequestParam("valor") String valor, @RequestParam("idUser") String userName,
            @PathVariable("id") Long id) {
        compraService.salvar(valor, id, userName);
        return compras(id);
    }

    @GetMapping("/download")
    public HttpEntity<byte[]> download(@RequestParam("id") Long id) throws IOException {

        compraService.gerarRelatorio(id);

        byte[] arquivo = Files.readAllBytes(Paths.get("C:\\Workspace\\autorizo\\relatorios\\RelatorioVendas.pdf"));

        org.springframework.http.HttpHeaders httpHeaders = new org.springframework.http.HttpHeaders();

        httpHeaders.add("Content-Disposition",
                "attachment;filename=\""
                        + associadoService.findById(id).getNome().replace(" ", "") + ".pdf\"");

        HttpEntity<byte[]> entity = new HttpEntity<byte[]>(arquivo, httpHeaders);

        System.out.println("Passei aqui");

        return entity;
    }

    @GetMapping("/adm")
    public ModelAndView downloadRelatorioMensal() {
        ModelAndView mv = new ModelAndView("associado/relatorioMensal");
        return mv;
    }

    @GetMapping("/downloadRelatorioMensal")
    public HttpEntity<byte[]> download() throws IOException {

        // compraService.gerarRelatorioMensal();
        List<Compra> compras = compraRepository.findAll();
        RelatorioMensal relatorioMensal = new RelatorioMensal(compras);
        relatorioMensal.gerarCabecalho();
        relatorioMensal.gerarCorpo();
        relatorioMensal.imprimirRelaotrio();

        // byte[] arquivo =
        // Files.readAllBytes(Paths.get("C:\\Workspace\\autorizo\\relatorios\\RelatorioMensal.pdf"));
        byte[] arquivo = Files.readAllBytes(Paths.get("C:\\Workspace\\autorizo\\src\\relatorios\\sample1.pdf"));

        org.springframework.http.HttpHeaders httpHeaders = new org.springframework.http.HttpHeaders();

        httpHeaders.add("Content-Disposition",
                "attachment;filename=\"relatorioMensal.pdf\"");

        HttpEntity<byte[]> entity = new HttpEntity<byte[]>(arquivo, httpHeaders);

        System.out.println("Passei aqui");

        return entity;
    }

}
