package caixa.beneficente.autorizo.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.DocumentException;

import caixa.beneficente.autorizo.models.Compra;
import caixa.beneficente.autorizo.repositories.AssociadoRepository;
import caixa.beneficente.autorizo.repositories.CompraRepository;
import caixa.beneficente.autorizo.repositories.UsuarioRepository;
import caixa.beneficente.autorizo.util.Relatorio;
import caixa.beneficente.autorizo.util.RelatorioMensal;

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

    public List<Compra> findAllData(Long id) {
        LocalDate dataI = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1);
        LocalDate dataF = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(),
                LocalDate.now().lengthOfMonth());
        List<Compra> compras = compraRepository.findEntradasByDataBetweenAndAssociadoEqualsId(dataI, dataF, id);
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
        Relatorio relatorio = new Relatorio(associadoRepository.findById(id).get(),
                compraRepository.findByCompraId(id));
        relatorio.gerarCabecalho();
        relatorio.gerarCorpo();
        relatorio.imprimirRelaotrio();

    }

    public void gerarRelatorioMensal() throws DocumentException, IOException {
        RelatorioMensal relatorioMensal = new RelatorioMensal(compraRepository.findAll());
        relatorioMensal.gerarCabecalhoSemData();
        relatorioMensal.gerarCorpo();
        relatorioMensal.gerarMetadados();
        relatorioMensal.imprimirRelaotrio();
        relatorioMensal.addPageNumber();
    }

    public void gerarRelatorioPorData(String dataInicial, String dataFinal) throws DocumentException, IOException {
        LocalDate dataI = LocalDate.parse(dataInicial, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate dataF = LocalDate.parse(dataFinal, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        RelatorioMensal relatorioMensal = new RelatorioMensal(
                compraRepository.findComprasByDataCompraBetween(dataI, dataF));
        relatorioMensal.gerarCabecalhoComData(dataI, dataF);
        relatorioMensal.gerarCorpo();
        relatorioMensal.gerarMetadados();
        relatorioMensal.imprimirRelaotrio();
        relatorioMensal.addPageNumber();

    }

    public double calcularTotal(Long id) {
        double total = 0;
        for (Compra compra : compraRepository.findByCompraId(id)) {
            total += compra.getValor();
        }
        return total;
    }

}
