package caixa.beneficente.autorizo.repositories;

import caixa.beneficente.autorizo.models.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {
    List<Associado> findByRg(String rg);
    @Query("select a from Associado a where a.nome like %?1%")
    List<Associado> findByNome(String nome);
    @Query("select a from Associado a where a.cpf like %?1%")
    List<Associado> findByCpf(String cpf);
}
