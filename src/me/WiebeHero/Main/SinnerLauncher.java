package me.WiebeHero.Main;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import me.WiebeHero.Input.CustomKeyManager;
import me.WiebeHero.Input.CustomMouseManager;
import me.WiebeHero.Settings.Settings;
import me.WiebeHero.States.FightState;
import me.WiebeHero.States.GameState;
import me.WiebeHero.States.MenuState;
import me.WiebeHero.States.SettingsState;
import me.WiebeHero.gfx.Assets;

public class SinnerLauncher {
	
	public static void main(String args[]) {
		Toolkit.getDefaultToolkit().getDesktopProperty("awt.dynamicLayoutSupported");
		Toolkit.getDefaultToolkit().setDynamicLayout(false);
		System.setProperty("sun.awt.noerasebackground", "true");
		Settings settings = new Settings();
		Settings replaceSettings = settings.loadSettings();
		if(replaceSettings != null) {
			settings = replaceSettings;
		}
		Settings finalSettings = settings;
		CustomMouseManager mouseManager = new CustomMouseManager();
		CustomKeyManager keyManager = new CustomKeyManager(finalSettings.getKeyboardControls());
		Game game = new Game("Sinner", 840, 520, keyManager, mouseManager) {
			@Override
			public void endInit() {
				
				this.display.getFrame().addWindowListener(new WindowAdapter() {
				
				    public void windowClosing(WindowEvent windowEvent) {
				    	finalSettings.saveSettings();
				    }
				    
				});
				Assets.init();
				MenuState menuState = new MenuState(this.display, this.screen, this.mouseManager);
				this.display.getFrame().addKeyListener(this.keyManager);
				this.stateManager.setStates(
						menuState, 
						new SettingsState(this.display, this.screen, this.mouseManager, finalSettings), 
						new GameState(this.display, this.screen, this.mouseManager, (CustomKeyManager)this.keyManager), 
						new FightState(this.display, this.screen, this.mouseManager)
				);
				this.stateManager.setState(MenuState.class);
				menuState.initMouseManager();
			}
		};
		game.start();
	}
	
}
