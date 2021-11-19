package me.mr9te.basiccommands.commands;

import me.mr9te.basiccommands.BasicCommands;
import me.mr9te.basiccommands.data.CustomConfig;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class HomeCommand implements CommandExecutor {

    Plugin plugin = BasicCommands.getPlugin(BasicCommands.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Location home;
        if (sender instanceof Player player) {
            if (args.length > 0) {
                CustomConfig.setupCustomConfig(player.getUniqueId() + "CustomConfig.yml");
                List<String> homes = CustomConfig.getCustomConfig().getStringList("homes");
                for (String s : homes) {
                    String[] homeinfo = s.split(";");
                    if (homeinfo[0].equalsIgnoreCase(args[0])) {
                        home = new Location(plugin.getServer().getWorld(homeinfo[6]),
                                Double.parseDouble(homeinfo[1]),
                                Double.parseDouble(homeinfo[2]),
                                Double.parseDouble(homeinfo[3]),
                                Float.parseFloat(homeinfo[4]),
                                Float.parseFloat(homeinfo[5]));
                        player.teleport(home);
                        player.sendMessage(ChatColor.YELLOW + "Teleporting to " + args[0] + "... ");
                        return true;
                    }
                }
                player.sendMessage(ChatColor.RED + "Home called " + args[0] + " does not exist. ");
            } else {
                player.sendMessage(ChatColor.RED + "Specify a home name. ");
            }
            return false;
        } else {
            plugin.getLogger().info("Have to be a player to teleport home.");
        }
        return true;
    }
}
