package me.WiebeHero.UI.Game.Inventory;

import java.awt.Graphics;

import me.WiebeHero.Inventory.Inventory;
import me.WiebeHero.Items.Item;
import me.WiebeHero.UI.UIObject;
import me.WiebeHero.UI.Menu.UIText;
import me.WiebeHero.gfx.Assets;
import me.WiebeHero.gfx.Screen;

public class UIInventory extends UIObject{
	
	private UIText uiText;
	private Inventory inv;
	private UIInventorySlot[] slots;
	private int slotSelected1, slotSelected2;
	
	public UIInventory(double marginX, double marginY, int width, int height, Screen screen, UIObject object) {
		super(marginX, marginY, width, height, screen, object);
		this.inv = new Inventory();
		this.uiText = new UIText(50.0D, 20.0D, screen, this, "Inventory", Assets.font28);
		this.slots = new UIInventorySlot[40];
		this.generateSlots();
		this.addChild(this.uiText);
		this.slotSelected1 = -1;
		this.slotSelected2 = -1;
	}
	
	// Inventory Methods
	
	@Override
	public void tick() {
		this.uiText.tick();
		Item[] items = inv.getItems();
		for(int i = 0; i < this.slots.length; i++) {
			this.slots[i].tick();
			this.slots[i].setItem(items[i]);
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.inventoryScreen[0], (int)(this.x), (int)(this.y), this.width, this.height, null);
		for(int i = 0; i < this.slots.length; i++) {
			this.slots[i].render(g);
		}
		this.uiText.render(g);
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
	
	@Override
	public void onKeyType(int key) {
		// TODO Auto-generated method stub
		
	}
	
	private void generateSlots() {
		Item[] items = this.inv.getItems();
		int xTimes = 0;
		double xAdditive = 9.2D;
		double yAdditive = 17.125D;
		for(int i = 0; i < this.slots.length; i++) {
			if(xTimes == 10) {
				xTimes = 0;
			}
			this.slots[i] = new UIInventorySlot(8.5 + xAdditive * xTimes, 32.75D + yAdditive * (i / 10), 32, 32, screen, this, items[i], i);
			this.addChild(this.slots[i]);
			xTimes++;
		}
	}
	
	public Inventory getInventory() {
		return this.inv;
	}
	
	public UIInventorySlot[] getSlots() {
		return this.slots;
	}
	
	public void addItem(Item item) {
		this.inv.addItem(item);
	}
	
	public int getFirstSelectedSlot() {
		return this.slotSelected1;
	}
	
	public void setFirstSelectedSlot(int slot) {
		this.slotSelected1 = slot;
	}
	
	public int getSecondSelectedSlot() {
		return this.slotSelected2;
	}
	
	public void setSecondSelectedSlot(int slot) {
		this.slotSelected2 = slot;
	}
}
