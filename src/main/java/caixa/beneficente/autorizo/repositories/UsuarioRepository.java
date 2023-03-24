package caixa.beneficente.autorizo.repositories;


import caixa.beneficente.autorizo.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findUser(String user, String senha);
}
