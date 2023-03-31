package caixa.beneficente.autorizo.services;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import caixa.beneficente.autorizo.models.Associado;
import caixa.beneficente.autorizo.models.Compra;
import caixa.beneficente.autorizo.repositories.CompraRepository;
import jakarta.validation.OverridesAttribute.List;

@Service
public class CompraService {

    @Autowired
    AssociadoService associadoService;
    @Autowired
    CompraRepository compraRepository;

    public void salvar(String valor, Long id) {
        valor = valor.replace(".", "");
        valor = valor.replace(",", ".");
        double vDouble = Double.valueOf(valor).doubleValue();
        System.out.println(vDouble);
        Compra compra = new Compra();
        compra.setValor(vDouble);
        compra.setAssociado(associadoService.findById(id));
        compraRepository.save(compra);
    }


}
