package com.choucair.api.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static io.restassured.http.ContentType.JSON;
import static net.serenitybdd.rest.SerenityRest.lastResponse;

public class RegisterUser implements Task {

    private final String email;
    private final String password;

    public RegisterUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static RegisterUser withCredentials(String email, String password) {
        return new RegisterUser(email, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/register")
                        .with(request -> request
                                .contentType(JSON)
                                .body("{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}"))
        );
    }

    public static String getToken() {
        return lastResponse().path("accessToken");
    }
}
