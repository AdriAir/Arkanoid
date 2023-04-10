package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.game.Main;
import com.mygdx.game.config.GameConfig;

public class MenuScreen extends BaseScreen {

    private Stage stage;
    private Skin skin;
    private TextButton playButton;
    private TextButton exitButton;
    private TextButton optionButton;

    private Label title;

    public MenuScreen(Main game) {
        super(game);

        //Skins and Camera
        this.stage = new Stage(new FillViewport(GameConfig.WINDOW_SIZE_IN_PIXEL.x, GameConfig.WINDOW_SIZE_IN_PIXEL.y));
        this.skin = new Skin(Gdx.files.internal("Skin/uiskin.json"));

        //Widgets
        this.playButton = new TextButton("Jugar", this.skin);
        this.exitButton = new TextButton("Salir", this.skin);
        this.optionButton = new TextButton("Opciones", this.skin);
        this.title = new Label("ARKANOID", this.skin);

        //Styles
        this.title.setSize(200, 50);
        this.title.setPosition(GameConfig.WINDOW_SIZE_IN_PIXEL.x / 2 - this.title.getWidth() / 2, GameConfig.WINDOW_SIZE_IN_PIXEL.y / 2 + this.title.getHeight() * 5);
        this.title.setAlignment(1);

        this.playButton.setSize(200, 50);
        this.playButton.setPosition(GameConfig.WINDOW_SIZE_IN_PIXEL.x / 2 - this.playButton.getWidth() / 2, GameConfig.WINDOW_SIZE_IN_PIXEL.y / 2);

        this.exitButton.setSize(200, 50);
        this.exitButton.setPosition(GameConfig.WINDOW_SIZE_IN_PIXEL.x / 2 - this.exitButton.getWidth() / 2, GameConfig.WINDOW_SIZE_IN_PIXEL.y / 2 - this.exitButton.getHeight() * 4);

        this.optionButton.setSize(200, 50);
        this.optionButton.setPosition(GameConfig.WINDOW_SIZE_IN_PIXEL.x / 2 - this.optionButton.getWidth() / 2, GameConfig.WINDOW_SIZE_IN_PIXEL.y / 2 - this.optionButton.getHeight() * 2);

        this.stage.addActor(this.title);
        this.stage.addActor(this.playButton);
        this.stage.addActor(this.exitButton);
        this.stage.addActor(this.optionButton);

        //ACTIONS
        this.playButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                MenuScreen.super.game.setScreen(MenuScreen.super.game.getInGameScreen());

            }
        });

        this.optionButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //NOTHING YET
            }
        });

        this.exitButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                MenuScreen.super.game.dispose();
                Gdx.app.exit();
            }
        });

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this.stage);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.stage.act();
        this.stage.draw();

    }

    @Override
    public void dispose() {

        this.skin.dispose();
        this.stage.dispose();

    }
}
