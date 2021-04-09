package me.WiebeHero.UI.Game.Inventory;

import java.awt.Graphics;

import me.WiebeHero.Input.CustomMouseManager;
import me.WiebeHero.Inventory.Inventory;
import me.WiebeHero.Items.Item;
import me.WiebeHero.UI.UIObject;
import me.WiebeHero.gfx.Screen;

public class UIInventorySlot extends UIObject{
	
	private Item item;
	private boolean selected;
	private int slot;

	public UIInventorySlot(double marginX, double marginY, int width, int height, Screen screen, UIObject inheritance, Item item, int slot) {
		super(marginX, marginY, width, height, screen, inheritance);
		this.item = item;
		this.slot = slot;
	}

	@Override
	public void tick() {
		if(this.item != null) {
			this.item.tick();
		}
	}

	@Override
	public void render(Graphics g) {
		if(this.item != null) {
			if(this.isSelected()) {
				g.drawImage(this.item.getTextures()[0], (int)CustomMouseManager.mouseX - 16, (int)CustomMouseManager.mouseY - 16, width, height, null);
			}
			else {
				g.drawImage(this.item.getTextures()[0], (int)this.x + 1, (int)this.y + 1, width, height, null);
			}
		}
	}

	@Override
	public void onMouseHover() {
		
	}

	@Override
	public void onMouseRelease() {
		
	}

	@Override
	public void onMousePress() {
		this.selected = true;
		UIInventory uiInv = (UIInventory) this.getParent();
		int first = uiInv.getFirstSelectedSlot();
		int second = uiInv.getSecondSelectedSlot();
		if(first == -1) {
			uiInv.setFirstSelectedSlot(this.slot);
		}
		else if(second == -1) {
			uiInv.setSecondSelectedSlot(this.slot);
		}
		first = uiInv.getFirstSelectedSlot();
		second = uiInv.getSecondSelectedSlot();
		if(first != -1 && second != -1) {
			Inventory inv = uiInv.getInventory();
			Item[] items = inv.getItems();
			Item firstItem = items[first];
			Item secondItem = items[second];
			items[first] = secondItem;
			items[second] = firstItem;
			UIInventorySlot[] slots = uiInv.getSlots();
			slots[first].setSelected(false);
			slots[second].setSelected(false);
			uiInv.setFirstSelectedSlot(-1);
			uiInv.setSecondSelectedSlot(-1);
		}
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
	
	@Override
	public void onKeyType(int key) {
		
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public boolean isSelected() {
		return this.selected;
	}

}
