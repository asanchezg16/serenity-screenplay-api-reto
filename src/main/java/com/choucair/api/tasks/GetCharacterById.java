package com.choucair.api.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetCharacterById implements Task {

    private final int characterId;

    public GetCharacterById(int characterId) {
        this.characterId = characterId;
    }

    public static GetCharacterById withId(int characterId) {
        return instrumented(GetCharacterById.class, characterId);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/character/" + characterId)
        );
    }
}