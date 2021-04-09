package me.WiebeHero.UI.Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;

import me.WiebeHero.Settings.Settings;
import me.WiebeHero.UI.UIObject;
import me.WiebeHero.gfx.Screen;

public class UIInputRecorder extends UIObject{
	
	private Settings settings;
	private Color color;
	private boolean recording;
	private BufferedImage[] images;

	public UIInputRecorder(double marginX, double marginY, int width, int height, Screen screen, Settings settings, Color color) {
		super(marginX, marginY, width, height, screen);
		this.settings = settings;
		this.color = color;
	}
	
	public UIInputRecorder(double marginX, double marginY, int width, int height, Screen screen, Settings settings, BufferedImage images[]) {
		super(marginX, marginY, width, height, screen);
		this.settings = settings;
		this.images = images;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		if(this.color != null)
			g.setColor(color);
			g.fillRect((int)x, (int)y, width, height);
		if(this.images != null)
			g.drawImage(this.images[0], (int)x, (int)y, width, height, null);
		g.setColor(Color.RED);
		Font font = g.getFont();
		FontRenderContext frc = g.getFontMetrics().getFontRenderContext();
		TextLayout layout = new TextLayout(KeyEvent.getKeyText(this.settings.getKeyboardControls().getUp()) + "", font, frc);
		layout.draw((Graphics2D) g, (int)(x + (width / 2) - layout.getBounds().getWidth() / 2 + 2), (int)(y + (height / 2) + (layout.getBounds().getHeight() / 2 + 1)));
	}

	@Override
	public void onMouseHover() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseRelease() {
		this.recording = true;
		Thread t = new Thread() {
			public void run() {
				try {
					sleep(1000);
				} 
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				recording = false;
			}
		};
		t.start();
	}

	@Override
	public void onMousePress() {
		
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
		if(!this.recording) 
			return;
		this.recorded(key);
	}
	
	@Override
	public void onKeyRelease(int key) {
		
	}
	
	@Override
	public void onKeyType(int key) {
		// TODO Auto-generated method stub
		
	}

	public void recorded(int key) {}

}
