package net.ProgrammerD.MuteChat.Data;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import net.ProgrammerD.MuteChat.Main;

import java.io.File;
import java.io.IOException;


public class Data {

    @SuppressWarnings("unused")
    private Main plugin;
    private FileConfiguration fc;
    private File file;

    public Data(Main plugin)
    {
        this.plugin = plugin;
    }

    public boolean exists()
    {
        if (fc == null || file == null)
        {
            File temp = new File(getDataFolder(), "data.yml");
            if (!temp.exists())
            {
                return false;
            } else {
                file = temp;
            }
        }
        return true;
    }

    public File getDataFolder()
    {
        File dir = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        File d = new File(dir.getParentFile().getPath() + "/" + plugin.folderName + "/Data/");
        if (!d.exists())
        {
            d.mkdirs();
        }
        return  d;
    }

    public  File getFile()
    {
        if (file == null)
        {
            file = new File(getDataFolder(), "data.yml");
            if (!file.exists())
            {
                try {
                    file.createNewFile();
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&eMuteChat &8- &fCreating &9data.yml &ffile."));
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    public FileConfiguration getData()
    {
        if (fc == null)
        {
            fc = YamlConfiguration.loadConfiguration(getFile());
        }
        return fc;
    }

    public void saveData()
    {
        try {
            getData().save(getFile());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
