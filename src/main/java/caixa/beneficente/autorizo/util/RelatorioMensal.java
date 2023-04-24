package caixa.beneficente.autorizo.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        listaCompras.sort((c1, c2) -> c1.getAssociado().getNome().toUpperCase()
                .compareTo(c2.getAssociado().getNome().toUpperCase()));
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

        List<Compra> compras;
        listaCompras.sort(Comparator.comparing(c -> c.getAssociado().getNome()));
        Set<Long> ids = new HashSet<Long>();

        for (int i = 1; i < listaCompras.size(); i++) {
            ids.add(listaCompras.get(i).getUsuario().getId());
        }
        Long[] listId = new Long[ids.size()];

        List<Long> listLong = new ArrayList<Long>(ids);

        for (int i = 0; i < ids.size(); i++) {
            listId[i] = listLong.get(i);
        }

        for (int i = 0; i < ids.size(); i++) {
            System.out.println(listId[i]);
        }

        for (int i = 0; i < listLong.size(); i++) {
            compras = new ArrayList<>();
            Long id = listLong.get(i);

            for (int j = 0; j < listaCompras.size(); j++) {
                if (listaCompras.get(j).getUsuario().getId() == id) {
                    compras.add(listaCompras.get(j));
                }
            }

            Paragraph nomeFarmacia = new Paragraph();
            nomeFarmacia.setAlignment(Element.ALIGN_CENTER);
            nomeFarmacia.add(new Chunk(compras.get(0).getUsuario().getNome()));
            nomeFarmacia.add(new Paragraph());
            nomeFarmacia.add(new Paragraph());
            documentoPdf.add(nomeFarmacia);

            compras.sort((c1, c2) -> c1.getAssociado().getNome().compareTo(c2.getAssociado().getNome()));

            String nomeAssociado = null;
            Double tot = 0.0;
            Paragraph dadosAssociado;
            for (int k = 0; k < compras.size(); k++) {

                dadosAssociado = new Paragraph();
                if (k == 0) {
                    nomeAssociado = compras.get(k).getAssociado().getNome();
                    dadosAssociado.add(new Chunk("Associado: " + compras.get(k).getAssociado().getNome()));
                    dadosAssociado.add(new Paragraph());
                }
                if (!nomeAssociado.equals(compras.get(k).getAssociado().getNome())) {
                    dadosAssociado.add(new Chunk("Total: " + tot));
                    dadosAssociado.add(new Paragraph());
                    dadosAssociado.add(new Paragraph());
                    nomeAssociado = compras.get(k).getAssociado().getNome();
                    dadosAssociado.add(new Chunk("Associado: " + compras.get(k).getAssociado().getNome()));
                    dadosAssociado.add(new Paragraph());
                    tot = 0.0;
                }
                tot += compras.get(k).getValor();
                dadosAssociado.setAlignment(Element.ALIGN_LEFT);
                dadosAssociado.add(compras.get(k).toString());
                dadosAssociado.add(new Paragraph());
                documentoPdf.add(dadosAssociado);
            }
            dadosAssociado = new Paragraph();
            dadosAssociado.add(new Chunk("Total: " + tot));
            tot = 0.0;
            documentoPdf.add(dadosAssociado);

            Paragraph total = new Paragraph();
            total.setAlignment(Element.ALIGN_RIGHT);
            total.add(new Chunk("Total: " + compras.stream().map(c -> c.getValor()).reduce(0.0, (x, y) -> x + y),
                    new Font(Font.BOLD, 14)));
            documentoPdf.add(total);

            compras = null;
        }

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

    public void imprimirRelaotrio() {
        if (this.documentoPdf != null || this.documentoPdf.isOpen()) {
            this.documentoPdf.close();
        }

    }

}
