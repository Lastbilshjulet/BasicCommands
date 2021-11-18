package me.mr9te.basiccommands.commands;

import me.mr9te.basiccommands.BasicCommands;
import me.mr9te.basiccommands.data.TypeOfTP;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public record TPACommand(BasicCommands plugin) implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player player) {
            if (args.length > 0) {
                Collection<? extends Player> players = plugin.getServer().getOnlinePlayers();
                Player target = plugin.getServer().getPlayer(args[0]);
                if (!players.contains(target) || target == null) {
                    player.sendMessage(ChatColor.RED + "User not online. ");
                    return false;
                } else {
                    if (player == target) {
                        player.sendMessage(ChatColor.RED + "Can't teleport to yourself. ");
                        return false;
                    }
                    target.sendMessage(ChatColor.DARK_GREEN + player.getDisplayName() + ChatColor.GREEN + " wants to teleport to you, type /tpaccept to accept or /tpdeny to deny. ");
                    player.sendMessage(ChatColor.GREEN + "Request sent to " + ChatColor.DARK_GREEN + target.getDisplayName() + ChatColor.GREEN + ". ");
                    plugin.addRequest(player, target, TypeOfTP.TPA);
                }
            } else {
                player.sendMessage(ChatColor.RED + "Name of player missing. ");
                return false;
            }
        } else {
            plugin.getLogger().info("Have to be a player to send a teleport request. ");
        }

        return true;
    }
}
