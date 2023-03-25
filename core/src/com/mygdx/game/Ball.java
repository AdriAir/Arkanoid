package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.awt.geom.Ellipse2D;

public class Ball implements GlobalActions {

    private Vector2 pos;
    private Vector2 vel;
    private ShapeRenderer body;
    private float radius;
    private Ellipse2D collider;

    public Ball(float posX, float posY, float velX, float velY, float radius, Color color) {

        this.pos = new Vector2(posX, posY);
        this.vel = new Vector2(velX, velY);
        this.radius = radius;
        this.body = new ShapeRenderer();
        this.body.setColor(color);
        this.collider = new Ellipse2D.Double(this.pos.x, this.pos.y, this.radius / 2, this.radius / 2);

    }

    @Override
    public void dispose() {

    }

    @Override
    public void render() {

        this.body.begin(ShapeRenderer.ShapeType.Filled);

        //Rectangulo
        this.body.circle(this.pos.x, this.pos.y, this.radius);

        this.body.end();

    }

    public Vector2 getVel() {
        return vel;
    }

    public void setVel(Vector2 vel) {
        this.vel = vel;
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public Ellipse2D getCollider() {
        return collider;
    }

    public void setCollider(Ellipse2D collider) {
        this.collider = collider;
    }

    public void move() {

        this.pos.add(vel);
        this.collider.setFrame(this.pos.x, this.pos.y, this.radius / 2, this.radius / 2);

    }

    public boolean wallCollide(Vector2 WINDOW_SIZE) {

        if (this.pos.x + this.radius > WINDOW_SIZE.x || this.pos.x < 0) {

            this.vel.x *= -1;
            return true;

        } else if (this.pos.y + this.radius > WINDOW_SIZE.y || this.pos.y < 0) {

            this.vel.y *= -1;
            return true;

        } else {

            return false;

        }

    }

    public boolean playerCollide(Player player) {

        if (this.collider.intersects(player.getCollider())) {

            this.vel.y *= -1;
            this.vel.x *= -1;
            return true;

        }

        return false;

    }

    public boolean brickCollide(Brick brick) {

        if (this.collider.intersects(brick.getCollider())) {

            this.vel.y *= -1;
            this.vel.x *= -1;
            return true;

        }

        return false;

    }

}

