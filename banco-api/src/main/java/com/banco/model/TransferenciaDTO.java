package com.banco.model;

public class TransferenciaDTO {

    private Long contaOrigem;
    private Long contaDestino;
    private double valor;

    public Long getContaOrigem()        { return contaOrigem; }
    public void setContaOrigem(Long v)  { this.contaOrigem = v; }
    public Long getContaDestino()       { return contaDestino; }
    public void setContaDestino(Long v) { this.contaDestino = v; }
    public double getValor()            { return valor; }
    public void setValor(double valor)  { this.valor = valor; }
}
