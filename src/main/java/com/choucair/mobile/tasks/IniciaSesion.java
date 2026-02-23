package com.choucair.mobile.tasks;

import com.choucair.mobile.models.LoginModel;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static com.choucair.mobile.userinterface.UILogin.*;

public class IniciaSesion implements Task {

    LoginModel loginData;

    public IniciaSesion(LoginModel loginData) {
        this.loginData = loginData;
    }

    public static IniciaSesion correctamente (LoginModel loginData){
        return Tasks.instrumented(IniciaSesion.class, loginData);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(USERNAME_TXT),
                Enter.theValue(loginData.getUsuario()).into(USERNAME_TXT),
                Enter.theValue(loginData.getContrasena()).into(PASSWORD_TXT),
                Click.on(LOGIN_BTN)
        );
    }
}