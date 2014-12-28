package lostsoulsquasher.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import lostsoulsquasher.GameConfig;
import lostsoulsquasher.LostSoulGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new LostSoulGame(mkConfig()), config);
	}

	private GameConfig mkConfig() {
		GameConfig config = new GameConfig();
		config.setTouchScreen(true);
		config.setFullscreenMakesSense(false);
		config.setFontSize(32);
		return config;
	}
}
