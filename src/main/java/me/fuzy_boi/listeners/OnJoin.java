package me.fuzy_boi.listeners;

import me.fuzy_boi.objects.MPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoin implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        new MPlayer(event.getPlayer().getUniqueId());
    }

}
