package me.mr9te.basiccommands.data;

import me.mr9te.basiccommands.BasicCommands;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class HomeLocations {

    private static File file;
    private static FileConfiguration customFile;
    private static final Plugin plugin = BasicCommands.getPlugin(BasicCommands.class);

    //Finds or generates the custom config file
    public static void setup(String filename){
        file = new File(plugin.getDataFolder(), filename);

        if (!file.exists()){
            try{
                if (file.createNewFile()) {
                    plugin.getLogger().info("Created a new file. ");
                }
            }catch (IOException e){
                plugin.getLogger().info("Could not create a new file. ");
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration getHomeLocations(){
        return customFile;
    }

    public static void saveHomeLocations(){
        try {
            customFile.save(file);
        } catch (IOException e) {
            plugin.getLogger().info("Could not save the file. ");
        }
    }

    public static void reloadHomeLocations(){
        customFile = YamlConfiguration.loadConfiguration(file);
    }
}
