package me.WiebeHero.gfx;

import me.WiebeHero.Entities.Entity;
import me.WiebeHero.Tiles.TileManager;
import me.WiebeHero.Worlds.World;

public class GameCamera extends Camera{

	private World world;
	
	public GameCamera(CameraPosition cameraPos, Screen screen, World world) {
		super(cameraPos, screen);
		this.world = world;
	}
	
	public void checkBlankSpace() {
		CameraPosition cameraPos = this.cameraPos;
		if(cameraPos.getXOffset() < 0) {
			cameraPos.setXOffset(0);
		}
		else if(cameraPos.getXOffset() > world.getWidth() * TileManager.TILEWIDTH - screen.getWidth()) {
			cameraPos.setXOffset(world.getWidth() * TileManager.TILEWIDTH - screen.getWidth());
		}
		if(cameraPos.getYOffset() < 0) {
			cameraPos.setYOffset(0);
		}
		else if(cameraPos.getYOffset() > world.getHeight() * TileManager.TILEHEIGHT - screen.getHeight()) {
			cameraPos.setYOffset(world.getHeight() * TileManager.TILEHEIGHT - screen.getHeight());
		}
	}

	public void centerOnEntity(Entity entity) {
		CameraPosition cameraPos = this.cameraPos;
		cameraPos.setXOffset(entity.getX() - screen.getWidth() / 2 + entity.getWidth() / 2);
		cameraPos.setYOffset(entity.getY() - screen.getHeight() / 2 + entity.getHeight() / 2);
		this.checkBlankSpace();
		
	}

	public void move(float xAmount, float yAmount) {
		CameraPosition cameraPos = this.cameraPos;
		cameraPos.setXOffset(cameraPos.getXOffset() + xAmount);
		cameraPos.setYOffset(cameraPos.getYOffset() + yAmount);
		this.checkBlankSpace();
	}
}
