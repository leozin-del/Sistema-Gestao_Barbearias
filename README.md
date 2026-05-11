# 💈 BarbeariaPro
### Sistema de Gestão para Barbearias

<div align="center">

[![Java](https://img.shields.io/badge/Java_17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)

</div>

---

## 🚀 Sobre o Projeto

O **BarbeariaPro** é um sistema backend desenvolvido com **Spring Boot**, com o objetivo de gerenciar uma barbearia de forma simples e eficiente.

A aplicação permite o controle de:

| Módulo | Descrição |
|--------|-----------|
| 👤 **Clientes** | Cadastro e gestão de clientes |
| ✂️ **Barbeiros** | Perfis e controle de profissionais |
| 📅 **Agendamentos** | Criação e acompanhamento de agendamentos |
| 💼 **Serviços** | Catálogo de serviços oferecidos |
| 🔐 **Usuários** | Controle de acesso com papéis `CLIENTE` e `BARBEIRO` |

---

## 🛠️ Tecnologias Utilizadas

- ☕ **Java 17+**
- 🌱 **Spring Boot**
- 🗄️ **Spring Data JPA / Hibernate**
- 🐬 **MySQL**
- 🐳 **Docker & Docker Compose**
- 🔧 **Maven**

---

## 📦 Arquitetura

A aplicação segue um padrão de camadas bem definido:

```
Controller   →   recebe as requisições HTTP
    ↓
Service      →   aplica as regras de negócio
    ↓
Repository   →   realiza o acesso ao banco de dados
    ↓
Entity       →   representa o mapeamento das tabelas
```

---

## 🐳 Como Rodar com Docker

### 🔹 Pré-requisitos

- [Docker](https://www.docker.com/products/docker-desktop/) instalado *(Docker Desktop)*

### 🔹 Passo a Passo

```bash
# 1. Clonar o repositório
git clone <URL_DO_REPOSITORIO>

# 2. Entrar na pasta do projeto
cd barbeariapro

# 3. Subir a aplicação + banco de dados
docker compose up --build
```

### 🔹 Acessar a Aplicação

```
http://localhost:8080
```

---

## 🧪 Testando a API

Como este projeto é um **backend REST**, você pode testar os endpoints utilizando:

- 🟠 [Postman](https://www.postman.com/)
- 🟣 [Insomnia](https://insomnia.rest/)

---

## 📊 Estrutura do Banco de Dados

O sistema cria automaticamente as seguintes tabelas:

```
📁 banco de dados
 ├── 👤 usuarios
 ├── 🧑 clientes
 ├── ✂️  barbeiros
 ├── 💼 servicos
 └── 📅 agendamentos
```

---

## 🔐 Regras de Negócio

- Um usuário pode ser **`CLIENTE`** ou **`BARBEIRO`**
- Cada cliente/barbeiro está **vinculado a um usuário**
- Agendamentos conectam:
  - 🧑 Cliente
  - ✂️ Barbeiro
  - 💼 Serviço
- **Status de agendamento:**

| Status | Descrição |
|--------|-----------|
| `AGENDADO` | Horário marcado e confirmado |
| `CANCELADO` | Agendamento cancelado |
| `CONCLUIDO` | Atendimento realizado |

---

## ⚙️ Configuração

A aplicação utiliza a seguinte configuração no `application.properties`:

```properties
spring.jpa.hibernate.ddl-auto=update
```

> 👉 O banco de dados é **criado e atualizado automaticamente** pela aplicação.

---

## 💡 Objetivo do Projeto

Este projeto foi desenvolvido com foco em:

- 📚 Aprendizado de **Spring Boot**
- 🗄️ Integração com **banco de dados relacional**
- 🐳 Uso de **Docker** em ambiente real
- 🏗️ Simulação de um **sistema de gestão completo**

---

## 🚀 Possíveis Melhorias Futuras

- [ ] 📊 **Dashboard** administrativo
- [ ] 🌐 **Frontend** (React ou Angular)
- [ ] 💳 Integração com **pagamentos**

---

## 👨‍💻 Autor

<div align="center">

Desenvolvido por Leonardo Paulin🧙‍♂️

</div>

---

## ⭐ Considerações Finais

Este projeto demonstra habilidades em:

- ✅ Desenvolvimento **backend com Spring Boot**
- ✅ **Modelagem** de banco de dados relacional
- ✅ **Containerização** com Docker
- ✅ **Organização** de projeto profissional

---

<div align="center">

💈 **BarbeariaPro** — *Simplificando a gestão da sua barbearia*

</div>
