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

public class HomesCommand implements CommandExecutor {

    Plugin plugin = BasicCommands.getPlugin(BasicCommands.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            HomeLocations.setup(player.getUniqueId() + "HomeLocations.yml");
            List<String> homesInfo = HomeLocations.getHomeLocations().getStringList("homes");
            if (homesInfo.size() > 0) {
                player.sendMessage(ChatColor.GOLD + "Your homes:");
            } else {
                player.sendMessage(ChatColor.RED + "You don't have any homes set. ");
            }
            for (String s : homesInfo) {
                player.sendMessage(ChatColor.YELLOW + " > " + s.split(";")[0]);
            }
        } else {
            plugin.getLogger().info("Have to be a player to set home.");
        }
        return true;
    }
}
