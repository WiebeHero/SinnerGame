package me.WiebeHero.States;

import java.awt.Color;
import java.awt.Graphics;

import me.WiebeHero.Display.Display;
import me.WiebeHero.Input.KeyControls;
import me.WiebeHero.Input.KeyManager;
import me.WiebeHero.Input.MouseManager;
import me.WiebeHero.Input.Listeners.MouseListeners.MouseReleaseListener;
import me.WiebeHero.Settings.Settings;
import me.WiebeHero.UI.UIBox;
import me.WiebeHero.UI.UIManager;
import me.WiebeHero.UI.Menu.UIInputRecorder;
import me.WiebeHero.UI.Menu.UIPivot;
import me.WiebeHero.UI.Menu.UISlider;
import me.WiebeHero.UI.Menu.UIText;
import me.WiebeHero.gfx.Screen;

public class SettingsState extends State{
	
	private UIManager uiManager;
	private MouseManager mouseManager;
	private KeyManager keyManager;
	private Display display;
	private Screen screen;
	private Settings settings;
	
	public SettingsState(Display display, Screen screen, MouseManager mouseManager, KeyManager keyManager, Settings settings) {		
		this.display = display;
		this.screen = screen;
		this.mouseManager = mouseManager;
		this.keyManager = keyManager;
		this.settings = settings;
		this.uiManager = new UIManager(this.display);
		UIBox menuBox = new UIBox(50.0D, 50.0D, 350, 450, this.screen);
		UIPivot pivot = new UIPivot(20, 30, this.screen, Color.BLUE);
		UISlider slider = new UISlider(pivot, 40.0D, 30.0D, 200, 18, this.screen, 100, (int)this.settings.getSoundControls().getVolume(), true);
		slider.addListeners(new MouseReleaseListener() {

			@Override
			public void listen() {
				settings.getSoundControls().setVolume(slider.getValue());
			}
			
		});
		menuBox.addChild(slider);
		UIText textVolume = new UIText(90.0D, 31.75D, screen, "Volume");
		menuBox.addChild(textVolume);
		UIInputRecorder upRecorder = new UIInputRecorder(50.0, 60.0, 25, 25, screen, this.settings.getKeyboardControls(), Color.BLUE, KeyControls.UP) {
			@Override
			public void recorded(int key) { 
				settings.getKeyboardControls().setUp(key);
			}
		};
		UIInputRecorder downRecorder = new UIInputRecorder(50.0, 67.5, 25, 25, screen, this.settings.getKeyboardControls(), Color.BLUE, KeyControls.DOWN) {
			@Override
			public void recorded(int key) { 
				settings.getKeyboardControls().setDown(key);
			}
		};
		UIInputRecorder leftRecorder = new UIInputRecorder(40.0, 67.5, 25, 25, screen, this.settings.getKeyboardControls(), Color.BLUE, KeyControls.LEFT) {
			@Override
			public void recorded(int key) { 
				settings.getKeyboardControls().setLeft(key);
			}
		};
		UIInputRecorder rightRecorder = new UIInputRecorder(60.0, 67.5, 25, 25, screen, this.settings.getKeyboardControls(), Color.BLUE, KeyControls.RIGHT) {
			@Override
			public void recorded(int key) { 
				settings.getKeyboardControls().setRight(key);
			}
		};
		menuBox.addChildren(upRecorder, downRecorder, leftRecorder, rightRecorder);
		this.uiManager.addObject(menuBox);
	}

	@Override
	public void tick() {
		this.uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1930, 1080);
		this.uiManager.render(g);
	}

	@Override
	public void initMouseManager() {
		this.mouseManager.setUIManager(this.uiManager);
	}
	
	@Override
	public void initKeyManager() {
		this.keyManager.setUIManager(this.uiManager);
	}
	
}
