package com.banco.repository;

import com.banco.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    // Busca todas as contas de um usuário pelo username (campo "titular")
    List<Conta> findByTitular(String titular);
}
