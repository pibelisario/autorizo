package caixa.beneficente.autorizo.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import caixa.beneficente.autorizo.models.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    // @Query("select i from Usuario i where i.email = :email")
    @Query("select c from Compra c where c.associado.id = :id")
    List<Compra> findByCompraId(Long id);

    @Query("SELECT c FROM Compra c WHERE dataCompra BETWEEN ?1 AND ?2 and c.associado.id = ?3")
    List<Compra> findEntradasByDataBetweenAndAssociadoEqualsId(LocalDate date1, LocalDate date2, Long id);

    List<Compra> findComprasByDataCompraBetween(LocalDate dataInicial, LocalDate dataFinal);

}
