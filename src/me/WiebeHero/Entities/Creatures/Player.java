package me.WiebeHero.Entities.Creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import me.WiebeHero.Entities.Entity;
import me.WiebeHero.Input.CustomKeyManager;
import me.WiebeHero.UI.Game.Inventory.UIInventory;
import me.WiebeHero.Worlds.World;
import me.WiebeHero.gfx.Assets;
import me.WiebeHero.gfx.GameCamera;
import me.WiebeHero.gfx.Animations.AnimOption;
import me.WiebeHero.gfx.Animations.SpriteAnimation;

public class Player extends Creature{
	
	//Animations
	private SpriteAnimation animDown, animUp, animLeft, animRight;
	private SpriteAnimation slashExample;
	private int attackX, attackY;
	private int normalSpeed, sprintSpeed;
	private boolean sprinting;
	private int level, xp, mxp;
	//Attack timer
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
	
	private UIInventory uiInventory;
	private GameCamera gameCamera;
	private CustomKeyManager keyManager;
	
	public Player(float x, float y, World world, GameCamera gameCamera, CustomKeyManager keyManager) {
		super(x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT, world);
		this.gameCamera = gameCamera;
		this.gameCamera.centerOnEntity(this);
		this.keyManager = keyManager;
		this.level = 1;
		this.xp = 0;
		this.mxp = 25;
		this.bounds.x = 16;
		this.bounds.y = 32;
		this.bounds.width = 32;
		this.bounds.height = 32;
		this.normalSpeed = 150;
		this.sprintSpeed = 125;
		this.sprinting = false;
		//Animations
		this.animDown = new SpriteAnimation(this.normalSpeed, Assets.player_down, AnimOption.LOOP);
		this.animUp = new SpriteAnimation(this.normalSpeed, Assets.player_up, AnimOption.LOOP);
		this.animLeft = new SpriteAnimation(this.normalSpeed, Assets.player_left, AnimOption.LOOP);
		this.animRight = new SpriteAnimation(this.normalSpeed, Assets.player_right, AnimOption.LOOP);
		this.slashExample = new SpriteAnimation(40, Assets.slashExample, AnimOption.PAUSED, AnimOption.LOOP, AnimOption.PAUSE_END_LOOP);
	}
	
	@Override
	public void die() {
		System.out.println("U lose nerd");
	}

	@Override
	public void tick() {
		//Animations
		this.animDown.tick();
		this.animUp.tick();
		this.animLeft.tick();
		this.animRight.tick();
		this.slashExample.tick();
		//Movement
		this.getInput();
		this.move();
		this.gameCamera.centerOnEntity(this);
		//Attack
		this.checkAttacks();
		int inv = this.keyManager.getKeyboardControls().inventory;
		if(this.keyManager.inventory && !this.keyManager.cantPress(inv)) {
			this.uiInventory.setActive(!this.uiInventory.isActive());
		}
	}
	
	private void checkAttacks() {
		this.attackTimer += System.currentTimeMillis() - this.lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown) 
			return;
		
		Rectangle cb = this.getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		int arSize = 20;
		ar.width = arSize;
		ar.height = arSize;
		
		if(this.keyManager.aUp) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
		}
		else if(this.keyManager.aDown) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
		}
		else if(this.keyManager.aLeft) {
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}
		else if(this.keyManager.aRight) {
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}
		else {
			return;
		}
		
		attackTimer = 0;
		
		for(Entity e : this.world.getEntityManager().getEntities()) {
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0, 0).intersects(ar)) {
				e.hurt(1);
				attackX = ar.x;
				attackY = ar.y;
				this.slashExample.setOptionState(AnimOption.PAUSED, false);
				return;
			}
		}
	}
	
	private void getInput() {
		this.xMove = 0.0F;
		this.yMove = 0.0F;
		float tempSpeed = this.speed;
		if(this.keyManager.sprint) {
			tempSpeed *= 1.5;
			if(!this.sprinting) {
				this.animDown.setSpeed(this.sprintSpeed);
				this.animUp.setSpeed(this.sprintSpeed);
				this.animLeft.setSpeed(this.sprintSpeed);
				this.animRight.setSpeed(this.sprintSpeed);
			}
			this.sprinting = true;
		}
		else if(this.sprinting) {
			this.sprinting = false;
			this.animDown.setSpeed(this.normalSpeed);
			this.animUp.setSpeed(this.normalSpeed);
			this.animLeft.setSpeed(this.normalSpeed);
			this.animRight.setSpeed(this.normalSpeed);
		}
		if(this.keyManager.up) {
			this.yMove = -tempSpeed;
		}
		if(this.keyManager.down) {
			this.yMove = tempSpeed;
		}
		if(this.keyManager.left) {
			this.xMove = -tempSpeed;
		}
		if(this.keyManager.right) {
			this.xMove = tempSpeed;
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(this.getCurrentAnimationFrame(), (int)(this.x - this.gameCamera.getCameraPosition().getXOffset()), (int)(this.y - this.gameCamera.getCameraPosition().getYOffset()), this.width, this.height, null);
		g.drawImage(this.slashExample.getCurrentFrame(), (int)(attackX - this.gameCamera.getCameraPosition().getXOffset()), (int)(attackY - this.gameCamera.getCameraPosition().getYOffset()), 32, 32, null);
//		g.setColor(Color.RED);
//		g.fillRect((int)(this.x + this.bounds.x - this.handler.getGameCamera().getxOffset()), 
//				(int)(this.y + this.bounds.y - this.handler.getGameCamera().getyOffset()),
//				this.bounds.width, this.bounds.height);
	}

	private BufferedImage getCurrentAnimationFrame() {
		
		switch(this.getDirection()) {
			case LEFT:
				if(this.xMove < 0) {//Left Animation
					return this.animLeft.getCurrentFrame();
				}
				else {
					return Assets.player_left_still;
				}
			case RIGHT:
				if(this.xMove > 0) {//Right Animation
					return this.animRight.getCurrentFrame();
				}
				else {
					return Assets.player_right_still;
				}
			case UP:
				if(this.yMove < 0) {//Up Animation
					return this.animUp.getCurrentFrame();
				}
				else {
					return Assets.player_up_still;
				}
			case DOWN:
				if(this.yMove > 0) {//Down Animation
					return this.animDown.getCurrentFrame();
				}
				else {
					return Assets.player_down_still;
				}
			default:
				return Assets.player_down_still;
		}
	}
	
	public boolean isSprinting() {
		return this.sprinting;
	}
	
	public int getWalkAnimationSpeed() {
		return this.normalSpeed;
	}
	
	public int getSprintAnimationSpeed() {
		return this.normalSpeed;
	}
	
	public int getBoundingBoxWidth() {
		return this.bounds.x;
	}
	
	public int getBoundingBoxHeight() {
		return this.bounds.y;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExperience() {
		return xp;
	}

	public void setExperience(int xp) {
		this.xp = xp;
	}

	public int getMaxExperience() {
		return mxp;
	}

	public void setMaxExperience(int mxp) {
		this.mxp = mxp;
	}
	
	public UIInventory getUIInventory() {
		return this.uiInventory;
	}

	public void setUIInventory(UIInventory uiInventory) {
		this.uiInventory = uiInventory;
	}
}
