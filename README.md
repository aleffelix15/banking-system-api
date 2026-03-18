# 💰 Sistema Bancário - API Java

![Java](https://img.shields.io/badge/Java-17+-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green?style=flat-square&logo=springboot)
![Spring Security](https://img.shields.io/badge/Spring_Security-✓-brightgreen?style=flat-square&logo=springsecurity)
![MySQL](https://img.shields.io/badge/MySQL-✓-blue?style=flat-square&logo=mysql)
![Status](https://img.shields.io/badge/Status-Em_desenvolvimento-yellow?style=flat-square)

## 📌 Sobre o Projeto

Este projeto é um sistema bancário desenvolvido em Java utilizando Spring Boot, com foco em simular operações reais de um banco digital, como gerenciamento de contas, transações e autenticação de usuários.

O objetivo é praticar conceitos de back-end, arquitetura de software e integração com banco de dados, além de servir como projeto de portfólio.

---

## 🚀 Funcionalidades

- 👤 Cadastro de usuários
- 🔐 Autenticação com Spring Security
- 🏦 Criação e gerenciamento de contas bancárias
- 💸 Depósitos
- 💳 Saques
- 🔄 Transferências entre contas
- 📊 Histórico de transações
- 🛡️ Estrutura preparada para detecção de fraudes

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão |
|---|---|
| Java | 17+ |
| Spring Boot | 3.x |
| Spring Security | - |
| JPA / Hibernate | - |
| MySQL / H2 | - |
| Maven | - |
| REST API | - |

---

## 📂 Estrutura do Projeto

```
src/
 ├── config/        # Configurações (Security, etc)
 ├── controller/    # Endpoints da API
 ├── service/       # Regras de negócio
 ├── repository/    # Acesso ao banco de dados
 ├── model/         # Entidades do sistema
```

---

## ⚙️ Como Executar o Projeto

### Pré-requisitos

- Java 17+
- Maven
- MySQL (ou usar H2 em memória para testes)

### Passo a passo

**1. Clonar o repositório**
```bash
git clone https://github.com/aleffelix15/banking-system-api.git
```

**2. Entrar na pasta**
```bash
cd banking-system-api
```

**3. Configurar o banco de dados**

Edite o arquivo `src/main/resources/application.properties` com suas credenciais:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/banking_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

**4. Rodar o projeto**

Pela IDE (IntelliJ ou VS Code), execute a classe `Application.java`, ou pelo terminal:
```bash
mvn spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

---

## 🔗 Endpoints Principais

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/auth/register` | Criar usuário |
| POST | `/auth/login` | Login |
| GET | `/contas` | Listar contas |
| POST | `/transacoes/deposito` | Realizar depósito |
| POST | `/transacoes/saque` | Realizar saque |
| POST | `/transacoes/transferencia` | Transferência entre contas |

---

## 📊 Exemplo de Requisição

```json
POST /transacoes/deposito

{
  "contaId": 1,
  "valor": 500.00
}
```

---

## 🎯 Objetivo do Projeto

Este projeto foi desenvolvido com o intuito de:

- Praticar desenvolvimento backend com Java
- Entender arquitetura em camadas
- Trabalhar com autenticação e segurança
- Simular regras reais de um sistema bancário

---

## 📌 Melhorias Futuras

- [ ] Interface web (frontend)
- [ ] Dashboard com gráficos
- [ ] Sistema de alertas antifraude
- [ ] Deploy em nuvem (AWS, Render ou Railway)

---

## 👨‍💻 Autor

Desenvolvido por **Alef Felix Teixeira**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Alef_Felix-blue?style=flat-square&logo=linkedin)](https://linkedin.com/in/alef-felix-teixeira-a5030b236)
[![GitHub](https://img.shields.io/badge/GitHub-aleffelix15-black?style=flat-square&logo=github)](https://github.com/aleffelix15)
[![Gmail](https://img.shields.io/badge/Gmail-aleffelix81@gmail.com-red?style=flat-square&logo=gmail)](mailto:aleffelix81@gmail.com)

---

## ⭐ Contribuição

Sinta-se à vontade para abrir uma *issue* ou enviar um *pull request* com sugestões e melhorias!

---

<p align="center">Feito com ☕ e Java</p>
