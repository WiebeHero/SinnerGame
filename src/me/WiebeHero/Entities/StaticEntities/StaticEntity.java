package me.WiebeHero.Entities.StaticEntities;

import me.WiebeHero.Entities.Entity;
import me.WiebeHero.Worlds.World;

public abstract class StaticEntity extends Entity{
	
	public StaticEntity(float x, float y, int width, int height, World world) {
		super(x, y, width, height, world);
	}
	
}
