package me.mr9te.basiccommands.commands;

import me.mr9te.basiccommands.BasicCommands;
import me.mr9te.basiccommands.data.TPAData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public record TPDenyCommand(BasicCommands plugin) implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            ArrayList<TPAData> requests = plugin.getRequests();
            for (int i = 0; i < requests.size(); i++) {
                if (requests.get(i).getTarget().getDisplayName().equalsIgnoreCase(player.getDisplayName())) {
                    player.sendMessage(ChatColor.GREEN + "Denied teleport request from " + requests.get(i).getSource().getDisplayName() + ". ");
                    requests.remove(i);
                    plugin.setRequests(requests);
                    return true;
                }
            }
        } else {
            plugin.getLogger().info("Have to be a player to deny a teleport request. ");
        }
        return false;
    }
}
