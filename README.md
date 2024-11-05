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
- **Notificar** o cliente sobre o agendamento.

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
Para visualização dos dados da api no banco de dados, acessar localmente o banco H2 através do endpoint:
- **Banco H2**: http://localhost:8080/h2-console
- **Driver Class**: org.h2.Driver
- **JDBC URL**: jdbc:h2:mem:MegaByteful
- **User Name**: sa
- **Password**:

Os endpoints desenvolvidos podem ser acessados através do Swagger:
- **Swagger UI**: http://localhost:8080/swagger-ui/index.html
- Para o funcionamento correto da aplicação, existe uma ordem nas chamadas dos endpoints. Abaixo deixo a ordem com os curls das chamadas:
- Criação de um cliente:
```json
curl --location 'localhost:8080/megabyteful/customers' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Gabi",
"cpf": 87987987912,
"phone": 84999999999999,
"email": "test@gmail.com",
"appointments": []
}'
```
- Busca de um cliente por CPF:
```json
curl --location 'localhost:8080/megabyteful/customers/87987987912'
```
- Criação de um profissional:
```json
curl --location 'localhost:8080/megabyteful/servicesProviders' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Salao",
    "document": 87987987912,
    "phone": 84999999998,
    "beautyServices": "Haircut",
    "address": "rua test",
    "email": "test@gmail.com",
    "services": []
}'
```
- Busca de um profissional por document:
```json
curl --location 'localhost:8080/megabyteful/servicesProviders/87987987912'
```
- Criação de um serviço:
```json
curl --location 'localhost:8080/megabyteful/services' \
--header 'Content-Type: application/json' \
--data '{
  "name": "testing",
  "price": 15,
  "schedules": [],
  "serviceProvider": {
    "id": 1
  }
}'
```
- Busca de um serviço por id:
```json
curl --location 'localhost:8080/megabyteful/services/1'
```
- Criação de uma agenda de horários:
```json
curl --location 'localhost:8080/megabyteful/schedules?Content-Type=application%2Fjson' \
--header 'Content-Type: application/json' \
--data '{
    "serviceId": 1,
    "appointments": [],
    "serviceTime": "2024-11-10T15:00:00",
    "availableTimes": [
        "15:00",
        "15:30",
        "16:00"
    ]
}'
```
- Busca de uma agenda de horários pelo horário:
```json
curl --location 'localhost:8080/megabyteful/schedules/2024-11-10T15:00:00'
```
- Criação de um agendamento de um serviço:
```json
curl --location 'localhost:8080/megabyteful/appointments/createAppointment?cpf=87987987912' \
--header 'Content-Type: application/json' \
--data '{
    "scheduleId": 1,
    "customerId": 1,
    "serviceTime": "2024-11-10T15:00:00"
}'
```
- Busca de um agendamento de um serviço pelo id:
```json
curl --location 'localhost:8080/megabyteful/appointments/1'
```
- Notificação de lembrete de agendamento ao cliente:
```json
curl --location --request POST 'localhost:8080/megabyteful/appointments/notificationAppointment'
```
- Atualização dos dados de um cliente:
```json
curl --location --request PUT 'localhost:8080/megabyteful/customers/87987987912' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Gabis",
    "phone": "84888887777",
    "email": "testing@hotmail.com"
}'
```
- Atualização dos dados de um profissional:
```json
curl --location --request PUT 'localhost:8080/megabyteful/servicesProviders/87987987912' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "batatinha",
    "document": 87987987912,
    "phone": 84999999993,
    "beautyServices": "Haircut",
    "address": "rua test",
    "email": "testing@hotmail.com"
}'
```
- Atualização dos dados de um serviço:
```json
curl --location --request PUT 'localhost:8080/megabyteful/services/1' \
--header 'Content-Type: application/json' \
--data '{
  "name": "testing",
  "price": 20
}'
```
- Atualização de uma agenda de horários:
```json
curl --location --request PUT 'localhost:8080/megabyteful/schedules/2024-11-10T15:00:00' \
--header 'Content-Type: application/json' \
--data '{
    "serviceTime": "2024-11-10T15:00:00",
    "availableTimes": [
        "17:00",
        "17:30",
        "18:00"
    ]
}'
```
- Atualização de um agendamento:
```json
curl --location --request PUT 'localhost:8080/megabyteful/appointments/1' \
--header 'Content-Type: application/json' \
--data '{
    "scheduleId": 1,
    "customerId": 1,
    "serviceTime": "2024-12-10T16:00:00"
}'
```
- Delete de um agendamento pelo id:
```json
curl --location --request DELETE 'localhost:8080/megabyteful/appointments/1'
```
- Delete de um cliente pelo cpf:
```json
curl --location --request DELETE 'localhost:8080/megabyteful/customers/87987987912'
```
- Delete de uma agenda de horários pelo horário inicial:
```json
curl --location --request DELETE 'localhost:8080/megabyteful/schedules/2024-11-10T15:00:00'
```
- Delete de um serviço oferecido pelo id:
```json
curl --location --request DELETE 'localhost:8080/megabyteful/services/1'
```
- Delete de um profissional pelo documento:
```json
curl --location --request DELETE 'localhost:8080/megabyteful/servicesProviders/87987987912'
```

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
- Camila Marques de Lima - RM: 358903
- Eduardo Bento Nakandakare - RM: 359050
- Gabriela de Mesquita Ferraz - RM: 358745
