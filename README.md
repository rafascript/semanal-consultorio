# Atividade 01 referente ao projeto mensal intitulado: "ORIENTAÇÃO A OBJETOS E BANCO DE DADOS"

Sistema de gerenciamento de consultório médico desenvolvido em Spring Boot com interface CLI (Command Line Interface).

## Funcionalidades

### 📋 Gerenciamento de Endereços
- Criar novo endereço
- Editar endereço existente
- Listar todos os endereços
- Buscar endereço por CEP
- Remover endereço

### 👥 Gerenciamento de Pacientes
- Cadastrar novo paciente
- Editar dados do paciente
- Vincular/desvincular endereço ao paciente
- Listar todos os pacientes
- Buscar paciente por ID
- Remover paciente

### 📅 Gerenciamento de Consultas
- Agendar nova consulta
- Editar consulta
- Vincular paciente à consulta
- Reagendar consulta
- Confirmar consulta
- Cancelar consulta
- Listar todas as consultas
- Buscar consulta por ID
- Remover consulta

#### Status de Consultas
- `AGENDADA` - Consulta recém-criada ou reagendada
- `CONFIRMADA` - Consulta confirmada pelo paciente
- `CANCELADA` - Consulta cancelada
- `REALIZADA` - Consulta já realizada

## Tecnologias

- Java 17+
- Spring Boot 3.5.6
- Spring Data JPA
- PostgreSQL 15
- Hibernate
- Lombok
- Docker

## Banco de Dados PostgreSQL - Docker

Este projeto utiliza PostgreSQL para persistência de dados, rodando dentro de um container Docker.

### Configuração do Container

Execute o seguinte comando no PowerShell:

```powershell
docker run -d `
  --name pg-default `
  -e POSTGRES_USER=default `
  -e POSTGRES_PASSWORD=default `
  -e POSTGRES_DB=pg-default `
  -p 5432:5432 `
  -v pgdata:/var/lib/postgresql/data `
  --restart unless-stopped `
  postgres:15
```

**Explicação dos parâmetros:**
- `--name pg-default` → Nome do container
- `-e POSTGRES_USER=default` → Usuário do banco
- `-e POSTGRES_PASSWORD=default` → Senha do usuário
- `-e POSTGRES_DB=pg-default` → Nome do banco criado automaticamente
- `-p 5432:5432` → Mapeia a porta padrão do PostgreSQL para o host
- `-v pgdata:/var/lib/postgresql/data` → Volume persistente para não perder dados
- `--restart unless-stopped` → Reinicia automaticamente o container
- `postgres:15` → Imagem oficial do PostgreSQL versão 15



## Configuração do Projeto

### application.properties

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/pg-default
spring.datasource.username=default
spring.datasource.password=default
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

## Como Executar

1. Certifique-se de que o Docker está instalado e rodando
2. Execute o comando de criação do container PostgreSQL
3. Clone o repositório
4. Configure o `application.properties` com as credenciais do banco
5. Execute o projeto


## Estrutura do Projeto

```
src/main/java/com/projeto/consultoriomedico/
├── models/
│   ├── Endereco.java
│   ├── Paciente.java
│   ├── Consulta.java
│   └── StatusConsulta.java
├── repository/
│   ├── EnderecoRepository.java
│   ├── PacienteRepository.java
│   └── ConsultaRepository.java
├── service/
│   ├── EnderecoService.java
│   ├── PacienteService.java
│   └── ConsultaService.java
└── ConsultorioMedicoApplication.java
```

## Modelo de Dados

### Endereco
- `id` (UUID)
- `estado` (String)
- `cidade` (String)
- `rua` (String)
- `numero` (String)
- `cep` (String)

### Paciente
- `id` (UUID)
- `nome` (String)
- `cpf` (String)
- `email` (String)
- `telefone` (String)
- `endereco` (Endereco) - Relacionamento OneToOne

### Consulta
- `id` (UUID)
- `agendamento` (LocalDateTime)
- `status` (StatusConsulta)
- `paciente` (Paciente) - Relacionamento ManyToOne

## Uso da Interface CLI

Ao executar a aplicação, você verá o menu principal:

```
1 - Pacientes
2 - Consultas
3 - Enderecos
4 - Sair
Digite a opcao:
```

Navegue pelos menus usando os números das opções.

### Formato de Data para Consultas
Use o formato: `dd/MM/yyyy HH:mm`

Exemplo: `15/10/2025 14:30`

## Observações Importantes

- Os relacionamentos entre Paciente e Endereco não utilizam cascade para evitar problemas com entidades detached
- Ao vincular um endereço existente a um paciente, apenas a referência é criada
- É possível criar um novo endereço diretamente ao cadastrar um paciente
- Consultas podem existir sem paciente vinculado


