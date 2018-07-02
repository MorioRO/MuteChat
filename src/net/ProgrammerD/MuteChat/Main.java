package net.ProgrammerD.MuteChat;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import net.ProgrammerD.MuteChat.Commands.ChatCommand;
import net.ProgrammerD.MuteChat.Data.Data;
import net.ProgrammerD.MuteChat.Events.ChatEvent;

public class Main extends JavaPlugin {

	public String folderName = getDescription().getName();
	
	@Override
	public void onEnable() {
		registerConfigs();
		registerEvents();
	}
	
	@Override
	public void onDisable() {
		registerConfigsDisable();
	}
	
	private void registerEvents() {
		new ChatEvent();
		new ChatCommand();
	}
	
	private void registerConfigs() {
		getConfig().options().copyDefaults(false);
		saveDefaultConfig();
		
		// In caz de ceva :).
		Data data = new Data(this);
		if (data.getData().getString("Chat Enabled") == null) {
			data.getData().set("Chat Enabled", true);
		}
	}
	
	private void registerConfigsDisable() {
		getConfig().options().copyDefaults(false);
		if (fileExists("config.yml")) {
			saveDefaultConfig();
		}
	}
	
    private boolean fileExists(String file)
    {
        File file2 = new File("/" + folderName + "/" + file);
        return file2.exists();
    }
	
}
