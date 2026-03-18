💰 Sistema Bancário - API Java
📌 Sobre o Projeto

Este projeto é um sistema bancário desenvolvido em Java utilizando Spring Boot, com foco em simular operações reais de um banco digital, como gerenciamento de contas, transações e autenticação de usuários.

O objetivo é praticar conceitos de back-end, arquitetura de software e integração com banco de dados, além de servir como projeto de portfólio.

🚀 Funcionalidades

👤 Cadastro de usuários

🔐 Autenticação com Spring Security

🏦 Criação e gerenciamento de contas bancárias

💸 Depósitos

💳 Saques

🔄 Transferências entre contas

📊 Histórico de transações

🛡️ Estrutura preparada para detecção de fraudes

🛠️ Tecnologias Utilizadas

Java 17+

Spring Boot

Spring Security

JPA / Hibernate

Banco de Dados (MySQL ou H2)

Maven

REST API

📂 Estrutura do Projeto
src/
 ├── config/        # Configurações (Security, etc)
 ├── controller/    # Endpoints da API
 ├── service/       # Regras de negócio
 ├── repository/    # Acesso ao banco de dados
 ├── model/         # Entidades do sistema
⚙️ Como Executar o Projeto
1. Clonar o repositório
git clone https://github.com/seu-usuario/seu-repositorio.git
2. Entrar na pasta
cd seu-repositorio
3. Rodar o projeto

Se estiver usando o VS Code ou IntelliJ:

Execute a classe principal (Application.java)

Ou pelo terminal:

mvn spring-boot:run
🔗 Endpoints Principais
Método	Endpoint	Descrição
POST	/auth/register	Criar usuário
POST	/auth/login	Login
GET	/contas	Listar contas
POST	/transacoes/deposito	Realizar depósito
POST	/transacoes/saque	Realizar saque
POST	/transacoes/transferencia	Transferência
📊 Exemplo de Requisição
POST /transacoes/deposito

{
  "contaId": 1,
  "valor": 500.00
}
🎯 Objetivo do Projeto

Este projeto foi desenvolvido com o intuito de:

Praticar desenvolvimento backend com Java

Entender arquitetura em camadas

Trabalhar com autenticação e segurança

Simular regras reais de um sistema bancário

📌 Melhorias Futuras

Interface web (frontend)

Dashboard com gráficos

Sistema de alertas antifraude

Deploy em nuvem (AWS, Render ou Railway)

👨‍💻 Autor

Desenvolvido por Alef

⭐ Contribuição

Sinta-se à vontade para contribuir ou dar sugestões!
