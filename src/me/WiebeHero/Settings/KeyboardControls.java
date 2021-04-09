package me.WiebeHero.Settings;

import java.awt.event.KeyEvent;

public class KeyboardControls {
	
	public int inventory;
	public int up, down, left, right, sprint;
	public int aUp, aDown, aLeft, aRight;
	
	public KeyboardControls() {
		this.inventory = KeyEvent.VK_E;
		this.sprint = KeyEvent.VK_SHIFT;
		this.up = KeyEvent.VK_W;
		this.down = KeyEvent.VK_S;
		this.left = KeyEvent.VK_A;
		this.right = KeyEvent.VK_D;
		this.aUp = KeyEvent.VK_UP;
		this.aDown = KeyEvent.VK_DOWN;
		this.aLeft = KeyEvent.VK_LEFT;
		this.aRight = KeyEvent.VK_RIGHT;
	}
	
	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public int getUp() {
		return up;
	}

	public void setUp(int up) {
		this.up = up;
	}

	public int getDown() {
		return down;
	}

	public void setDown(int down) {
		this.down = down;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public int getSprint() {
		return sprint;
	}

	public void setSprint(int sprint) {
		this.sprint = sprint;
	}

	public int getaUp() {
		return aUp;
	}

	public void setaUp(int aUp) {
		this.aUp = aUp;
	}

	public int getaDown() {
		return aDown;
	}

	public void setaDown(int aDown) {
		this.aDown = aDown;
	}

	public int getaLeft() {
		return aLeft;
	}

	public void setaLeft(int aLeft) {
		this.aLeft = aLeft;
	}

	public int getaRight() {
		return aRight;
	}

	public void setaRight(int aRight) {
		this.aRight = aRight;
	}
}
