package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {

    //CONSTANTS
    private final Vector2 WINDOW_SIZE = new Vector2(500, 800);

    private final Vector2 PLAYER_SIZE = new Vector2(50, 10);
    private final Vector2 PLAYER_POSITION = new Vector2(this.WINDOW_SIZE.x / 2 - this.PLAYER_SIZE.x / 2, 25);
    private final Vector2 PLAYER_VELOCITY = new Vector2(6f, 0f);
    private final Color PLAYER_COLOR = new Color((float) Math.random(), (float) Math.random(), (float) Math.random(), 1);
    private final float BALL_RADIUS = 10f;
    private final Vector2 BALL_POSITION = new Vector2(this.WINDOW_SIZE.x / 2 - this.BALL_RADIUS / 2, 500);
    private final Vector2 BALL_VELOCITY = new Vector2(2.5f, 2.5f);
    private final Color BALL_COLOR = new Color((float) Math.random(), (float) Math.random(), (float) Math.random(), 1);
    private final Vector2 BRICK_NUMBER = new Vector2(15, 8);
    private final Vector2 BRICK_SIZE = new Vector2(50, 15);
    private final Vector2 BRICK_POSITION = new Vector2(25, this.WINDOW_SIZE.y - 50);
    private final Vector2 SCORE_POSITION = new Vector2(10, this.WINDOW_SIZE.y - 10);


    //VARIABLES
    private int score = 0;
    private float red = 0f;
    private float green = 0f;
    private float blue = 0f;
    private boolean reverse;

    //OBJECTS
    private Ball ball;
    private Player player;
    private BrickGroup bricks;
    private SpriteBatch batch;
    private BitmapFont font;
    private GameMusic backGroundMusic;
    private GameSound scoreSound;
    private GameSound gameOverSound;
    private GameSound edgeSound;


    @Override
    public void create() {

        //WINDOW
        Gdx.graphics.setWindowedMode((int) this.WINDOW_SIZE.x, (int) this.WINDOW_SIZE.y);
        Gdx.graphics.setResizable(false);

        //OBJECT INNIT
        this.ball = new Ball(this.BALL_POSITION.x, this.BALL_POSITION.y, this.BALL_VELOCITY.x, this.BALL_VELOCITY.y, this.BALL_RADIUS, this.BALL_COLOR);
        this.player = new Player(this.PLAYER_POSITION.x, this.PLAYER_POSITION.y, 0, 0, this.PLAYER_SIZE.x, this.PLAYER_SIZE.y, this.PLAYER_COLOR);
        this.bricks = new BrickGroup(this.BRICK_NUMBER, this.BRICK_POSITION, this.BRICK_SIZE);
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.backGroundMusic = new GameMusic("Music/Arcade Dwellers.ogg", 0.02f, true);
        this.edgeSound = new GameSound("Sounds/edgeCollisionSound.ogg");
        this.scoreSound = new GameSound("Sounds/ScooreSound.ogg");
        this.gameOverSound = new GameSound("Sounds/gameOverSound.ogg");

        this.backGroundMusic.play();


    }

    @Override
    public void render() {

        int ballCollisionEdge;

        if (this.red >= 0.4f){

            this.reverse = true;

        } else if (this.red <= 0.2f){

            this.reverse = false;

        }

        if (this.reverse){

            this.red -= 0.002f;
            this.green -= 0;
            this.blue -= 0.002f;

        } else {

            this.red += 0.002f;
            this.green += 0;
            this.blue += 0.002f;

        }

        //CLEAR SCREEN
        ScreenUtils.clear(this.red,this.green,this.blue,1f);

        //PLAYER
        this.player.move();
        if (this.player.wallCollide(this.WINDOW_SIZE)){

            //this.edgeSound.play(0.2f);

        }
        this.player.render();

        //BALL
        this.ball.move();
        ballCollisionEdge = this.ball.wallCollide(this.WINDOW_SIZE);

        if (ballCollisionEdge == 1 || ballCollisionEdge == 2 || ballCollisionEdge == 4) {

            //HITS LEFTS/RIGHT/TOP EDGES
            this.edgeSound.play(0.08f);

        }

        if (ballCollisionEdge == 3) {

            //IF LOOSE, STOP BK MUSIC AND PLAY GAMEOVER SOUND
            this.backGroundMusic.stop();
            this.gameOverSound.play(0.05f);

        }

        if (this.ball.playerCollide(this.player)){

            this.edgeSound.play(0.08f);

        }
        this.ball.render();

        //BRICKS
        if (this.bricks.collideBall(this.ball)) {

            //SCORE
            score++;
            this.scoreSound.play(0.005f);

        }
        this.bricks.render();

        //BATCH DRAWS
        batch.begin();

        font.draw(batch, "Puntuación: " + score, this.SCORE_POSITION.x, this.SCORE_POSITION.y);

        batch.end();

        //KEY_LISTENER
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {

            this.player.setVel(new Vector2(-this.PLAYER_VELOCITY.x, 0));

        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {

            this.player.setVel(new Vector2(this.PLAYER_VELOCITY.x, 0));

        } else {

            this.player.setVel(new Vector2(0, 0));

        }
    }

    @Override
    public void dispose() {

        this.player.dispose();
        this.ball.dispose();
        this.bricks.dispose();
        this.backGroundMusic.dispose();
        this.edgeSound.dispose();
        this.scoreSound.dispose();
        this.gameOverSound.dispose();

    }
}
