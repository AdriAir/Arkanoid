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
    private Vector2 direction;
    private double angle;

    public Ball(float posX, float posY, float velX, float velY, float radius, Color color) {

        this.pos = new Vector2(posX, posY);
        this.vel = new Vector2(velX, velY);
        this.radius = radius;
        this.body = new ShapeRenderer();
        this.body.setColor(color);
        this.collider = new Ellipse2D.Double(this.pos.x, this.pos.y, this.radius * 2, this.radius * 2);

        //Asignamos el primer ángulo aleatorio entre 225 y 315 grados
        this.angle = Math.toRadians((int) (Math.random() * (315 - 225) + 225));

        //Generamos las dX y dY usando el ángulo y la velocidad;
        this.direction = new Vector2();

    }

    @Override
    public void dispose() {
        this.body.dispose();
    }

    @Override
    public void render() {

        this.body.begin(ShapeRenderer.ShapeType.Filled);

        //Círculo
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

        //Moverse en X y en Y en una dirección a una velocidad
        this.pos.x += this.direction.x * this.vel.x;
        this.pos.y += this.direction.y * this.vel.y;

        //Desplazar collider
        this.collider.setFrame(this.pos.x, this.pos.y, this.radius / 2, this.radius / 2);

    }

    public int wallCollide(Vector2 WINDOW_SIZE) {

        int collision = 0;

        // Comprobamos si la pelota choca con el borde izquierdo
        if (this.pos.x - this.radius <= 0) {
            collision = 1;
            this.direction.x *= -1;
        }

        // Comprobamos si la pelota choca con el borde derecho
        if (this.pos.x + this.radius >= WINDOW_SIZE.x) {
            collision = 2;
            this.direction.x *= -1;
        }

        // Comprobamos si la pelota choca con el borde inferior
        if (this.pos.y - this.radius <= 0) {
            collision = 3;
            this.direction.y *= -1;
        }

        // Comprobamos si la pelota choca con el borde superior
        if (this.pos.y + this.radius >= WINDOW_SIZE.y) {
            collision = 4;
            this.direction.y *= -1;
        }

        return collision;

    }

    public boolean playerCollide(Player player) {

        if (this.collider.intersects(player.getCollider())) {

            //Choca con el jugador
            //Recalcular dirección
            return true;

        }

        return false;

    }

}

