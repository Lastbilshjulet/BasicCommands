package me.mr9te.basiccommands;

import me.mr9te.basiccommands.commands.*;
import me.mr9te.basiccommands.data.TPAData;
import me.mr9te.basiccommands.data.TypeOfTP;
import me.mr9te.basiccommands.listeners.PlayerListeners;
import me.mr9te.basiccommands.tabcompletion.HomeTabCompletion;
import me.mr9te.basiccommands.tabcompletion.TPATabCompletion;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public final class BasicCommands extends JavaPlugin {

    private ArrayList<TPAData> requests;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("BasicCommands has started!");

        requests = new ArrayList<>();

        //Setup config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Commands
        Objects.requireNonNull(getCommand("home")).setExecutor(new HomeCommand());
        Objects.requireNonNull(getCommand("sethome")).setExecutor(new SethomeCommand());
        Objects.requireNonNull(getCommand("delhome")).setExecutor(new DelhomeCommand());
        Objects.requireNonNull(getCommand("homes")).setExecutor(new HomesCommand());
        Objects.requireNonNull(getCommand("tpa")).setExecutor(new TPACommand(this));
        Objects.requireNonNull(getCommand("tpaccept")).setExecutor(new TPAcceptCommand(this));
        Objects.requireNonNull(getCommand("tpdeny")).setExecutor(new TPDenyCommand(this));
        Objects.requireNonNull(getCommand("tpahere")).setExecutor(new TPAHereCommand(this));
        Objects.requireNonNull(getCommand("spawn")).setExecutor(new SpawnCommand(this));
        Objects.requireNonNull(getCommand("rtp")).setExecutor(new RandomTeleportCommand(this));

        // Tabcompletions
        Objects.requireNonNull(getCommand("home")).setTabCompleter(new HomeTabCompletion());
        Objects.requireNonNull(getCommand("sethome")).setTabCompleter(new HomeTabCompletion());
        Objects.requireNonNull(getCommand("delhome")).setTabCompleter(new HomeTabCompletion());
        Objects.requireNonNull(getCommand("tpa")).setTabCompleter(new TPATabCompletion());
        Objects.requireNonNull(getCommand("tpahere")).setTabCompleter(new TPATabCompletion());

        // Listeners
        getServer().getPluginManager().registerEvents(new PlayerListeners(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("BasicCommands has stopped!");
    }

    // Functions for requests
    public void addRequest(Player source, Player target, TypeOfTP type) {
        requests.add(new TPAData(source, target, type));
    }

    public ArrayList<TPAData> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<TPAData> requests) {
        this.requests = requests;
    }
}
