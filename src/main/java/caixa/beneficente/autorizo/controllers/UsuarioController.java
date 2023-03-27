package caixa.beneficente.autorizo.controllers;


import caixa.beneficente.autorizo.models.Usuario;
import caixa.beneficente.autorizo.services.ServiceExc;
import caixa.beneficente.autorizo.services.UsuarioService;
import caixa.beneficente.autorizo.util.Util;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;

@Controller
public class UsuarioController {

    UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("login/login");
        return mv;
    }

    @PostMapping("login")
    public ModelAndView login(@Valid Usuario usuario, BindingResult br, HttpSession session) throws NoSuchAlgorithmException, ServiceExc {
        ModelAndView mv = new ModelAndView("login/login");
        Usuario userLogin = usuarioService.loginUsuario(usuario.getUser(), Util.md5(usuario.getSenha()));
        if (userLogin == null) {
            mv.addObject("msg", "Verifique Usuário e Senha");
        } else {
            session.setAttribute("usuarioLogado", userLogin);
            mv.setViewName("associado/index");
            return mv;
        }
        return mv;
    }

    @PostMapping("logout")
    public ModelAndView logout(HttpSession session){
        session.invalidate();
        return login();
    }
}
