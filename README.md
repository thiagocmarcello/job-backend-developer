# autenticacao-api

Um serviço de autenticação usando Spring Boot.
Stak de tecnologia: Java 8 + Spring boot + Spring MVC + JWT.

> O JWT é um padrão (RFC-7519) de mercado que define como transmitir e armazenar objetos JSON de forma compacta e segura entre diferentes aplicações. Os dados nele contidos podem ser validados a qualquer momento pois o token é assinado digitalmente.


## Configuração de datasource

`application.yml`

**Para criação do banco de dados e inserção de usuarios, alterar:**
  ```bash
    ddl-auto: none
  ```
  Alterar  `none` para `create`. Voltar para `none` depois de iniciado.
  ```bash
  datasource:
    url: jdbc:postgresql://localhost:5432/autenticacao
    username: meutreino
    password: meutreino
   ```


## Start da aplicação com Docker

_Na raiz da aplicação, executar:_
  ```bash
  sudo mvn install dockerfile:build
  sudo docker run -p 8080:8080 -t spring/autenticacao-api
  ```

## Login app externo
  ```bash
  URL:
    /oauth/token
  headers:
    Authorization:Basic YXBwLWNsaWVudDo0cHA=
  body:
    username:ADMIN@TESTE.COM.BR
    password:123456
    grant_type:password
   ```

## Login external-api
  ```bash
    client: external-api
    secret: 4p1
    grant_type:client_credentials
   ```
## Tela Login
`http://localhost:8080/login`
 ``` sh
 usuario: ADMIN@TESTE.COM.BR
 senha: 123456
 ```
 
 ## Postman
  - Arquivo `PERSONAL.postman_collection.json` contém informações para request de Token.