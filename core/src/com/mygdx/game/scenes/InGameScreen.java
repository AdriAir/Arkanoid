package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.game.Main;
import com.mygdx.game.actors.Player;
import com.mygdx.game.config.GameConfig;
import com.mygdx.game.sources.GameContactListener;

public class InGameScreen extends BaseScreen {

    private Stage stage;
    private World world;
    private Player player;
    private Sound edgeHitSound;
    private Sound scoreSound;
    private Sound gameOverSound;
    private Music backGroundMusic;
    private Vector3 cameraPosition;

    public InGameScreen(Main game) {

        super(game);

        //New Scene2D
        this.stage = new Stage(new FillViewport(GameConfig.WORLD_SIZE_IN_METER.x, GameConfig.WORLD_SIZE_IN_METER.y));
        this.cameraPosition = new Vector3(this.stage.getCamera().position);

        //New World
        this.world = new World(new Vector2(0, 0), true);
        this.world.setContactListener(new GameContactListener());

        //Get Sounds/Music Sources
        this.scoreSound = super.game.getAssets().get("Sounds/ScoreSound.ogg", Sound.class);
        this.edgeHitSound = super.game.getAssets().get("Sounds/edgeCollisionSound.ogg", Sound.class);
        this.gameOverSound = super.game.getAssets().get("Sounds/gameOverSound.ogg", Sound.class);
        this.backGroundMusic = super.game.getAssets().get("Music/Arcade Dwellers.ogg", Music.class);

    }

    @Override
    public void show() {

        //Creating Actors
        this.player = new Player(this.world, super.game.getAssets().get("Textures/player.png", Texture.class), new Vector2(2f, 10f));

        //Adding Actors
        this.stage.addActor(this.player);

        //Camera Settings
        this.stage.getCamera().position.set(this.cameraPosition);
        this.stage.getCamera().update();

        //Play Music
        this.backGroundMusic.setVolume(0.02f);
        this.backGroundMusic.play();

    }

    @Override
    public void hide() {

        this.stage.clear();
        this.player.dispose();

    }

    @Override
    public void render(float delta) {
        // Cleaning Screen
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Updating Stage and Actors
        this.stage.act();
        this.player.act(delta);

        //Updating World
        this.world.step(delta, 6, 2);

        //Game Actions

        // Render Stage
        stage.draw();
    }

    @Override
    public void dispose() {

        this.stage.dispose();
        this.world.dispose();

    }

}
