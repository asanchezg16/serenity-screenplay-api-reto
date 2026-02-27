package stepdefinitions.api;

import com.choucair.api.models.MovieModel;
import com.choucair.api.questions.TheResponseBody;
import com.choucair.api.tasks.*;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Y;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.*;

public class MovieSteps {

    private String movieId;
    private String token;

    @Cuando("consulto la lista de películas")
    public void consultoLaListaDePeliculas() {
        theActorInTheSpotlight().attemptsTo(
                GetMovies.fromAPI()
        );
    }

    @Cuando("creo una película con título {string} año {int} género {string} director {string} y calificación {int}")
    public void creoUnaPelicula(String title, int year, String genre, String director, int rate) {
        theActorInTheSpotlight().attemptsTo(
                RegisterUser.withCredentials("test@gmail.com", "mysupersecretpassword")
        );
        token = RegisterUser.getToken();
        MovieModel movie = new MovieModel(title, year, genre, director, rate);
        theActorInTheSpotlight().attemptsTo(
                CreateMovie.withData(movie, token)
        );
        movieId = TheResponseBody.field("movieId").answeredBy(theActorInTheSpotlight());
    }

    @Cuando("actualizo la película con título {string} año {int} género {string} director {string} y calificación {int}")
    public void actualizoLaPelicula(String title, int year, String genre, String director, int rate) {
        MovieModel movie = new MovieModel(title, year, genre, director, rate);
        theActorInTheSpotlight().attemptsTo(
                UpdateMovie.withId(movieId, movie, token)
        );
    }

    @Cuando("elimino la película creada")
    public void eliminoLaPeliculaCreada() {
        theActorInTheSpotlight().attemptsTo(
                DeleteMovie.withId(movieId, token)
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
    @Y("la respuesta debería contener {string}")
    public void laRespuestaDeberiaContener(String texto) {
        theActorInTheSpotlight().should(
                seeThat("La respuesta contiene " + texto,
                        TheResponseBody.asText(),
                        containsString(texto))
        );
    }
}