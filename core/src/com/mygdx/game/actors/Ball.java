package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.config.BallConfig;
import com.mygdx.game.config.GameConfig;
import com.mygdx.game.config.PlayerConfig;

public class Ball extends Actor {

    private Texture texture;
    private World world;
    private Body body;
    private Fixture fixture;

    public Ball(World world, Texture texture, Vector2 position) {

        this.world = world;
        this.texture = texture;

        //Creating Body
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(position);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        this.body = this.world.createBody(bodyDef);

        //Creating Collider
        CircleShape circle = new CircleShape();
        circle.setRadius(BallConfig.BALL_RADIUS);

        this.fixture = body.createFixture(circle, 5);
        this.fixture.setUserData("ball");
        circle.dispose();

        //Setting Size
        this.setSize(BallConfig.BALL_RADIUS * 2, BallConfig.BALL_RADIUS * 2);
        //Setting Starting Pos
        setPosition(position.x, position.y);


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        this.setPosition(this.getX() + this.body.getLinearVelocity().x, this.getY() + this.body.getLinearVelocity().y);

        batch.draw(texture, getX(), getY(), getWidth(), getHeight());

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        //Movement

    }

    public void dispose() {

        this.body.destroyFixture(this.fixture);
        this.world.destroyBody(this.body);
        this.texture.dispose();

    }

    public boolean wallCollide() {

        if (this.getX() + this.getWidth() > GameConfig.WORLD_SIZE_IN_METER.x) {

            this.setX(GameConfig.WORLD_SIZE_IN_METER.x - this.getWidth());
            return false;

        } else if (this.getX() < 0) {

            this.setX(0);
            return false;
        }

        return true;
    }

}
