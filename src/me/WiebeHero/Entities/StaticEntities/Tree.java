package me.WiebeHero.Entities.StaticEntities;

import java.awt.Graphics;

import me.WiebeHero.Items.Item;
import me.WiebeHero.Main.Game;
import me.WiebeHero.Tiles.TileManager;
import me.WiebeHero.Worlds.GameWorld;
import me.WiebeHero.Worlds.World;
import me.WiebeHero.gfx.Assets;
import me.WiebeHero.gfx.CameraPosition;

public class Tree extends StaticEntity{
	
	private CameraPosition cameraPos;
	
	public Tree(float x, float y, World world, CameraPosition cameraPos) {
		super(x, y, TileManager.TILEWIDTH, TileManager.TILEHEIGHT * 2, world);
		
		this.cameraPos = cameraPos;
		this.bounds.x = 10;
		this.bounds.y = (int) (height / 1.5F);
		this.bounds.width = this.width - 20;
		this.bounds.height = (int) (this.height - this.height / 1.10F); 
	}
	
	@Override
	public void die() {
		
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree, (int)(this.x - this.cameraPos.getXOffset()), (int)(this.y - this.cameraPos.getYOffset()), this.width, this.height, null);
	}
	
}
