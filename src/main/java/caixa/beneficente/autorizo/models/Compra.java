package caixa.beneficente.autorizo.models;

import java.time.LocalDate;

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

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "associado_id")
    private Associado associado;

    @Override
    public String toString() {
        return "Compra [valor=" + valor + ", dataCompra=" + dataCompra+ "]";
    }


}
