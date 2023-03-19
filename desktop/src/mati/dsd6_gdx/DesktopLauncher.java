package mati.dsd6_gdx;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import java.awt.*;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setWindowedMode(1280, 720);
		config.setResizable(false);
		config.setTitle("DeepSpaceD6_GDX");
		config.setWindowIcon(Files.FileType.Internal, "die_commander.png");
		new Lwjgl3Application(new D6(), config);
	}
}
