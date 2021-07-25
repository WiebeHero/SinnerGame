package me.WiebeHero.States;

import java.awt.Graphics;

import me.WiebeHero.Display.Display;
import me.WiebeHero.Input.MouseManager;
import me.WiebeHero.UI.UIBox;
import me.WiebeHero.UI.UIManager;
import me.WiebeHero.UI.Menu.UIImageButton;
import me.WiebeHero.Worlds.FightWorld;
import me.WiebeHero.gfx.Screen;
import me.WiebeHero.gfx.SinnerAssetManager;

public class FightState extends State{
	
	private FightWorld world;
	private UIManager uiManager;
	private Display display;
	private MouseManager mouseManager;
	private Screen screen;
	private SinnerAssetManager assetManager;
	
	public FightState(Display display, SinnerAssetManager assetManager, Screen screen, MouseManager mouseManager) {
		this.display = display;
		this.uiManager = new UIManager(this.display);
		this.mouseManager = mouseManager;
		this.screen = screen;
		UIBox playerButtons = new UIBox(20.0D, 40.0D, 300, 300, this.screen);
		UIImageButton attackButton = new UIImageButton(50.0D, 50.0D, 32, 32, this.screen, this.assetManager.getAsset("AttackIcon").getImages());
	}

	@Override
	public void tick() {
		this.world.tick();
		this.uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		this.world.render(g);
		this.uiManager.render(g);
	}

	@Override
	public void initMouseManager() {
		this.mouseManager.setUIManager(this.uiManager);
	}
	
	@Override
	public void initKeyManager() {
		
	}
	
}
