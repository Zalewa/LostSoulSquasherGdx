package lostsoulsquasher.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import lostsoulsquasher.GameConfig;
import lostsoulsquasher.LostSoulGame;
import lostsoulsquasher.exceptions.ErrorDump;

public class DesktopLauncher {
	public static void main (String[] arg) {
		try {
			LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
			config.fullscreen = false;
			config.title = "Lost Soul Squasher";
			config.addIcon("ic_launcher_32x32.png", Files.FileType.Internal);
			config.width = 800;
			config.height = 480;
			new LwjglApplication(new LostSoulGame(mkConfig()), config);
		} catch (RuntimeException e) {
			ErrorDump.dumpExceptionToTempFile(e);
			throw e;
		}
	}

	private static GameConfig mkConfig() {
		GameConfig config = new GameConfig();
		config.setTouchScreen(false);
		return config;
	}
}
