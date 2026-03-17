package estrutura;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Teste do Banco XYZ ===\n");

        // Criando clientes com senha
        Cliente alef = new Cliente("Alef", "12345678900", "62999999999", "1234");
        Cliente ana = new Cliente("Ana", "11122233344", "62998887777", "abcd");

        // Criando contas
        Conta contaAlef1 = new Conta(1001, 500);
        Conta contaAlef2 = new Conta(1002, 1000);
        Conta contaAna = new Conta(1003, 800);

        // Associando contas aos clientes
        alef.adicionarConta(contaAlef1);
        alef.adicionarConta(contaAlef2);
        ana.adicionarConta(contaAna);

        // Depósitos e saques
        contaAlef1.depositar(200);
        contaAlef1.sacar(100);

        contaAlef2.depositar(500);
        contaAlef2.sacar(200);

        contaAna.depositar(300);
        contaAna.sacar(100);

        // Transferência
        System.out.println("\n--- Transferência ---");
        contaAlef1.transferir(150, contaAna);

        // Mostrando dados e históricos
        mostrarClienteComContas(alef);
        mostrarClienteComContas(ana);
    }

    // Método auxiliar para mostrar cliente, contas e histórico
    private static void mostrarClienteComContas(Cliente cliente) {
        cliente.mostrarCliente();
        cliente.mostrarContas();
        for (Conta c : cliente.getContas()) {
            c.mostrarHistorico();
        }
    }
}

