package se.snrn.anteater.player;

import se.snrn.anteater.Renderable;
import se.snrn.anteater.Updatable;

public interface PlayerState extends Updatable, Renderable {
    PlayerState handleInput(PlayerInput input);
    void enter();
    void exit();
}
