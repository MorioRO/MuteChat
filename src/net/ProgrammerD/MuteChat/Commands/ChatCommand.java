package net.ProgrammerD.MuteChat.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.ProgrammerD.MuteChat.Main;
import net.ProgrammerD.MuteChat.Data.Data;
import net.md_5.bungee.api.ChatColor;

public class ChatCommand implements CommandExecutor {

	private Main plugin = Main.getPlugin(Main.class);
	
	public ChatCommand() {
		plugin.getCommand("chat").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			
			if (args.length == 0) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a\u00BB &fFoloseste: &e/chat &8(&fenable/disable&8)"));
				return true;
			}
			
			if (args.length > 1) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a\u00BB &fArgumente invalide."));
				return true;
			}
			
			if (args[0].equalsIgnoreCase("enable")) {
				Data data = new Data(plugin);
				if (data.getData().getBoolean("Chat Enabled")) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Chat Already Enabled")));
					return true;
				}
				data.getData().set("Chat Enabled", true);
				data.saveData();
				Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Chat Enabled Message").replace("%player_name%", "Console").replace("%player_displayname%", "Console")));
				return true;
			}
			
			if (args[0].equalsIgnoreCase("disable")) {
				Data data = new Data(plugin);
				if (data.getData().getBoolean("Chat Enabled") == false) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Chat Already Disabled")));
					return true;
				}
				data.getData().set("Chat Enabled", false);
				data.saveData();
				Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Chat Disabled Message").replace("%player_name%", "Console").replace("%player_displayname%", "Console")));
				return true;
			}
			
			if (!args[0].equalsIgnoreCase("enable") || !args[0].equalsIgnoreCase("disable")) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a\u00BB &fArgumente invalide."));
				return true;
			}
			
		} else {
			Player p = (Player) sender;
			if (!p.hasPermission(plugin.getConfig().getString("Permission"))) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("No Permission to execute command")));
				return true;
			} else {
				if (args.length == 0) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a\u00BB &fFoloseste: &e/chat &8(&fenable/disable&8)"));
					return true;
				}
				
				if (args.length > 1) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a\u00BB &fArgumente invalide."));
					return true;
				}
				
				if (args[0].equalsIgnoreCase("enable")) {
					Data data = new Data(plugin);
					if (data.getData().getBoolean("Chat Enabled")) {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Chat Already Enabled")));
						return true;
					}
					data.getData().set("Chat Enabled", true);
					data.saveData();
					Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Chat Enabled Message").replace("%player_name%", p.getName()).replace("%player_displayname%", p.getDisplayName())));
					return true;
				}
				
				if (args[0].equalsIgnoreCase("disable")) {
					Data data = new Data(plugin);
					if (data.getData().getBoolean("Chat Enabled") == false) {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Chat Already Disabled")));
						return true;
					}
					data.getData().set("Chat Enabled", false);
					data.saveData();
					Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Chat Disabled Message").replace("%player_name%", p.getName()).replace("%player_displayname%", p.getDisplayName())));
					return true;
				}
				
				if (!args[0].equalsIgnoreCase("enable") || !args[0].equalsIgnoreCase("disable")) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a\u00BB &fArgumente invalide."));
					return true;
				}
			}
		}
		
		return false;
	}

}
