package com.choucair.web.tasks;

import com.choucair.web.userinterface.ProductsPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isPresent;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class AddProductToCart implements Task {

    private final String productName;

    public AddProductToCart(String productName) {
        this.productName = productName;
    }

    public static AddProductToCart called(String productName) {
        return instrumented(AddProductToCart.class, productName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(ProductsPage.ADD_TO_CART_BUTTON(productName), isPresent()).forNoMoreThan(10).seconds(),  // ← Espera explícita
                Click.on(ProductsPage.ADD_TO_CART_BUTTON(productName))
        );
    }
}