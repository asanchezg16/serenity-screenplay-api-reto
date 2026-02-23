package com.choucair.api.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetCharacters implements Task {

    private final String name;
    private final String status;

    public GetCharacters(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public static GetCharacters fromAPI() {
        return instrumented(GetCharacters.class, null, null);
    }

    public static GetCharacters withName(String name) {
        return instrumented(GetCharacters.class, name, null);
    }

    public static GetCharacters withFilters(String name, String status) {
        return instrumented(GetCharacters.class, name, status);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/character")
                        .with(request -> {
                            if (name != null) request.queryParam("name", name);
                            if (status != null) request.queryParam("status", status);
                            return request;
                        })
        );
    }
}
