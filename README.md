# GameVault API

[![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk&logoColor=white)](https://www.oracle.com/java/technologies/downloads/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-6DB33F?logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Oracle Database](https://img.shields.io/badge/Oracle%20Database-Free%2023c-F80000?logo=oracle&logoColor=white)](https://www.oracle.com/database/free/)
[![Docker](https://img.shields.io/badge/Docker-Compose-2496ED?logo=docker&logoColor=white)](https://www.docker.com/)
[![License](https://img.shields.io/badge/license-academic-lightgrey)](#licença)

API REST para gerenciamento de uma biblioteca de jogos. O projeto foi criado em uma aula da **FIAP** para praticar o desenvolvimento de uma aplicação Java com Spring Boot, persistência com JPA e acesso a um banco Oracle local.

O banco de dados local é executado com **Docker**, utilizando Oracle Database Free. Os métodos da API foram testados manualmente com **Insomnia**.

## Tecnologias

- Java 25
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA
- Bean Validation
- Oracle Database Free
- Docker Compose
- Maven Wrapper

## Funcionalidades

- Cadastrar, listar, consultar, atualizar e remover jogos.
- Validar título, plataforma e nota.
- Impedir o cadastro de jogos com títulos duplicados.
- Retornar respostas para recursos inexistentes, conflitos e dados inválidos.

## Pré-requisitos

- JDK 25
- Docker Desktop ou Docker Engine com Docker Compose
- Insomnia (opcional, para testar os endpoints manualmente)

## Instalação e execução

1. Clone o repositório e entre na pasta do projeto:

   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd GameVault-API
   ```

2. Suba o Oracle local com Docker Compose:

   ```bash
   docker compose up -d
   ```

   Na primeira inicialização, o Oracle pode levar alguns minutos até ficar disponível.

3. Crie um arquivo `.env` a partir do exemplo para manter a configuração local fora do Git:

   ```bash
   cp .env.example .env
   ```

   Carregue as variáveis no terminal antes de iniciar a aplicação:

   ```bash
   set -a
   source .env
   set +a
   ```

   O arquivo `.env` é ignorado pelo Git. Os valores do exemplo são placeholders para desenvolvimento local e não devem ser usados em produção.

4. Execute a aplicação:

   ```bash
   ./mvnw spring-boot:run
   ```

   No Windows, use `mvnw.cmd spring-boot:run`.

   A API ficará disponível em `http://localhost:8080`.

## Testes

Para compilar e executar os testes automatizados:

```bash
./mvnw test
```

Para verificar se a aplicação está respondendo:

```bash
curl http://localhost:8080/api/hello
```

Resposta esperada:

```text
Hello World!
```

## Endpoints principais

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/api/hello` | Verifica se a API está respondendo |
| `POST` | `/api/jogos` | Cadastra um jogo |
| `GET` | `/api/jogos` | Lista os jogos |
| `GET` | `/api/jogos/{id}` | Consulta um jogo por código |
| `PUT` | `/api/jogos/{id}` | Atualiza um jogo |
| `DELETE` | `/api/jogos/{id}` | Remove um jogo |

### Exemplo de cadastro

```bash
curl -X POST http://localhost:8080/api/jogos \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Hades",
    "plataforma": "PC",
    "nota": 9.5,
    "dataLancamento": "2020-09-17",
    "zerado": false
  }'
```

O mesmo fluxo pode ser reproduzido no Insomnia criando uma requisição `POST` para a rota acima e enviando o JSON no corpo.

## Configuração do banco

A aplicação lê a conexão pelas variáveis `DB_URL`, `DB_USERNAME` e `DB_PASSWORD`. Os valores padrão apontam para o Oracle local iniciado pelo Compose. Nenhuma credencial real é armazenada neste repositório.

Para parar o banco:

```bash
docker compose down
```

Para remover também os dados persistidos localmente:

```bash
docker compose down -v
```

## Licença

Projeto acadêmico desenvolvido para fins de estudo na FIAP.

## Autor

[Vinicius Santos](https://linkedin.com/santos-vinicius)
