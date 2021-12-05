package me.mr9te.basiccommands.tabcompletion;

import me.mr9te.basiccommands.BasicCommands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TPATabCompletion implements TabCompleter {

    Plugin plugin = BasicCommands.getPlugin(BasicCommands.class);

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender instanceof Player player) {
            Collection<? extends Player> players = plugin.getServer().getOnlinePlayers();
            List<String> playerNames = new ArrayList<>();
            List<String> updatedPlayerNames = new ArrayList<>();
            for (Player p : players) {
                if (!p.getDisplayName().equalsIgnoreCase(player.getDisplayName())) {
                    playerNames.add(p.getDisplayName());
                }
            }
            if (args.length == 1){
                for (String name : playerNames) {
                    if (args[0].length() < name.length()) {
                        int length = 0;
                        for (int i = 0; i < args[0].length(); i++) {
                            if (args[0].charAt(i) == Character.toUpperCase(name.charAt(i)) || args[0].charAt(i) == Character.toLowerCase(name.charAt(i))) {
                                length++;
                            }
                        }
                        if (length == args[0].length()) {
                            updatedPlayerNames.add(name);
                        }
                    }
                }
            }
            return updatedPlayerNames;
        }
        return null;
    }
}
