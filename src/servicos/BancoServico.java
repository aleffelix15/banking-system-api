package servicos;

import estrutura.Cliente;
import estrutura.Conta;
import java.util.List;

public class BancoServico {

    public static void mostrarClientes(List<Cliente> clientes) {
        System.out.println("=== Lista de clientes ===");
        if(clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        for(Cliente c : clientes) {
            System.out.println("Cliente: " + c.getNome() + " | CPF: " + c.getCpf());
            for(Conta conta : c.getContas()) {
                System.out.println("  Conta #" + conta.getNumeroConta() + " | Saldo: R$ " + conta.getSaldo());
            }
        }
    }

    public static Conta selecionarConta(Cliente cliente, int numeroConta) {
        for(Conta c : cliente.getContas()) {
            if(c.getNumeroConta() == numeroConta) return c;
        }
        return null;
    }
}
