package caixa.beneficente.autorizo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import caixa.beneficente.autorizo.models.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

}
