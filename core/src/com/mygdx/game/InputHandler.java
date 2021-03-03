package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputHandler extends ApplicationAdapter implements InputProcessor {

    public boolean dragging = false;
    public boolean touching = false;
    public float touchX = 0f;
    public float touchY = 0f;

    public float w = Gdx.graphics.getWidth();
    public float h = Gdx.graphics.getHeight();

    public InputHandler(){
        Gdx.input.setInputProcessor(this);
    }

    @Override public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        // ignore if its not left mouse button or first touch pointer
        if (button != Input.Buttons.LEFT || pointer > 0) return false;
        touchX = screenX;
        touchY = h - screenY;
        dragging = true;
        touching = true;
        return true;
    }

    @Override public boolean touchDragged (int screenX, int screenY, int pointer) {
        if (!dragging) return false;
        //camera.unproject(tp.set(screenX, screenY, 0));
        touchX = screenX;
        touchY = h - screenY;
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        touchX = screenX;
        touchY = screenY;
        return false;
    }

    @Override public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) return false;
        //camera.unproject(tp.set(screenX, screenY, 0));
        dragging = false;
        touching = false;
        return true;
    }

    @Override public void resize (int width, int height) {
        // viewport must be updated for it to work properly
        //viewport.update(width, height, true);
    }


    @Override public boolean keyDown (int keycode) {
        return false;
    }

    @Override public boolean keyUp (int keycode) {
        return false;
    }

    @Override public boolean keyTyped (char character) {
        return false;
    }

    @Override public boolean scrolled (int amount) {
        return false;
    }
}
