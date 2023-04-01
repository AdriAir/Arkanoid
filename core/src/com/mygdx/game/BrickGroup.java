package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class BrickGroup implements GlobalActions {

    private final ArrayList<BrickLine> brickGroup;

    BrickGroup(Vector2 table, Vector2 pos, Vector2 size) {

        this.brickGroup = new ArrayList<>();

        for (int i = 0; i < table.x; i++) {

            if (i % 2 == 0) {

                pos.x += size.x / 2;

            } else {

                pos.x -= size.x / 2;

            }

            this.brickGroup.add(new BrickLine((int) table.y, new Vector2(pos.x, pos.y), new Vector2(size.x, size.y), new Color((float) Math.random(), (float) Math.random(), (float) Math.random(), 1f)));
            pos.y -= size.y + 2;

        }

    }

    @Override
    public void dispose() {

        for (int i = 0; i < this.brickGroup.size(); i++) {

            this.brickGroup.get(i).dispose();

        }

    }

    @Override
    public void render() {

        for (int i = 0; i < this.brickGroup.size(); i++) {

            this.brickGroup.get(i).render();

        }

    }

    public boolean collideBall(Ball ball) {

        for (int i = 0; i < this.brickGroup.size(); i++) {

            if (this.brickGroup.get(i).collideBall(ball)){

                return true;

            }

        }

        return false;
    }

}
