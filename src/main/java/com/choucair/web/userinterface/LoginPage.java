package com.choucair.web.userinterface;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginPage {
    
    public static final Target USERNAME_INPUT = Target.the("Campo de usuario")
            .located(By.id("user-name"));
    
    public static final Target PASSWORD_INPUT = Target.the("Campo de contraseña")
            .located(By.id("password"));
    
    public static final Target LOGIN_BUTTON = Target.the("Botón de login")
            .located(By.id("login-button"));
    
    public static final Target ERROR_MESSAGE = Target.the("Mensaje de error")
            .located(By.cssSelector("[data-test='error']"));
    
    public static final Target PRODUCTS_TITLE = Target.the("Título de productos")
            .located(By.className("title"));
}
