# Deliverit
________________________________________________________________________________________

O sistema foi desenvolvido em Java 8, utilizando o framework Spring Boot 2.2. A linguagem de banco utilizada foi o Postgresql.

________________________________________________________________________________________

*CONFIGURAÇÕES INICIAIS:*

Necessário instalar o Postgresql.

Caso necessário deve-se alterar as configuração de banco nos properties do projeto.

Também é necessário rodar o comando abaixo:
- create database deliverirt;

Feito isso deve-se startar o projeto pra a criação das tabelas e colunas.

________________________________________________________________________________________
*END-POINTS E EXEMPLO DE UTILIZAÇÃO*

HEADER - Content-Type:application/json

SALVAR - POST - http://127.0.0.1:8080/conta - 
- JSON - { "nome": "Boleto", "valorOriginal": 100.00, "dataVencimento": "2019-01-14", "dataPagamento": "2019-01-18"}

ALTERAR - PUT - http://127.0.0.1:8080/conta 
- JSON - { "id": 1, "nome": "Boleto", "valorOriginal": 110.00, "dataVencimento": "2019-01-15", "dataPagamento": "2019-01-19"}

EXCLUIR - DELETE - http://127.0.0.1:8080/conta/1

BUSCA 1 REGISTRO - GET - http://127.0.0.1:8080/conta/1

BUSCA TODOS - GET - http://127.0.0.1:8080/contas