package me.WiebeHero.Input;

import java.awt.event.MouseEvent;

public class CustomMouseManager extends MouseManager{
	
	private boolean leftPressed, rightPressed;
	public static int mouseX, mouseY;
	
	public CustomMouseManager() {
		super();
	}
	
	public boolean isLeftPressed() {
		return this.leftPressed;
	}

	public boolean isRightPressed() {
		return this.rightPressed;
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		if(this.uiManager != null) {
			this.uiManager.onMouseClicked(event);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent event) {
		mouseX = event.getX();
		mouseY = event.getY();
		if(this.uiManager != null) {
			this.uiManager.onMouseDragged(event);
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent event) {
		if(this.uiManager != null) {
			this.uiManager.onMouseEntered(event);
		}
	}
	
	@Override
	public void mouseExited(MouseEvent event) {
		if(this.uiManager != null) {
			this.uiManager.onMouseExited(event);
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent event) {
		mouseX = event.getX();
		mouseY = event.getY();
		if(this.uiManager != null) {
			this.uiManager.onMouseMoved(event);
		}
	}
	
	@Override
	public void mousePressed(MouseEvent event) {
		if(event.getButton() == MouseEvent.BUTTON1) {
			this.leftPressed = true;
		}
		else if(event.getButton() == MouseEvent.BUTTON3) {
			this.rightPressed = true;
		}
		if(this.uiManager != null) {
			this.uiManager.onMousePressed(event);
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent event) {
		if(event.getButton() == MouseEvent.BUTTON1) {
			this.leftPressed = false;
		}
		else if(event.getButton() == MouseEvent.BUTTON3) {
			this.rightPressed = false;
		}
		if(this.uiManager != null) {
			this.uiManager.onMouseReleased(event);
		}
	}
}
