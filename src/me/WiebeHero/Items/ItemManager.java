package me.WiebeHero.Items;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

public class ItemManager {
	
	private ArrayList<Item> items;
	
	public ItemManager() {
		this.items = new ArrayList<Item>();
	}
	
	public void tick() {
		Iterator<Item> it = items.iterator();
		while(it.hasNext()) {
			Item i = it.next();
			i.tick();
			if(i.isPickedUp())
				it.remove();
		}
	}
	
	public void render(Graphics g) {
		for(Item i : items)
			i.render(g);
	}
	
	public void addItem(Item item) {
		this.items.add(item);
	}
	
}
