# Deliverit
________________________________________________________________________________________

O sistema foi desenvolvido em Java 8, utilizando o framework Spring Boot 2.2. A linguagem de banco utilizada foi o H2 ou Postgresql.

Configurações iniciais do sistema:

É possível escolher entre dois banco de dados no sistema, o H2 (Banco em memória) ou Postgresql. Caso queira utilizar o Postgresql é necessário ter ele instalado na máquina. Também é necessário rodar o comando abaixo:
- create database deliverirt;

Para alternar entre os banco basta descomentar as linhas nas properties do projeto e alterar dados conforme o necessário.

Feito isso deve-se startar o projeto pra a criação das tabelas e colunas.

Após é possível utilizar os End-Point abaixo

________________________________________________________________________________________
MODELO

HEADER - Content-Type:application/json

SALVAR - POST - http://127.0.0.1:8080/conta - 
- JSON - { "nome": "Boleto", "valorOriginal": 100.00, "dataVencimento": "2019-01-14", "dataPagamento": "2019-01-18"}

ALTERAR - PUT - http://127.0.0.1:8080/conta 
- JSON - { "id": 1, "nome": "Boleto", "valorOriginal": 110.00, "dataVencimento": "2019-01-15", "dataPagamento": "2019-01-19"}

EXCLUIR - DELETE - http://127.0.0.1:8080/conta/1

BUSCA 1 REGISTRO - GET - http://127.0.0.1:8080/conta/1

BUSCA TODOS - GET - http://127.0.0.1:8080/contas