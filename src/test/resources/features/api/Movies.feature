#language: es
@api @movies
Característica: API de Movies

  Como usuario de la API de Movies
  Quiero gestionar películas
  Para mantener el catálogo actualizado

  @get_all_movies
  Escenario: Obtener lista de películas
    Cuando consulto la lista de películas
    Entonces el código de respuesta debería ser 200
    Y la respuesta debería contener películas

  @create_movie
  Escenario: Crear una película
    Cuando creo una película con título "Avengers" año 2012 género "Action" director "Joss Whedon" y calificación 5
    Entonces el código de respuesta debería ser 201
    Y la respuesta debería contener "A movie has been saved"

  @update_movie
  Escenario: Actualizar una película
    Cuando actualizo la película con título "Avengers Updated" año 2012 género "Action" director "Joss Whedon" y calificación 4
    Entonces el código de respuesta debería ser 204


  @delete_movie
  Escenario: Eliminar una película
    Cuando elimino la película creada
    Entonces el código de respuesta debería ser 200

@crud?movies
    Cuando creo una película con título "Avengers" año 2012 género "Action" director "Joss Whedon" y calificación 5
    Cuando actualizo la película con título "Avengers Updated" año 2012 género "Action" director "Joss Whedon" y calificación 4
    Cuando elimino la película creada
    Entonces el código de respuesta debería ser 200
