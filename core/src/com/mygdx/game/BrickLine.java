package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class BrickLine implements GlobalActions {

    private final ArrayList<Brick> brickLine;

    BrickLine(int bricks, Vector2 pos, Vector2 size, Color color) {

        this.brickLine = new ArrayList<>();

        for (int i = 0; i < bricks; i++) {

            this.brickLine.add(new Brick(new Vector2(pos.x, pos.y), new Vector2(size.x, size.y), color));
            pos.x += size.x + 2;

        }


    }

    @Override
    public void dispose() {

        for (int i = 0; i < this.brickLine.size(); i++) {

            this.brickLine.get(i).dispose();

        }

    }

    @Override
    public void render() {

        for (int i = 0; i < this.brickLine.size(); i++) {

            this.brickLine.get(i).render();

        }

    }

    public boolean collideBall(Ball ball) {

        for (int i = 0; i < this.brickLine.size(); i++) {

            if (this.brickLine.get(i).collideBall(ball)) {

                this.brickLine.remove(i);
                return true;

            }

        }

        return false;
    }

}
