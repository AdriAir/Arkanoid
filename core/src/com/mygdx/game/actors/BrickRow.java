package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.config.BrickConfig;
import com.mygdx.game.config.GameConfig;

import java.util.ArrayList;

public class BrickRow extends Actor {

    private final ArrayList<Brick> brickRow;

    public BrickRow(int bricksPerRow, World world, Texture texture, Vector2 position) {

        this.brickRow = new ArrayList<>();

        for (int i = 0; i < bricksPerRow; i++) {

            this.brickRow.add(new Brick(world, texture, position));
            position.x += (BrickConfig.SIZE_IN_METER.x * 45) / GameConfig.PIXELS_IN_A_METER;

        }


    }

    public void dispose() {

        for (Brick brick : this.brickRow) {

            brick.dispose();

        }

    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        for (Brick brick : this.brickRow) {

            brick.draw(batch, parentAlpha);

        }

    }

    /*
    public boolean collideBall(Ball ball) {

        for (int i = 0; i < this.brickRow.size(); i++) {

            if (this.brickRow.get(i).collideBall(ball)) {

                this.brickRow.remove(i);
                return true;

            }

        }

        return false;
    }
     */

}