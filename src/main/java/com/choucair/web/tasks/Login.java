package com.choucair.web.tasks;

import com.choucair.web.models.LoginModel;
import com.choucair.web.userinterface.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import org.openqa.selenium.By;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isPresent;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

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

                // 🔥 Esperar que el campo usuario exista primero
                WaitUntil.the(LoginPage.USERNAME_INPUT, isVisible())
                        .forNoMoreThan(15).seconds(),

                Enter.theValue(credentials.getUsername()).into(LoginPage.USERNAME_INPUT),
                Enter.theValue(credentials.getPassword()).into(LoginPage.PASSWORD_INPUT),
                Click.on(LoginPage.LOGIN_BUTTON)
        );
    }
}