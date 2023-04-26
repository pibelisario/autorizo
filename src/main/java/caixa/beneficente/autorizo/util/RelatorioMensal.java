package caixa.beneficente.autorizo.util;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfTemplate;
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
    FileOutputStream pdfOutputFile;
    private LocalDate dataReferencia = LocalDate.now();
    int ano = dataReferencia.getYear();
    Month mes = dataReferencia.getMonth();
    private LocalDate dataRelatorio = LocalDate.of(ano, mes, 25);
    private PdfGState gstate;
    private PdfWriter pdfWriter;
    public BaseFont helv;
    public PdfTemplate tpl;

    public RelatorioMensal(List<Compra> listaCompras) throws DocumentException,
            IOException {
        // Criando arquivo de saida
        this.pdfOutputFile = new FileOutputStream("C:\\Workspace\\autorizo\\src\\relatorios\\sample1.pdf");
        // Criando um novo mundo pdf e adicinando configurações
        this.documentoPdf = new Document(PageSize.A4,
                40f, // left
                40f, // right
                150f, // top
                50f); // down

        this.listaCompras = listaCompras;
        pdfWriter = PdfWriter.getInstance(documentoPdf, pdfOutputFile);
        this.documentoPdf.open();

        // Inicializando contador de págs
        gstate = new PdfGState();
        gstate.setFillOpacity(0.3f);
        gstate.setStrokeOpacity(0.3f);

        // Colocando as Listas em ordem alfabetica
        listaCompras.sort((c1, c2) -> c1.getAssociado().getNome().toUpperCase()
                .compareTo(c2.getAssociado().getNome().toUpperCase()));

        helv = BaseFont.createFont("Helvetica", BaseFont.WINANSI, false);
        tpl = pdfWriter.getDirectContent().createTemplate(100, 100);
    }

    public void gerarCabecalho() throws BadElementException, IOException {
        Paragraph cabecalho = new Paragraph();
        cabecalho.setAlignment(Element.ALIGN_CENTER);
        adicionarImagemCabecalho();
        cabecalho.add(new Paragraph(""));
        cabecalho.add(new Paragraph(""));
        cabecalho.add(new Chunk("Relatório do mês " + dataReferencia.getMonthValue() + " ,dias: 01 até 25",
                new Font(Font.NORMAL, 16f, Font.BOLDITALIC, Color.black)));
        cabecalho.add(new Paragraph(""));
        cabecalho.add(new Paragraph(""));
        this.documentoPdf.add(cabecalho);

    }

    public void adicionarImagemCabecalho() throws BadElementException, IOException {
        Image image = Image.getInstance("https://i.imgur.com/MaQQcUN.png");
        image.scalePercent(100, 100);
        documentoPdf.add(image);
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

    public void gerarMetadados() {
        documentoPdf.addTitle("Relatório de gastos mensais das farmácias conveniadas");
        documentoPdf.addSubject("Este e um relatório contendos os gastos mensais das farmácias conveniadas");
        documentoPdf.addKeywords("Java, OpenPDF, relatório");
        documentoPdf.addCreator("Caixa Beneficente dos Militares do Estado de Goiás");
        documentoPdf.addAuthor("Paulo Inácio Belisario de Noronha");
    }

    // public void onEndPage() {
    // PdfContentByte cb = pdfWriter.getDirectContent();
    // cb.saveState();
    // // compose the footer
    // String text = "Page " + pdfWriter.getPageNumber();
    // float textSize = helv.getWidthPoint(text, 12);
    // float textBase = documentoPdf.bottom() - 20;
    // cb.beginText();
    // cb.setFontAndSize(helv, 12);
    // // for odd pagenumbers, show the footer at the left
    // cb.setTextMatrix(documentoPdf.left(), textBase);
    // cb.showText(text);
    // cb.endText();
    // cb.addTemplate(tpl, documentoPdf.left() + textSize, textBase);
    // cb.restoreState();
    // cb.sanityCheck();
    // }

    // public void onEndPage() {
    // PdfContentByte cb = pdfWriter.getDirectContent();
    // cb.saveState();
    // // compose the footer
    // String text = "Page " + pdfWriter.getPageNumber() + " of ";
    // float textSize = helv.getWidthPoint(text, 12);
    // float textBase = documentoPdf.bottom() - 20;
    // cb.beginText();
    // cb.setFontAndSize(helv, 12);
    // // for odd pagenumbers, show the footer at the left
    // cb.setTextMatrix(documentoPdf.left(), textBase);
    // cb.showText(text);
    // cb.endText();
    // cb.addTemplate(tpl, documentoPdf.left() + textSize, textBase);

    // cb.saveState();
    // // draw a Rectangle around the page
    // cb.setColorStroke(Color.orange);
    // cb.setLineWidth(2);
    // cb.rectangle(20, 20, documentoPdf.getPageSize().getWidth() - 40,
    // documentoPdf.getPageSize().getHeight() - 40);
    // cb.stroke();
    // cb.restoreState();
    // cb.restoreState();
    // cb.sanityCheck();
    // }

    public void onEndPage() throws IOException {
        PdfReader reader = new PdfReader("C:\\Workspace\\autorizo\\src\\relatorios\\sample1.pdf");
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("sample12.pdf"));
        stamper.setRotateContents(false);
        Phrase t = new Phrase("Total pages " + reader.getNumberOfPages(), new Font(Font.HELVETICA, 14));
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            float xt = reader.getPageSize(i).getWidth() - 50;
            float yt = reader.getPageSize(i).getBottom(5);
            ColumnText.showTextAligned(
                    stamper.getOverContent(i), Element.ALIGN_RIGHT,
                    t, xt, yt, 0);
        }
        stamper.close();
        reader.close();
    }

    public void imprimirRelaotrio() {
        if (this.documentoPdf != null || this.documentoPdf.isOpen()) {
            this.documentoPdf.close();
        }
    }
}
