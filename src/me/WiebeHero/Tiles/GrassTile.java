package me.WiebeHero.Tiles;

import java.awt.Graphics;

import me.WiebeHero.gfx.Assets;

public class GrassTile extends Tile {

	public GrassTile() {
		super(Assets.grass, 0);
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
	
}
