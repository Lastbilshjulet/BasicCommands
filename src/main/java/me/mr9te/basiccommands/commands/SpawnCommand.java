package me.mr9te.basiccommands.commands;

import me.mr9te.basiccommands.BasicCommands;
import me.mr9te.basiccommands.data.CustomConfig;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public record SpawnCommand(BasicCommands plugin) implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            CustomConfig.setupCustomConfig("spawn.yml");
            double spawnX = CustomConfig.getCustomConfig().getDouble("spawn.x");
            double spawnY = CustomConfig.getCustomConfig().getDouble("spawn.y");
            double spawnZ = CustomConfig.getCustomConfig().getDouble("spawn.z");
            float spawnYaw = (float) CustomConfig.getCustomConfig().getDouble("spawn.yaw");
            float spawnPitch = (float) CustomConfig.getCustomConfig().getDouble("spawn.pitch");
            Location spawnLocation = new Location(plugin.getServer().getWorld("world"), spawnX, spawnY, spawnZ, spawnYaw, spawnPitch);
            player.sendMessage(ChatColor.GREEN + "Teleporting to spawn...");
            player.teleport(spawnLocation);
            return true;
        } else {
            plugin.getLogger().info("Have to be a player to teleport to spawn. ");
        }
        return false;
    }
}
