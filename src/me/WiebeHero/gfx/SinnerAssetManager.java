package me.WiebeHero.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import me.WiebeHero.Assets.AssetManager;

public class SinnerAssetManager extends AssetManager{
	
	private int index;
	
	public SinnerAssetManager(int size) {
		super(size);
		this.index = 0;
	}
	
	@Override
	public void loadAssets() {
		URL url = SinnerAssetManager.class.getResource("/Assets");
		File folder = new File(url.getPath());
		this.loadImages(folder);
		System.out.println(this.assets[3].getIdentifier());
	}
	
	private void loadImages(File folder) {
		for(File file : folder.listFiles()) {
			if(file.isDirectory()) {
				this.loadImages(file);
			}
			else {
				BufferedImage image = ImageLoader.loadImage(file);
				SpriteSheet sheet = new SpriteSheet(image);
				String name = file.getName();
				String nameParts[] = name.split(",|\\.");
				int spriteWidth = 32;
				int spriteHeight = 32;
				if(nameParts.length > 2) {
					try {
						spriteWidth = Integer.parseInt(nameParts[1]);
						spriteHeight = Integer.parseInt(nameParts[2]);
					}
					catch(NumberFormatException ex) {
						ex.printStackTrace();
					}
				}
				this.assets[this.index] = new SinnerAsset(nameParts[0], sheet.completeCrop(spriteWidth, spriteHeight));
				this.index++;
			}
		}
	}

}
