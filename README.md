# :star2: Medical System - Sistema de Agendamento de Consultas

Este projeto é um sistema de agendamento de consultas desenvolvido com Spring Boot. Ele permite que os usuários agendem consultas com médicos, visualizem horários disponíveis e recebam lembretes. O sistema é dividido em camadas seguindo os princípios da Clean Architecture e utiliza boas práticas como SOLID e Spring Security.
## Funcionalidades

- Agendamento de Consultas:
  - Os usuários podem agendar consultas com médicos.
- Gestão de Médicos:
  - Cadastro, atualização e exclusão de médicos.
  - Listagem de médicos por especialidade.
- Gestão de Pacientes:
  - Cadastro, atualização e exclusão de pacientes.
- Autenticação e Autorização:
  - Proteção dos endpoints com Spring Security.
  - Autenticação básica (usuário e senha).

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- H2 Database: Banco de dados em memória para teste.
- PostgreSQL: Banco de dados relacional para desenvolvimento.
- Maven: Gerenciamento de dependências.

## Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

- Java 17: Download JDK 17
- Maven: Instalação do Maven
- PostgreSQL (opcional para Desenvolvimento): Download PostgreSQL

## Estrutura do Projeto

O projeto segue a Clean Architecture, dividido em camadas:

   ```
    src/main/java/com/guilhermeonizio/AgendamentoConsultas/
    ├── config/              # Configurações do Spring (Security, Beans, etc.)
    ├── domain/              # Entidades do domínio (Consulta, Medico, Paciente)
    ├── persistence/         # Repositórios (Spring Data JPA)
    ├── business/            # Serviços (lógica de negócios)
    ├── presentation/        # Controladores (endpoints da API)
    └── AgendamentoApplication.java # Classe principal do Spring Boot
   ```

## Como Executar o Projeto

Pré-requisitos
- Java 17 (ou superior)
- Maven (para gerenciamento de dependências)
- PostgreSQL (banco de dados)

Passo 1: Configurar o Banco de Dados

1. Crie um banco de dados no PostgreSQL chamado agendamento_db.

2. Atualize as configurações no arquivo application.properties:

   ```application-properties
    spring.profiles.active=${APP_PROFILE:dev}
    spring.jpa.open-in-view=false
   ```

   ```application-test-properties
    spring.datasource.url=jdbc:h2:mem:agendamento_db
    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.username=sa
    spring.datasource.password=
    spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
    
    spring.h2.console.enabled=true
   ```

   ```application-dev-properties
    # Configurações do PostgreSQL
    spring.datasource.url=jdbc:postgresql://localhost:5432/agendamento_db
    spring.datasource.username=postgres
    spring.datasource.password=yourpassword
    spring.datasource.driver-class-name=org.postgresql.Driver
    
    # Configurações do Hibernate
    spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
    spring.jpa.hibernate.ddl-auto=update
   ```

Passo 2: Executar o Projeto

1. Clone o repositório:
   
   ```bash
    git clone https://github.com/seu-usuario/agendamento-consultas.git
   ```
   
2. Navegue até o diretório do projeto:

   ```bash
    cd agendamento-consultas
   ```

3. Compile e execute o projeto:

   ```bash
    mvn clean install
    mvn spring-boot:run
   ```

Passo 3: Acessar a Aplicação

- A aplicação estará disponível em: http://localhost:8080.


## Endpoints da API

### /consultas
  - GET /consultas → Lista todas as consultas.
  - POST /consultas → Agenda uma nova consulta.
  - DELETE /consultas/{id} → Cancela uma consulta.

### /medicos
  - GET /medicos → Lista todos os médicos.
  - POST /medicos → Cadastra um novo médico.
  - GET /medicos/especialidade?especialidade=Cardiologia → Lista médicos por especialidade.

### /pacientes
  - GET /pacientes → Lista todos os pacientes.
  - POST /pacientes → Cadastra um novo paciente.
  - PUT /pacientes/{id} → Atualiza um paciente.


## Autenticação

O sistema usa Spring Security para proteger os endpoints. Para acessar endpoints protegidos, use as seguintes credenciais:

Basic Auth
  - Usuário: user
  - Senha: password

## Fim

Muito obrigado por ler até aqui! Te vejo no meu próximo projeto s2
