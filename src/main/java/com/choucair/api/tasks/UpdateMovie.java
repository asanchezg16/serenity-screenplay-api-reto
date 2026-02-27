package com.choucair.api.tasks;

import com.choucair.api.models.MovieModel;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;

import static io.restassured.http.ContentType.JSON;

public class UpdateMovie implements Task {

    private final String movieId;
    private final MovieModel movie;
    private final String token;

    public UpdateMovie(String movieId, MovieModel movie, String token) {
        this.movieId = movieId;
        this.movie = movie;
        this.token = token;
    }

    public static UpdateMovie withId(String movieId, MovieModel movie, String token) {
        return new UpdateMovie(movieId, movie, token);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Put.to("/movies/" + movieId)
                        .with(request -> request
                                .contentType(JSON)
                                .header("Authorization", "Bearer " + token)
                                .body(movie))
        );
    }
}