package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.awt.geom.Rectangle2D;

public class Brick implements GlobalActions {


    private Vector2 pos;
    private Vector2 size;
    private ShapeRenderer body;
    private Rectangle2D collider;


    public Brick(Vector2 pos, Vector2 size) {

        this.pos = pos;
        this.size = size;
        this.body = new ShapeRenderer();
        this.body.setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random(), 1f));
        this.collider = new Rectangle2D.Double(this.pos.x, this.pos.y, this.size.x, this.size.y);

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

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public Rectangle2D getCollider() {
        return collider;
    }

    public void setCollider(Rectangle2D collider) {
        this.collider = collider;
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
