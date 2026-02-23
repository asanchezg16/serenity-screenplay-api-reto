package com.choucair.web.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class NavigateTo implements Task {
    
    private final String url;
    
    public NavigateTo(String url) {
        this.url = url;
    }
    
    public static NavigateTo theLoginPage() {
        return instrumented(NavigateTo.class, "https://www.saucedemo.com");
    }
    
    public static NavigateTo url(String url) {
        return instrumented(NavigateTo.class, url);
    }
    
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url(url));
    }
}
