# Project Orion Beacon - DevOps

## Descrição da Solução

O **Project Orion Beacon** é uma solução voltada para a identificação e priorização de áreas com possível presença de água na Lua e em Marte.

A aplicação utiliza dados simulados de sensores orbitais para registrar áreas analisadas, armazenar leituras de sensores e consultar informações persistidas em banco de dados. A proposta apoia a escolha de regiões mais promissoras antes do envio de rovers, sondas ou equipamentos de exploração à superfície.

Esta documentação apresenta a configuração de **DevOps**, utilizando Docker, Docker Compose, PostgreSQL e Azure VM.

---

## Tecnologias Utilizadas

* Java 21
* Spring Boot
* Spring Data JPA
* Oracle
* PostgreSQL 16.4
* Docker
* Docker Compose
* Azure VM
* Swagger/OpenAPI

---

## Perfis de Banco de Dados

O projeto possui dois perfis de banco de dados:

| Profile    | Banco      | Uso                              |
| ---------- | ---------- | -------------------------------- |
| `oracle`   | Oracle     | Execução local da aplicação Java |
| `postgres` | PostgreSQL | Execução em Docker/DevOps        |

O profile padrão é:

```text
oracle
```

No ambiente Docker, o profile PostgreSQL é ativado no `docker-compose.yml` por meio da variável:

```text
SPRING_PROFILES_ACTIVE=postgres
```

---

## Arquitetura DevOps

A arquitetura DevOps utiliza uma máquina virtual Ubuntu na Azure executando dois containers Docker integrados na mesma rede:

* Container da aplicação Java Spring Boot.
* Container do banco de dados PostgreSQL.
* Volume nomeado para persistência dos dados.
* Porta 8080 exposta para acesso à API.
* Porta 5432 exposta para acesso ao banco PostgreSQL.
* Rede Docker compartilhada entre aplicação e banco.

A imagem da arquitetura macro será adicionada na pasta `docs`.

```text
docs/arquitetura-macro.png
```

---

## Estrutura dos Arquivos DevOps

```text
orion-beacon-api/
├── Dockerfile
├── docker-compose.yml
├── README-DEVOPS.md
├── docs/
│   └── arquitetura-macro.png
└── scripts/
    ├── azure-create-vm.sh
    └── azure-delete-resources.sh
```

---

## Dockerfile

O `Dockerfile` é responsável por gerar a imagem personalizada da aplicação Java.

Principais pontos atendidos:

* Utiliza Java 21.
* Não utiliza imagem com tag `latest`.
* Gera o `.jar` dentro do próprio processo de build.
* Define diretório de trabalho com `WORKDIR`.
* Executa a aplicação com usuário não privilegiado.
* Expõe a porta 8080.

---

## Docker Compose

O arquivo `docker-compose.yml` cria dois containers:

| Container            | Função                     | Porta |
| -------------------- | -------------------------- | ----- |
| `orion-app-rm564995` | Aplicação Java Spring Boot | 8080  |
| `orion-db-rm564995`  | Banco PostgreSQL           | 5432  |

Também são configurados:

* Rede Docker `orion_network`.
* Volume nomeado `orion_postgres_data`.
* Variáveis de ambiente para o banco e para a aplicação.
* Profile `postgres` para execução no ambiente Docker.

---

## Como Executar Localmente com Docker

Na pasta onde está o `docker-compose.yml`, execute:

```bash
docker compose up -d --build
```

Esse comando constrói a imagem da aplicação Java e sobe os containers em segundo plano.

---

## Verificar Containers em Execução

```bash
docker ps
```

Devem aparecer os containers:

```text
orion-app-rm564995
orion-db-rm564995
```

---

## Visualizar Logs dos Containers

Logs da aplicação:

```bash
docker compose logs app
```

Logs do banco:

```bash
docker compose logs db
```

---

## Acesso à Aplicação

Localmente:

```text
http://localhost:8080
```

Swagger local:

```text
http://localhost:8080/swagger-ui/index.html
```

Na Azure, substituir `localhost` pelo IP público da VM:

```text
http://IP_DA_VM:8080/swagger-ui/index.html
```

---

## Evidências do Container da Aplicação

Acessar o container da aplicação:

```bash
docker container exec -it orion-app-rm564995 sh
```

Dentro do container, executar:

```bash
whoami
pwd
ls -l
exit
```

Esses comandos demonstram:

* Usuário conectado no container.
* Diretório atual.
* Estrutura de arquivos da aplicação.

---

## Evidências do Container do Banco

Acessar o container do PostgreSQL:

```bash
docker container exec -it orion-db-rm564995 bash
```

Entrar no banco:

```bash
psql -U orion_user -d orion_db
```

Listar as tabelas:

```sql
\dt
```

Consultar dados persistidos:

```sql
SELECT * FROM area_analisada;
SELECT * FROM leitura_sensor;
```

Sair do PostgreSQL:

```sql
\q
```

Sair do container:

```bash
exit
```

---

## Persistência de Dados

O PostgreSQL utiliza o volume nomeado:

```text
orion_postgres_data
```

Esse volume mantém os dados armazenados mesmo após parar ou recriar os containers.

---

## Scripts Azure

Os scripts de apoio estão na pasta:

```text
scripts/
```

### Criar VM e subir aplicação

```bash
bash scripts/azure-create-vm.sh
```

Esse script realiza:

* Criação do Resource Group.
* Criação da VM Ubuntu.
* Abertura da porta 8080 para a API.
* Abertura da porta 5432 para o PostgreSQL.
* Instalação de Docker, Docker Compose e Git.
* Clone do repositório.
* Execução do `docker compose up -d --build`.

### Remover recursos da Azure

```bash
bash scripts/azure-delete-resources.sh
```

Esse script remove o Resource Group criado para o projeto, apagando VM, disco, IP público, rede e demais recursos associados.

---

## Execução em Nuvem

A aplicação será executada em uma VM Ubuntu na Azure.

Após a execução do script, o terminal exibirá:

```text
IP público da aplicação: http://IP_DA_VM:8080
Swagger: http://IP_DA_VM:8080/swagger-ui/index.html
PostgreSQL: IP_DA_VM:5432
SSH: ssh gu564995@IP_DA_VM
```

---

## Boas Práticas Aplicadas

* Não foi utilizada tag `latest` nas imagens Docker.
* PostgreSQL fixado na versão `16.4`.
* Aplicação Java executada com usuário não privilegiado.
* Diretório de trabalho definido no Dockerfile.
* Banco PostgreSQL com volume nomeado.
* Aplicação e banco executando na mesma rede Docker.
* Configuração por variáveis de ambiente.
* Containers nomeados com o RM do representante DevOps.
* Execução em background com Docker Compose.
* Scripts de criação e remoção do ambiente em nuvem.

---

## Comandos Principais para Demonstração

```bash
docker compose up -d --build
docker ps
docker compose logs app
docker compose logs db
docker container exec -it orion-app-rm564995 sh
docker container exec -it orion-db-rm564995 bash
```

---

## Observação

O banco PostgreSQL é utilizado para a entrega de DevOps. O Oracle permanece configurado como profile padrão para preservar a execução local da aplicação Java.
