package me.mr9te.basiccommands.commands;

import me.mr9te.basiccommands.BasicCommands;
import me.mr9te.basiccommands.data.HomeLocations;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class SethomeCommand implements CommandExecutor {

    Plugin plugin = BasicCommands.getPlugin(BasicCommands.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player player) {
            if (args.length > 0) {

                HomeLocations.setup(player.getUniqueId() + "HomeLocations.yml");
                Location currLocation = player.getLocation();
                String[] location = {Double.toString(currLocation.getX()), Double.toString(currLocation.getY()), Double.toString(currLocation.getZ())};

                List<String> homes = HomeLocations.getHomeLocations().getStringList("homes");

                // Check if there is a home with this name already to overwrite it
                int index = -1;
                for (int i = 0; i < homes.size(); i++) {
                    String[] homeinfo = homes.get(i).split(";");
                    if (homeinfo[0].equalsIgnoreCase(args[0])) {
                        index = i;
                    }
                }
                if (index > -1) {
                    homes.set(index, args[0] + ";" +
                            round2d(currLocation.getX()) + ";" +
                            round2d(currLocation.getY()) + ";" +
                            round2d(currLocation.getZ()) + ";" +
                            round2f(player.getLocation().getYaw()) + ";" +
                            round2f(player.getLocation().getPitch()));
                    player.sendMessage(ChatColor.GREEN + args[0] + " set at: " + round2d(currLocation.getX()) + ", " + round2d(currLocation.getY()) + ", " + round2d(currLocation.getZ()));
                } else {
                    // Check if there are too many homes else add home to list
                    if (homes.size() == plugin.getConfig().getInt("max-homes")) {
                        player.sendMessage(ChatColor.RED + "Max " + plugin.getConfig().getInt("max-homes") + " homes per person");
                    } else {
                        homes.add(args[0] + ";" +
                                round2d(currLocation.getX()) + ";" +
                                round2d(currLocation.getY()) + ";" +
                                round2d(currLocation.getZ()) + ";" +
                                round2f(player.getLocation().getYaw()) + ";" +
                                round2f(player.getLocation().getPitch()));
                        player.sendMessage(ChatColor.GREEN + args[0] + " set at: " + round2d(currLocation.getX()) + ", " + round2d(currLocation.getY()) + ", " + round2d(currLocation.getZ()));
                    }
                }
                // Save homes
                HomeLocations.getHomeLocations().set("homes", homes);
                HomeLocations.saveHomeLocations();
                HomeLocations.reloadHomeLocations();
            } else {
                player.sendMessage(ChatColor.RED + "You need a name for your home.");
                return false;
            }
        } else {
            plugin.getLogger().info("Have to be a player to set home.");
        }

        return true;
    }

    private double round2d(Double value) {
        return Math.round(value*100)/100D;
    }

    private float round2f(Float value) {
        return (float) (Math.round(value*100)/100D);
    }
}