package caixa.beneficente.autorizo.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import caixa.beneficente.autorizo.models.Associado;
import caixa.beneficente.autorizo.models.Compra;
import caixa.beneficente.autorizo.repositories.AssociadoRepository;
import caixa.beneficente.autorizo.repositories.CompraRepository;

@Service
public class CompraService {

    @Autowired
    AssociadoService associadoService;
    @Autowired
    CompraRepository compraRepository;
    @Autowired
    AssociadoRepository associadoRepository;

    public List<Compra> findAll() {
        List<Compra> compras = compraRepository.findAll();
        Collections.reverse(compras);
        return compras;
    }

    public void salvar(String valor, Long id) {
        valor = valor.replace(".", "");
        valor = valor.replace(",", ".");
        double vDouble = Double.valueOf(valor).doubleValue();
        System.out.println(vDouble);
        Compra compra = new Compra();
        compra.setValor(vDouble);
        compra.setAssociado(associadoService.findById(id));
        compraRepository.save(compra);
        associadoService.ajustarLimite(vDouble, id);
    }

}