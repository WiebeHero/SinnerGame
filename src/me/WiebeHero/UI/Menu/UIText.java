package me.WiebeHero.UI.Menu;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

import me.WiebeHero.UI.UIObject;
import me.WiebeHero.gfx.Screen;

public class UIText extends UIObject{
	
	private String text;
	private Font font;
	
	public UIText(double marginX, double marginY, Screen screen, String text) {
		super(marginX, marginY, 0, 0, screen);
		this.text = text;
	}
	
	public UIText(double marginX, double marginY, Screen screen, String text, Font font) {
		super(marginX, marginY, 0, 0, screen);
		this.text = text;
		this.font = font;
	}
	
	public UIText(double marginX, double marginY, Screen screen, UIObject parent, String text) {
		super(marginX, marginY, 0, 0, screen, parent);
		this.text = text;
	}
	
	public UIText(double marginX, double marginY, Screen screen, UIObject parent, String text, Font font) {
		super(marginX, marginY, 0, 0, screen, parent);
		this.text = text;
		this.font = font;
	}

	@Override
	public void tick() {
		this.updateMargin();
	}

	@Override
	public void render(Graphics g) {
		Font oldFont = g.getFont();
		Font font = null;
		if(this.font != null) {
			font = this.font;
		}
		else {
			font = g.getFont();
		}
		g.setFont(font);
		FontRenderContext frc = g.getFontMetrics().getFontRenderContext();
		TextLayout layout = new TextLayout(text, font, frc);
		layout.draw((Graphics2D) g, (int)x, (int)y);
		if(width != (int)layout.getBounds().getWidth() && height != (int)layout.getBounds().getHeight()) {
			System.out.println("Yes");
			width = (int) layout.getBounds().getWidth();
			height = (int) layout.getBounds().getHeight();
		}
		g.setFont(oldFont);
	}

	@Override
	public void onMouseHover() {
		
	}

	@Override
	public void onMouseRelease() {
		
	}

	@Override
	public void onMousePress() {
		
	}

	@Override
	public void onMouseDrag() {
		
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
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return this.text;
	}

}
