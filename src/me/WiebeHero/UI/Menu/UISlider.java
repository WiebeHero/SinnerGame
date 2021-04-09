package me.WiebeHero.UI.Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.util.ArrayList;
import java.util.Arrays;

import me.WiebeHero.Input.CustomMouseManager;
import me.WiebeHero.Input.MouseManager;
import me.WiebeHero.Input.Listeners.HoverListener;
import me.WiebeHero.Input.Listeners.Listener;
import me.WiebeHero.Input.Listeners.PressListener;
import me.WiebeHero.Input.Listeners.ReleaseListener;
import me.WiebeHero.UI.UIObject;
import me.WiebeHero.gfx.Screen;

public class UISlider extends UIObject{
	
	private UIPivot pivot;
	private int max;
	private int value;
	private boolean[] options;
	private ArrayList<Listener> listeners;
	
	//**OPTIONS**
	//There are different options you can choose for the animation to perform
	//Arguments: 0 : FALSE = PIVOT FOLLOWS MOUSE, TRUE = PIVOT FOLLOWS POINTS
	//**OPTIONS**
	
	public UISlider(UIPivot pivotG, double marginX, double marginY, int width, int height, Screen screen, int max, boolean... options) {
		super(marginX, marginY, width, height, screen);
		this.listeners = new ArrayList<Listener>();
		this.max = max;
		this.pivot = pivotG;
		this.pivot.setParent(this);
		int[] points = new int[max + 1];
		int distance = width / max;
		for(int i = 0; i < points.length; i++) {
			points[i] = (int)x + (distance * i);
		}
		double diff = 100.00 / (double)points.length;
		this.pivot.setMarginX(diff * this.value);
		this.pivot.setMarginY(50.0);
		this.options = new boolean[1];
		this.addChild(this.pivot);
		for(int i = 0; i < options.length; i++) {
			this.options[i] = options[i];
		}
		
	}
	
	public UISlider(UIPivot pivotG, double marginX, double marginY, int width, int height, Screen screen, int max, int value1, boolean... options) {
		super(marginX, marginY, width, height, screen);
		this.listeners = new ArrayList<Listener>();
		this.max = max;
		this.pivot = pivotG;
		this.pivot.setParent(this);
		this.value = value1;
		int[] points = new int[max + 1];
		int distance = width / max;
		for(int i = 0; i < points.length; i++) {
			points[i] = (int)x + (distance * i);
		}
		double diff = 100.00 / (double)points.length;
		this.pivot.setMarginX(diff * this.value);
		this.pivot.setMarginY(50.0);
		this.options = new boolean[1];
		this.addChild(this.pivot);
		for(int i = 0; i < options.length; i++) {
			this.options[i] = options[i];
		}
	}

	@Override
	public void tick() {
		this.pivot.tick();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, width, height);
		int[] points = new int[max + 1];
		for(int i = 0; i < points.length; i++) {
			points[i] = (int)x + (width / max * i);
		}
		g.setColor(Color.BLUE);
		Font font = g.getFont();
		FontRenderContext frc = g.getFontMetrics().getFontRenderContext();
		TextLayout layout = new TextLayout(value + "", font, frc);
		layout.draw((Graphics2D) g, (int)(x - layout.getBounds().getWidth() / 2 - 18), (int)(y + (height / 2) + (layout.getBounds().getHeight() / 2)));
		this.pivot.render(g);
	}

	@Override
	public void onMouseHover() {
		for(Listener listener : this.listeners) {
			if(listener instanceof HoverListener) {
				listener.listen();
			}
		}
	}

	@Override
	public void onMousePress() {
		this.update();
		for(Listener listener : this.listeners) {
			if(listener instanceof PressListener) {
				listener.listen();
			}
		}
	}
	
	@Override
	public void onMouseRelease() {
		int mouseX = CustomMouseManager.mouseX;
		int[] points = new int[max + 1];
		int distance = width / max;
		for(int i = 0; i < points.length; i++) {
			points[i] = (int)x + (distance * i);
		}
		for(int i = 0; i < points.length; i++) {
			if(mouseX - points[i]  < distance / 2 || points[i] - mouseX > -distance / 2) {
				value = i;
				double diff = (double)(points[i] - x) / (double)width * 100.00;
				pivot.setMarginX(diff);
				break;
			}
		}
		for(Listener listener : this.listeners) {
			if(listener instanceof ReleaseListener) {
				listener.listen();
			}
		}
	}

	@Override
	public void onMouseDrag() {
		this.update();
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
	
	private void update() {
		int mouseX = CustomMouseManager.mouseX;
		int[] points = new int[max + 1];
		int distance = width / max;
		for(int i = 0; i < points.length; i++) {
			points[i] = (int)x + (distance * i);
		}
		for(int i = 0; i < points.length; i++) {
			if(mouseX - points[i]  < distance / 2 || points[i] - mouseX > -distance / 2) {
				if(options[0]) {
					double diff = (double)(points[i] - x) / (double)width * 100.00;
					pivot.setMarginX(diff);
				}
				value = i;
				break;
			}
		}
	}
	
	public void addListeners(Listener... listeners) {
		this.listeners.addAll(Arrays.asList(listeners));
	}
	
	public int getValue() {
		return this.value;
	}
}
