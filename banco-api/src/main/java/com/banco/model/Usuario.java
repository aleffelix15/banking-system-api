package com.banco.model;

import jakarta.persistence.*;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String nome;
    private String cpf;
    private String telefone;
    private double saldo;

    public Usuario() {}

    public Usuario(String username, String password, double saldo) {
        this.username = username;
        this.password = password;
        this.saldo    = saldo;
    }

    public Long getId()                    { return id; }
    public String getUsername()            { return username; }
    public void setUsername(String u)      { this.username = u; }
    public String getPassword()            { return password; }
    public void setPassword(String p)      { this.password = p; }
    public String getNome()                { return nome; }
    public void setNome(String nome)       { this.nome = nome; }
    public String getCpf()                 { return cpf; }
    public void setCpf(String cpf)         { this.cpf = cpf; }
    public String getTelefone()            { return telefone; }
    public void setTelefone(String t)      { this.telefone = t; }
    public double getSaldo()               { return saldo; }
    public void setSaldo(double saldo)     { this.saldo = saldo; }
}