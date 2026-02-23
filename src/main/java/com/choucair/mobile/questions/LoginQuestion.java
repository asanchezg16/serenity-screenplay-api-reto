package com.choucair.mobile.questions;

import com.choucair.mobile.userinterface.UILogin;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class LoginQuestion implements Question <Boolean> {

    String mensaje;

    public LoginQuestion(String mensaje) {
        this.mensaje = mensaje;
    }

    public static LoginQuestion verifiedLogin(String mensaje) {
        return new LoginQuestion(mensaje);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        actor.attemptsTo(
                WaitUntil.the(UILogin.LOGIN_SUCCESSFULL_TXT, WebElementStateMatchers.isVisible()).forNoMoreThan(30).seconds());
        return mensaje.equals(Text.of(UILogin.LOGIN_SUCCESSFULL_TXT).answeredBy(actor));
    }
}
