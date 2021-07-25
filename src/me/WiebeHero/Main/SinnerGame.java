package me.WiebeHero.Main;

import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import me.WiebeHero.Display.Display;
import me.WiebeHero.Input.CustomKeyManager;
import me.WiebeHero.Input.CustomMouseManager;
import me.WiebeHero.Settings.Settings;
import me.WiebeHero.States.FightState;
import me.WiebeHero.States.GameState;
import me.WiebeHero.States.MenuState;
import me.WiebeHero.States.SettingsState;
import me.WiebeHero.States.StateManager;
import me.WiebeHero.gfx.SinnerAssetManager;

public class SinnerGame extends Game{

	private BufferStrategy bs;
	private Graphics g;
	private Settings settings;
	private SinnerAssetManager assetManager;
	
	public SinnerGame(String displayName, int width, int height) {
		super(displayName, width, height);
		this.settings = new Settings();
		Settings replaceSettings = settings.loadSettings();
		if(replaceSettings != null) {
			this.settings = replaceSettings;
		}
		this.assetManager = new SinnerAssetManager(20);
		this.assetManager.loadAssets();
		this.mouseManager = new CustomMouseManager();
		this.keyManager = new CustomKeyManager(this.settings.getKeyboardControls());
	}

	@Override
	protected void init() {
		this.display = new Display(this.title, "/Assets/Icons/TitleIconRes,64,64.png", screen.getWidth(), screen.getHeight());
		this.display.getFrame().addMouseListener(this.mouseManager);
		this.display.getFrame().addMouseMotionListener(this.mouseManager);
		this.display.getFrame().getRootPane().addComponentListener(new ComponentAdapter() {
			
		    public void componentResized(ComponentEvent componentEvent) {
		    	screen.resize(display.getFrame().getWidth() - 16, display.getFrame().getHeight() - 39);
		    }
		    
		});
		this.display.getFrame().addWindowListener(new WindowAdapter() {
			
		    public void windowClosing(WindowEvent windowEvent) {
		    	settings.saveSettings();
		    }
		    
		});
		this.display.getCanvas().addMouseListener(this.mouseManager);
		this.display.getCanvas().addMouseMotionListener(this.mouseManager);
		MenuState menuState = new MenuState(this.display, this.screen, this.mouseManager);
		this.display.getFrame().addKeyListener(this.keyManager);
		this.display.getCanvas().addKeyListener(this.keyManager);
		this.stateManager = StateManager.getInstance();
		this.stateManager.setStates(
				menuState, 
				new SettingsState(this.display, this.screen, this.mouseManager, this.keyManager, this.settings), 
				new GameState(this.display, this.screen, this.mouseManager, (CustomKeyManager)this.keyManager), 
				new FightState(this.display, this.screen, this.mouseManager)
		);
		this.stateManager.setState(MenuState.class);
		menuState.initMouseManager();
	}

	@Override
	protected void render() {
		this.bs = this.display.getCanvas().getBufferStrategy();
		
		if(bs == null) {
			this.display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		this.g = this.bs.getDrawGraphics();
		
		this.g.clearRect(0, 0, screen.getWidth(), screen.getHeight());
		
		if(this.stateManager.getState() != null) {
			this.stateManager.getState().render(this.g);
		}
		
		this.bs.show();
		this.g.dispose();
	}

	@Override
	protected void tick() {
		this.keyManager.tick();
		
		if(this.stateManager.getState() != null) {
			this.stateManager.getState().tick();
		}
	}

}
