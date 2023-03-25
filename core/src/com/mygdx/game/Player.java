package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.awt.geom.Rectangle2D;

public class Player extends Actor implements GlobalActions{

    private Vector2 pos;
    private Vector2 vel;
    private Vector2 size;
    private ShapeRenderer body;
    private float radius;
    private Rectangle2D collider;


    public Player(float posX, float posY, float velX, float velY, float width, float heigth, Color color) {

        this.pos = new Vector2(posX,posY);
        this.vel = new Vector2(velX, velY);
        this.size = new Vector2(width, heigth);
        this.radius = 5f;
        this.body = new ShapeRenderer();
        this.body.setColor(color);
        this.collider = new Rectangle2D.Double(this.pos.x, this.pos.y, this.size.x, this.size.y);

    }


    @Override
    public void dispose() {

        this.body.dispose();

    }

    @Override
    public void render() {

        this.body.begin(ShapeRenderer.ShapeType.Filled);

        //Rectangulo
        this.body.rect(this.pos.x + this.radius, this.pos.y + this.radius, this.size.x - 2*this.radius, this.size.y - 2*this.radius);

        //Lados
        this.body.rect(this.pos.x + radius, this.pos.y, this.size.x - 2*radius, radius);
        this.body.rect(this.pos.x + this.size.x - radius, this.pos.y + radius, radius, this.size.y - 2*radius);
        this.body.rect(this.pos.x + radius, this.pos.y + this.size.y - radius, this.size.x - 2*radius, radius);
        this.body.rect(this.pos.x, this.pos.y + radius, radius, this.size.y - 2*radius);

        //Esquinas con arco
        this.body.arc(this.pos.x + radius, this.pos.y + radius, radius, 180f, 90f);
        this.body.arc(this.pos.x + this.size.x - radius, this.pos.y + radius, radius, 270f, 90f);
        this.body.arc(this.pos.x + this.size.x - radius, this.pos.y + this.size.y - radius, radius, 0f, 90f);
        this.body.arc(this.pos.x + radius, this.pos.y + this.size.y - radius, radius, 90f, 90f);

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

    public void move(){

        this.pos.add(vel);
        this.collider.setRect(this.pos.x, this.pos.y, this.size.x, this.size.y);

    }

    public boolean wallCollide(Vector2 WINDOW_SIZE) {

        if(this.pos.x + this.size.x > WINDOW_SIZE.x){

            this.pos.x = WINDOW_SIZE.x - this.size.x;
            return true;

        } else if (this.pos.x < 0) {

            this.pos.x = 0;
            return true;

        }else {

            return false;

        }

    }

}
