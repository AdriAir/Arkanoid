package com.mygdx.game.config;

import com.badlogic.gdx.math.Vector2;

public class PlayerConfig {

    //STARTING VALUES
    public static final Vector2 SIZE_IN_METER = new Vector2(3f, 0.8f);
    public static final Vector2 POSITION_IN_METER = new Vector2(((GameConfig.WINDOW_SIZE_IN_PIXEL.x / GameConfig.PIXELS_IN_A_METER) / 2) + (SIZE_IN_METER.x / 2), 50 / GameConfig.PIXELS_IN_A_METER);
    public static final Vector2 VELOCITY_IN_PIXEL = new Vector2(20f,0f);

}
