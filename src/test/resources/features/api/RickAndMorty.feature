#language: es
@api @rickandmorty
Característica: API de Rick and Morty

  Como fan de Rick and Morty
  Quiero consultar información de personajes
  Para conocer detalles del universo de la serie

  @get_all_characters
  Escenario: Obtener lista de personajes
    Cuando consulto la lista de personajes
    Entonces el código de respuesta debería ser 200
    Y la respuesta debería contener personajes
    Y el campo "info.count" debería ser mayor a 0

  @get_character_by_id
  Escenario: Obtener un personaje específico
    Cuando consulto el personaje con ID 1
    Entonces el código de respuesta debería ser 200
    Y el campo "name" debería ser "Rick Sanchez"
    Y el campo "status" debería ser "Alive"
    Y el campo "species" debería ser "Human"

  @search_by_name
  Escenario: Buscar personajes por nombre
    Cuando busco personajes con el nombre "Morty"
    Entonces el código de respuesta debería ser 200
    Y la respuesta debería contener al menos 1 personaje
    Y todos los personajes deberían contener "Morty" en su nombre

  @search_with_filters
  Escenario: Buscar personajes con filtros
    Cuando busco personajes con nombre "Rick" y estado "Alive"
    Entonces el código de respuesta debería ser 200
    Y todos los personajes deberían tener estado "Alive"

  @character_not_found
  Escenario: Consultar personaje inexistente
    Cuando consulto el personaje con ID 99999
    Entonces el código de respuesta debería ser 404