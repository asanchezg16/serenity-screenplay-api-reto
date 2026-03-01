#language: es
@apis @movies
Característica: API de Movies

  Como usuario de la API de Movies
  Quiero gestionar películas
  Para mantener el catálogo actualizado

  Background:
  Dado que estoy autenticado en la API
  Y existe una película creada

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
  Escenario: Actualizar una película exitosamente
    Cuando actualizo la película con título "Avengers Updated" año 2012 género "Action" director "Joss Whedon" y calificación 4
    Entonces el código de respuesta debería ser 204
    Y la película debería tener el título "Avengers Updated" y el director "Joss Whedon"


  @delete_movie
  Escenario: Eliminar una película exitosamente
    Dado que estoy autenticado en la API
    Y existe una película creada
    Cuando elimino la película creada
    Entonces el código de respuesta debería ser 204
    ##Y la respuesta debería contener "Movie deleted successfully"

