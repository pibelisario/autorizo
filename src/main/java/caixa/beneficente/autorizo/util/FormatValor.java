package caixa.beneficente.autorizo.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class FormatValor {

    DecimalFormat df = new DecimalFormat("###.##");

    public String formatValor(Double valor) {
        df.setRoundingMode(RoundingMode.UP);
        return df.format(valor);
    }

}
