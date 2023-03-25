package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {

    //CONSTANTS
    final Vector2 WINDOW_SIZE = new Vector2(500, 800);

    final Vector2 PLAYER_SIZE = new Vector2(50, 10);
    final Vector2 PLAYER_POSITION = new Vector2(this.WINDOW_SIZE.x / 2 - this.PLAYER_SIZE.x / 2, 15);
    final Vector2 PLAYER_VELOCITY = new Vector2(8, 8);
    final Color PLAYER_COLOR = new Color(1, 1, 1, 1);
    final float BALL_RADIUS = 10f;
    final Vector2 BALL_POSITION = new Vector2(this.WINDOW_SIZE.x / 2 - this.BALL_RADIUS / 2, this.WINDOW_SIZE.y / 2 - this.BALL_RADIUS / 2);
    final Vector2 BALL_VELOCITY = new Vector2(6, 6);
    final Color BALL_COLOR = new Color(1, 1, 1, 1);
    final Vector2 BRICK_NUMBER = new Vector2(5, 9);
    final Vector2 BRICK_SIZE = new Vector2(50, 15);
    final Vector2 BRICK_POSITION = new Vector2(15, this.WINDOW_SIZE.y - 50);
    final Color BRICK_COLOR = new Color(1, 1, 1, 1);

    //VARIABLES

    //OBJECTS
    Ball ball;
    Player player;
    BrickGroup bricks;


    @Override
    public void create() {

        //WINDOW
        Gdx.graphics.setWindowedMode((int) this.WINDOW_SIZE.x, (int) this.WINDOW_SIZE.y);
        Gdx.graphics.setResizable(false);

        //OBJECT INNIT
        this.ball = new Ball(this.BALL_POSITION.x, this.BALL_POSITION.y, this.BALL_VELOCITY.x, this.BALL_VELOCITY.y, this.BALL_RADIUS, this.BALL_COLOR);
        this.player = new Player(this.PLAYER_POSITION.x, this.PLAYER_POSITION.y, 0, 0, this.PLAYER_SIZE.x, this.PLAYER_SIZE.y, this.PLAYER_COLOR);
        this.bricks = new BrickGroup(this.BRICK_NUMBER, this.BRICK_POSITION, this.BRICK_SIZE, this.BRICK_COLOR);


    }

    @Override
    public void render() {

        //CLEAR SCREEN
        ScreenUtils.clear(0f, 0f, 0f, 1f);

        //PLAYER
        this.player.render();
        this.player.move();
        this.player.wallCollide(this.WINDOW_SIZE);

        //BALL
        this.ball.render();
        this.ball.move();
        this.ball.wallCollide(this.WINDOW_SIZE);
        this.ball.playerCollide(this.player);

        //BRICKS
        this.bricks.render();
        this.bricks.collideBall(this.ball);


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

    }
}
