package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.config.GameConfig;
import com.mygdx.game.config.PlayerConfig;

public class Brick extends Actor {

    private Texture texture;
    private World world;
    private Body body;
    private Fixture fixture;
    private boolean isAlive;

    public Brick(World world, Texture texture, Vector2 position) {

        this.world = world;
        this.texture = texture;
        this.isAlive = true;

        //Creating Body
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(position);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        this.body = this.world.createBody(bodyDef);

        //Creating Collider
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(PlayerConfig.SIZE_IN_METER.x / 2, PlayerConfig.SIZE_IN_METER.y / 2);

        this.fixture = body.createFixture(rectangle, 5);
        this.fixture.setUserData("player");
        rectangle.dispose();

        //Setting Size
        this.setSize(PlayerConfig.SIZE_IN_METER.x, PlayerConfig.SIZE_IN_METER.y);
        //Setting Starting Pos
        setPosition(PlayerConfig.POSITION_IN_METER.x, PlayerConfig.POSITION_IN_METER.y);


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
        if (Gdx.input.isKeyPressed(Input.Keys.D) && !this.wallCollide()) {

            this.body.setLinearVelocity(PlayerConfig.VELOCITY_IN_PIXEL.x / GameConfig.PIXELS_IN_A_METER, PlayerConfig.VELOCITY_IN_PIXEL.y / GameConfig.PIXELS_IN_A_METER);

        } else if (Gdx.input.isKeyPressed(Input.Keys.A) && !this.wallCollide()) {

            this.body.setLinearVelocity(-PlayerConfig.VELOCITY_IN_PIXEL.x / GameConfig.PIXELS_IN_A_METER, PlayerConfig.VELOCITY_IN_PIXEL.y / GameConfig.PIXELS_IN_A_METER);

        } else {

            this.body.setLinearVelocity(0f, 0f);

        }
    }

    public void dispose() {

        this.body.destroyFixture(this.fixture);
        this.world.destroyBody(this.body);
        this.texture.dispose();

    }

    public boolean wallCollide() {

        if (this.getX() + this.getWidth() > GameConfig.WORLD_SIZE_IN_METER.x) {

            this.setX(GameConfig.WORLD_SIZE_IN_METER.x - this.getWidth());
            return true;

        } else if (this.getX() < 0) {

            this.setX(0);
            return true;
        }

        return false;
    }

    public void kill() {

        this.isAlive = false;

    }

    public boolean isAlive() {
        return isAlive;
    }
}
