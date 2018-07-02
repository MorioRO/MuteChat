package net.ProgrammerD.MuteChat.Events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.ProgrammerD.MuteChat.Main;
import net.ProgrammerD.MuteChat.Data.Data;

public class ChatEvent implements Listener {

	private Main plugin = Main.getPlugin(Main.class);
	
	public ChatEvent()
	{
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent e) {
	
		Player p = e.getPlayer();
		Data data = new Data(plugin);
		
		if (p.isOp() || p.hasPermission(plugin.getConfig().getString("Bypass permission"))) return;
		
		if (data.getData().getBoolean("Chat Enabled") == false){
			e.setCancelled(true);
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("No Permission to type")));
			return;
		}
		
	}
	
}
