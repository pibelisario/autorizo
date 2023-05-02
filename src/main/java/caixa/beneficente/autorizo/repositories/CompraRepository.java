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

    // @Query("SELECT c FROM Compra c WHERE dataCompra BETWEEN c.dataCompra = :date1
    // AND c.dataCompra = :date2 AND c.associado.id = :id")

    // List<Compra> findByCompraIdAndDate(LocalDate date1, LocalDate date2, Long
    // id);
    // @Query("SELECT c FROM Compra c WHERE dataCompra BETWEEN c.dataCompra1 =
    // :date1 AND c.dataCompra2 = :date2 AND c.associado.id = :id")

    // @Query("SELECT c FROM Compra c WHERE dataCompra BETWEEN '2023-04-01' AND
    // '2023-04-30' AND c.associado.id = :id")
    // @Query(value = "SELECT * FROM Compra c WHERE c.dataCompra BETWEEN ?1 and ?2
    // AND c.associado.id = ?3", nativeQuery = true)
    @Query(value = "SELECT * FROM autorizo.compra WHERE data_compra BETWEEN '?1' AND '?2' and associado_id = '?3'", nativeQuery = true)
    List<Compra> findEntradasByDataBetweenAndAssociadoEqualsId(LocalDate date1, LocalDate date2, Long id);

    List<Compra> findComprasByDataCompraBetween(LocalDate dataInicial, LocalDate dataFinal);

}
