# Project Orion Beacon - DevOps

## Descrição da Solução

O **Project Orion Beacon** é uma solução voltada para a identificação e priorização de áreas com possível presença de água na Lua, em Marte e em outros corpos celestes.

A aplicação utiliza dados simulados de sensores orbitais para registrar corpos celestes, áreas analisadas, sensores, análises e leituras de sensores. A proposta apoia a escolha de regiões mais promissoras antes do envio de rovers, sondas ou equipamentos de exploração à superfície.

Esta documentação apresenta a configuração de **DevOps** do projeto, utilizando **Docker**, **Docker Compose**, **PostgreSQL** e **Azure VM**.

---

## Tecnologias Utilizadas

* Java 21
* Spring Boot
* Spring Data JPA
* Swagger/OpenAPI
* Oracle
* PostgreSQL 16.4
* Docker
* Docker Compose
* Azure VM
* Bash Script

---

## Perfis de Banco de Dados

O projeto possui dois perfis de banco de dados:

| Profile    | Banco      | Uso                              |
| ---------- | ---------- | -------------------------------- |
| `oracle`   | Oracle     | Execução local da aplicação Java |
| `postgres` | PostgreSQL | Execução em Docker/DevOps        |

O profile padrão da aplicação é:

```text
oracle
```

No ambiente Docker, o profile PostgreSQL é ativado no `docker-compose.yml` por meio da variável de ambiente:

```text
SPRING_PROFILES_ACTIVE=postgres
```

---

## Arquitetura DevOps

A arquitetura DevOps utiliza uma máquina virtual Ubuntu na Azure executando containers Docker por meio do Docker Compose.

A estrutura é composta por:

* Resource Group na Azure: `rg-orion-beacon-devops`
* VM Ubuntu: `vm-orion-beacon`
* Docker Engine
* Docker Compose
* Container da aplicação Java Spring Boot
* Container do banco PostgreSQL
* Rede Docker compartilhada entre aplicação e banco
* Volume nomeado para persistência dos dados
* Porta `8080` exposta para acesso à API e ao Swagger
* Porta `5432` exposta para acesso ao PostgreSQL

Imagem da arquitetura macro:

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
│   ├── arquitetura-macro.png
│   └── arquitetura-macro.drawio
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
* Gera o arquivo `.jar` no processo de build.
* Define o diretório de trabalho com `WORKDIR`.
* Executa a aplicação com usuário não privilegiado.
* Expõe a porta `8080`.
* Executa a aplicação por meio do arquivo `app.jar`.

A imagem da aplicação é versionada como:

```text
orion-beacon-api:1.0
```

---

## Docker Compose

O arquivo `docker-compose.yml` cria e orquestra os serviços do projeto.

| Serviço | Container            | Função               | Imagem                 | Porta  |
| ------- | -------------------- | -------------------- | ---------------------- | ------ |
| `app`   | `orion-app-rm564995` | API Java Spring Boot | `orion-beacon-api:1.0` | `8080` |
| `db`    | `orion-db-rm564995`  | Banco PostgreSQL     | `postgres:16.4`        | `5432` |

Também são configurados:

* Rede Docker do projeto.
* Volume nomeado do PostgreSQL.
* Variáveis de ambiente para banco e aplicação.
* Profile `postgres` para execução no ambiente Docker.
* Containers nomeados com o RM do representante DevOps.

Nomes reais gerados pelo Docker Compose:

```text
Rede:   orion-beacon-api_orion_network
Volume: orion-beacon-api_orion_postgres_data
```

---

# How To - Como Executar a Demonstração

## 1. Clonar o repositório

No Azure Cloud Shell ou Git Bash:

```bash
git clone https://github.com/larimagalh/gsjava.git
```

Entrar na pasta da aplicação:

```bash
cd gsjava/orion-beacon-api/orion-beacon-api
```

Conferir os arquivos principais:

```bash
ls
ls scripts
ls docs
```

---

## 2. Criar infraestrutura na Azure

Executar o script de criação:

```bash
bash scripts/azure-create-vm.sh
```

Esse script realiza:

* Criação do Resource Group `rg-orion-beacon-devops`.
* Criação da VM Ubuntu `vm-orion-beacon`.
* Abertura da porta `8080` para a API.
* Abertura da porta `5432` para o PostgreSQL.
* Instalação de Docker, Docker Compose e Git.
* Clone do repositório dentro da VM.
* Execução do `docker compose up -d --build`.

Ao final, o terminal exibe:

```text
IP público da aplicação
Link do Swagger
Comando SSH
```

---

## 3. Mostrar VM, Resource Group e IP público

No Azure Cloud Shell:

```bash
az vm list-ip-addresses --resource-group rg-orion-beacon-devops --name vm-orion-beacon --output table
```

Esse comando mostra a VM criada, o IP público e o IP privado.

O IP público é utilizado para acessar a API e o Swagger pela internet:

```text
http://IP_PUBLICO:8080/swagger-ui/index.html
```

---

## 4. Acessar a VM

No Azure Cloud Shell:

```bash
ssh gu564995@IP_PUBLICO
```

Caso seja solicitada confirmação:

```bash
yes
```

---

## 5. Verificar containers e serviços pelo Docker Compose

Dentro da VM:

```bash
sudo docker compose -f ~/gsjava/orion-beacon-api/orion-beacon-api/docker-compose.yml ps
```

Esse comando mostra os serviços definidos no Docker Compose e os containers criados a partir deles:

* Serviço `app`: container `orion-app-rm564995`
* Serviço `db`: container `orion-db-rm564995`

Também são exibidas as imagens utilizadas, o status `Up` e as portas expostas.

---

## 6. Verificar logs filtrados da aplicação

```bash
sudo docker logs orion-app-rm564995 2>&1 | grep -E "Tomcat started|Started OrionBeaconApiApplication|Initialized JPA"
```

Esse comando mostra apenas as principais evidências de inicialização da aplicação:

* JPA inicializado.
* Tomcat iniciado na porta `8080`.
* Aplicação Spring Boot iniciada corretamente.

---

## 7. Verificar logs filtrados do PostgreSQL

```bash
sudo docker logs orion-db-rm564995 2>&1 | grep -E "PostgreSQL|ready to accept connections"
```

Esse comando mostra que o PostgreSQL iniciou corretamente e está pronto para receber conexões.

---

## 8. Verificar volume e rede Docker

Listar volumes:

```bash
sudo docker volume ls
```

Volume esperado:

```text
orion-beacon-api_orion_postgres_data
```

Esse volume é utilizado pelo PostgreSQL para manter os dados persistidos.

Listar redes:

```bash
sudo docker network ls
```

Rede esperada:

```text
orion-beacon-api_orion_network
```

Essa rede permite a comunicação interna entre o container da aplicação Java e o container do PostgreSQL.

---

## 9. Entrar no container da aplicação

```bash
sudo docker container exec -it orion-app-rm564995 sh
```

Dentro do container:

```bash
whoami
ls -l
exit
```

Esses comandos demonstram:

* O usuário que executa a aplicação dentro do container.
* A presença do arquivo `app.jar`.
* A execução da aplicação em um ambiente isolado.

O usuário esperado é:

```text
orionuser
```

---

## 10. Testar a API pelo Swagger

Acessar no navegador:

```text
http://IP_PUBLICO:8080/swagger-ui/index.html
```

A ordem sugerida para teste é:

```text
Corpo Celeste → Área Analisada → Sensor → Análise → Leitura Sensor
```

Operações demonstradas:

* `POST`: cadastrar registros.
* `GET`: consultar/listar registros.
* `PUT`: atualizar registros.
* `DELETE`: remover registros.

---

## 11. Acessar o banco PostgreSQL

Entrar no container do banco:

```bash
sudo docker container exec -it orion-db-rm564995 bash
```

Entrar no PostgreSQL:

```bash
psql -U orion_user -d orion_db
```

Listar tabelas:

```sql
\dt
```

Consultar dados persistidos:

```sql
SELECT * FROM tb_corpo_celeste;
SELECT * FROM tb_area_analisada;
SELECT * FROM tb_sensor;
SELECT * FROM tb_analise;
SELECT * FROM tb_leitura_sensor;
```

Consultar relacionamento entre as tabelas:

```sql
SELECT
    c.nome AS corpo_celeste,
    ar.nome AS area_analisada,
    ar.regiao,
    an.classificacao,
    an.observacao,
    s.nome AS sensor,
    s.tipo,
    l.valor,
    l.interpretacao
FROM tb_corpo_celeste c
INNER JOIN tb_area_analisada ar
    ON ar.corpo_celeste_id = c.id
INNER JOIN tb_analise an
    ON an.area_id = ar.id
INNER JOIN tb_leitura_sensor l
    ON l.analise_id = an.id
INNER JOIN tb_sensor s
    ON l.sensor_id = s.id;
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

## 12. Remover recursos da Azure

No Azure Cloud Shell, sair da VM se necessário:

```bash
exit
```

Entrar na pasta do projeto:

```bash
cd ~/gsjava/orion-beacon-api/orion-beacon-api
```

Executar o script de remoção:

```bash
bash scripts/azure-delete-resources.sh
```


## Acesso à Aplicação

Localmente:

```text
http://localhost:8080
```

Swagger local:

```text
http://localhost:8080/swagger-ui/index.html
```

Na Azure:

```text
http://IP_PUBLICO:8080/swagger-ui/index.html
```

---

## Persistência de Dados

O PostgreSQL utiliza o volume nomeado:

```text
orion-beacon-api_orion_postgres_data
```

Esse volume mantém os dados armazenados mesmo após parar ou recriar os containers, desde que o volume não seja removido.

Na demonstração, a persistência também é comprovada por meio de consultas diretas no PostgreSQL usando `SELECT`.

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

### Remover recursos da Azure

```bash
bash scripts/azure-delete-resources.sh
```

---


## Comandos Principais para Demonstração

```bash
bash scripts/azure-create-vm.sh
az vm list-ip-addresses --resource-group rg-orion-beacon-devops --name vm-orion-beacon --output table
ssh gu564995@IP_PUBLICO
sudo docker compose -f ~/gsjava/orion-beacon-api/orion-beacon-api/docker-compose.yml ps
sudo docker logs orion-app-rm564995 2>&1 | grep -E "Tomcat started|Started OrionBeaconApiApplication|Initialized JPA"
sudo docker logs orion-db-rm564995 2>&1 | grep -E "PostgreSQL|ready to accept connections"
sudo docker volume ls
sudo docker network ls
sudo docker container exec -it orion-app-rm564995 sh
sudo docker container exec -it orion-db-rm564995 bash
bash scripts/azure-delete-resources.sh
```

---

## Observação

O banco PostgreSQL é utilizado para a entrega de DevOps em Docker e Azure. O Oracle permanece configurado como profile padrão para preservar a execução local da aplicação Java.
