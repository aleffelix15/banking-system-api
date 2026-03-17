package estrutura;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Conta {

    private int numeroConta;
    private double saldo;
    private ArrayList<String> historico;

    public Conta(int numeroConta, double saldoInicial) {
        this.numeroConta = numeroConta;
        this.saldo = saldoInicial;
        this.historico = new ArrayList<>();
        historico.add("Conta criada com saldo: R$" + saldoInicial + " em " + LocalDateTime.now());
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public ArrayList<String> getHistorico() {
        return historico;
    }

    public void depositar(double valor) {
        saldo += valor;
        historico.add("Depósito de R$" + valor + " em " + LocalDateTime.now());
    }

    public void sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            historico.add("Saque de R$" + valor + " em " + LocalDateTime.now());
        } else {
            historico.add("Tentativa de saque de R$" + valor + " sem saldo suficiente em " + LocalDateTime.now());
        }
    }

    public void transferir(double valor, Conta destino) {
        if (valor <= saldo) {
            saldo -= valor;
            destino.depositar(valor);
            historico.add("Transferência de R$" + valor + " para conta #" + destino.getNumeroConta() + " em " + LocalDateTime.now());
        } else {
            historico.add("Tentativa de transferência de R$" + valor + " sem saldo suficiente em " + LocalDateTime.now());
        }
    }

    public String getHistoricoString() {
        return String.join("\n", historico);
    }

    public void mostrarHistorico() {
        System.out.println("=== Histórico Conta #" + numeroConta + " ===");
        for (String h : historico) System.out.println(h);
    }
}
