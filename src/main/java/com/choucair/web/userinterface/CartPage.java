package com.choucair.web.userinterface;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CartPage {

    public static Target PRODUCT_NAME(String productName) {
        return Target.the("producto " + productName + " en el carrito")
                .located(By.xpath("//div[@data-test='inventory-item-name' and text()='" + productName + "']"));
    }
}

//Pregunta: "¿Por qué usaste XPath aquí si antes dijiste que era el último recurso?"
//Respuesta: "XPath es el último recurso cuando solo necesitas buscar por un atributo.
// Pero cuando necesitas combinar atributo + texto, XPath es la mejor opción porque CSS Selector
// no puede hacerlo fácilmente. En este caso necesitaba validar que el producto específico esté
// en el carrito, no solo que exista el elemento."