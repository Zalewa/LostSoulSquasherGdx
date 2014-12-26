package lostsoulsquasher.factormodifier;

import lostsoulsquasher.event.EventArgs;

public class FactorModifierActorAddedEventArgs extends EventArgs {
    private FactorModifierActor actor;

    public FactorModifierActorAddedEventArgs(FactorModifierActor actor) {
        this.actor = actor;
    }

    public FactorModifierActor getActor() {
        return actor;
    }
}
