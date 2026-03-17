package com.banco.service;

import com.banco.model.Usuario;
import com.banco.repository.UsuarioRepository;  // ← essa linha estava faltando
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    public void login(String username, String password) {
        Usuario usuario = repository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        if (!usuario.getPassword().equals(password)) {
            throw new RuntimeException("Senha incorreta!");
        }
    }
}