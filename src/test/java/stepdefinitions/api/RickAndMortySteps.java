package stepdefinitions.api;

import com.choucair.api.questions.TheResponseBody;
import com.choucair.api.questions.TheResponseStatusCode;
import com.choucair.api.tasks.GetCharacterById;
import com.choucair.api.tasks.GetCharacters;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.*;

public class RickAndMortySteps {

    @Cuando("consulto la lista de personajes")
    public void consultoLaListaDePersonajes() {
        theActorInTheSpotlight().attemptsTo(
                GetCharacters.fromAPI()
        );
    }

    @Cuando("consulto el personaje con ID {int}")
    public void consultoElPersonajeConID(Integer id) {
        theActorInTheSpotlight().attemptsTo(
                GetCharacterById.withId(id)
        );
    }

    @Cuando("busco personajes con el nombre {string}")
    public void buscoPersonajesConElNombre(String name) {
        theActorInTheSpotlight().attemptsTo(
                GetCharacters.withName(name)
        );
    }

    @Cuando("busco personajes con nombre {string} y estado {string}")
    public void buscoPersonajesConNombreYEstado(String name, String status) {
        theActorInTheSpotlight().attemptsTo(
                GetCharacters.withFilters(name, status)
        );
    }

    @Entonces("el código de respuesta debería ser {int}")
    public void elCodigoDeRespuestaDeberiaSer(Integer statusCode) {
        theActorInTheSpotlight().should(
                seeThat("El código de estado", TheResponseStatusCode.value(), equalTo(statusCode))
        );
    }

    @Y("la respuesta debería contener personajes")
    public void laRespuestaDeberiaContenerPersonajes() {
        theActorInTheSpotlight().should(
                seeThat("La respuesta contiene personajes",
                        TheResponseBody.asText(),
                        containsString("\"results\""))
        );
    }

    @Y("el campo {string} debería ser {string}")
    public void elCampoDeberiaSer(String campo, String valor) {
        theActorInTheSpotlight().should(
                seeThat("El campo " + campo,
                        TheResponseBody.field(campo),
                        equalTo(valor))
        );
    }

    @Y("el campo {string} debería ser mayor a {int}")
    public void elCampoDeberiaSerMayorA(String campo, Integer valor) {
        theActorInTheSpotlight().should(
                seeThat("El campo " + campo,
                        actor -> Integer.parseInt(TheResponseBody.field(campo).answeredBy(actor)),
                        greaterThan(valor))
        );
    }

    @Y("la respuesta debería contener al menos {int} personaje")
    public void laRespuestaDeberiaContenerAlMenosPersonaje(Integer cantidad) {
        theActorInTheSpotlight().should(
                seeThat("La cantidad de personajes",
                        TheResponseBody.asText(),
                        containsString("\"results\""))
        );
    }

    @Y("todos los personajes deberían contener {string} en su nombre")
    public void todosLosPersonajesDeberianContenerEnSuNombre(String texto) {
        theActorInTheSpotlight().should(
                seeThat("Los nombres contienen " + texto,
                        TheResponseBody.asText(),
                        containsString(texto))
        );
    }

    @Y("todos los personajes deberían tener estado {string}")
    public void todosLosPersonajesDeberianTenerEstado(String status) {
        theActorInTheSpotlight().should(
                seeThat("El estado de los personajes",
                        TheResponseBody.asText(),
                        containsString("\"status\":\"" + status + "\""))
        );
    }
}