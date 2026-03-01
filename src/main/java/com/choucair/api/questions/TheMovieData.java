package com.choucair.api.questions;

import com.choucair.api.models.MovieModel;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class TheMovieData implements Question<MovieModel> {

    @Override
    public MovieModel answeredBy(Actor actor) {
        // Esto transforma el JSON de la respuesta en un objeto MovieModel
        return SerenityRest.lastResponse().as(MovieModel.class);
    }

    public static TheMovieData fetched() {
        return new TheMovieData();
    }
}