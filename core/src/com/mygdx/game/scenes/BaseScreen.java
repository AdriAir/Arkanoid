package com.mygdx.game.scenes;

import com.badlogic.gdx.Screen;
import com.mygdx.game.Main;

public abstract class BaseScreen implements Screen {

    protected Main game;

    public BaseScreen(Main game) {

        this.game = game;

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
