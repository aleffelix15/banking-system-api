package com.banco.controller;

import com.banco.model.Conta;
import com.banco.model.Transacao;
import com.banco.model.Transferencia;
import com.banco.service.ContaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    // Retorna apenas as contas do usuário logado (usa a sessão Spring Security)
    @GetMapping
    public ResponseEntity<List<Conta>> listar(Principal principal) {
        if (principal == null) return ResponseEntity.status(401).build();
        return ResponseEntity.ok(contaService.listarPorTitular(principal.getName()));
    }

    @PostMapping
    public ResponseEntity<Conta> criar(@RequestBody Conta conta) {
        return ResponseEntity.ok(contaService.salvar(conta));
    }

    @PostMapping("/{id}/depositar")
    public ResponseEntity<?> depositar(@PathVariable Long id, @RequestParam double valor) {
        try {
            return ResponseEntity.ok(contaService.depositar(id, valor));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/sacar")
    public ResponseEntity<?> sacar(@PathVariable Long id, @RequestParam double valor) {
        try {
            return ResponseEntity.ok(contaService.sacar(id, valor));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/transferir")
    public ResponseEntity<?> transferir(@RequestBody Transferencia t) {
        try {
            contaService.transferir(t.getContaOrigem(), t.getContaDestino(), t.getValor());
            return ResponseEntity.ok("Transferência realizada com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/transacoes")
    public ResponseEntity<?> transacoes(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(contaService.listarTransacoes(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}