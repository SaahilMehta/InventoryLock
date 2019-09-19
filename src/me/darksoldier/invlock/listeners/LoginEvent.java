package me.darksoldier.invlock.listeners;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import me.darksoldier.invlock.Main;

public class LoginEvent implements Listener {
	
	FileConfiguration config = Main.plugin.getConfig();	
	
	@EventHandler
	public void onLogin(PlayerJoinEvent pje) {
		Player p = pje.getPlayer();
		ItemStack is = new ItemStack(Material.ARROW);
		is.getItemMeta().setDisplayName("drop me");
		
		if(p.getInventory().getItem(4) != is) {
			p.getInventory().setItem(4, is);
		}
		
		String result = ""
;		for(String x : config.getStringList("enabled-slots")) {
			result += x + " ";
		}
		
		p.sendMessage("Locked slots: " + result );
		
	}

}
