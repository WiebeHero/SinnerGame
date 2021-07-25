package me.WiebeHero.Main;

import java.awt.Toolkit;

import me.WiebeHero.gfx.SinnerAssetManager;

public class SinnerLauncher {
	
	public static void main(String args[]) {
		Toolkit.getDefaultToolkit().getDesktopProperty("awt.dynamicLayoutSupported");
		Toolkit.getDefaultToolkit().setDynamicLayout(false);
		System.setProperty("sun.awt.noerasebackground", "true");
		SinnerAssetManager assetManager = new SinnerAssetManager(20);
		assetManager.loadAssets();
		SinnerGame sinnerGame = new SinnerGame("Sinner", 840, 520);
		sinnerGame.start();
	}
	
}
