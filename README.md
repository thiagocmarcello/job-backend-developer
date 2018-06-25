# autenticacao-api

Um serviõ de autenticação usando Spring Boot.


## Configuração de datasource

  datasource:
    url: jdbc:postgresql://localhost:5432/autenticacao
    username: meutreino
    password: meutreino


## Start da aplicação

Na raiz da aplicação, executar:
  ```bash
  sudo mvn install dockerfile:build
  sudo docker run -p 8080:8080 -t spring/autenticacao-api
  ```

## Login
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