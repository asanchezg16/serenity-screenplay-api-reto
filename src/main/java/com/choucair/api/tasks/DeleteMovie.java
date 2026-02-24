package com.choucair.api.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

public class DeleteMovie implements Task {

    private final String movieId;

    public DeleteMovie(String movieId) {
        this.movieId = movieId;
    }

    public static DeleteMovie withId(String movieId) {
        return new DeleteMovie(movieId);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from("/movies/" + movieId)
        );
    }
}