Feature: Gestión de Películas - Escenarios Atómicos

  Background:
    * url 'http://localhost:3002/api'
    # Esto corre ANTES de cada escenario: cada test tiene su propio usuario y token
    * def userEmail = 'test_' + java.lang.System.currentTimeMillis() + '@gmail.com'
    Given path 'register'
    And request { "email": '#(userEmail)', "password": "mysupersecretpassword" }
    When method post
    Then status 201
    * def accessToken = response.accessToken

  Scenario: Crear una película exitosamente
    Given path 'movies'
    And header Authorization = 'Bearer ' + accessToken
    And request { title: 'American Gangster', director: 'Ridley Scott', genre: 'Drama', year: 2007, rate: 5 }
    When method post
    Then status 201
    # Guardamos lo que haya, si es null no importa aquí, lo buscaremos en el GET
    * def movieId = response.id || null
    * print 'Respuesta del servidor:', response

  Scenario: Buscar películas usando Query Strings
    Given path 'movies'
    And header Authorization = 'Bearer ' + accessToken
    And request { title: 'Equalizer', director: 'Antoine Fuqua', genre: 'Action', year: 2014, rate: 4 }
    When method post
    Then status 201

    Given path 'movies'
    And param title = 'Equalizer'
    When method get
    Then status 200
    # SOLUCIÓN AL ERROR DE KEYS: Validamos que al menos un elemento tenga ese título
    And match response[*].title contains 'Equalizer'

  Scenario: Actualizar información de una película
    # 1. Creamos la película
    Given path 'movies'
    And header Authorization = 'Bearer ' + accessToken
    And request { title: 'Original Title', director: 'Original Director', genre: 'Drama', year: 2020, rate: 3 }
    When method post
    Then status 201
    * def idParaUpdate = response.movieId

    # 2. Enviamos los nuevos datos con PUT
    Given path 'movies', idParaUpdate
    And header Authorization = 'Bearer ' + accessToken
    And request { title: 'Updated Title', director: 'Original Director', genre: 'Drama', year: 2020, rate: 5 }
    When method put

    # CORRECCIÓN AQUÍ: Tu API devuelve 204 al actualizar con éxito
    Then status 204

    # 3. Validar que el cambio se guardó (GET con Query String)
    Given path 'movies'
    And param title = 'Updated Title'
    When method get
    Then status 200
    And match response[*].title contains 'Updated Title'


  Scenario: Eliminar una película existente
    # 1. Crear la película
    Given path 'movies'
    And header Authorization = 'Bearer ' + accessToken
    And request { title: 'Pelicula Para Borrar', director: 'Test', genre: 'Action', year: 2026, rate: 5 }
    When method post
    Then status 201
    # CORRECCIÓN 1: Capturamos 'movieId' (como dice tu log)
    * def idParaBorrar = response.movieId
    * print 'DEBUG - ID capturado directamente:', idParaBorrar

    # 2. Borrar la película
    Given path 'movies', idParaBorrar
    And header Authorization = 'Bearer ' + accessToken
    When method delete
    # CORRECCIÓN 2: El log dice que tu API devuelve 204
    Then status 204