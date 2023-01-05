package me.fuzy_boi.listeners;

import me.fuzy_boi.inventories.InvConcealer;
import me.fuzy_boi.objects.GhostEquipment;
import me.fuzy_boi.objects.MPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class OnUseConcealer implements Listener {
    @EventHandler
    public void onUse(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        Player player = event.getPlayer();

        ItemStack mainHand = player.getInventory().getItemInMainHand();

        if (mainHand.getType() != Material.IRON_SWORD) return;

        GhostEquipment equipment = MPlayer.get(player).getEquipment();

        Inventory inv = new InvConcealer().getInventory();

        InvConcealer.update(equipment, inv);

        player.openInventory(inv);

        event.setCancelled(true);
    }
}
