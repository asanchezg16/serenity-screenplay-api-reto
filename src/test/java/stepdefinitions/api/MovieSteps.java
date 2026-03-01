package stepdefinitions.api;

import com.choucair.api.models.MovieModel;
import com.choucair.api.questions.TheMovieData;
import com.choucair.api.questions.TheResponseBody;
import com.choucair.api.tasks.*;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.rest.SerenityRest;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MovieSteps {

    @Dado("que estoy autenticado en la API")
    public void queEstoyAutenticadoEnLaAPI() {
        theActorInTheSpotlight().attemptsTo(
                RegisterUser.withCredentials("test@gmail.com", "mysupersecretpassword")
        );
    }

    @Y("existe una película creada")
    public void existeUnaPeliculaCreada() {
        String token = theActorInTheSpotlight().recall("TOKEN");
        MovieModel movie = new MovieModel("Temp Movie", 2020, "Drama", "Director Test", 4);

        theActorInTheSpotlight().attemptsTo(
                CreateMovie.withData(movie, token)
        );

        // Guardamos el ID en la memoria del actor
        String id = lastResponse().jsonPath().getString("movieId");
        theActorInTheSpotlight().remember("MOVIE_ID", id);
    }

    @Cuando("consulto la lista de películas")
    public void consultoLaListaDePeliculas() {
        theActorInTheSpotlight().attemptsTo(GetMovies.fromAPI());
    }

    @Cuando("creo una película con título {string} año {int} género {string} director {string} y calificación {int}")
    public void creoUnaPelicula(String title, int year, String genre, String director, int rate) {
        // Aseguramos token antes de crear
        theActorInTheSpotlight().attemptsTo(
                RegisterUser.withCredentials("test@gmail.com", "mysupersecretpassword")
        );
        String token = theActorInTheSpotlight().recall("TOKEN");

        MovieModel movie = new MovieModel(title, year, genre, director, rate);
        theActorInTheSpotlight().attemptsTo(
                CreateMovie.withData(movie, token)
        );
    }

    @Cuando("actualizo la película con título {string} año {int} género {string} director {string} y calificación {int}")
    public void actualizoLaPelicula(String title, int year, String genre, String director, int rate) {
        // 1. Obtener Token
        theActorInTheSpotlight().attemptsTo(
                RegisterUser.withCredentials("test@gmail.com", "mysupersecretpassword")
        );
        String token = theActorInTheSpotlight().recall("TOKEN");

        // 2. Crear película base para obtener un ID real y evitar el /null
        MovieModel movieBase = new MovieModel("Base Movie", 2024, "Action", "Director", 5);
        theActorInTheSpotlight().attemptsTo(
                CreateMovie.withData(movieBase, token)
        );

        // 3. Capturar el ID de la respuesta del POST
        String idParaActualizar = lastResponse().jsonPath().getString("movieId");
        theActorInTheSpotlight().remember("MOVIE_ID", idParaActualizar);

        // 4. Ejecutar la actualización (PUT)
        MovieModel movieUpdated = new MovieModel(title, year, genre, director, rate);
        theActorInTheSpotlight().attemptsTo(
                UpdateMovie.withId(idParaActualizar, movieUpdated, token)
        );
    }

    @Cuando("elimino la película creada")
    public void eliminoLaPeliculaCreada() {
        // 1. Login para obtener el token (Evita el 401)
        theActorInTheSpotlight().attemptsTo(
                RegisterUser.withCredentials("test@gmail.com", "mysupersecretpassword")
        );
        String token = theActorInTheSpotlight().recall("TOKEN");

        // 2. Crear una película base para tener algo que borrar (Evita el /null)
        MovieModel movieBase = new MovieModel("Movie To Delete", 2024, "Action", "Director", 5);
        theActorInTheSpotlight().attemptsTo(
                CreateMovie.withData(movieBase, token)
        );

        // 3. Capturar el ID de esa película que acabamos de crear
        String idParaEliminar = net.serenitybdd.rest.SerenityRest.lastResponse().jsonPath().getString("movieId");

        System.out.println("ID QUE VAMOS A ELIMINAR: " + idParaEliminar);

        // 4. Ejecutar la tarea de eliminar
        theActorInTheSpotlight().attemptsTo(
                DeleteMovie.withId(idParaEliminar, token)
        );
    }

    @Y("la película debería tener el título {string} y el director {string}")
    public void laPeliculaDeberiaTenerLosDatosActualizados(String titulo, String director) {
        String token = theActorInTheSpotlight().recall("TOKEN");
        String id = theActorInTheSpotlight().recall("MOVIE_ID");

        // 1. Hacemos un GET para traer la película actualizada
        theActorInTheSpotlight().attemptsTo(
                GetMovieById.with(id, token)
        );

        // 2. Verificamos los campos específicos usando la Question
        theActorInTheSpotlight().should(
                seeThat("El título actualizado",
                        act -> TheMovieData.fetched().answeredBy(act).getTitle(),
                        equalTo(titulo)),
                seeThat("El director actualizado",
                        act -> TheMovieData.fetched().answeredBy(act).getDirector(),
                        equalTo(director))
        );
    }

    @Entonces("el código de respuesta debería ser {int}")
    public void elCodigoDeRespuestaDeberiaSer(Integer statusCode) {
        assertThat(lastResponse().getStatusCode(), is(statusCode));
    }

    @Y("la respuesta debería contener {string}")
    public void laRespuestaDeberiaContener(String texto) {
        theActorInTheSpotlight().should(
                seeThat("La respuesta contiene " + texto,
                        TheResponseBody.asText(),
                        containsString(texto))
        );
    }

    @Y("la respuesta debería contener películas")
    public void laRespuestaDeberiaContenerPeliculas() {
        theActorInTheSpotlight().should(
                seeThat("La respuesta contiene películas",
                        TheResponseBody.asText(),
                        containsString("title"))
        );
    }
}