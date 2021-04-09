package me.WiebeHero.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}
	
	public BufferedImage crop(int x, int y, int width, int height) {
		return this.sheet.getSubimage(x, y, width, height);
	}
	/**
	 * 
	 * @param columns : The amount of columns in the sprite
	 * @param rows : The amount of rows in the sprite
	 * @return BufferedImage[]
	 */
	
	public BufferedImage[] completeCrop(int columns, int rows) {
		int imageWidth = this.sheet.getWidth();
		int imageHeight = this.sheet.getHeight();
		int width = imageWidth / columns;
		int height = imageHeight / rows;
		int totalImages = columns * rows;
		BufferedImage[] crops = new BufferedImage[totalImages];
		int x = 0;
		int y = 0;
		for(int i = 0; i < totalImages; i++) {
			if(x == columns) {
				x = 0;
				y++;
			}
			crops[i] = this.sheet.getSubimage(width * x, height * y, width, height);
			x++;
		}
		return crops;
	}
	
	public BufferedImage[] completeCrop(int columns, int rows, int exempt) {
		int imageWidth = this.sheet.getWidth();
		int imageHeight = this.sheet.getHeight();
		int width = imageWidth / columns;
		int height = imageHeight / rows;
		int totalImages = columns * rows;
		BufferedImage[] crops = new BufferedImage[totalImages - exempt];
		int x = 0;
		int y = 0;
		for(int i = 0; i < totalImages - exempt; i++) {
			if(x == columns) {
				x = 0;
				y++;
			}
			crops[i] = this.sheet.getSubimage(width * x, height * y, width, height);
			x++;
		}
		return crops;
	}
	
}
