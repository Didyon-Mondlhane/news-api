# 📰 NewsManager API

API REST desenvolvida com Spring Boot para gestão de notícias, categorias, comentários e reações. Inclui autenticação JWT, controlo de acesso por papéis e endpoints bem definidos para publicação e interacção com conteúdos jornalísticos.

---

## 🛠️ Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Security (JWT)
- Spring Web
- Spring Data JPA
- Hibernate
- Jakarta Validation
- BCrypt (hashing de senhas)
- Base de dados relacional (MySQL)

---

## 🚀 Funcionalidades Principais

### 🔐 Autenticação e Autorização
- Registo de utilizadores
- Login com retorno de JWT
- Proteção de endpoints por autenticação e autorização

### 📰 Gestão de Notícias
- Listagem pública de notícias
- Criação, actualização e remoção
- Busca por ID

### 📂 Gestão de Categorias
- CRUD completo
- Organização hierárquica

### 💬 Comentários
- Comentários em notícias
- Respostas a comentários (threads)
- Contagem e listagem por notícia

### ❤️ Reacções
- Sistema de "like" e "dislike"
- Actualização e remoção de reacções

---

## 🌐 Endpoints Principais

### 🔐 Autenticação

| Método | Rota             | Acesso  | Descrição         |
|--------|------------------|---------|-------------------|
| POST   | /auth/login      | Público | Login             |
| POST   | /auth/register   | Público | Registo de utilizador |

### 📰 Notícias

| Método | Rota          | Descrição               |
|--------|---------------|-------------------------|
| GET    | /news         | Lista todas as notícias |
| GET    | /news/{id}    | Obtém uma notícia por ID|
| POST   | /news         | Cria uma nova notícia   |
| PUT    | /news/{id}    | Actualiza uma notícia    |
| DELETE | /news/{id}    | Remove uma notícia      |

### 📂 Categorias

| Método | Rota              | Descrição              |
|--------|-------------------|------------------------|
| GET    | /categories       | Lista todas as categorias |
| GET    | /categories/{id}  | Obtém categoria por ID    |
| POST   | /categories       | Cria nova categoria       |
| PUT    | /categories/{id}  | Actualiza categoria        |
| DELETE | /categories/{id}  | Remove categoria          |

### 💬 Comentários

| Método | Rota                           | Descrição                    |
|--------|--------------------------------|------------------------------|
| POST   | /comments                      | Cria novo comentário         |
| GET    | /comments/news/{newsId}        | Lista comentários de uma notícia |
| GET    | /comments/{commentId}/replies  | Lista respostas a um comentário  |
| DELETE | /comments/{id}                 | Remove comentário            |
| GET    | /comments/news/{newsId}/count  | Conta comentários da notícia |
| GET    | /comments/news/{newsId}/latest | Lista últimos comentários da notícia |

### ❤️ Reacções

| Método | Rota                 | Descrição                |
|--------|----------------------|--------------------------|
| GET    | /reactions           | Lista todas as reacções   |
| GET    | /reactions/news/{newsId} | Obtém reações por notícia |
| POST   | /reactions           | Adiciona nova reacção     |
| DELETE | /reactions/{id}      | Remove reacção            |

---

## 🔒 Segurança

- Autenticação JWT stateless
- Proteção de endpoints sensíveis por papéis (roles)
- BCrypt para hash seguro de senhas
- Validação de dados com Jakarta Validation

---

## ▶️ Como Executar o Projeto

### ✅ Pré-requisitos

- Java 17+
- Maven
- Banco de dados MySQL ou PostgreSQL
- IDE (IntelliJ IDEA, VS Code, etc.)

### 📥 Passos

git clone https://github.com/Didyon-Mondlhane/news-api.git

cd NewsManager

### ⚙️ Configuração do `application.properties`

spring.datasource.url=jdbc:mysql://localhost:3306/newsapplication?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

jwt.secret=your-256-bit-secret-key-here
jwt.expiration=86400000 # 24 horas


## ▶️ Executar o Projeto

./mvnw spring-boot:run (opcional)

## 🌍 Acessar a API

http://localhost:8080/swagger-ui/index.html