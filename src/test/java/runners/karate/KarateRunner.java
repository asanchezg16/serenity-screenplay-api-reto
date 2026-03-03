package runners.karate;

import com.intuit.karate.junit5.Karate;

class KarateRunner {


    @Karate.Test // Esta anotación le dice a JUnit 5: "Esto es una prueba ejecutable"
    Karate testMovies() {
        // Buscamos el archivo .feature dentro de la carpeta resources/karate
        return Karate.run("classpath:karate/movies.feature");
    }
}