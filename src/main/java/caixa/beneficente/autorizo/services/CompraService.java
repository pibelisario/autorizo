package caixa.beneficente.autorizo.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.DocumentException;

import caixa.beneficente.autorizo.models.Associado;
import caixa.beneficente.autorizo.models.Compra;
import caixa.beneficente.autorizo.repositories.AssociadoRepository;
import caixa.beneficente.autorizo.repositories.CompraRepository;
import caixa.beneficente.autorizo.repositories.UsuarioRepository;
import caixa.beneficente.autorizo.util.Relatorio;
import caixa.beneficente.autorizo.util.RelatorioMensal;
import caixa.beneficente.autorizo.util.RelatorioMensalTeste;

@Service
public class CompraService {

    @Autowired
    AssociadoService associadoService;
    @Autowired
    CompraRepository compraRepository;
    @Autowired
    AssociadoRepository associadoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Compra> findAll() {
        List<Compra> compras = compraRepository.findAll();
        Collections.reverse(compras);
        return compras;
    }

    public void salvar(String valor, Long id, String userName) {
        valor = valor.replace(".", "");
        valor = valor.replace(",", ".");
        double vDouble = Double.valueOf(valor).doubleValue();
        Math.round(vDouble);
        Compra compra = new Compra();
        compra.setValor(vDouble);
        compra.setAssociado(associadoService.findById(id));
        compra.setUsuario(usuarioRepository.findByUser(userName).get());
        compraRepository.save(compra);
        associadoService.ajustarLimite(vDouble, id);
    }

    public void gerarRelatorio(Long id) throws DocumentException, FileNotFoundException {
        Associado associado = associadoRepository.findById(id).get();
        List<Compra> compras = compraRepository.findByCompraId(id);
        Relatorio relatorio = new Relatorio(associado, compras);
        relatorio.gerarCabecalho();
        relatorio.gerarCorpo();
        relatorio.imprimirRelaotrio();

    }

    public void gerarRelatorioMensal() throws DocumentException, IOException {
        RelatorioMensal relatorioMensal = new RelatorioMensal(compraRepository.findAll());
        relatorioMensal.gerarCabecalho();
        relatorioMensal.gerarCorpo();
        relatorioMensal.gerarMetadados();
        relatorioMensal.onEndPage();
        relatorioMensal.imprimirRelaotrio();
    }

    public double calcularTotal(Long id) {
        double total = 0;
        for (Compra compra : compraRepository.findByCompraId(id)) {
            total += compra.getValor();
        }
        return total;
    }

}
