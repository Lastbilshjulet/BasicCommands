package me.mr9te.basiccommands.tabcompletion;

import me.mr9te.basiccommands.data.HomeLocations;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HomeTabCompletion implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 1){
                List<String> homes = new ArrayList<>();
                HomeLocations.setup(player.getUniqueId() + "HomeLocations.yml");
                List<String> homesInfo = HomeLocations.getHomeLocations().getStringList("homes");
                for (String s : homesInfo) {
                    homes.add(s.split(";")[0]);
                }
                return homes;
            }
        }
        return null;
    }
}
