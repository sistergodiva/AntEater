package se.snrn.anteater.player.playerstates;

import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.anteater.player.Player;
import se.snrn.anteater.player.PlayerInput;
import se.snrn.anteater.player.PlayerState;

import static se.snrn.anteater.player.PlayerInput.*;

public class LandState implements PlayerState {

    private Player player;

    public LandState(Player player) {
        this.player = player;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public PlayerState handleInput(PlayerInput input) {


        if(input == LEFT_STOP){
            return new GroundState(player);
        }
        if(input == RIGHT_STOP){
            return new GroundState(player);
        }
        if(input == IDLE){
            return new GroundState(player);
        }

        return null;
    }

    @Override
    public void enter() {
        if(player.getVelocity().x == 0){
            player.handleInput(IDLE);
        }
        if(player.getVelocity().x >0 ){
            player.handleInput(RIGHT_START);
        }

        if(player.getVelocity().x <0 ){
            player.handleInput(LEFT_START);
        }
    }

    @Override
    public void exit() {

    }

    @Override
    public void render(Batch batch) {

    }
}
