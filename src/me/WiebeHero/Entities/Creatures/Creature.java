package me.WiebeHero.Entities.Creatures;

import me.WiebeHero.Entities.Direction;
import me.WiebeHero.Entities.Entity;
import me.WiebeHero.TileRules.Collidable;
import me.WiebeHero.Tiles.Tile;
import me.WiebeHero.Tiles.TileManager;
import me.WiebeHero.Worlds.GameWorld;
import me.WiebeHero.Worlds.World;

public abstract class Creature extends Entity{
	
	public static final float DEFAULT_SPEED = 2.4F;
	public static final int DEFAULT_CREATURE_WIDTH = 64, DEFAULT_CREATURE_HEIGHT = 64;
	
	protected Direction dir;
	protected float speed;
	protected float xMove, yMove;
	
	/**
	 * The Creature constructor. This constructor can normally not be called
	 * due to it being an abstract class. Extend this class to use it!
	 * @param x 		| The X position of the Creature.
	 * @param y 		| The Y position of the Creature.
	 * @param width		| The Width of the Creature.
	 * @param height	| The Height of the Creature.
	 */
	public Creature(float x, float y, int width, int height, World world) {
		super(x, y, width, height, world);
		this.speed = DEFAULT_SPEED;
		this.xMove = 0.0F;
		this.yMove = 0.0F;
		this.dir = Direction.DOWN;
	}
	/**
	 * The move method is called when the Creature needs to move in a certain direction.
	 */
	public void move() {
		if(!this.checkEntityCollisions(xMove, 0F)) {
			this.moveX();
		}
		if(!this.checkEntityCollisions(0F, yMove)) {
			this.moveY();
		}
	}
	/**
	 * A method that sends an Creature along the X axis (Left and Right)
	 */
	public void moveX() {
		if(this.xMove > 0) {//Moving Right
			int tx = (int) (this.x + this.xMove + this.bounds.x + this.bounds.width) / TileManager.TILEWIDTH;
			
			if(!this.collisionWithTile(tx, (int)(this.y + this.bounds.y) / TileManager.TILEHEIGHT) && 
					!this.collisionWithTile(tx, (int) (this.y + this.bounds.y + this.bounds.height) / TileManager.TILEHEIGHT)) {
				this.x += this.xMove;
			}
			else {
				this.x = tx * TileManager.TILEWIDTH - this.bounds.x - this.bounds.width - 1;
			}
			this.dir = Direction.RIGHT;
		}
		else if(this.xMove < 0) {//Moving Left
			int tx = (int) (this.x + this.xMove + this.bounds.x) / TileManager.TILEWIDTH;
			
			if(!this.collisionWithTile(tx, (int)(this.y + this.bounds.y) / TileManager.TILEHEIGHT) && 
					!this.collisionWithTile(tx, (int) (this.y + this.bounds.y + this.bounds.height) / TileManager.TILEHEIGHT)) {
				this.x += this.xMove;
			}
			else {
				this.x = tx * TileManager.TILEWIDTH + TileManager.TILEWIDTH - this.bounds.x;
			}
			this.dir = Direction.LEFT;
		}
	}
	/**
	 * A method that sends an Creature along the Y axis (Up and Down)
	 */
	public void moveY() {
		if(this.yMove < 0) {//Moving Up
			int ty = (int)(this.y + this.yMove + this.bounds.y) / TileManager.TILEHEIGHT;
			
			if(!this.collisionWithTile((int) (this.x + this.bounds.x) / TileManager.TILEWIDTH, ty) &&
					!this.collisionWithTile((int) (this.x + this.bounds.x + this.bounds.width) / TileManager.TILEWIDTH, ty)) {
				this.y += this.yMove;
			}
			else {
				this.y = ty * TileManager.TILEHEIGHT + TileManager.TILEHEIGHT - this.bounds.y;
			}
			this.dir = Direction.UP;
		}
		else if(this.yMove > 0) {//Moving Down
			int ty = (int)(this.y + this.yMove + this.bounds.y + this.bounds.height) / TileManager.TILEHEIGHT;
			
			if(!this.collisionWithTile((int) (this.x + this.bounds.x) / TileManager.TILEWIDTH, ty) &&
					!this.collisionWithTile((int) (this.x + this.bounds.x + this.bounds.width) / TileManager.TILEWIDTH, ty)) {
				this.y += this.yMove;
			}
			else {
				this.y = ty * TileManager.TILEHEIGHT - this.bounds.y - this.bounds.height - 1;
			}
			this.dir = Direction.DOWN;
		}
	}
	/**
	 * A method to see if the Creature is colliding with a tile or not.
	 * @param x 		| X position of the tile.
	 * @param y 		| Y position of the tile.
	 * @return boolean	| To see if the Creature is actually colliding with the entity or not.
	 */
	protected boolean collisionWithTile(int x, int y) {
		if(this.world instanceof GameWorld) {
			GameWorld gameWorld = (GameWorld) this.world;
			Tile tile = gameWorld.getTile(x, y);
			if(tile instanceof Collidable) {
				Collidable collTile = (Collidable) tile;
				return collTile.isSolid();
			}
		}
		return false;
	}
	
	//GETTERS AND SETTERS
	
	/**
	 * A getter that returns the amount of X movement the Creature needs
	 * to perform
	 * @return xMove | The amount of movement the Creature must perform along the X axis.
	 */
	public float getxMove() {
		return this.xMove;
	}
	/**
	 * A setter that sets the amount of X movement the Creature needs
	 * to perform
	 * @param xMove | The desired amount of movement the Creature must perform along the X axis.
	 */
	public void setxMove(float xMove) {
		this.xMove = xMove;
	}
	/**
	 * A getter that returns the amount of Y movement the Creature needs
	 * to perform
	 * @return yMove | The amount of movement the Creature must perform along the Y axis.
	 */
	public float getyMove() {
		return this.yMove;
	}
	/**
	 * A setter that sets the amount of Y movement the Creature needs
	 * to perform
	 * @param yMove | The desired amount of movement the Creature must perform along the Y axis.
	 */
	public void setyMove(float yMove) {
		this.yMove = yMove;
	}
	/**
	 * A getter that returns the speed at which the Creature can move at.
	 * @return speed | Speed at which the entity can move at per frame.
	 */
	public float getSpeed() {
		return this.speed;
	}
	/**
	 * A setter that sets the speed at which the Create can move at.
	 * 
	 * @param speed
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public void setDirection(Direction dir) {
		this.dir = dir;
	}
	
	public Direction getDirection() {
		return this.dir;
	}
	
}
