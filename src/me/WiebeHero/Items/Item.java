package me.WiebeHero.Items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import me.WiebeHero.Entities.Entity;
import me.WiebeHero.Entities.EntityManager;
import me.WiebeHero.Entities.Creatures.Player;
import me.WiebeHero.Worlds.World;
import me.WiebeHero.gfx.Assets;
import me.WiebeHero.gfx.CameraPosition;

public class Item {
	
	// CLASS
	
	public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;
	
	protected BufferedImage[] texture;
	protected String name;
	protected final int id;
	protected World world;
	protected CameraPosition cameraPos;
	
	protected Rectangle bounds;
	
	protected int x, y, count;
	protected boolean pickedUp = false;
	
	public Item(BufferedImage[] texture, String name, int id, CameraPosition cameraPos, World world) {
		this.texture = texture;
		this.name = name;
		this.id = id;
		this.count = 1;
		this.bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);
		this.cameraPos = cameraPos;
		this.world = world;
	}
	
	public void tick() {
		EntityManager entityManager = this.world.getEntityManager();
		for(Entity entity : entityManager.getEntities()) {
			if(entity instanceof Player) {
				Player player = (Player) entity;
				if(player.getCollisionBounds(0f, 0f).intersects(bounds) && pickedUp == false) {
					pickedUp = true;
					player.getUIInventory().addItem(this);
				}
				break;
			}
		}
	}

	public void render(Graphics g) {
		this.render(g, (int)(this.x - this.cameraPos.getXOffset()), (int)(this.y - this.cameraPos.getYOffset()));
	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(this.texture[0], x, y, ITEMWIDTH, ITEMHEIGHT, null);
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		bounds.x = x;
		bounds.y = y;
	}
	
	// GETTERS AND SETTERS
	
	public BufferedImage[] getTextures() {
		return texture;
	}

	public void setTextures(BufferedImage[] texture) {
		this.texture = texture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getId() {
		return id;
	}
	
	public boolean isPickedUp() {
		return pickedUp;
	}
	
	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = true;
	}
	
}
