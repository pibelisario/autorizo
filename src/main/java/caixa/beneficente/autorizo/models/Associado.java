package caixa.beneficente.autorizo.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Associado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rg;
    private String nome;
    private String cpf;
    private String cidade;
    private String situacao;
    private Double limite;

    @OneToMany(mappedBy = "associado")
    private List<Compra> compras = new ArrayList<Compra>();
}
