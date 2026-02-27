package com.choucair.api.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

public class DeleteMovie implements Task {

    private final String movieId;
    private final String token;

    public DeleteMovie(String movieId, String token) {
        this.movieId = movieId;
        this.token = token;
    }

    public static DeleteMovie withId(String movieId, String token) {
        return new DeleteMovie(movieId, token);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from("/movies/" + movieId)
                        .with(request -> request
                                .header("Authorization", "Bearer " + token))
        );
    }
}