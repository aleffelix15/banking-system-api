package estrutura;

import java.util.ArrayList;

public class Cliente {

    private String nome;
    private String cpf;
    private String telefone;
    private String senha;
    private ArrayList<Conta> contas;

    public Cliente(String nome, String cpf, String telefone, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.senha = senha;
        this.contas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

    public ArrayList<Conta> getContas() {
        return contas;
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public void mostrarCliente() {
        System.out.println("Cliente: " + nome + " | CPF: " + cpf + " | Tel: " + telefone);
    }

    public void mostrarContas() {
        for (Conta c : contas) {
            System.out.println("  Conta #" + c.getNumeroConta() + " | Saldo: R$ " + c.getSaldo());
        }
    }

    // Novo método: mostra cliente e todas as contas + histórico
    public void mostrarClienteComContas() {
        mostrarCliente();
        mostrarContas();
        for (Conta c : contas) {
            c.mostrarHistorico();
        }
    }
}
