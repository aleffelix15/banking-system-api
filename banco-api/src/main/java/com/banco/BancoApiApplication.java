package com.banco;

import com.banco.model.Conta;
import com.banco.model.Usuario;
import com.banco.repository.ContaRepository;
import com.banco.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BancoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BancoApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(UsuarioRepository usuarioRepository,
                                  ContaRepository contaRepository) {
        return args -> {
            if (usuarioRepository.findByUsername("alef").isEmpty()) {
                // Cria usuário de teste
                Usuario alef = new Usuario("alef", "{noop}123", 0.0);
                alef.setNome("Alef");
                alef.setCpf("12345678900");
                alef.setTelefone("62999999999");
                usuarioRepository.save(alef);

                // Cria conta bancária vinculada ao mesmo titular
                Conta conta = new Conta("alef", 1250.0);
                contaRepository.save(conta);

                System.out.println("===========================================");
                System.out.println("  USUÁRIO CRIADO → login: alef  senha: 123");
                System.out.println("  CONTA   CRIADA → ID: " + conta.getId() + "  saldo: R$ 1.250,00");
                System.out.println("===========================================");
            }
        };
    }
}