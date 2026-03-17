package com.banco.controller;

import com.banco.model.Usuario;
import com.banco.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class NavegacaoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String raiz(Principal principal) {
        return principal == null ? "redirect:/login" : "redirect:/home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro"; // templates/cadastro.html
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        if (principal == null) return "redirect:/login";
        Usuario usuario = usuarioRepository.findByUsername(principal.getName()).orElse(null);
        model.addAttribute("usuario", usuario);
        return "home";
    }
}