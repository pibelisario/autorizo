package caixa.beneficente.autorizo.models;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double valor;
    private LocalDate dataCompra = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "associado_id")
    private Associado associado;
}
