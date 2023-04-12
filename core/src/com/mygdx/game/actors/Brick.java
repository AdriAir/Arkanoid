package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.config.BrickConfig;
import com.mygdx.game.config.PlayerConfig;

public class Brick extends Actor {

    private Texture texture;
    private World world;
    private Body body;
    private Fixture fixture;

    public Brick(World world, Texture texture, Vector2 position) {

        this.world = world;
        this.texture = texture;

        //Creating Body
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(position);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        this.body = this.world.createBody(bodyDef);

        //Creating Collider
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(BrickConfig.SIZE_IN_METER.x / 2, BrickConfig.SIZE_IN_METER.y / 2);

        this.fixture = body.createFixture(rectangle, 5);
        this.fixture.setUserData("brick");
        rectangle.dispose();

        //Setting Size
        this.setSize(BrickConfig.SIZE_IN_METER.x, BrickConfig.SIZE_IN_METER.y);
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
    }

    public void dispose() {

        this.body.destroyFixture(this.fixture);
        this.world.destroyBody(this.body);
        this.texture.dispose();

    }

}
