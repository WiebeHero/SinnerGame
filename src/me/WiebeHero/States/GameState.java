package me.WiebeHero.States;

import java.awt.Graphics;

import me.WiebeHero.Display.Display;
import me.WiebeHero.Entities.Entity;
import me.WiebeHero.Entities.EntityManager;
import me.WiebeHero.Entities.Creatures.Player;
import me.WiebeHero.Input.CustomKeyManager;
import me.WiebeHero.Input.MouseManager;
import me.WiebeHero.UI.UIBox;
import me.WiebeHero.UI.UIManager;
import me.WiebeHero.UI.UIObject;
import me.WiebeHero.UI.Game.Inventory.UIInventory;
import me.WiebeHero.Worlds.GameWorld;
import me.WiebeHero.gfx.Screen;

public class GameState extends State{
	
	private GameWorld world;
	private UIManager uiManager;
	private Display display;
	private Screen screen;
	private MouseManager mouseManager;
	private CustomKeyManager keyManager;

	public GameState(Display display, Screen screen, MouseManager mouseManager, CustomKeyManager keyManager) {
		this.display = display;
		this.screen = screen;
		this.mouseManager = mouseManager;
		this.keyManager = keyManager;
		this.world = new GameWorld("worlds/MainWorld.txt", this.screen, this.keyManager);
		this.uiManager = new UIManager(this.display);
		UIBox uiBox = new UIBox(50.0D, 50.0D, 480, 256, this.screen, new UIObject[0]);
		this.uiManager.addObject(uiBox);
		UIInventory uiInventory = new UIInventory(50.0D, 50.0D, 480, 256, this.screen, uiBox);
		uiInventory.setActive(false);
		EntityManager entityManager = this.world.getEntityManager();
		for(Entity entity : entityManager.getEntities()) {
			if(entity instanceof Player) {
				Player player = (Player) entity;
				player.setUIInventory(uiInventory);
			}
		}
		this.uiManager.addObject(uiInventory);
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
		this.keyManager.setUIManager(this.uiManager);
	}

}
