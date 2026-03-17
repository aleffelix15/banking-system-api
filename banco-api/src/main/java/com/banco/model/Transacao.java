package com.banco.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double valor;

    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo;

    private LocalDateTime data;

    // Descrição legível igual ao projeto console: "Depósito de R$200.0 em 15/03/2026 17:30"
    private String descricao;

    @ManyToOne
    private Conta conta;

    public Transacao() {}

    public Transacao(double valor, TipoTransacao tipo, Conta conta) {
        this.valor = valor;
        this.tipo  = tipo;
        this.conta = conta;
        this.data  = LocalDateTime.now();
        this.descricao = gerarDescricao(tipo, valor, conta);
    }

    // Construtor com conta destino para transferências
    public Transacao(double valor, TipoTransacao tipo, Conta conta, Conta contaRelacionada) {
        this.valor = valor;
        this.tipo  = tipo;
        this.conta = conta;
        this.data  = LocalDateTime.now();
        if (tipo == TipoTransacao.TRANSFERENCIA && contaRelacionada != null) {
            this.descricao = "Transferência de R$" + String.format("%.2f", valor)
                    + " para conta #" + contaRelacionada.getId();
        } else {
            this.descricao = gerarDescricao(tipo, valor, conta);
        }
    }

    private String gerarDescricao(TipoTransacao tipo, double valor, Conta conta) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataStr = fmt.format(LocalDateTime.now());
        return switch (tipo) {
            case DEPOSITO      -> "Depósito de R$" + String.format("%.2f", valor) + " em " + dataStr;
            case SAQUE         -> "Saque de R$" + String.format("%.2f", valor) + " em " + dataStr;
            case TRANSFERENCIA -> "Transferência de R$" + String.format("%.2f", valor) + " em " + dataStr;
        };
    }

    public Long getId()            { return id; }
    public double getValor()       { return valor; }
    public TipoTransacao getTipo() { return tipo; }
    public LocalDateTime getData() { return data; }
    public Conta getConta()        { return conta; }
    public String getDescricao()   { return descricao; }
}