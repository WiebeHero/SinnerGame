package me.WiebeHero.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;
	
	public static Font font28;
	
	public static BufferedImage dirt, grass, tree;
	public static BufferedImage[] player_up, player_down, player_left, player_right;
	public static BufferedImage player_up_still, player_down_still, player_left_still, player_right_still;
	public static BufferedImage[] title_icon, play_icon, new_icon, load_icon, settings_icon;
	public static BufferedImage[] swordExample, slashExample;
	public static BufferedImage[] inventoryScreen;
	public static BufferedImage[] attackIcon;
	public static BufferedImage[] slime;
	
	public static void init() {
		font28 = FontLoader.loadFont("/fonts/font.ttf", 28);
		
		SpriteSheet tileSheet = new SpriteSheet(ImageLoader.loadImage("/textures/TileSheet.png"));
		SpriteSheet staticEntitySheet = new SpriteSheet(ImageLoader.loadImage("/sprites/StaticEntitySprite.png"));
		SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/sprites/CharacterSprite.png"));
		SpriteSheet titleIconSheet = new SpriteSheet(ImageLoader.loadImage("/icons/TitleIcon.png"));
		SpriteSheet playIconSheet = new SpriteSheet(ImageLoader.loadImage("/icons/PlayIcon.png"));
		SpriteSheet newIconSheet = new SpriteSheet(ImageLoader.loadImage("/icons/NewIcon.png"));
		SpriteSheet loadIconSheet = new SpriteSheet(ImageLoader.loadImage("/icons/LoadIcon.png"));
		SpriteSheet settingsIconSheet = new SpriteSheet(ImageLoader.loadImage("/icons/SettingsIcon.png"));
		SpriteSheet swordSheet = new SpriteSheet(ImageLoader.loadImage("/sprites/SwordExampleSprite.png"));
		SpriteSheet slashSheet = new SpriteSheet(ImageLoader.loadImage("/sprites/Slash.png"));
		SpriteSheet inventorySheet = new SpriteSheet(ImageLoader.loadImage("/icons/InventoryScreen.png"));
		SpriteSheet attackIconSheet = new SpriteSheet(ImageLoader.loadImage("/icons/AttackIcon.png"));
		SpriteSheet slimeSheet = new SpriteSheet(ImageLoader.loadImage("/sprites/Slime.png"));
		
		slime = slimeSheet.completeCrop(1, 1);
		attackIcon = attackIconSheet.completeCrop(1, 1);
		inventoryScreen = inventorySheet.completeCrop(1, 1);
		swordExample = swordSheet.completeCrop(3, 3, 1);
		slashExample = slashSheet.completeCrop(3, 3, 2);
		
		player_up = new BufferedImage[4];
		player_down = new BufferedImage[4];
		player_left = new BufferedImage[4];
		player_right = new BufferedImage[4];
		play_icon = playIconSheet.completeCrop(5, 7, 3);
		load_icon = loadIconSheet.completeCrop(1, 1);
		new_icon = newIconSheet.completeCrop(1, 1);
		settings_icon = settingsIconSheet.completeCrop(5, 7, 2);
		title_icon = titleIconSheet.completeCrop(1, 1);
		
		for(int i = 0; i < 4; i++) {
			player_down[i] = playerSheet.crop(width * i, 0, width, height);
		}
		for(int i = 0; i < 4; i++) {
			player_right[i] = playerSheet.crop(width * i, height, width, height);
		}
		for(int i = 0; i < 4; i++) {
			player_left[i] = playerSheet.crop(width * i, height * 2, width, height);
		}
		for(int i = 0; i < 4; i++) {
			player_up[i] = playerSheet.crop(width * i, height * 3, width, height);
		}
		
		player_down_still = playerSheet.crop(0, 0, width, height);
		player_right_still = playerSheet.crop(0, height, width, height);
		player_left_still = playerSheet.crop(0, height * 2, width, height);
		player_up_still = playerSheet.crop(0, height * 3, width, height);
		
		grass = tileSheet.crop(0, 0, width, height);
		dirt = tileSheet.crop(0, height, width, height);
		tree = staticEntitySheet.crop(0, 0, width, height * 2);
	}
	
}
