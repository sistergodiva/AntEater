package se.snrn.anteater.player.playerstates;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import se.snrn.anteater.player.Player;
import se.snrn.anteater.player.PlayerInput;
import se.snrn.anteater.player.PlayerState;


import static se.snrn.anteater.player.PlayerInput.LEFT_STOP;
import static se.snrn.anteater.player.PlayerInput.RIGHT_STOP;


public class JumpState implements PlayerState {
    private Player player;

    public JumpState(Player player) {

        this.player = player;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public PlayerState handleInput(PlayerInput input) {
        if(input == PlayerInput.LEFT_START){
            player.getVelocity().x = -5;
            return null;

        }
        if(input == PlayerInput.RIGHT_START){
            player.getVelocity().x = 5;
            return null;

        }
        if (input == LEFT_STOP) {
            player.getVelocity().x = MathUtils.clamp(player.getVelocity().x, 0, 99);
            return null;
        }
        if (input == RIGHT_STOP) {
            player.getVelocity().x = MathUtils.clamp(player.getVelocity().x, -99, 0);
            return null;
        }

        if (input == PlayerInput.JUMP_STOP) {
            return new AirState(player);
        }

        if (input == PlayerInput.LAND) {
            return new GroundState(player);
        }
        return null;
    }

    @Override
    public void enter() {
        player.addYVelocity(20);
    }

    @Override
    public void exit() {

    }

    @Override
    public void render(Batch batch) {

    }
}
