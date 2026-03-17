package com.banco.controller;

import com.banco.model.Usuario;
import com.banco.security.JwtUtil;
import com.banco.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService service;

    public AuthController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(service.salvar(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        service.login(usuario.getUsername(), usuario.getPassword());
        return ResponseEntity.ok(JwtUtil.generateToken(usuario.getUsername()));
    }
}