package me.WiebeHero.Worlds;

import java.awt.Graphics;

import me.WiebeHero.Entities.Entity;
import me.WiebeHero.Entities.Creatures.Player;
import me.WiebeHero.Entities.StaticEntities.Tree;
import me.WiebeHero.Input.CustomKeyManager;
import me.WiebeHero.Items.ItemManager;
import me.WiebeHero.Tiles.DirtTile;
import me.WiebeHero.Tiles.GrassTile;
import me.WiebeHero.Tiles.Tile;
import me.WiebeHero.Tiles.TileManager;
import me.WiebeHero.Utils.Utils;
import me.WiebeHero.gfx.CameraPosition;
import me.WiebeHero.gfx.GameCamera;
import me.WiebeHero.gfx.Screen;

public class FightWorld extends World{
	
	private TileManager tileManager;
	private GameCamera gameCamera;
	private CustomKeyManager keyManager;
	private int spawnX, spawnY;
	private Screen screen;
	private int tiles[][];
	
	public FightWorld(String path, Screen screen, CustomKeyManager keyManager, Player player, Entity entityToFight) {
		super();
		this.screen = screen;
		this.keyManager = keyManager;
		this.gameCamera = new GameCamera(new CameraPosition(0.0F, 0.0F), this.screen, this);
		this.entityManager.addEntity(player);
		this.entityManager.addEntity(entityToFight);
		this.tileManager = new TileManager();
		this.tileManager.addTile(new GrassTile());
		this.loadWorld(path);
	}

	@Override
	public void render(Graphics g) {
		CameraPosition cameraPos = this.gameCamera.getCameraPosition();
		Screen screen = this.gameCamera.getScreen();
		int xStart = (int)Math.max(0, cameraPos.getXOffset() / TileManager.TILEWIDTH);
		int xEnd = (int)Math.min(screen.getWidth(), (cameraPos.getXOffset() + screen.getWidth()) / TileManager.TILEWIDTH + 1);
		int yStart = (int)Math.max(0, cameraPos.getYOffset() / TileManager.TILEHEIGHT);
		int yEnd = (int)Math.min(screen.getHeight(), (cameraPos.getYOffset() + screen.getHeight()) / TileManager.TILEHEIGHT + 1);
		for(int y = yStart; y < yEnd; y++) {
			for(int x = xStart; x < xEnd; x++) {
				if(this.getTile(x, y) != null) {
					this.getTile(x, y).render(g, (int) (x * TileManager.TILEWIDTH - cameraPos.getXOffset()), (int) (y * TileManager.TILEHEIGHT - cameraPos.getYOffset()));
				}
			}
		}
		//Entities
		this.entityManager.render(g);
	}

	@Override
	public void tick() {
		this.entityManager.tick();
	}

	public Tile getTile(int x, int y) {
		if(x >= 0 && y >= 0  && x < this.width && y < this.height) {
			Tile t = tileManager.getTile(tiles[x][y]);
			if(t != null) {
				return t;
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}
	
	private void loadWorld(String path) {
		this.width = 30;
		this.height = 30;
		this.spawnX = 0;
		this.spawnY = 0;
		
		this.tiles = new int[width][height];
		for(int y = 0; y < this.height; y++) {
			for(int x = 0; x < this.width; x++) {
				this.tiles[x][y] = 0;
			}
		}
	}

}
