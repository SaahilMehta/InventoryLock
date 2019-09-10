package me.darksoldier.invlock;



import org.bukkit.plugin.java.JavaPlugin;
import me.darksoldier.invlock.listeners.InvEvents;

public class Main extends JavaPlugin{
	
	public Main() {
		
	}
	
	public static Main plugin;
	
	
	@Override
	public void onEnable() {
		plugin = this;
//		this.saveDefaultConfig();
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		
		getServer().getPluginManager().registerEvents(new InvEvents(), this);
	}
	
	@Override
	public void onDisable() {
		this.saveConfig();
	}	
	
	

	

}
