package com.choucair.web.questions;

import com.choucair.web.userinterface.CartPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;

public class TheProductInCart implements Question<Boolean> {

    private final String productName;

    public TheProductInCart(String productName) {
        this.productName = productName;
    }

    public static TheProductInCart called(String productName) {
        return new TheProductInCart(productName);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return Visibility.of(CartPage.PRODUCT_NAME(productName)).answeredBy(actor);
    }
}

//¿Cuál es la diferencia entre Text.of() y Visibility.of()?"
//Respuesta: "Text.of() extrae el texto de un elemento y retorna un String.
// Visibility.of() verifica si un elemento es visible en la página y retorna
// un Boolean (true o false). Uso Text.of() cuando necesito el contenido del elemento,
// y Visibility.of() cuando solo necesito saber si existe y es visible."