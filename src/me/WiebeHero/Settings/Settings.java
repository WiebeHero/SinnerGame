package me.WiebeHero.Settings;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.google.gson.Gson;

public class Settings {
	
	private KeyboardControls keyboardControls;
	private SoundControl soundControl;
	
	public Settings() {
		this.keyboardControls = new KeyboardControls();
		this.soundControl = new SoundControl();
	}
	
	public void saveSettings() {
		try {
			String path = (Settings.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
			if(path.contains(".jar")) {
				path = path.substring(0, path.indexOf("Sinner.jar"));
				File file = new File(path + "/Settings.txt");
				Gson gson = new Gson();
				if(!file.exists()) {
					try {
						file.createNewFile();
						FileWriter writer = new FileWriter(file);
						writer.write(gson.toJson(this));
						writer.close();
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
				else {
					try {
						FileWriter writer = new FileWriter(file);
						writer.write(gson.toJson(this));
						writer.close();
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Settings loadSettings() {
		try {
			String path = (Settings.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
			if(path.contains(".jar")) {
				path = path.substring(0, path.indexOf("Sinner.jar"));
				File file = new File(path + "Settings.txt");
				if(file.exists()) {
					Scanner reader = new Scanner(file);
					StringBuilder builder = new StringBuilder();
					while(reader.hasNextLine()) {
						builder.append(reader.nextLine());
					}
					reader.close();
					Gson gson = new Gson();
					Settings settings = gson.fromJson(builder.toString(), Settings.class);
					return settings;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String settingsToGson() {
		Gson gson = new Gson();
		String str = gson.toJson(Settings.class);
		return str;
	}
	
	public KeyboardControls getKeyboardControls() {
		return this.keyboardControls;
	}
	
	public SoundControl getSoundControls() {
		return this.soundControl;
	}
}
