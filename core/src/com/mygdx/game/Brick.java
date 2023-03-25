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


    public Brick(Vector2 pos, Vector2 size, Color color) {

        this.pos = pos;
        this.size = size;
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

}
