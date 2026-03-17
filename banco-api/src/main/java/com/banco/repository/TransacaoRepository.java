package com.banco.repository;

import com.banco.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    // Busca todas as transações de uma conta específica
    List<Transacao> findByContaId(Long contaId);
}
