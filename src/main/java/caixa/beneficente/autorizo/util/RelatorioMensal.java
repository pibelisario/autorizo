package caixa.beneficente.autorizo.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.hibernate.engine.jdbc.Size;

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
public class RelatorioMensal {

    private Associado associado;
    private List<Compra> listaCompras;
    private Document documentoPdf;
    private String caminhoRelatorio = "relatorios/RelatorioMensal.pdf";
    private LocalDate dataReferencia = LocalDate.now();
    int ano = dataReferencia.getYear();
    Month mes = dataReferencia.getMonth();
    private LocalDate dataRelatorio = LocalDate.of(ano, mes, 25);

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
        for (int i = 0; i < listaCompras.size(); i ++) {
            Paragraph dadosAssociado = new Paragraph();
            dadosAssociado.add(new Chunk(new Chunk("Rg: " + listaCompras.get(i).getAssociado().getRg() +
                    " Nome: " + listaCompras.get(i).getAssociado().getNome() + " Valor: " + listaCompras.get(i).getValor(),
                    new Font(Font.HELVETICA, 12))));
            this.documentoPdf.add(dadosAssociado);
            long idAssociado = listaCompras.get(i).getAssociado().getId();
            double total = 0;
            for (int j = 0; j < listaCompras.size(); j++) {
                if (listaCompras.get(j).getAssociado().getId() == idAssociado) {
                    total += listaCompras.get(j).getValor();
                }
            }

            if (i % 2 != 0){
                Paragraph totalCompras = new Paragraph();
                totalCompras.setAlignment(Element.ALIGN_LEFT);
                totalCompras.add(new Chunk("Total: " + total, new Font(Font.BOLD, 12)));
                totalCompras.add(new Paragraph());
                totalCompras.add(new Paragraph());
                this.documentoPdf.add(totalCompras);
                totalCompras.add(new Paragraph());
                totalCompras.add(new Paragraph());
            }
            
        }
        Paragraph totalCompras = new Paragraph();
            totalCompras.setAlignment(Element.ALIGN_RIGHT);
            totalCompras.add(new Chunk("Total Geral: " + calcularTotal(), new Font(Font.BOLD, 20)));
            totalCompras.add(new Paragraph());
            totalCompras.add(new Paragraph());
            this.documentoPdf.add(totalCompras);

    }

    public void imprimirRelaotrio() {
        if (this.documentoPdf != null || this.documentoPdf.isOpen()) {
            this.documentoPdf.close();
        }

    }

    public double calcularTotal() {
        double total = 0;
        for (Compra compra : listaCompras) {
            // if (compra.getDataCompra().getDayOfMonth() == dataReferencia.getDayOfMonth())
            // {
            total += compra.getValor();
            // }
        }
        return total;
    }

}
