# 🛍️ Microsserviço de Controle de E-commerce

Projeto desenvolvido como parte do aprendizado sobre **arquitetura de microsserviços**, **mensageria com RabbitMQ** e **comunicação entre aplicações Spring Boot**.  
A ideia foi criar um sistema simples de **controle de produtos e estoque**, simulando o backend de um e-commerce — com dois serviços independentes que se comunicam entre si.

---

## 🧠 Visão Geral

O sistema é composto por dois microsserviços principais:

- **Warehouse** → Responsável por gerenciar o estoque (armazenamento, disponibilidade e status dos produtos).  
- **Storefront** → Representa a "vitrine" do e-commerce, permitindo a consulta e cadastro de produtos.

Esses dois serviços se comunicam por meio de mensagens enviadas via **RabbitMQ**, permitindo que mudanças no estoque reflitam automaticamente na aplicação principal.

---

## ⚙️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3**
- **Spring Data JPA**
- **Spring Web**
- **MapStruct**
- **RabbitMQ**
- **Lombok**
- **Maven**
- **Docker**
- **H2 Database**

---

## 🧩 Estrutura do Projeto

📦 microsservico-controle-ecommerce
├── 📁 warehouse
│ ├── src/main/java/br/com/dio/warehouse
│ ├── src/main/resources
│ └── Dockerfile
│
├── 📁 storefront
│ ├── src/main/java/br/com/dio/storefront
│ ├── src/main/resources
│ └── Dockerfile
│
├── 📁 images
│ ├── swagger-warehouse.png
│ ├── swagger-storefront.png
│ ├── docker-containers.png
│ └── ...
│
└── docker-compose.yml

---

## 🚀 Como Executar

### 🔹 Pré-requisitos
- Java 21 instalado  
- Docker e Docker Compose instalados  
- Maven configurado

### 🔹 Executando com Docker Compose

Na raiz do projeto:

```bash
docker compose up -d
````

Isso vai subir:

RabbitMQ

Microsserviço warehouse

Microsserviço storefront

Você pode acessar os Swaggers em:

Warehouse: http://localhost:8081/swagger-ui.html

Storefront: http://localhost:8080/swagger-ui.html

💬 Comunicação entre os Serviços

O Warehouse publica mensagens no RabbitMQ sempre que há uma mudança no status de um produto (por exemplo, produto se tornando disponível ou indisponível).

O Storefront consome essas mensagens e atualiza seu próprio estado, refletindo em tempo real as alterações vindas do outro serviço.

Isso garante um fluxo assíncrono e desacoplado entre os microsserviços.

🧰 Aprendizados e Melhorias Futuras

Esse projeto foi uma ótima oportunidade para praticar:

Comunicação entre microsserviços com RabbitMQ

Organização de código em múltiplos módulos

Uso de MapStruct para conversão de DTOs e entidades

Execução de múltiplos containers com Docker Compose

🔧 Melhorias Futuras:

Adicionar tratamento de erros mais detalhado (para evitar erros 500 em cenários específicos).

Criar mensagens de feedback mais claras para o usuário.

Revisar a conexão inicial do Storefront com o RabbitMQ, que às vezes falha na primeira tentativa.

Adicionar testes automatizados com JUnit 5 e Testcontainers.

Criar uma documentação mais visual da arquitetura.

Desenvolvido por Guilherme Almeida

💬 Projeto criado com fins de aprendizado e prática de microsserviços.

“Cada projeto é uma nova linha no código da minha evolução como desenvolvedor.”
