package caixa.beneficente.autorizo.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import caixa.beneficente.autorizo.models.Associado;
import caixa.beneficente.autorizo.models.Compra;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RelatorioMensal {

    private Associado associado;
    private List<Compra> listaCompras;
    private Document documentoPdf;
    private String caminhoRelatorio = "relatorios/RelatorioMensal.pdf";
    private LocalDate dataReferencia = LocalDate.now();
    int ano = dataReferencia.getYear();
    Month mes = dataReferencia.getMonth();

    private LocalDate dataRelatorio = LocalDate.of(ano, mes, 25);

    public RelatorioMensal(Associado associado, List<Compra> listaCompras, LocalDate dataRelatorio)
            throws DocumentException, FileNotFoundException {
        this.associado = associado;
        this.listaCompras = listaCompras;
        this.dataRelatorio = dataRelatorio;
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
        Paragraph dadosAssociado = new Paragraph();
        dadosAssociado.add(
                new Chunk("Rg: " + associado.getRg() + " Nome: " + associado.getNome(), new Font(Font.HELVETICA, 14)));
        dadosAssociado.add(new Paragraph(" "));
        dadosAssociado.add(new Paragraph(" "));
        this.documentoPdf.add(dadosAssociado);

        for (Compra compra : this.listaCompras) {
            Paragraph comprasAssociado = new Paragraph();
            comprasAssociado
                    .add(new Chunk("Rg: " + compra.getAssociado().getRg() +
                            " Nome: " + compra.getAssociado().getNome() +
                            "Total: ",
                            new Font(Font.HELVETICA, 12)));
            comprasAssociado.add(new Paragraph(" "));
            comprasAssociado.add(new Paragraph(" "));
            this.documentoPdf.add(comprasAssociado);
        }

        Paragraph totalCompra = new Paragraph();
        totalCompra.setAlignment(Element.ALIGN_RIGHT);
        totalCompra.add(new Chunk("Total da Compra: " + calcularTotal(), new Font(Font.BOLD, 14)));
        this.documentoPdf.add(totalCompra);
    }

    public void imprimirRelaotrio() {
        if (this.documentoPdf != null || this.documentoPdf.isOpen()) {
            this.documentoPdf.close();
        }

    }

    public double calcularTotal() {
        double total = 0;
        for (Compra compra : this.listaCompras) {
            total += compra.getValor();
        }
        return total;
    }

}
