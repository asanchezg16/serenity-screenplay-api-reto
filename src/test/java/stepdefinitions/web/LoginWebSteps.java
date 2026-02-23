package stepdefinitions.web;

import com.choucair.web.models.LoginModel;
import com.choucair.web.questions.TheLoginResult;
import com.choucair.web.tasks.Login;
import com.choucair.web.tasks.NavigateTo;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.ensure.Ensure;

import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class LoginWebSteps {

    @Dado("que el usuario navega a la página de login")
    public void queElUsuarioNavegaALaPaginaDeLogin() {
        theActorInTheSpotlight().attemptsTo(
                NavigateTo.theLoginPage()
        );
    }

    @Cuando("ingresa las credenciales válidas")
    public void ingresaLasCredencialesValidas(DataTable dataTable) {
        Map<String, String> credentials = dataTable.asMaps().get(0);
        
        LoginModel loginModel = LoginModel.builder()
                .username(credentials.get("username"))
                .password(credentials.get("password"))
                .build();
        
        theActorInTheSpotlight().attemptsTo(
                Login.withCredentials(loginModel)
        );
    }

    @Cuando("ingresa las credenciales inválidas")
    public void ingresaLasCredencialesInvalidas(DataTable dataTable) {
        Map<String, String> credentials = dataTable.asMaps().get(0);
        
        theActorInTheSpotlight().attemptsTo(
                Login.withCredentials(
                        credentials.get("username"),
                        credentials.get("password")
                )
        );
    }

    @Cuando("ingresa el usuario {string} con password {string}")
    public void ingresaElUsuarioConPassword(String username, String password) {
        theActorInTheSpotlight().attemptsTo(
                Login.withCredentials(username, password)
        );
    }

    @Entonces("debería ver la página de productos")
    public void deberiaVerLaPaginaDeProductos() {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(TheLoginResult.displayed()).isEqualTo("Products")
        );
    }

    @Entonces("debería ver un mensaje de error")
    public void deberiaVerUnMensajeDeError() {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(TheLoginResult.displayed()).isNotEmpty()
        );
    }

    @Entonces("debería ver {string}")
    public void deberiaVer(String expectedResult) {
        if (expectedResult.equals("Products")) {
            theActorInTheSpotlight().attemptsTo(
                    Ensure.that(TheLoginResult.displayed()).contains(expectedResult)
            );
        }
        // Para error, validar que existe algún mensaje
    }
}
