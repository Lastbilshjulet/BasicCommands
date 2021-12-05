package me.mr9te.basiccommands.tabcompletion;

import me.mr9te.basiccommands.BasicCommands;
import me.mr9te.basiccommands.data.CustomConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class HomeTabCompletion implements TabCompleter {

    Plugin plugin = BasicCommands.getPlugin(BasicCommands.class);

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender instanceof Player player) {
            List<String> homes = new ArrayList<>();
            List<String> updatedHomes = new ArrayList<>();
            CustomConfig.setupCustomConfig(player.getUniqueId() + "CustomConfig.yml");
            List<String> homesInfo = CustomConfig.getCustomConfig().getStringList("homes");
            for (String s : homesInfo) {
                homes.add(s.split(";")[0]);
            }
            if (args.length == 1){
                for (String home : homes) {
                    if (args[0].length() < home.length()) {
                        int length = 0;
                        for (int i = 0; i < args[0].length(); i++) {
                            if (args[0].charAt(i) == Character.toUpperCase(home.charAt(i)) || args[0].charAt(i) == Character.toLowerCase(home.charAt(i))) {
                                length++;
                            }
                        }
                        if (length == args[0].length()) {
                            updatedHomes.add(home);
                        }
                    }
                }
            }
            return updatedHomes;
        }
        return null;
    }
}
