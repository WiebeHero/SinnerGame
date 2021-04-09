package me.WiebeHero.Inventory;

import me.WiebeHero.Items.Item;

public class Inventory{
	
	private Item[] inventoryItems;
	
	public Inventory() {
		this.inventoryItems = new Item[40];
	}
	
	// Inventory Methods
	
	public void addItem(Item item) {
		for(int i = 0; i < inventoryItems.length; i++) {
			Item oldItem = inventoryItems[i];
			if(oldItem == null) {
				inventoryItems[i] = item;
				break;
			}
			else {
				if(oldItem.getId() == item.getId()) {
					oldItem.setCount(oldItem.getCount() + item.getCount());
					break;
				}
			}
		}
	}
	
	public Item[] getItems() {
		return inventoryItems;
	}
}
