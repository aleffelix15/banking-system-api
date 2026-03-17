package com.banco.service;

import com.banco.model.Conta;
import com.banco.model.TipoTransacao;
import com.banco.model.Transacao;
import com.banco.repository.ContaRepository;
import com.banco.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final TransacaoRepository transacaoRepository;
    private final FraudeService fraudeService;

    public ContaService(ContaRepository contaRepository,
                        TransacaoRepository transacaoRepository,
                        FraudeService fraudeService) {
        this.contaRepository     = contaRepository;
        this.transacaoRepository = transacaoRepository;
        this.fraudeService       = fraudeService;
    }

    public Conta salvar(Conta conta) {
        return contaRepository.save(conta);
    }

    public List<Conta> listar() {
        return contaRepository.findAll();
    }

    // Retorna apenas as contas do usuário logado
    public List<Conta> listarPorTitular(String username) {
        return contaRepository.findByTitular(username);
    }

    public Conta depositar(Long id, double valor) {
        if (valor <= 0) throw new RuntimeException("Valor inválido");
        Conta conta = buscarConta(id);
        conta.setSaldo(conta.getSaldo() + valor);
        contaRepository.save(conta);
        transacaoRepository.save(new Transacao(valor, TipoTransacao.DEPOSITO, conta));
        return conta;
    }

    public Conta sacar(Long id, double valor) {
        if (valor <= 0) throw new RuntimeException("Valor inválido");
        Conta conta = buscarConta(id);
        if (conta.getSaldo() < valor) throw new RuntimeException("Saldo insuficiente");
        conta.setSaldo(conta.getSaldo() - valor);
        contaRepository.save(conta);
        transacaoRepository.save(new Transacao(valor, TipoTransacao.SAQUE, conta));
        return conta;
    }

    public void transferir(Long origemId, Long destinoId, double valor) {
        if (valor <= 0) throw new RuntimeException("Valor inválido");
        fraudeService.verificarTransferencia(valor);

        Conta origem  = buscarConta(origemId);
        Conta destino = buscarConta(destinoId);

        if (origemId.equals(destinoId)) throw new RuntimeException("Conta de origem e destino são iguais");
        if (origem.getSaldo() < valor)  throw new RuntimeException("Saldo insuficiente");

        origem.setSaldo(origem.getSaldo() - valor);
        destino.setSaldo(destino.getSaldo() + valor);

        contaRepository.save(origem);
        contaRepository.save(destino);

        // Registra para origem: "Transferência de R$X para conta #Y"
        transacaoRepository.save(new Transacao(valor, TipoTransacao.TRANSFERENCIA, origem, destino));
        // Registra para destino: "Transferência recebida de conta #X"
        Transacao recebida = new Transacao(valor, TipoTransacao.TRANSFERENCIA, destino, origem);
        transacaoRepository.save(recebida);
    }

    public List<Transacao> listarTransacoes(Long contaId) {
        buscarConta(contaId); // valida existência
        return transacaoRepository.findByContaId(contaId);
    }

    private Conta buscarConta(Long id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada: " + id));
    }
}