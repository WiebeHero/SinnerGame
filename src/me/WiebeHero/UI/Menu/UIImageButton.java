package me.WiebeHero.UI.Menu;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import me.WiebeHero.Input.Listeners.Listener;
import me.WiebeHero.UI.UIObject;
import me.WiebeHero.gfx.Screen;
import me.WiebeHero.gfx.Animations.AnimType;
import me.WiebeHero.gfx.Animations.Animation;
import me.WiebeHero.gfx.Animations.MovementAnimation;
import me.WiebeHero.gfx.Animations.SizeAnimation;
import me.WiebeHero.gfx.Animations.SpriteAnimation;

public class UIImageButton extends UIObject{

	private BufferedImage[] images;
	private ArrayList<Animation> anims;
	
	public UIImageButton(float x, float y, int width, int height, Screen screen, BufferedImage[] images) {
		super(x, y, width, height, screen);
		this.images = images;
		this.anims = new ArrayList<Animation>();
	}
	
	public UIImageButton(double marginX, double marginY, int width, int height, Screen screen, BufferedImage[] images) {
		super(marginX, marginY, width, height, screen);
		this.images = images;
		this.anims = new ArrayList<Animation>();
	}
	
	public UIImageButton(double marginX, double marginY, int width, int height, Screen screen, BufferedImage[] images, UIObject inheritance) {
		super(marginX, marginY, width, height, screen, inheritance);
		this.images = images;
		this.anims = new ArrayList<Animation>();
	}

	@Override
	public void tick() {
		if(this.anims != null && this.anims.size() != 0) {
			for(Animation anim : this.anims) {
				anim.setHovering(this.hovering);
				anim.tick();
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		if(this.anims != null && this.anims.size() != 0) {
			BufferedImage img = this.images[0];
			Rectangle rect = this.getBounds();
			int tempX = (int)this.x;
			int tempY = (int)this.y;
			float xOffset = 0;
			float yOffset = 0;
			int widthOffset = 0;
			int heightOffset = 0;
			for(Animation anim : this.anims) {
				switch(anim.getAnimationType()) {
					case SPRITE:
						img = this.getCurrentFrame();
						break;
					case MOVEMENT:
						int pair[] = this.getCurrentOffsets();
						xOffset = pair[0];
						yOffset = pair[1];
						break;
					case SIZE:
						pair = this.getCurrentSizes();
						widthOffset = pair[0];
						heightOffset = pair[1];
						break;
				}
			}
			tempX -= widthOffset / 2;
			tempY -= heightOffset / 2;
			rect.x = (int)(tempX + xOffset);
			rect.y = (int)(tempY + yOffset);
			rect.width = this.getWidth() + widthOffset;
			rect.height = this.getHeight() + heightOffset;
			g.drawImage(img, (int)(tempX + xOffset), (int)(tempY + yOffset), this.width + widthOffset, this.height + heightOffset, null);
		}
		else {
			g.drawImage(this.images[0], (int)(this.x), (int)(this.y), this.width, this.height, null);
		}
	}
	
	public BufferedImage getCurrentFrame() {
		if(this.anims != null && this.anims.size() != 0) {
			for(Animation a : this.anims) {
				if(a.getAnimationType() == AnimType.SPRITE) {
					SpriteAnimation anim = (SpriteAnimation) a;
					return anim.getCurrentFrame();
				}
			}
		}
		return null;
	}
	
	public int[] getCurrentOffsets() {
		if(this.anims != null && this.anims.size() != 0) {
			for(Animation a : this.anims) {
				if(a.getAnimationType() == AnimType.MOVEMENT) {
					MovementAnimation anim = (MovementAnimation) a;
					return new int[] {(int)anim.getXOffset() * anim.getCurrentIndex(), (int)anim.getYOffset() * anim.getCurrentIndex()};
				}
			}
		}
		return null;
	}
	
	public int[] getCurrentSizes() {
		if(this.anims != null && this.anims.size() != 0) {
			for(Animation a : this.anims) {
				if(a.getAnimationType() == AnimType.SIZE) {
					SizeAnimation anim = (SizeAnimation) a;
					return new int[] {(int)anim.getWidthOffset() * anim.getCurrentIndex(), (int)anim.getHeightOffset() * anim.getCurrentIndex()};
				}
			}
		}
		return null;
	}

	@Override
	public void onMousePress() {
		
	}
	
	@Override
	public void onMouseHover() {
		
	}
	
	@Override
	public void onMouseDrag() {
		
	}
	
	@Override
	public void onMouseRelease() {
		
	}
	
	@Override
	public void onMouseClicked() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseEntered() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseExited() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onKeyPress(int key) {
		
	}
	
	@Override
	public void onKeyRelease(int key) {
		
	}
	
	@Override
	public void onKeyType(int key) {
		
	}
	
	public Animation getAnimation(int index) {
		return this.anims.get(index);
	}
	
	public void addAnimations(Animation... animations) {
		this.anims.addAll(Arrays.asList(animations));
	}
	
	public void addListeners(Listener... listeners) {
		this.listeners.addAll(Arrays.asList(listeners));
	}
	
}
