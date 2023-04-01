package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class GameMusic {

    private String musicPath;
    Music music;

    GameMusic(String musicPath, float volume, boolean loop) {

        this.musicPath = musicPath;
        this.music = Gdx.audio.newMusic(Gdx.files.internal(this.musicPath));
        this.music.setLooping(loop);
        this.music.setVolume(volume);

    }

    public void play() {

        this.music.play();

    }

    public void stop(){

        this.music.stop();

    }

    public void dispose(){

        this.music.dispose();

    }

    public void setLoop(boolean loop) {

        this.music.setLooping(loop);

    }

    public boolean isLooping(){

        return this.music.isLooping();

    }

    public void setVolume(float volume){

        this.music.setVolume(volume);

    }

}
