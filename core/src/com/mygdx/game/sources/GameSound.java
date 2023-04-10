package com.mygdx.game.sources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class GameSound {

    private String soundPath;
    private Sound sound;

    public GameSound(String soundPath){

        this.soundPath = soundPath;
        this.sound = Gdx.audio.newSound(Gdx.files.internal(this.soundPath));

    }

    public void play(float volume){

        this.sound.play(volume);

    }

    public void stop(){

        this.sound.stop();

    }

    public void dispose(){

        this.sound.dispose();

    }

}
