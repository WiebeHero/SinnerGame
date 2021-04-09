package me.WiebeHero.UI.Menu;

import java.awt.Color;
import java.awt.Graphics;

import me.WiebeHero.UI.UIObject;
import me.WiebeHero.gfx.Screen;

public class UIPivot extends UIObject{
	
	private Color color;
	
	public UIPivot(int width, int height, Screen screen, Color color) {
		super(0.0, 0.0, width, height, screen);
		this.color = color;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int)this.x, (int)this.y, this.width, this.height);
	}

	@Override
	public void onMouseHover() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseRelease() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMousePress() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseDrag() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}
}
