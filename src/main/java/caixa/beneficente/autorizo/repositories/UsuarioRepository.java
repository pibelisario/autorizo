package caixa.beneficente.autorizo.repositories;


import caixa.beneficente.autorizo.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("select u from Usuario u where u.user = :user and u.senha = :senha")
    Usuario findUser(String user, String senha);
}
