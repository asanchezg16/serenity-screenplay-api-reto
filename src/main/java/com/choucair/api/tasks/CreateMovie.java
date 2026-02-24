package com.choucair.api.tasks;

import com.choucair.api.models.MovieModel;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static io.restassured.http.ContentType.JSON;

public class CreateMovie implements Task {

    private final MovieModel movie;

    public CreateMovie(MovieModel movie) {
        this.movie = movie;
    }

    public static CreateMovie withData(MovieModel movie) {
        return new CreateMovie(movie);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/movies")
                        .with(request -> request
                                .contentType(JSON)
                                .body(movie))
        );
    }
}
