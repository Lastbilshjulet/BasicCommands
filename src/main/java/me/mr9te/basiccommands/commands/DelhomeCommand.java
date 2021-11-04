package me.mr9te.basiccommands.commands;

import me.mr9te.basiccommands.BasicCommands;
import me.mr9te.basiccommands.data.HomeLocations;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class DelhomeCommand implements CommandExecutor {

    Plugin plugin = BasicCommands.getPlugin(BasicCommands.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player player) {
            if (args.length > 0) {

                HomeLocations.setup(player.getUniqueId() + "HomeLocations.yml");
                List<String> homes = HomeLocations.getHomeLocations().getStringList("homes");

                // Check if there is a home with this name already to overwrite it
                for (int i = 0; i < homes.size(); i++) {
                    String[] homeinfo = homes.get(i).split(";");
                    if (homeinfo[0].equalsIgnoreCase(args[0])) {
                        homes.remove(i);
                    }
                }
                // Save homes
                HomeLocations.getHomeLocations().set("homes", homes);
                HomeLocations.saveHomeLocations();
                HomeLocations.reloadHomeLocations();
            } else {
                player.sendMessage(ChatColor.RED + "You need the name of the home.");
                return false;
            }
        } else {
            plugin.getLogger().info("Have to be a player to delete a home.");
        }

        return true;
    }
}
