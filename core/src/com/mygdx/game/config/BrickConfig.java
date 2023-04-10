package com.mygdx.game.config;

import com.badlogic.gdx.math.Vector2;

public class BrickConfig {

    public static final Vector2 COUNT = new Vector2(15, 8);
    public static final Vector2 SIZE_IN_METER = new Vector2(3f, 0.8f);
    public static final Vector2 POSITION_IN_METER = new Vector2(((GameConfig.WINDOW_SIZE_IN_PIXEL.x / GameConfig.PIXELS_IN_A_METER) / 2) + (SIZE_IN_METER.x / 2),
            GameConfig.WORLD_SIZE_IN_METER.y * GameConfig.PIXELS_IN_A_METER - 20);
    public static final Vector2 VELOCITY_IN_PIXEL = new Vector2(20f, 0f);
    public static final Vector2 SCORE_POSITION = new Vector2(10, GameConfig.WINDOW_SIZE_IN_PIXEL.y - 10);

}
