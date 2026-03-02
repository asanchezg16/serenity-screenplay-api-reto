package stepdefinitions.conf;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.model.util.EnvironmentVariables;
import net.thucydides.model.environment.SystemEnvironmentVariables;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class Hooks {


    @Before(order = 0)
    public void setTheStage(Scenario scenario) {
        // Verificamos si el test actual es de API por sus tags
        if (scenario.getSourceTagNames().contains("@api")) {
            // Cast.whereEveryoneCan(new Ability[0]) asegura que NO se inicie WebDriver
            OnStage.setTheStage(Cast.whereEveryoneCan(new Ability[0]));
        } else {
            // OnlineCast es obligatorio para Web/Mobile para que Serenity gestione el Driver
            OnStage.setTheStage(new OnlineCast());
        }
    }

    @Before(order = 1)
    public void setupActor(Scenario scenario) {
        // Configuramos el actor según el tipo de prueba
        if (scenario.getSourceTagNames().contains("@web")) {
            theActorCalled("Usuario Web");
        }
        else if (scenario.getSourceTagNames().contains("@api")) {
            EnvironmentVariables envVars = SystemEnvironmentVariables.createEnvironmentVariables();
            String baseUrl = envVars.getProperty("rest.api.baseurl");
            if (baseUrl == null) {
                baseUrl = "http://localhost:3002/api"; // Tu URL base por defecto
            }
            // Creamos el actor de API con su habilidad específica
            theActorCalled("Usuario API").whoCan(CallAnApi.at(baseUrl));
        }
    }

    @After
    public void tearDown() {
        // drawTheCurtain cierra el navegador si existe y limpia el Stage
        OnStage.drawTheCurtain();
    }
}