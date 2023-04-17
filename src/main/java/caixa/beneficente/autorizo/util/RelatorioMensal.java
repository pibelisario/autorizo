package caixa.beneficente.autorizo.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import caixa.beneficente.autorizo.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import caixa.beneficente.autorizo.models.Associado;
import caixa.beneficente.autorizo.models.Compra;
import caixa.beneficente.autorizo.repositories.AssociadoRepository;
import caixa.beneficente.autorizo.repositories.CompraRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Service
public class RelatorioMensal {

    @Autowired
    CompraService compraService;
    @Autowired
    AssociadoRepository associadoRepository;

    private Associado associado;
    private List<Compra> listaCompras;
    private Document documentoPdf;
    private String caminhoRelatorio = "relatorios/RelatorioMensal.pdf";
    private LocalDate dataReferencia = LocalDate.now();
    int ano = dataReferencia.getYear();
    Month mes = dataReferencia.getMonth();
    private LocalDate dataRelatorio = LocalDate.of(ano, mes, 25);

    List<Associado> associados = associadoRepository.findAll();
    List<Compra> compras = compraService.findAll();

    public RelatorioMensal() throws DocumentException, FileNotFoundException {
        this.documentoPdf = new Document();
        PdfWriter.getInstance(documentoPdf, new FileOutputStream(caminhoRelatorio));
        this.documentoPdf.open();
    }

    public RelatorioMensal(List<Compra> listaCompras) throws DocumentException,
            FileNotFoundException {
        this.listaCompras = listaCompras;
        this.documentoPdf = new Document();
        PdfWriter.getInstance(documentoPdf, new FileOutputStream(caminhoRelatorio));
        this.documentoPdf.open();
    }

    public void gerarCabecalho() {

        Paragraph cabecalho = new Paragraph();
        cabecalho.setAlignment(Element.ALIGN_CENTER);
        cabecalho.add(new Chunk("Caixa Beneficente dos Militares do Estado de Goiás", new Font(Font.BOLD, 20)));
        cabecalho.add(new Paragraph(""));
        cabecalho.add(new Paragraph(""));
        cabecalho.add(new Chunk("Relatório do mês " + dataReferencia.getMonthValue() + " ,dias: 01 até 25",
                new Font(Font.COURIER, 16)));
        cabecalho.add(new Paragraph(""));
        cabecalho.add(new Paragraph(""));
        this.documentoPdf.add(cabecalho);

    }

    public void gerarCorpo() {
        for (Compra compra : compras) {
            Paragraph comprasAssociado = new Paragraph();
            comprasAssociado
                    .add(new Chunk("Rg: " + compra.getAssociado().getRg() +
                            " Nome: " + compra.getAssociado().getNome() +
                            "Total: " + calcularTotal(),
                            new Font(Font.HELVETICA, 12)));
            comprasAssociado.add(new Paragraph(" "));
            comprasAssociado.add(new Paragraph(" "));
            this.documentoPdf.add(comprasAssociado);
        }
    }

    public void imprimirRelaotrio() {
        if (this.documentoPdf != null || this.documentoPdf.isOpen()) {
            this.documentoPdf.close();
        }

    }

    public double calcularTotal() {
        double total = 0;
        for (Compra compra : compras) {
            if (compra.getDataCompra().getDayOfMonth() == dataReferencia.getDayOfMonth()) {
                total += compra.getValor();
            }
        }
        return total;
    }

}
