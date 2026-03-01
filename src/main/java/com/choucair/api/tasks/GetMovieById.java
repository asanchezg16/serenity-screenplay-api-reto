package com.choucair.api.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetMovieById implements Task {

    private final String movieId;
    private final String token;

    public GetMovieById(String movieId, String token) {
        this.movieId = movieId;
        this.token = token;
    }

    public static GetMovieById with(String movieId, String token) {
        return instrumented(GetMovieById.class, movieId, token);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/movies/" + movieId)
                        .with(request -> request
                                .header("Authorization", "Bearer " + token)
                        )
        );
    }
}