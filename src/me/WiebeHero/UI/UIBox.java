package me.WiebeHero.UI;

import java.awt.Graphics;

import me.WiebeHero.gfx.Screen;

public class UIBox extends UIObject{
	
	public UIBox(double marginX, double marginY, int width, int height, Screen screen, UIObject... children) {
		super(marginX, marginY, width, height, screen, children);
	}

	@Override
	public void tick() {
		for(UIObject children : this.getChildren()) {
			children.tick();
		}
	}

	@Override
	public void render(Graphics g) {
		for(UIObject children : this.getChildren()) {
			children.render(g);
		}
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
	public void onKeyType(int arg0) {
		// TODO Auto-generated method stub
		
	}
}
