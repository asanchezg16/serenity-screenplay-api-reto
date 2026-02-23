package stepdefinitions.conf;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.model.environment.SystemEnvironmentVariables;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class Hooks {

    @Before(order = 0)
    public void setTheStage(io.cucumber.java.Scenario scenario) {
        // Detecta el tipo de test por tags
        if (scenario.getSourceTagNames().contains("@api")) {
            // API: Sin WebDriver
            OnStage.setTheStage(Cast.whereEveryoneCan(new Ability[0]));
        } else {
            // Web/Mobile: Con WebDriver (configurado por environment)
            OnStage.setTheStage(new OnlineCast());
        }
    }

    @Before(value = "@api", order = 1)
    public void setupApiActor() {
        String baseUrl = SystemEnvironmentVariables.createEnvironmentVariables()
                .getProperty("rest.api.baseurl");
        if (baseUrl == null) {
            baseUrl = "https://rickandmortyapi.com/api";
        }
        theActorCalled("Usuario API").whoCan(CallAnApi.at(baseUrl));
    }

    @Before(value = "@web", order = 1)
    public void setupWebActor() {
        theActorCalled("Usuario Web");
    }

    @Before(value = "@mobile", order = 1)
    public void setupMobileActor() {
        String env = System.getProperty("environment");
        System.out.println("🔍 Hooks - Environment detectado: " + env);

        theActorCalled("Usuario Mobile");
    }

    @After
    public void tearDown(io.cucumber.java.Scenario scenario) {
        OnStage.drawTheCurtain();
    }
}