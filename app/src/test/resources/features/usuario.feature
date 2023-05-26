# language: pt

Funcionalidade: Gerenciamento de um usuário na Petstore

  Algum context de negócio
  Histórias do Jira
  Qualquer coisa que faça sentido pro negócio

  Cenario: Cria um usuario na loja
    Quando eu faco um POST para /v3/user com os seguintes valores:
      | id         | 10               |
      | username   | rafael           |
      | firstName  | Rafael           |
      | lastName   | Lima             |
      | email      | rafael@email.com |
      | password   | 12345            |
      | phone      | 991234567        |
      | userStatus | 1                |
    Entao quando faco um GET para /v3/user/rafael, o usuário criado é retornado

  Cenario: Cria um usuario na loja usando docstring
    Quando faco um POST para /v3/user com a seguinte docstring:
    """json
    {
    "id": 10,
    "username": "theUser",
    "firstName": "John",
    "lastName": "James",
    "email": "john@email.com",
    "password": "12345",
    "phone": "12345",
    "userStatus": 1
    }
    """
    Entao quando faco um GET para /v3/user/theUser, o usuário criado é retornado

  Cenario: Cria usuario na loja refletindo o negocio
    Quando crio um usuario
    Entao o usuario é salvo no sistema
