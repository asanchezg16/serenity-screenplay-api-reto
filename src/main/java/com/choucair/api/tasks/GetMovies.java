package com.choucair.api.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class GetMovies implements Task {

    public static GetMovies fromAPI() {   //fromAPI trae las peliculas que hay en la api
        return new GetMovies();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/movies")
        );
    }
}