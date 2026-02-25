package com.choucair.web.questions;

import com.choucair.web.userinterface.ProductsPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isPresent;

public class TheCartBadge implements Question<String> {

    public static TheCartBadge value() {
        return new TheCartBadge();
    }

    @Override
    public String answeredBy(Actor actor) {
        Target badge = ProductsPage.CART_BADGE;

        // Serenity espera automáticamente, pero verificamos que exista
        actor.attemptsTo(WaitUntil.the(badge, isPresent()));

        return Text.of(badge).answeredBy(actor);
    }
}