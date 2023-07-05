package caixa.beneficente.autorizo.models;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;

import caixa.beneficente.autorizo.util.FormatDate;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double valor;
    private LocalDate dataCompra = LocalDate.now();
    private Long NumeroCupom;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "associado_id")
    private Associado associado;

    @Override
    public String toString() {
        return "Data: " + new FormatDate().formatarData(dataCompra) + " Valor: "
                + NumberFormat.getCurrencyInstance().format(valor);
    }

}
