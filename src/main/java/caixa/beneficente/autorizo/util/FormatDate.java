package caixa.beneficente.autorizo.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatDate {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public String formatarData(LocalDate localDate) {
        return formatter.format(localDate);
    }

}
