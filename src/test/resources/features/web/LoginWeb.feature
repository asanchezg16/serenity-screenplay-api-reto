# language: es
@web @login
Característica: Login en aplicación web
  Como usuario de SauceDemo
  Quiero poder iniciar sesión
  Para acceder a la tienda

  Antecedentes:
    Dado que el usuario navega a la página de login

  @successful_login
  Escenario: Login exitoso con credenciales válidas
    Cuando ingresa las credenciales válidas
      | username      | password     |
      | standard_user | secret_sauce |
    Entonces debería ver la página de productos
