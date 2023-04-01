package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Ball implements GlobalActions {

    private final Vector2 pos;
    private final Vector2 vel;
    private final ShapeRenderer body;
    private final float radius;
    private Vector2 direction;

    public Ball(float posX, float posY, float velX, float velY, float radius, Color color) {


        this.vel = new Vector2(velX, velY);
        this.radius = radius;
        this.pos = new Vector2(posX, posY);
        this.body = new ShapeRenderer();
        this.body.setColor(color);

        //Asignamos el primer ángulo aleatorio entre 225 y 315 grados
        double angle;
        do {
            angle = Math.toRadians((int) (Math.random() * (315 - 225) + 225));
        } while (angle >= Math.toRadians(250) && angle <= Math.toRadians(290));


        //Generamos las dX y dY usando el ángulo y la velocidad;
        this.direction = new Vector2((float) (Math.cos(angle) * this.vel.x), (float) (Math.sin(angle) * this.vel.y));

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

    public Vector2 getPos() {
        return pos;
    }

    public float getRadius() {
        return radius;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }

    public void move() {

        //Moverse en X y en Y en una dirección a una velocidad
        this.pos.x += this.direction.x * this.vel.x;
        this.pos.y += this.direction.y * this.vel.y;

    }

    public int wallCollide(Vector2 WINDOW_SIZE) {

        int collisionPoint = 0;

        // Comprobamos si la pelota choca con el borde izquierdo
        if (this.pos.x - this.radius <= 0) {
            this.direction.x *= -1;
            collisionPoint = 1;
        }

        // Comprobamos si la pelota choca con el borde derecho
        if (this.pos.x + this.radius >= WINDOW_SIZE.x) {
            this.direction.x *= -1;
            collisionPoint = 2;
        }

        // Comprobamos si la pelota choca con el borde inferior
        if (this.pos.y - this.radius <= 0) {

            //PIERDE
            vel.set(new Vector2(0, 0));

            //Si pongo 0, suena el gmae over en bucle
            pos.y = 1 + this.radius;
            collisionPoint = 3;
        }

        // Comprobamos si la pelota choca con el borde superior
        if (this.pos.y + this.radius >= WINDOW_SIZE.y) {
            this.direction.y *= -1;
            collisionPoint = 4;
        }

        return collisionPoint;

    }

    public boolean playerCollide(Player player) {

        boolean xCollision = true;
        boolean yCollision = true;

        //DESDE LA DERECHA DEL PLAYER
        if (this.pos.x - this.radius + 2 > player.getPos().x + player.getSize().x) {

            xCollision = false;

        }

        //DESDE LA IZQUIERDA DEL PLAYER
        if (this.pos.x + this.radius - 2 < player.getPos().x) {

            xCollision = false;

        }

        //DESDE ARRIBA DEL PLAYER
        if (this.pos.y - this.radius + 2 > player.getPos().y + player.getSize().y) {

            yCollision = false;

        }

        //DESDE ABAJO DEL PLAYER
        if (this.pos.y + this.radius - 2 < player.getPos().y) {

            yCollision = false;

        }


        if (xCollision & yCollision) {

            if (this.pos.y - this.radius <= player.getPos().y + 2) {

                this.direction.x *= -1;

            } else {

                this.direction.y *= -1;

            }

            return true;

        }

        return false;
    }

}

