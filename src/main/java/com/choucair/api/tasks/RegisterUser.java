package com.choucair.api.tasks;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import static io.restassured.http.ContentType.JSON;

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

        // 👇 AGREGA ESTAS LÍNEAS DE DEBUG PARA VER EL TOKEN EN CONSOLA
        // En RegisterUser.java, dentro del performAs:
        String response = SerenityRest.lastResponse().asString();

// Esto busca "accessToken" y si no lo encuentra, busca "token"
        String token = SerenityRest.lastResponse().jsonPath().getString("accessToken");
        if (token == null) {
            token = SerenityRest.lastResponse().jsonPath().getString("token");
        }

        actor.remember("TOKEN", token);
    }
}