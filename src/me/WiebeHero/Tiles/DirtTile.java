package me.WiebeHero.Tiles;

import java.awt.Graphics;

import me.WiebeHero.TileRules.Collidable;
import me.WiebeHero.gfx.Assets;

public class DirtTile extends Tile implements Collidable{

	public DirtTile() {
		super(Assets.dirt, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g, int x, int y) {
		g.drawImage(this.texture, x, y, TileManager.TILEWIDTH, TileManager.TILEHEIGHT, null);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
	
}
