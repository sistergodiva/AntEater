package se.snrn.anteater;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import se.snrn.anteater.player.Player;

import static se.snrn.anteater.player.PlayerInput.*;

public class InputManager implements InputProcessor{

    private Player player;

    public InputManager(Player player) {
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.A){
            player.handleInput(LEFT_START);
        }
        if(keycode == Input.Keys.D){
            player.handleInput(RIGHT_START);
        }

        if(keycode == Input.Keys.SPACE){
            player.handleInput(JUMP_START);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.A){
            player.handleInput(LEFT_STOP);
        }
        if(keycode == Input.Keys.D){
            player.handleInput(RIGHT_STOP);
        }


        if(keycode == Input.Keys.SPACE){
            player.handleInput(JUMP_STOP);
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
