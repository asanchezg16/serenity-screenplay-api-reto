package com.choucair.api.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class GetMovieById implements Task {

    private final String movieId;

    public GetMovieById(String movieId) {
        this.movieId = movieId;
    }

    public static GetMovieById withId(String movieId) {
        return new GetMovieById(movieId);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/movies/" + movieId)
        );
    }
}
