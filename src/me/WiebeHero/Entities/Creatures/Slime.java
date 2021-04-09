package me.WiebeHero.Entities.Creatures;

import java.awt.Graphics;

import me.WiebeHero.Items.Item;
import me.WiebeHero.Worlds.GameWorld;
import me.WiebeHero.Worlds.World;
import me.WiebeHero.gfx.Assets;
import me.WiebeHero.gfx.CameraPosition;

public class Slime extends Creature{

	private CameraPosition cameraPos;
	
	public Slime(float x, float y, int width, int height, CameraPosition gameCamera, World world) {
		super(x, y, width, height, world);
		this.cameraPos = gameCamera;
	}

	@Override
	public void die() {
		if(this.world instanceof GameWorld) {
			GameWorld gameWorld = (GameWorld) world;
			Item item = new Item(Assets.swordExample, "Sword", 0, this.cameraPos, this.world);
			item.setPosition((int)this.x, (int)this.y);
			gameWorld.getItemManager().addItem(item);
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.slime[0], (int)(this.x - this.cameraPos.getXOffset()), (int)(this.y - this.cameraPos.getYOffset()), this.width, this.height, null);
	}

	@Override
	public void tick() {
		
	}

}
