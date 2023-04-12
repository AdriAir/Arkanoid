package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.config.BrickConfig;
import com.mygdx.game.config.GameConfig;

import java.util.ArrayList;

public class BrickGroup extends Actor {

    private final ArrayList<BrickRow> brickGroup;

    public BrickGroup(int rows, int bricksPerRow, World world, Texture texture, Vector2 position) {

        this.brickGroup = new ArrayList<>();

        for (int i = 0; i < rows; i++) {

            if (i % 2 == 0) {

                position.x += (BrickConfig.SIZE_IN_METER.x * 15) / GameConfig.PIXELS_IN_A_METER;

            } else {

                position.x -= (BrickConfig.SIZE_IN_METER.x * 15) / GameConfig.PIXELS_IN_A_METER;

            }

            this.brickGroup.add(new BrickRow(bricksPerRow, world, texture, new Vector2(position)));
            position.y -= (BrickConfig.SIZE_IN_METER.y * 50) / GameConfig.PIXELS_IN_A_METER;
        }
    }

    public void dispose() {

        for (BrickRow row : this.brickGroup) {

            row.dispose();

        }

    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        for (BrickRow row : this.brickGroup) {

            row.draw(batch, parentAlpha);

        }

    }

    /*
    public boolean collideBall(Ball ball) {

        for (int i = 0; i < this.brickGroup.size(); i++) {

            if (this.brickGroup.get(i).collideBall(ball)) {

                this.brickGroup.remove(i);
                return true;

            }

        }

        return false;
    }
     */

}