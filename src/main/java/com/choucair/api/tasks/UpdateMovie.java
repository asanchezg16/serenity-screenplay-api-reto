package com.choucair.api.tasks;

import com.choucair.api.models.MovieModel;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;

import static io.restassured.http.ContentType.JSON;

public class UpdateMovie implements Task {

    private final String movieId;
    private final MovieModel movie;

    public UpdateMovie(String movieId, MovieModel movie) {
        this.movieId = movieId;
        this.movie = movie;
    }

    public static UpdateMovie withId(String movieId, MovieModel movie) {
        return new UpdateMovie(movieId, movie);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Put.to("/movies/" + movieId)
                        .with(request -> request
                                .contentType(JSON)
                                .body(movie))
        );
    }
}