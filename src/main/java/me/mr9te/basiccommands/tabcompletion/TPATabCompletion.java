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
            if (args.length == 1){
                Collection<? extends Player> players = plugin.getServer().getOnlinePlayers();
                List<String> playerNames = new ArrayList<>();
                for (Player p : players) {
                    if (!p.getDisplayName().equalsIgnoreCase(player.getDisplayName())) {
                        playerNames.add(p.getDisplayName());
                    }
                }
                return playerNames;
            }
        }
        return null;
    }
}
