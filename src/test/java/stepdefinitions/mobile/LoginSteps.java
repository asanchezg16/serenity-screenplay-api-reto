package stepdefinitions.mobile;

import com.choucair.mobile.models.LoginModel;
import com.choucair.mobile.questions.LoginQuestion;
import com.choucair.mobile.tasks.AbreLaApp;
import com.choucair.mobile.tasks.IniciaSesion;
import com.choucair.mobile.utils.DataUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class LoginSteps {

    @Given("^que (.*) desea abrir la aplicacion swaglabs")
    public void queDeseaabrirlaaplicacioneribank(String actor) {
        theActorInTheSpotlight().wasAbleTo(AbreLaApp.swaglabs());
    }

    @When("^se ingresan credenciales correctamente$")
    public void seIngresanCredencialesCorrectamente(DataTable data) {
        theActorInTheSpotlight().attemptsTo(IniciaSesion.correctamente(DataUtil.setData(data, LoginModel.class).getFirst()));
    }

    @Then("^podremos visualizar la pantalla de home y ver nuestro balance (.*)$")
    public void podremosvisualizarlapantalladehomeyvernuestrobalance$(String mensaje) {
        theActorInTheSpotlight().should(seeThat(LoginQuestion.verifiedLogin(mensaje)));
    }
}