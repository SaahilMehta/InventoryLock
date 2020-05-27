package me.darksoldier.invlock.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

import me.darksoldier.invlock.Main;

public class InvEvents implements Listener{
	

	FileConfiguration config = Main.plugin.getConfig();

	@EventHandler
	public void clickEvent(InventoryClickEvent e) {
		Player p = (Player)e.getWhoClicked();
		if(!hasBypassPermission(p) && playerInWorld(p) && slotIsInList(e.getSlot())) {
			e.setCancelled(true);
			p.updateInventory();
		}
		
	}
	
	@EventHandler
	public void dropEvent(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if(!hasBypassPermission(p) && playerInWorld(p) && slotIsInList(p.getInventory().getHeldItemSlot())) {
//		if(!hasBypassPermission(p) && playerInWorld(p)) {
			e.setCancelled(true);
			p.updateInventory();
		}
	}
	
	@EventHandler
	public void swapEvent(PlayerSwapHandItemsEvent e) {
		Player p = e.getPlayer();
		if(!hasBypassPermission(p) && playerInWorld(p) && slotIsInList(p.getInventory().getHeldItemSlot())) {
			e.setCancelled(true);
			p.updateInventory();
		}
	}
	
	private boolean hasBypassPermission(Player p) {
		boolean hasPerm = false;
		if(p.hasPermission("invlock.bypass")) {
			hasPerm = true;
		}
		return hasPerm;
	}
	
	private boolean playerInWorld(Player p) {
		return config.getStringList("enabled-worlds").contains(p.getWorld().getName());
	}
	
	private boolean slotIsInList(int slotIndex){
		return config.getStringList("enabled-slots").size() == 0 || config.getIntegerList("enabled-slots").contains(slotIndex+1);
	}

}
