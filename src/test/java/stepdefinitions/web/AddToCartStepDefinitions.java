package stepdefinitions.web;

import com.choucair.web.questions.TheCartBadge;
import com.choucair.web.questions.TheProductInCart;
import com.choucair.web.tasks.AddProductToCart;
import com.choucair.web.tasks.Login;
import com.choucair.web.models.LoginModel;
import com.choucair.web.tasks.NavigateTo;
import com.choucair.web.userinterface.ProductsPage;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.actions.Click;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

public class AddToCartStepDefinitions {


    @Dado("que el usuario está autenticado en la aplicación")
    public void queElUsuarioEstaAutenticadoEnLaAplicacion() {
        LoginModel loginModel = LoginModel.builder()
                .username("standard_user")
                .password("secret_sauce")
                .build();

        theActorInTheSpotlight().attemptsTo(
                NavigateTo.theLoginPage(),
                Login.withCredentials(loginModel)
        );
    }

    @Cuando("el usuario agrega el producto {string} al carrito")
    public void elUsuarioAgregaElProductoAlCarrito(String productName) {
        theActorInTheSpotlight().attemptsTo(
                AddProductToCart.called(productName)
        );
    }

    @Entonces("el carrito debería mostrar {int} producto")
    public void elCarritoDeberiaMostrarProducto(Integer quantity) {
        theActorInTheSpotlight().should(
                seeThat(TheCartBadge.value(), equalTo(quantity.toString()))
        );
    }

    @Y("el producto {string} debería estar en el carrito")
    public void elProductoDeberiaEstarEnElCarrito(String productName) {
        theActorInTheSpotlight().attemptsTo(
                Click.on(ProductsPage.CART_ICON)
        );

        theActorInTheSpotlight().should(
                seeThat(TheProductInCart.called(productName), equalTo(true))
        );
    }
}