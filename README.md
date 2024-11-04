# Customer Management API
Este é um projeto da Especialização em Arquitetura e Desenvolvimento Java da FIAP. 
Uma API REST para gerenciar agendamento de serviços de salão de beleza. 
A aplicação foi desenvolvida em Java 17, utilizando Spring Boot, maven, 
um banco de dados H2 para testes e geração de documento através do Swagger.

## Descrição do Projeto
O objetivo desta API é fornecer uma interface para gerenciar clientes, fornecedores de serviços 
e serviços em um sistema de CRM (Customer Relationship Management), permitindo realizar operações 
de CRUD (Create, Read, Update, Delete) e validações sobre os dados dos clientes, dos fornecedores, 
dos serviços e do agendamento do serviço.

## Funcionalidades
A API permite:
- **Criar** um novo cliente, garantindo que o CPF seja único.
- **Criar** um novo profissional, garantindo que o CNPJ seja único.
- **Criar** um novo serviço, garantindo que estará vinculado a um profissional.
- **Criar** um agendamento de serviço, garantindo que estará vinculado a serviço eum cliente.
- **Buscar** clientes por CPF.
- **Buscar** profissional por CNPJ.
- **Buscar** serviço por id.
- **Buscar** agendamento de serviço por id.
- **Atualizar** as informações de um cliente.
- **Atualizar** as informações de um profissional.
- **Atualizar** as informações de um serviço.
- **Atualizar** as informações de um agendamento.
- **Excluir** um cliente do sistema.
- **Excluir** um profissional do sistema.
- **Excluir** um serviço do sistema.
- **Excluir** um agendamento de serviço do sistema.
- **Validar** campos como nome, CPF, CNPJ, telefone e e-mail.

## Tecnologias Utilizadas
- **Java 17**
- **Spring Boot**
- **Maven** para gerenciamento de dependências
- **Banco de Dados H2** para armazenamento e testes
- **Mockito** e **JUnit 5** para testes unitários
- **Lombok** para reduzir o código boilerplate
- **Swagger** para documentação da API

## Estrutura do Projeto
O projeto está organizado nas seguintes camadas:
- `domain`: Define as entidades principais do domínio.
- `gateway`: Interfaces e implementações para interação com o banco de dados.
- `usecase`: Contém os casos de uso com a lógica de negócios.
- `infrastructure.persistence.entity`: Representa as entidades de persistência do banco de dados.
- `infrastructure.persistence.repository`: Interfaces dos repositórios Spring Data JPA.
- `usecase.exception`: Exceções customizadas utilizadas nos casos de uso.

## Pré-requisitos
- Java 17
- Maven 3.6+
- IDE como IntelliJ IDEA ou Eclipse

## Configuração e Execução
1. **Clone o repositório**:
   ```bash
   url do repositório: https://github.com/GabiFerraz/Project-MegaByteful
   git clone git@github.com:GabiFerraz/Project-MegaByteful.git
   ```

2. **Instale as dependências:**
   ```bash
   mvn clean install
   ```

3. **Execute o projeto:**
   ```bash
   mvn spring-boot:run
   ```

## Uso da API
Os endpoints desenvolvidos podem ser acessados através do Swagger:
- **Swagger UI**: http://localhost:8080/swagger-ui/index.html

## Testes
Para executar os testes unitários:
   ```bash
   mvn test
   ```
O projeto inclui testes unitários para os principais casos de uso, utilizando Mockito 
para mockar dependências e ArgumentCaptor para verificar os valores dos parâmetros nos 
métodos chamados.

## Desenvolvedores:
- Bruna Casagrande Zaramela - RM: 359536
- Camila Marques de Lima
- Eduardo Bento Nakandakare
- Gabriela de Mesquita Ferraz - RM: 358745
