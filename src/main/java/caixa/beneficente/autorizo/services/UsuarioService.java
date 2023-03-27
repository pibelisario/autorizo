package caixa.beneficente.autorizo.services;

import caixa.beneficente.autorizo.models.Usuario;
import caixa.beneficente.autorizo.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario loginUsuario(String user, String senha){
        Usuario userLogin = usuarioRepository.findUser(user, senha);
        return userLogin;
    }
}
