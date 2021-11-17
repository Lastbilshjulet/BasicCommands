package me.mr9te.basiccommands.commands;

import me.mr9te.basiccommands.BasicCommands;
import me.mr9te.basiccommands.data.CustomConfig;
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
            CustomConfig.setupCustomConfig(player.getUniqueId() + "CustomConfig.yml");
            List<String> homesInfo = CustomConfig.getCustomConfig().getStringList("homes");
            if (homesInfo.size() > 0) {
                player.sendMessage(ChatColor.GOLD + "Your homes:");
            } else {
                player.sendMessage(ChatColor.RED + "You don't have any homes set. ");
            }
            for (String s : homesInfo) {
                String[] arr = s.split(";");
                player.sendMessage(ChatColor.YELLOW + " > " + arr[0] + ChatColor.DARK_GREEN + " - XYZ: " + arr[1] + " / " + arr[2] + " / " + arr[3]);
            }
        } else {
            plugin.getLogger().info("Have to be a player to set home.");
        }
        return true;
    }
}
