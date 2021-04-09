package me.WiebeHero.States;

import java.awt.Color;
import java.awt.Graphics;

import me.WiebeHero.Display.Display;
import me.WiebeHero.Input.MouseManager;
import me.WiebeHero.Input.Listeners.HoverListener;
import me.WiebeHero.Input.Listeners.PressListener;
import me.WiebeHero.Sounds.Sounds;
import me.WiebeHero.UI.UIBox;
import me.WiebeHero.UI.UIManager;
import me.WiebeHero.UI.Menu.UIImageButton;
import me.WiebeHero.gfx.Assets;
import me.WiebeHero.gfx.Screen;
import me.WiebeHero.gfx.Animations.AnimOption;
import me.WiebeHero.gfx.Animations.MovementAnimation;
import me.WiebeHero.gfx.Animations.SizeAnimation;
import me.WiebeHero.gfx.Animations.SpriteAnimation;

public class MenuState extends State{

	private UIManager uiManager;
	private MouseManager mouseManager;
	private Screen screen;
	private Display display;
	
	public MenuState(Display display, Screen screen, MouseManager mouseManager) {
		this.display = display;
		this.screen = screen;
		this.mouseManager = mouseManager;
		this.uiManager = new UIManager(this.display);
		UIBox menuBox = new UIBox(47.5D, 50.0D, 512, 480, this.screen);
		
		UIImageButton title = new UIImageButton(50.0D, 16.5D, 320, 160, this.screen, Assets.title_icon);
		menuBox.addChild(title);
		
		UIImageButton newButton = new UIImageButton(64.5D, 43.75D, 100, 50, this.screen, Assets.new_icon);
		newButton.addAnimations(new MovementAnimation(16, 38, 3F, 0F, AnimOption.PAUSED), new SizeAnimation(30, 5, 2, 1, AnimOption.PAUSED, AnimOption.HOVER_TRIGGER));
		newButton.addListeners(
		new PressListener() {

			@Override
			public void listen() {
				UIImageButton playButton = (UIImageButton) menuBox.getChild(3);
				UIImageButton newButton = (UIImageButton) menuBox.getChild(1);
				if(!playButton.overlaps(newButton.getBounds())) {
					mouseManager.setUIManager(null);
					StateManager stateManager = StateManager.getInstance();
					stateManager.setState(GameState.class);
					stateManager.getState().initMouseManager();
				}
			}
			
		}, new HoverListener() {
			
			@Override
			public void listen() {
				UIImageButton playButton = (UIImageButton) menuBox.getChild(3);
				UIImageButton newButton = (UIImageButton) menuBox.getChild(1);
				if(!playButton.overlaps(newButton.getBounds())) {
					Sounds.hover_button.start();
				}
			}
			
		});
		menuBox.addChild(newButton);
		
		UIImageButton loadButton = new UIImageButton(64.5D, 55.5D, 100, 50, screen, Assets.load_icon);
		loadButton.addAnimations(new MovementAnimation(16, 38, 3F, 0F, AnimOption.PAUSED), new SizeAnimation(30, 5, 2, 1, AnimOption.PAUSED, AnimOption.HOVER_TRIGGER));
		loadButton.addListeners(
		new PressListener() {
			
			@Override
			public void listen() {
				UIImageButton playButton = (UIImageButton) menuBox.getChild(3);
				UIImageButton loadButton = (UIImageButton) menuBox.getChild(2);
				if(!playButton.overlaps(loadButton.getBounds())) {
					mouseManager.setUIManager(null);
					StateManager stateManager = StateManager.getInstance();
					stateManager.setState(GameState.class);
					stateManager.getState().initMouseManager();
				}
			}
			
		}, 
		new HoverListener() {
			
			@Override
			public void listen() {
				UIImageButton playButton = (UIImageButton) menuBox.getChild(3);
				UIImageButton loadButton = (UIImageButton) menuBox.getChild(2);
				if(!playButton.overlaps(loadButton.getBounds())) {
					Sounds.hover_button.start();
				}
			}
		});
		menuBox.addChild(loadButton);
		
		UIImageButton playButton = new UIImageButton(50.0D, 49.5D, 256, 128, screen, Assets.play_icon);
		playButton.addAnimations(new SpriteAnimation(25, Assets.play_icon, AnimOption.PAUSED, AnimOption.HOVER_TRIGGER));
		playButton.addListeners(new PressListener() {
			
			@Override
			public void listen() {
				UIImageButton newButton = (UIImageButton) menuBox.getChild(1);
				UIImageButton loadButton = (UIImageButton) menuBox.getChild(2);
				newButton.getAnimation(0).setOptionState(AnimOption.PAUSED, false);
				loadButton.getAnimation(0).setOptionState(AnimOption.PAUSED, false);
			}
			
		});
		menuBox.addChild(playButton);
		
		UIImageButton settingsButton = new UIImageButton(50.0D, 79.5D, 256, 128, screen, Assets.settings_icon);
		settingsButton.addAnimations(new SpriteAnimation(25, Assets.settings_icon, AnimOption.PAUSED, AnimOption.HOVER_TRIGGER));
		settingsButton.addListeners(
		new PressListener() {
			
			@Override
			public void listen() {
				mouseManager.setUIManager(null);
				StateManager stateManager = StateManager.getInstance();
				stateManager.setState(SettingsState.class);
				stateManager.getState().initMouseManager();
			}
			
		});
		menuBox.addChild(settingsButton);
		this.uiManager.addObject(menuBox);
	}
	
	//First Button: X = X + 150 Y = 150
	//Second Button: X = X + 150 Y = 191
	
	@Override
	public void initMouseManager() {
		this.mouseManager.setUIManager(this.uiManager);
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

}
