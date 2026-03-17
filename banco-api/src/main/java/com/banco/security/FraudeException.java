package com.banco.security;

public class FraudeException extends RuntimeException {
    public FraudeException(String mensagem) {
        super(mensagem);
    }
}