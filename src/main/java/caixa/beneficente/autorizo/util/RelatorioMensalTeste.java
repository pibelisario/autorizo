package caixa.beneficente.autorizo.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.Comparator;
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
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RelatorioMensalTeste {

    private Associado associado;
    private List<Compra> listaCompras;
    private Document documentoPdf;
    private String caminhoRelatorio = "relatorios/RelatorioMensal.pdf";
    private LocalDate dataReferencia = LocalDate.now();
    int ano = dataReferencia.getYear();
    Month mes = dataReferencia.getMonth();
    private LocalDate dataRelatorio = LocalDate.of(ano, mes, 25);

    public RelatorioMensalTeste() throws DocumentException, FileNotFoundException {
        this.documentoPdf = new Document();
        PdfWriter.getInstance(documentoPdf, new FileOutputStream(caminhoRelatorio));
        this.documentoPdf.open();
    }

    public RelatorioMensalTeste(List<Compra> listaCompras) throws DocumentException,
            FileNotFoundException {
        this.listaCompras = listaCompras;
        this.documentoPdf = new Document();
        PdfWriter.getInstance(documentoPdf, new FileOutputStream(caminhoRelatorio));
        this.documentoPdf.open();
        ordenaPorNome(listaCompras);
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
        double total = 0;
        String rgInicial = listaCompras.get(0).getAssociado().getRg();
        for (int i = 0; i < listaCompras.size(); i++) {
            String rgAssociado = listaCompras.get(i).getAssociado().getRg();
            if (!rgAssociado.equals(rgInicial)) {
                imprimirTotal(total);
                rgInicial = rgAssociado;
                total = 0;
            }
            Paragraph dadosAssociado = new Paragraph();
            dadosAssociado.add(new Chunk(new Chunk("Rg: " + listaCompras.get(i).getAssociado().getRg() +
                    " Nome: " + listaCompras.get(i).getAssociado().getNome() + " Valor: "
                    + listaCompras.get(i).getValor(),
                    new Font(Font.HELVETICA, 12))));
            total += listaCompras.get(i).getValor();
            this.documentoPdf.add(dadosAssociado);
        }
        imprimirTotal(total);
        Paragraph totalCompras = new Paragraph();
        totalCompras.setAlignment(Element.ALIGN_RIGHT);
        totalCompras.add(new Chunk("Total Geral: " + calcularTotal(), new Font(Font.BOLD, 20)));
        totalCompras.add(new Paragraph());
        totalCompras.add(new Paragraph());
        this.documentoPdf.add(totalCompras);

    }

    public void imprimirTotal(double total) {
        Paragraph totalCompras = new Paragraph(
                "-----------------------------------------------------------------------");
        totalCompras.setAlignment(Element.ALIGN_LEFT);
        totalCompras.add(new Chunk("Total: " + total, new Font(Font.BOLD, 12)));
        totalCompras.add(new Paragraph());
        totalCompras.add(new Paragraph());
        totalCompras.add(new Paragraph());
        this.documentoPdf.add(totalCompras);

    }

    public double calcularTotal() {
        double total = 0;
        for (Compra compra : listaCompras) {
            total += compra.getValor();
        }
        return total;
    }

    public void ordenaPorNome(List<Compra> listaCompras) {
        Collections.sort(listaCompras, new Comparator<Compra>() {
            @Override
            public int compare(Compra p1, Compra p2) {
                return p1.getAssociado().getNome().compareTo(p2.getAssociado().getNome());
            }

        });
    }

    public void imprimirRelaotrio() {
        if (this.documentoPdf != null || this.documentoPdf.isOpen()) {
            this.documentoPdf.close();
        }

    }

}
