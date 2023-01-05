package me.fuzy_boi.listeners;

import me.fuzy_boi.inventories.InvWardrobe;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class OnUseWardrobe implements Listener {
    @EventHandler
    public void onUse(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;


        Player player = event.getPlayer();

        ItemStack item = player.getInventory().getItemInMainHand();

        if (item.getType() != Material.CHAINMAIL_CHESTPLATE) return;


        InvWardrobe inv = new InvWardrobe();
        player.openInventory(inv.getInventory());
        event.setCancelled(true);
    }
}
