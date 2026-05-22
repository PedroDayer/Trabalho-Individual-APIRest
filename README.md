# 🚗 API de Gestão de Clientes e Veículos

API REST desenvolvida com Spring Boot para gerenciamento completo de clientes e veículos de uma concessionária. O sistema permite o cadastro de clientes, vinculação de veículos, controle de vendas e consultas personalizadas.

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Tecnologias](#-tecnologias)
- [Requisitos](#-requisitos)
- [Instalação](#-instalação)
- [Configuração](#-configuração)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Endpoints](#-endpoints)
- [Modelos de Dados](#-modelos-de-dados)
- [Validações](#-validações)
- [Regras de Negócio](#-regras-de-negócio)
- [Tratamento de Erros](#️-tratamento-de-erros)
- [Exemplos de Uso](#-exemplos-de-uso)

## 📖 Sobre o Projeto

Sistema de gerenciamento para concessionárias que oferece:

- **Gestão de Clientes**: Cadastro, consulta e remoção de clientes
- **Gestão de Veículos**: CRUD completo com controle de estoque e vendas
- **Vinculação**: Relacionamento entre clientes e veículos
- **Validações**: Verificações de dados duplicados (CPF, email, placa)
- **Controle de Vendas**: Gerenciamento de status de veículos vendidos
- **Consultas Personalizadas**: Busca por consultas diferenciadas 

## 🚀 Tecnologias

### Core
- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA** - Persistência de dados
- **PostgreSQL** - Banco de dados relacional
- **Maven** - Gerenciamento de dependências

### Bibliotecas
- **Lombok** - Redução de código boilerplate
- **Jakarta Validation** - Validação de dados
- **Hibernate** - ORM

## 📦 Requisitos

- Java JDK 17 ou superior
- PostgreSQL 12 ou superior
- Maven 3.6 ou superior
- IDE (IntelliJ IDEA, Eclipse, VS Code)

## 🔧 Instalação

### 1. Clone o repositório

```bash
git clone https://github.com/PedroDayer/Trabalho-Individual-APIRest.git
```

### 2. Configure o banco de dados

Crie um banco de dados no PostgreSQL:

```sql
CREATE DATABASE concessionaria;
```

### 3. Configure as credenciais

Edite o arquivo `src/main/resources/application.properties`:

```properties
# Configurações do Banco de Dados
spring.datasource.url=jdbc:postgresql://localhost:5432/concessionaria
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# Configurações do JPA/Hibernate
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true

# Configurações da Aplicação
server.port=8080
```

### 4. Execute o projeto

```bash
mvn clean install
mvn spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

## 📂 Estrutura do Projeto

```
src/main/java/org/serratec/trabalho/
├── controller/               # Camada de controle (Endpoints REST)
│   ├── ClienteController.java
│   └── VeiculoController.java
│
├── service/                  # Camada de serviço (Lógica de negócio)
│   ├── ClienteService.java
│   └── VeiculoService.java
│
├── repository/               # Camada de persistência (Acesso ao BD)
│   ├── ClienteRepository.java
│   └── VeiculoRepository.java
│
├── entity/                   # Entidades JPA (Tabelas do banco)
│   ├── Cliente.java
│   └── Veiculo.java
│
├── model/                    # DTOs (Transferência de dados)
│   ├── ClienteCriar.java
│   ├── ClienteBuscar.java
│   ├── VeiculoCadastrar.java
│   ├── VeiculoAtualizar.java
│   ├── VeiculoBuscar.java
│   ├── MensagemSucesso.java
│   └── MensagemErro.java
│
└── exception/                # Tratamento de exceções
    ├── GlobalExceptionHandler.java
    ├── CampoInvalidoException.java
    ├── DadosDuplicadosException.java
    ├── RegraNegocioException.java
    └── SolicitacaoNaoEncontradaException.java
```

## 🌐 Endpoints

### Cliente

#### Cadastrar Cliente
```http
POST /api/v1/cliente
Content-Type: application/json
```

**Body:**
```json
{
  "nome": "João Silva",
  "telefone": "21987654321",
  "cpf": "12345678901",
  "email": "joao@email.com"
}
```

#### Listar/Buscar Clientes
```http
GET /api/v1/cliente
GET /api/v1/cliente?nome=João
GET /api/v1/cliente?cpf=12345678901
```

**Resposta:** `200 OK`
```json
[
  {
    "id": "3fab6f64-5717-4662-b3fc-2c963f66efae",
    "nome": "João Silva",
    "telefone": "(21) 98765-4321",
    "cpf": "123.456.789-01",
    "email": "joao@email.com"
  }
]
```

#### Remover Cliente
```http
DELETE /api/v1/cliente/{id}
```

**Resposta:** `204 No Content`

---

### Veículo

#### Cadastrar Veículo
```http
POST /api/v1/veiculo
Content-Type: application/json
```

**Body:**
```json
{
  "clienteId": "3fab6f64-5717-4662-b3fc-2c963f66efae",
  "marca": "Toyota",
  "modelo": "Corolla",
  "ano": 2023,
  "valor": 120000.00,
  "placa": "ABC1D23",
  "maximoDesconto": 5000.00
}
```

**Resposta:** `201 Created`
```json
{
  "mensagem": "Veiculo cadastrado com sucesso."
}
```

#### Listar/Buscar Veículos
```http
GET /api/v1/veiculo
GET /api/v1/veiculo?placa=ABC1D23
GET /api/v1/veiculo?marca=Toyota
GET /api/v1/veiculo?modelo=Corolla
```

**Resposta:** `200 OK`
```json
[
  {
    "id": "7d8e9f10-1234-5678-9abc-def012345678",
    "marca": "Toyota",
    "modelo": "Corolla",
    "ano": 2023,
    "valor": 120000.00,
    "placa": "ABC1D23",
    "maximoDesconto": 5000.00,
    "vendido": false,
    "valorVenda": null,
    "clienteId": "3fab6f64-5717-4662-b3fc-2c963f66efae"
  }
]
```

#### Atualizar Veículo
```http
PUT /api/v1/veiculo/{id}
Content-Type: application/json
```

**Body (atualização parcial):**
```json
{
  "vendido": true,
  "valorVenda": 115000.00
}
```

**Resposta:** `200 OK`
```json
{
  "mensagem": "Veiculo atualizado com sucesso."
}
```

#### Remover Veículo
```http
DELETE /api/v1/veiculo/{id}
```

**Resposta:** `204 No Content`

## 📝 Modelos de Dados

### Cliente

| Campo | Tipo | Restrições | Descrição |
|-------|------|-----------|-----------|
| `id` | UUID | Gerado automaticamente | Identificador único |
| `nome` | String | Obrigatório |
| `telefone` | String | Obrigatório, 11 dígitos | Telefone com DDD |
| `cpf` | String | Obrigatório, único, 11 dígitos | CPF sem formatação |
| `email` | String | Obrigatório, único, formato email | Email válido |

### Veículo

| Campo | Tipo | Restrições | Descrição |
|-------|------|-----------|-----------|
| `id` | UUID | Gerado automaticamente | Identificador único |
| `clienteId` | UUID | Obrigatório, FK | Proprietário do veículo |
| `marca` | String | Obrigatório | Marca do veículo |
| `modelo` | String | Obrigatório | Modelo do veículo |
| `ano` | Integer | Obrigatório, ≥ 1900 | Ano de fabricação |
| `valor` | Float | Obrigatório, ≥ 1 | Preço do veículo |
| `placa` | String | Obrigatório, único | Placa do veículo |
| `maximoDesconto` | Float | Obrigatório, ≥ 0 | Desconto máximo permitido |
| `vendido` | Boolean | Obrigatório | Status de venda |
| `valorVenda` | Float | Condicional, ≥ 0 | Valor da venda (obrigatório se vendido = true) |

## ✅ Validações

### Cliente
- **Nome**: Não pode ser vazio ou nulo
- **Telefone**: Exatamente 11 dígitos numéricos
- **CPF**: Exatamente 11 dígitos numéricos, deve ser único
- **Email**: Formato válido de email, deve ser único

### Veículo
- **ClienteId**: Deve corresponder a um cliente existente
- **Marca**: Não pode ser vazio ou nulo
- **Modelo**: Não pode ser vazio ou nulo
- **Ano**: Mínimo 1900
- **Valor**: Mínimo 1
- **Placa**: Não pode ser vazio, deve ser única
- **MaximoDesconto**: Mínimo 0
- **ValorVenda**: Obrigatório se vendido = true, mínimo 0

## 📐 Regras de Negócio

### Cliente
1. **CPF único**: Não pode haver dois clientes com o mesmo CPF
2. **Email único**: Não pode haver dois clientes com o mesmo email
3. **Remoção**: Cliente pode ser removido mesmo tendo veículos vinculados (cascade)

### Veículo
1. **Placa única**: Não pode haver dois veículos com a mesma placa
2. **Cliente obrigatório**: Todo veículo deve estar vinculado a um cliente
3. **Vendido**: Quando `vendido = true`, o campo `valorVenda` é obrigatório
5. **Valor de venda**: Se o veículo for marcado como `vendido = false`, o `valorVenda` é automaticamente limpo

## ⚠️ Tratamento de Erros

### Códigos HTTP

| Código | Descrição | Exemplo |
|--------|-----------|---------|
| **200** | Sucesso (OK) | Atualização realizada |
| **201** | Criado com sucesso | Cliente/Veículo cadastrado |
| **204** | Sem conteúdo | Remoção realizada |
| **400** | Dados inválidos | Campo obrigatório ausente, formato incorreto |
| **404** | Não encontrado | Cliente/Veículo não existe |
| **409** | Conflito | CPF ou placa já cadastrado |
| **500** | Erro interno | Erro no servidor |

## 👤 Autor

Desenvolvido por Pedro Augusto Bastos Dayer
- GitHub: https://github.com/PedroDayer
- Email: pedrob.dayer@gmail.com
