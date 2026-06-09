# Orion Beacon API

## Integrantes

* Larissa Juvenal de Magalhães – RM566457
* Matheus Gianolli - RM565258
* Enzo Xavier Coelho - RM563379
* Gustavo Ribeiro Permagnani - RM564995
* Julia Menezes - RM565568

---

## Sobre o Projeto

O Orion Beacon API é uma API RESTful desenvolvida para gerenciamento e monitoramento de sensores utilizados em operações de vigilância e análise de ambientes.

A solução foi construída utilizando Spring Boot, Oracle Database e arquitetura em camadas, seguindo boas práticas de desenvolvimento de APIs modernas.

---

## Tecnologias Utilizadas

* Java
* Spring Boot
* Spring Data JPA
* Spring Security
* JWT Authentication
* Oracle Database
* Hibernate
* Swagger / OpenAPI
* HATEOAS
* Bean Validation
* Maven
* Render (Deploy)

---

## Funcionalidades

* CRUD completo de entidades do sistema
* Persistência de dados em Oracle Database
* DTOs para entrada e saída de dados
* Bean Validation para validação de requisições
* Tratamento global de exceções
* Documentação automática com Swagger
* Navegação entre recursos com HATEOAS
* Autenticação e autorização utilizando JWT
* Deploy em ambiente de produção

---

## Arquitetura

O projeto segue a arquitetura:

Controller → Service → Repository → Banco de Dados

Também foram implementados:

* DTOs
* Validation
* Exception Handler
* Spring Security
* JWT
* HATEOAS
* Swagger/OpenAPI

---

## Deploy

Aplicação publicada em:

https://gsjava-4.onrender.com/

---

## Vídeo de Apresentação

Vídeo do projeto:

https://drive.google.com/file/d/1tOOwuYdQUMKGbRSUcrVdlTf30m-0kfUc/view?usp=sharing

---

## Repositório GitHub

https://github.com/larimagalh/gsjava

---

## Documentação da API

Swagger UI:

https://gsjava-4.onrender.com/swagger-ui/index.html

Caso o deploy esteja em modo de suspensão, aguarde alguns segundos para o Render iniciar a aplicação.

---

## Como Executar o Projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/larimagalh/gsjava.git
```

### 2. Entrar na pasta do projeto

```bash
cd gsjava
```

### 3. Configurar banco Oracle

Definir as propriedades do banco Oracle no arquivo de configuração:

```properties
spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
spring.datasource.username=SEU_RM
spring.datasource.password=SUA_SENHA
```

### 4. Executar aplicação

Via Maven:

```bash
mvn spring-boot:run
```

Ou diretamente pela IDE (IntelliJ IDEA).

---

## Endpoints Principais

### Análises

* GET /analises
* GET /analises/{id}
* POST /analises
* PUT /analises/{id}
* DELETE /analises/{id}

### Coordenadas

* GET /coordenadas
* GET /coordenadas/{id}
* POST /coordenadas
* PUT /coordenadas/{id}
* DELETE /coordenadas/{id}

### Sensores

* GET /sensores
* GET /sensores/{id}
* POST /sensores
* PUT /sensores/{id}
* DELETE /sensores/{id}

### Sensores Térmicos

* GET /sensortermico
* POST /sensortermico
* PUT /sensortermico/{id}
* DELETE /sensortermico/{id}

### Sensores Radar

* GET /sensorradar
* POST /sensorradar
* PUT /sensorradar/{id}
* DELETE /sensorradar/{id}

---

## Requisitos Atendidos

✔ API RESTful

✔ CRUD Completo

✔ Oracle Database

✔ DTOs

✔ Bean Validation

✔ Exception Handler

✔ HATEOAS

✔ Swagger/OpenAPI

✔ JWT Authentication

✔ Deploy em Produção

✔ Documentação Completa no GitHub
