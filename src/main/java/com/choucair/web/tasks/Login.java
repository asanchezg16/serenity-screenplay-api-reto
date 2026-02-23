package com.choucair.web.tasks;

import com.choucair.web.models.LoginModel;
import com.choucair.web.userinterface.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Login implements Task {
    
    private final LoginModel credentials;
    
    public Login(LoginModel credentials) {
        this.credentials = credentials;
    }
    
    public static Login withCredentials(LoginModel credentials) {
        return instrumented(Login.class, credentials);
    }
    
    public static Login withCredentials(String username, String password) {
        LoginModel model = LoginModel.builder()
                .username(username)
                .password(password)
                .build();
        return instrumented(Login.class, model);
    }
    
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(credentials.getUsername()).into(LoginPage.USERNAME_INPUT),
                Enter.theValue(credentials.getPassword()).into(LoginPage.PASSWORD_INPUT),
                Click.on(LoginPage.LOGIN_BUTTON)
        );
    }
}
