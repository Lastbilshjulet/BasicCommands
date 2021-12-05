package me.mr9te.basiccommands.commands;

import me.mr9te.basiccommands.BasicCommands;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public record RandomTeleportCommand(BasicCommands plugin) implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player player) {
            Location randomLocation = new Location(player.getWorld(), 0, 0, 0);
            boolean ApprovedLocation = false;
            World world = player.getWorld();
            while (!ApprovedLocation) {
                randomLocation.setWorld(world);
                int xMin = -100000, xMax = 100000;
                randomLocation.setX(Math.random() * (xMax - xMin + 1) + xMin);
                int zMin = -100000, zMax = 100000;
                randomLocation.setZ(Math.random() * (zMax - zMin + 1) + zMin);
                int yMin = -59, yMax = 200;
                randomLocation.setY(Math.random() * (yMax - yMin + 1) + yMin);
                for (int i = randomLocation.getBlockY(); i > yMin; i--) {
                    if (randomLocation.getBlock().getType() == Material.AIR) {
                        randomLocation.setY(randomLocation.getY() - 1);
                        if (randomLocation.getBlock().getType() != Material.AIR) {
                            ApprovedLocation = true;
                            break;
                        }
                    }
                }
            }
            player.sendMessage(ChatColor.YELLOW + " Teleported to" + ChatColor.DARK_GREEN + " - XYZ: " + randomLocation.getX() + " / " + randomLocation.getY() + " / " + randomLocation.getZ());
            player.teleport(randomLocation);
        } else {
            plugin.getLogger().info("Have to be a player to teleport to a random place.");
        }
        return true;
    }
}
