package Sistema;

import estrutura.Cliente;
import estrutura.Conta;
import estrutura.Persistencia;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static int proximoNumeroConta = 1001;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Carrega clientes do JSON (arquivo será criado se não existir)
        ArrayList<Cliente> clientes = Persistencia.carregarClientes("clientes.json");

        // Se estiver vazio, adiciona clientes de teste
        if (clientes.isEmpty()) {
            Cliente alef = new Cliente("Alef", "12345678900", "62999999999", "1234");
            Conta contaAlef = new Conta(proximoNumeroConta++, 500);
            alef.adicionarConta(contaAlef);

            Cliente ana = new Cliente("Ana", "11122233344", "62998887777", "4321");
            Conta contaAna = new Conta(proximoNumeroConta++, 800);
            ana.adicionarConta(contaAna);

            clientes.add(alef);
            clientes.add(ana);

            Persistencia.salvarClientes(clientes, "clientes.json");
            System.out.println("Clientes de teste criados.");
        }

        System.out.println("\n=== Banco XYZ ===");
        System.out.println("1 - Login");
        System.out.println("2 - Criar nova conta");
        System.out.print("Escolha uma opção: ");
        int opc = sc.nextInt();
        sc.nextLine();

        Cliente usuarioLogado = null;

        if (opc == 1) {
            System.out.print("CPF: ");
            String cpf = sc.nextLine();
            System.out.print("Senha: ");
            String senha = sc.nextLine();

            if (cpf.equalsIgnoreCase("admin") && senha.equals("123456")) {
                System.out.println("Bem-vindo, Gerente!");
                menuGerente(sc, clientes);
                Persistencia.salvarClientes(clientes, "clientes.json");
                sc.close();
                return;
            }

            for (Cliente c : clientes) {
                if (c.getCpf().equals(cpf) && c.getSenha().equals(senha)) {
                    usuarioLogado = c;
                    break;
                }
            }

            if (usuarioLogado == null) {
                System.out.println("CPF ou senha incorretos! Encerrando...");
                sc.close();
                return;
            } else {
                System.out.println("Bem-vindo, " + usuarioLogado.getNome() + "!");
            }

        } else if (opc == 2) {
            System.out.print("Nome completo: ");
            String nome = sc.nextLine();
            System.out.print("CPF: ");
            String cpf = sc.nextLine();
            System.out.print("Telefone: ");
            String tel = sc.nextLine();
            System.out.print("Senha: ");
            String senha = sc.nextLine();
            System.out.print("Saldo inicial: ");
            double saldo = sc.nextDouble();
            sc.nextLine();

            Conta novaConta = new Conta(proximoNumeroConta++, saldo);
            usuarioLogado = new Cliente(nome, cpf, tel, senha);
            usuarioLogado.adicionarConta(novaConta);
            clientes.add(usuarioLogado);

            Persistencia.salvarClientes(clientes, "clientes.json");
            System.out.println("Conta criada com sucesso! Número da conta: " + novaConta.getNumeroConta());
        } else {
            System.out.println("Opção inválida. Encerrando...");
            sc.close();
            return;
        }

        // Menu Cliente
        boolean sair = false;
        while (!sair) {
            System.out.println("\n=== Menu Cliente ===");
            System.out.println("1 - Ver saldo e extrato");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Transferir");
            System.out.println("5 - Criar nova conta");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");
            int escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 1 -> usuarioLogado.mostrarClienteComContas();
                case 2 -> {
                    System.out.print("Valor para depósito: ");
                    double dep = sc.nextDouble();
                    sc.nextLine();
                    for (Conta c : usuarioLogado.getContas()) c.depositar(dep);
                }
                case 3 -> {
                    System.out.print("Valor para saque: ");
                    double saq = sc.nextDouble();
                    sc.nextLine();
                    for (Conta c : usuarioLogado.getContas()) c.sacar(saq);
                }
                case 4 -> {
                    System.out.print("Número da conta destino: ");
                    int numDest = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Valor a transferir: ");
                    double valTransf = sc.nextDouble();
                    sc.nextLine();

                    Conta destino = null;
                    for (Cliente c : clientes) {
                        for (Conta conta : c.getContas()) {
                            if (conta.getNumeroConta() == numDest) {
                                destino = conta;
                                break;
                            }
                        }
                    }

                    if (destino != null) {
                        for (Conta c : usuarioLogado.getContas()) c.transferir(valTransf, destino);
                    } else System.out.println("Conta destino não encontrada!");
                }
                case 5 -> {
                    System.out.print("Saldo inicial da nova conta: ");
                    double saldoNova = sc.nextDouble();
                    sc.nextLine();
                    Conta novaConta = new Conta(proximoNumeroConta++, saldoNova);
                    usuarioLogado.adicionarConta(novaConta);
                    System.out.println("Nova conta criada com sucesso! Número da conta: " + novaConta.getNumeroConta());
                }
                case 6 -> sair = true;
                default -> System.out.println("Opção inválida!");
            }
        }

        Persistencia.salvarClientes(clientes, "clientes.json");
        System.out.println("Dados salvos com sucesso!");
        sc.close();
    }

    private static void menuGerente(Scanner sc, ArrayList<Cliente> clientes) {
        boolean sair = false;
        while (!sair) {
            System.out.println("\n=== Menu Gerente ===");
            System.out.println("1 - Ver todos os clientes e contas");
            System.out.println("2 - Criar conta para cliente existente");
            System.out.println("3 - Ver relatório do banco");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");
            int escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 1 -> clientes.forEach(Cliente::mostrarClienteComContas);
                case 2 -> {
                    System.out.print("CPF do cliente: ");
                    String cpf = sc.nextLine();
                    Cliente cli = clientes.stream().filter(c -> c.getCpf().equals(cpf)).findFirst().orElse(null);
                    if (cli == null) System.out.println("Cliente não encontrado!");
                    else {
                        System.out.print("Saldo inicial da nova conta: ");
                        double saldo = sc.nextDouble();
                        sc.nextLine();
                        Conta novaConta = new Conta(proximoNumeroConta++, saldo);
                        cli.adicionarConta(novaConta);
                        System.out.println("Nova conta criada! Número: " + novaConta.getNumeroConta());
                    }
                }
                case 3 -> {
                    double total = clientes.stream()
                            .flatMap(c -> c.getContas().stream())
                            .mapToDouble(Conta::getSaldo)
                            .sum();
                    System.out.println("Saldo total do banco: R$" + total);
                }
                case 4 -> sair = true;
                default -> System.out.println("Opção inválida!");
            }
        }
    }
}
