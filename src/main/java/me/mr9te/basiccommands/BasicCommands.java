package me.mr9te.basiccommands;

import me.mr9te.basiccommands.commands.DelhomeCommand;
import me.mr9te.basiccommands.commands.HomeCommand;
import me.mr9te.basiccommands.commands.HomesCommand;
import me.mr9te.basiccommands.commands.SethomeCommand;
import me.mr9te.basiccommands.listeners.PlayerListeners;
import me.mr9te.basiccommands.tabcompletion.HomeTabCompletion;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class BasicCommands extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("BasicCommands has started!");

        //Setup config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        Objects.requireNonNull(getCommand("home")).setExecutor(new HomeCommand());
        Objects.requireNonNull(getCommand("sethome")).setExecutor(new SethomeCommand());
        Objects.requireNonNull(getCommand("delhome")).setExecutor(new DelhomeCommand());
        Objects.requireNonNull(getCommand("homes")).setExecutor(new HomesCommand());
        Objects.requireNonNull(getCommand("home")).setTabCompleter(new HomeTabCompletion());
        Objects.requireNonNull(getCommand("sethome")).setTabCompleter(new HomeTabCompletion());
        Objects.requireNonNull(getCommand("delhome")).setTabCompleter(new HomeTabCompletion());
        getServer().getPluginManager().registerEvents(new PlayerListeners(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("BasicCommands has stopped!");
    }
}
