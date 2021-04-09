package me.WiebeHero.Input;

import java.awt.event.KeyEvent;

import me.WiebeHero.Settings.KeyboardControls;

public class CustomKeyManager extends KeyManager{
	
	private KeyboardControls keyboardControls;
	private boolean[] cantPress, justPressed;
	public boolean inventory;
	public boolean up, down, left, right, sprint;
	public boolean aUp, aDown, aLeft, aRight;
	
	public CustomKeyManager(KeyboardControls keyboardControls) {
		super();
		this.cantPress = new boolean[this.keys.length];
		this.justPressed = new boolean[this.keys.length];
		this.keyboardControls = keyboardControls;
	}
	
	@Override
	public void tick() {
		for(int i = 0; i < keys.length; i++) {
			if(cantPress[i] && !keys[i]) {
				cantPress[i] = false;
			}
			else if(justPressed[i]) {
				cantPress[i] = true;
				justPressed[i] = false;
			}
			if(!cantPress[i] && keys[i]) {
				justPressed[i] = true;
			}
		}
		this.inventory = this.keys[this.keyboardControls.getInventory()];
		this.up = this.keys[this.keyboardControls.getUp()];
		this.left = this.keys[this.keyboardControls.getLeft()];
		this.right = this.keys[this.keyboardControls.getRight()];
		this.down = this.keys[this.keyboardControls.getDown()];
		this.sprint = this.keys[this.keyboardControls.getSprint()];
		
		this.aUp = this.keys[this.keyboardControls.getaUp()];
		this.aDown = this.keys[this.keyboardControls.getaDown()];
		this.aLeft = this.keys[this.keyboardControls.getaLeft()];
		this.aRight = this.keys[this.keyboardControls.getaRight()];
	}
	
	@Override
	public void keyPressed(KeyEvent event) {
		if(event.getKeyCode() < 0 || event.getKeyCode() >= keys.length)
			return;
		this.keys[event.getKeyCode()] = true;
		if(this.uiManager != null) {
			this.uiManager.onKeyPressed(event);
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
		if(event.getKeyCode() < 0 || event.getKeyCode() >= keys.length)
			return;
		this.keys[event.getKeyCode()] = false;
		if(this.uiManager != null) {
			this.uiManager.onKeyReleased(event);
		}
	}

	@Override
	public void keyTyped(KeyEvent event) {
		
	}
	
	public boolean cantPress(int key) {
		return cantPress[key];
	}
	
	public KeyboardControls getKeyboardControls() {
		return this.keyboardControls;
	}

	
}
