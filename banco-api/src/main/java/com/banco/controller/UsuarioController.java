package com.banco.controller;

import com.banco.model.Conta;
import com.banco.model.Usuario;
import com.banco.repository.ContaRepository;
import com.banco.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ContaRepository contaRepository;

    // Cadastro de novo usuário com CPF, telefone e saldo inicial
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        String nome     = body.get("nome");
        String cpf      = body.get("cpf");
        String telefone = body.get("telefone");
        String saldoStr = body.getOrDefault("saldo", "0");

        if (username == null || password == null || nome == null || cpf == null) {
            return ResponseEntity.badRequest().body("Campos obrigatórios: username, password, nome, cpf");
        }

        // Verifica username duplicado
        if (usuarioRepository.findByUsername(username).isPresent()) {
            return ResponseEntity.badRequest().body("Username já cadastrado");
        }

        // Verifica CPF duplicado
        if (usuarioRepository.findByCpf(cpf).isPresent()) {
            return ResponseEntity.badRequest().body("CPF já cadastrado");
        }

        double saldo = 0;
        try { saldo = Double.parseDouble(saldoStr); } catch (Exception ignored) {}

        Usuario usuario = new Usuario(username, "{noop}" + password, saldo);
        usuario.setNome(nome);
        usuario.setCpf(cpf);
        usuario.setTelefone(telefone);
        usuarioRepository.save(usuario);

        // Cria conta inicial vinculada ao novo usuário
        Conta conta = new Conta(username, saldo);
        contaRepository.save(conta);

        return ResponseEntity.ok(Map.of(
            "mensagem", "Usuário cadastrado com sucesso!",
            "username", username,
            "contaId", conta.getId()
        ));
    }

    // Retorna dados do usuário logado
    @GetMapping("/perfil")
    public ResponseEntity<?> perfil(java.security.Principal principal) {
        if (principal == null) return ResponseEntity.status(401).build();
        return usuarioRepository.findByUsername(principal.getName())
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
