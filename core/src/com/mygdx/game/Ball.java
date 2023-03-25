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
        this.collider = new Ellipse2D.Double(this.pos.x, this.pos.y, this.radius / 2, this.radius / 2);
        this.angle = Math.toRadians((int) (Math.random()*(315-225)+225));
        this.direction = new Vector2((float) (this.vel.x * Math.cos(this.angle)), (float) (this.vel.y * Math.sin(this.angle)));

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


        this.direction.x = (float) (this.vel.x * Math.cos(this.angle));
        this.direction.y = (float) (this.vel.y * Math.sin(this.angle));
        this.pos.x += this.direction.x;
        this.pos.y += this.direction.y;
        this.collider.setFrame(this.pos.x, this.pos.y, this.radius / 2, this.radius / 2);

    }

    public void changeAngle(int collision) {

        double newAngle = 0;

        if (collision == 1 && this.direction.y < 0) {

            // Choca con el borde izquierdo y va hacia abajo
            //Hacia abajo a la derecha

            newAngle = Math.toRadians((int) (Math.random()*(315-180)+180));

        } else if (collision == 1 && this.direction.y >= 0) {

            // Choca con el borde izquierdo y va hacia arriba
            //Hacia arriba a la derecha

            newAngle = Math.toRadians((int) (Math.random()*(90-45)+45));

        } else if (collision == 2 && this.direction.y < 0) {

            // Choca con el borde derecho y va hacia abajo
            //Hacia abajo a la izquierda

            newAngle = Math.toRadians((int) (Math.random()*(180-225)+225));

        } else if (collision == 2 && this.direction.y >= 0) {

            // Choca con el borde derecho y va hacia arriba
            //Hacia arriba a la izquierda

            newAngle = Math.toRadians((int) (Math.random()*(135-180)+180));


        } else if (collision == 3 && this.direction.x < 0) {

            // Choca con el borde inferior y va hacia la izquierda
            //Hacia arriba a la izquierda

            newAngle = Math.toRadians((int) (Math.random()*(315-180)+180));


        } else if (collision == 3 && this.direction.x >= 0) {

            // Choca con el borde inferior y va hacia la derecha
            //Hacia arriba a la derecha

            newAngle = Math.toRadians((int) (Math.random()*(90-45)+45));

        } else if (collision == 4 && this.direction.x < 0) {

            // Choca con el borde superior y va hacia la izquierda
            //Hacia abajo a la izquierda

            newAngle = Math.toRadians((int) (Math.random()*(180-225)+225));

        } else if (collision == 4 && this.direction.x >= 0) {

            // Choca con el borde superior y va hacia la derecha
            //Hacia abajo a la derecha

            newAngle = Math.toRadians((int) (Math.random()*(315-180)+180));

        } else if (collision == 0){

            //No cambiamos nada
            newAngle = this.angle;

        }

        this.angle = newAngle;

    }

    public int wallCollide(Vector2 WINDOW_SIZE) {

        int collision = 0;

        // Comprobamos si la pelota choca con el borde izquierdo
        if (this.pos.x - this.radius <= 0) {
            collision = 1;
        }

        // Comprobamos si la pelota choca con el borde derecho
        if (this.pos.x + this.radius >= WINDOW_SIZE.x) {
            collision = 2;
        }

        // Comprobamos si la pelota choca con el borde inferior
        if (this.pos.y - this.radius <= 0) {
            collision = 3;
        }

        // Comprobamos si la pelota choca con el borde superior
        if (this.pos.y + this.radius >= WINDOW_SIZE.y) {
            collision = 4;
        }

        this.changeAngle(collision);
        return collision;

    }

    public boolean playerCollide(Player player) {

        if (this.collider.intersects(player.getCollider())) {

            //Choca con el jugador
            this.angle = (Math.random() * Math.PI / 2 - Math.PI / 4);
            this.direction = new Vector2((float) (this.vel.x * Math.cos(this.angle)), (float) (this.vel.y * Math.sin(this.angle)));
            return true;

        }

        return false;

    }

}

