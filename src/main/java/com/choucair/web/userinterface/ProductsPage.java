package com.choucair.web.userinterface;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ProductsPage {

    public static Target ADD_TO_CART_BUTTON(String productName) {
        // Convertir "Sauce Labs Backpack" → "sauce-labs-backpack"
        String productId = productName.toLowerCase().replace(" ", "-");

        return Target.the("botón agregar al carrito de " + productName)
                .located(By.cssSelector("[data-test='add-to-cart-" + productId + "']"));
    }
    public static final Target CART_BADGE = Target.the("contador del carrito")
            .located(By.cssSelector("[data-test='shopping-cart-badge']"));

    public static final Target CART_ICON = Target.the("ícono del carrito")
            .located(By.cssSelector("[data-test='shopping-cart-link']"));
}
