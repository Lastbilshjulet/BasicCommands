package me.mr9te.basiccommands.listeners;

import me.mr9te.basiccommands.BasicCommands;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.plugin.Plugin;

public class PlayerListeners implements Listener {

    Plugin plugin = BasicCommands.getPlugin(BasicCommands.class);

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(ChatColor.GOLD + "Welcome to the server, " + player.getDisplayName() + "!");
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(ChatColor.GOLD + "Finally, " + player.getDisplayName() + ", left the server!");
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        plugin.getServer().broadcastMessage(ChatColor.GOLD + "Welcome back to the land of the living, " + player.getDisplayName() + "!");
    }

    @EventHandler
    public void onEnterBed(PlayerBedEnterEvent event) {
        Player player = event.getPlayer();
        if (event.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.OK) {
            plugin.getServer().broadcastMessage(ChatColor.GOLD + player.getDisplayName() + ": " + ChatColor.DARK_GRAY + "Dirt.");
            player.playSound(player.getLocation(), Sound.MUSIC_DISC_13, SoundCategory.MUSIC, (float) 1.0, (float) 1.0);
        }
    }

    @EventHandler
    public void onExitBed(PlayerBedLeaveEvent event) {
        Player player = event.getPlayer();
        player.stopSound(Sound.MUSIC_DISC_13, SoundCategory.MUSIC);
    }
}
