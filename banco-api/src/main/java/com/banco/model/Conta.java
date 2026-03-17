package com.banco.model;

import jakarta.persistence.*;

@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // "titular" armazena o username do dono — usado para filtrar contas por usuário logado
    private String titular;
    private double saldo;

    public Conta() {}

    public Conta(String titular, double saldo) {
        this.titular = titular;
        this.saldo   = saldo;
    }

    public Long getId()                  { return id; }
    public String getTitular()           { return titular; }
    public void setTitular(String t)     { this.titular = t; }
    public double getSaldo()             { return saldo; }
    public void setSaldo(double saldo)   { this.saldo = saldo; }
}