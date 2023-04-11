package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.config.GameConfig;
import com.mygdx.game.scenes.BaseScreen;
import com.mygdx.game.scenes.GameOverScreen;
import com.mygdx.game.scenes.InGameScreen;
import com.mygdx.game.scenes.LoadingScreen;
import com.mygdx.game.scenes.MenuScreen;

public class GDXMain extends Game {

	private AssetManager assets;
	private BaseScreen menuScreen;
	private BaseScreen gameOverScreen;
	private BaseScreen loadingScreen;
	private BaseScreen inGameScreen;

	@Override
	public void create() {

		//WINDOW CONFIG
		Gdx.graphics.setTitle(GameConfig.WINDOW_TITLE);
		Gdx.graphics.setWindowedMode((int) GameConfig.WINDOW_SIZE_IN_PIXEL.x, (int) GameConfig.WINDOW_SIZE_IN_PIXEL.y);
		Gdx.graphics.setResizable(GameConfig.WINDOW_RESIZABLE);


		// Cargador de recursos
		assets = new AssetManager();

		assets.load("Sounds/edgeCollisionSound.ogg", Sound.class);
		assets.load("Sounds/ScoreSound.ogg", Sound.class);
		assets.load("Sounds/gameOverSound.ogg", Sound.class);
		assets.load("Music/Arcade Dwellers.ogg", Music.class);
		assets.load("Textures/player.png", Texture.class);

		assets.finishLoading();

		//Carga de Escenas
		this.loadingScreen = new LoadingScreen(this);
		this.menuScreen = new MenuScreen(this);
		this.inGameScreen = new InGameScreen(this);
		this.gameOverScreen = new GameOverScreen(this);

		//Iniciar Escena "menuScreen"
		this.setScreen(menuScreen);

	}

	public AssetManager getAssets() {
		return assets;
	}

	public BaseScreen getMenuScreen() {
		return menuScreen;
	}

	public BaseScreen getGameOverScreen() {
		return gameOverScreen;
	}

	public BaseScreen getInGameScreen() {
		return inGameScreen;
	}
}
