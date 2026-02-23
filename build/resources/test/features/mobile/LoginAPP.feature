@mobile @Login
Feature: Se desea ingresar a la aplicacion swaglabs
  As Nicolas
  I want login into swaglabs
  So that I can used the app

  @LoginExitoso
  Scenario Outline: Se desea realizar login exitoso swaglabs para poder verificar el balance
    Given que Nicolas desea abrir la aplicacion swaglabs
    When se ingresan credenciales correctamente
      |usuario|contrasena|
      |<usuario>|<contrasena>|
    Then podremos visualizar la pantalla de home y ver nuestro balance <mensaje>

    Examples:
      |usuario|contrasena|mensaje|
      |standard_user|secret_sauce|PRODUCTS|

