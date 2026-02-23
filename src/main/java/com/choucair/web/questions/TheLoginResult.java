package com.choucair.web.questions;

import com.choucair.web.userinterface.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class TheLoginResult implements Question<String> {
    
    public static TheLoginResult displayed() {
        return new TheLoginResult();
    }
    
    @Override
    public String answeredBy(Actor actor) {
        return Text.of(LoginPage.PRODUCTS_TITLE).answeredBy(actor);
    }
}
