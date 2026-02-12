# 📊 API para Consulta de Vendas

API REST em Java + Spring Boot para consulta de vendas e sumário por vendedor.

## 🚀 Sobre

Este projeto é uma API RESTful desenvolvida com Spring Boot que permite realizar consultas de vendas e sumarizar valores por vendedor.

Ele faz parte do treinamento de backend Java com foco em Spring Data JPA, JPA, H2 Database e padrões de projeto típicos de aplicações profissionais.

## 🧠 Funcionalidades

📌 Relatório de Vendas
Permite retornar uma listagem paginada de vendas com dados como:

- ID da venda

- Data

- Valor total

- Nome do vendedor
  
### Filtros aplicáveis:

- Data inicial (opcional)

- Data final (opcional)

- Parte do nome do vendedor (opcional)

## 📌 Sumário de Vendas por Vendedor
Retorna um agrupamento de vendedores com o total de vendas no período informado (ou nos últimos 12 meses, se não informado).

## 📦 Tecnologias Utilizadas

✔️ Java 21
✔️ Spring Boot
✔️ Spring Data JPA
✔️ H2 Database (em memória)
✔️ Maven
✔️ DTOs para transporte de dados
✔️ JPA para persistência relacional

## 📥 Como Rodar o Projeto

🔹 Requisitos: JDK 21+ e Maven instalados.

Clone o repositório
 ```bash
git clone https://github.com/Vitor-PereiraS/desafio-consulta-vendas.git
 ```

Entre na pasta do projeto
 ```bash
cd desafio-consulta-vendas
 ```

Execute a aplicação
```bash
mvn spring-boot:run
```

A API será iniciada em http://localhost:8080.

## 📍 Endpoints Principais

✨ Exemplo das rotas disponíveis após iniciar o servidor:
```bash
Método	Endpoint	Descrição
GET	/sales/report	Relatório de vendas paginado
GET	/sales/summary	Sumário de vendas por vendedor
```

⚙️ Possíveis parâmetros:

- minDate — Data inicial (YYYY-MM-DD)

- maxDate — Data final (YYYY-MM-DD)

- name — Parte do nome do vendedor

## 🧪 Testes & Ferramentas

Você pode testar a API com ferramentas como:

- Postman

- Insomnia

- Curl

Simplesmente envie requisições HTTP para os endpoints acima com os parâmetros desejados.
