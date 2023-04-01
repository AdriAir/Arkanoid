package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Brick implements GlobalActions {


    private final Vector2 pos;
    private final Vector2 size;
    private final ShapeRenderer body;


    public Brick(Vector2 pos, Vector2 size, Color color) {

        this.pos = pos;
        this.size = size;
        this.body = new ShapeRenderer();
        this.body.setColor(color);

    }

    @Override
    public void dispose() {

        this.body.dispose();

    }

    @Override
    public void render() {

        this.body.begin(ShapeRenderer.ShapeType.Filled);

        this.body.rect(this.pos.x, this.pos.y, this.size.x, this.size.y);

        this.body.end();

    }

    public boolean collideBall(Ball ball) {

        boolean xCollision = true;
        boolean yCollision = true;

        //DESDE LA DERECHA DEL BRICK
        if (ball.getPos().x - ball.getRadius() > this.pos.x + this.size.x) {

            xCollision = false;

        }

        //DESDE LA IZQUIERDA DEL BRICK
        if (ball.getPos().x + ball.getRadius() < this.pos.x) {

            xCollision = false;

        }

        //DESDE ARRIBA DEL BRICK
        if (ball.getPos().y - ball.getRadius() > this.pos.y + this.size.y) {

            yCollision = false;

        }

        //DESDE ABAJO DEL BRICK
        if (ball.getPos().y + ball.getRadius() < this.pos.y) {

            yCollision = false;

        }


        if (xCollision & yCollision) {

            if (ball.getPos().y + ball.getRadius() >= this.pos.y + 2) {

                ball.setDirection(new Vector2(ball.getDirection().x * -1, ball.getDirection().y));

            } else {

                ball.setDirection(new Vector2(ball.getDirection().x, ball.getDirection().y * -1));

            }
        }

        return xCollision & yCollision;

    }

}
