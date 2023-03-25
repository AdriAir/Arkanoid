package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class BrickLine implements GlobalActions {

    private ArrayList<Brick> brickLine;
    private Vector2 pos;
    private Vector2 size;
    private Color color;

    BrickLine(int bricks, Vector2 pos, Vector2 size, Color color) {

        this.pos = pos;
        this.size = size;
        this.color = color;

        this.brickLine = new ArrayList<>();

        for (int i = 0; i < bricks; i++) {

            this.brickLine.add(new Brick(this.pos, this.size, this.color));
            this.pos.x += this.size.x + 1;

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
}
