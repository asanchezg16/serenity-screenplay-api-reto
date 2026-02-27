package com.choucair.api.tasks;

import com.choucair.api.models.MovieModel;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static io.restassured.http.ContentType.JSON;

public class CreateMovie implements Task {

    private final MovieModel movie;
    private final String token;

    public CreateMovie(MovieModel movie, String token) {
        this.movie = movie;
        this.token = token;
    }

    public static CreateMovie withData(MovieModel movie, String token) {
        return new CreateMovie(movie, token);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/movies")
                        .with(request -> request
                                .contentType(JSON)
                                .header("Authorization", "Bearer " + token)
                                .body(movie))
        );
    }
}
