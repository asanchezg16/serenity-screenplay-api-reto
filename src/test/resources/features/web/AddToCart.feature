# language: es
@web @cart
Característica: Agregar productos al carrito
  Como usuario de SauceDemo
  Quiero poder agregar productos al carrito
  Para posteriromente realziar una compra

  Antecedentes:
    Dado que el usuario está autenticado en la aplicación

  @smoke @cart
  Escenario: Agregar un producto al carrito exitosamente
    Cuando el usuario agrega el producto "Sauce Labs Backpack" al carrito
    Entonces el carrito debería mostrar 1 producto
    Y el producto "Sauce Labs Backpack" debería estar en el carrito