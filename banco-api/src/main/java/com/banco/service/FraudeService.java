package com.banco.service;

import com.banco.security.FraudeException;
import org.springframework.stereotype.Service;

@Service
public class FraudeService {

    public void verificarTransferencia(double valor) {
        if (valor > 10000) {
            throw new FraudeException("Transferência bloqueada por suspeita de fraude (limite: R$ 10.000)");
        }
    }
}