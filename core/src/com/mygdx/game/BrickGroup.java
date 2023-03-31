package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class BrickGroup implements GlobalActions {

    private ArrayList<BrickLine> brickGroup;
    private Vector2 pos;
    private Vector2 size;
    private Color color;

    BrickGroup(Vector2 table, Vector2 pos, Vector2 size, Color color) {

        this.pos = pos;
        this.size = size;
        this.color = color;

        this.brickGroup = new ArrayList<>();

        for (int i = 0; i < table.x; i++) {

            if (i % 2 == 0) {

                this.pos.x += this.size.x / 2;

            } else {

                this.pos.x -= this.size.x / 2;

            }

            this.brickGroup.add(new BrickLine((int) table.y, new Vector2(this.pos.x, this.pos.y), new Vector2(this.size.x, this.size.y)));
            this.pos.y -= this.size.y + 2;

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

    public void collideBall(Ball ball) {

        for (int i = 0; i < this.brickGroup.size(); i++) {

            this.brickGroup.get(i).collideBall(ball);

        }

    }

}
