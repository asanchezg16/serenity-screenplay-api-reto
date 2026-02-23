package com.choucair.api.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class TheResponseBody implements Question<String> {

    private final String jsonPath;

    private TheResponseBody(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    public static TheResponseBody field(String jsonPath) {
        return new TheResponseBody(jsonPath);
    }

    public static TheResponseBody asText() {
        return new TheResponseBody(null);
    }

    @Override
    public String answeredBy(Actor actor) {
        if (jsonPath != null) {
            return LastResponse.received()
                    .answeredBy(actor)
                    .path(jsonPath)
                    .toString();
        } else {
            return LastResponse.received()
                    .answeredBy(actor)
                    .asString();
        }
    }
}
