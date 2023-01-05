package me.fuzy_boi.listeners;

import com.comphenix.protocol.wrappers.EnumWrappers;
import me.fuzy_boi.inventories.InvWardrobe;
import me.fuzy_boi.objects.GhostEquipment;
import me.fuzy_boi.objects.MPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class OnGUIWardrobe implements Listener {
    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getClickedInventory() == null) return;
        if (!(event.getClickedInventory().getHolder() instanceof InvWardrobe)) return;

        ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem == null) return;
        event.setCancelled(true);

        UUID uuid = player.getUniqueId();
        Material material = clickedItem.getType();
        MPlayer mPlayer = MPlayer.get(uuid);
        GhostEquipment equipment = mPlayer.getEquipment();

        if (material == Material.RED_STAINED_GLASS_PANE) {
            equipment.clear().update();
            player.sendMessage(ChatColor.GREEN + "Successfully reset your fake armor!");
            return;
        }

        //the item's appropriate slot
        EnumWrappers.ItemSlot slotType;
        switch ((event.getSlot() / 9) + 1) {
            case 2: {
                slotType = EnumWrappers.ItemSlot.HEAD;
                break;
            }
            case 3: {
                slotType = EnumWrappers.ItemSlot.CHEST;
                break;
            }
            case 4: {
                slotType = EnumWrappers.ItemSlot.LEGS;
                break;
            }
            case 5: {
                slotType = EnumWrappers.ItemSlot.FEET;
                break;
            }
            default: {
                return;
            }

        }

        //NOTE: THIS IS NOT THE ARMOR THE PLAYER IS WEARING
        ItemStack ghostArmor = equipment.getItem(slotType);

        if (material == Material.DIAMOND) {
            if (ghostArmor != null) {
                ItemMeta meta = ghostArmor.getItemMeta();
                if (meta == null) return;
                meta.addEnchant(Enchantment.LUCK, 1, true);
                ghostArmor.setItemMeta(meta);
                equipment.setItem(slotType, ghostArmor).update();

            } else {
                player.sendMessage(ChatColor.RED + "You must select an item before enchanting it!");
            }
            return;


        }

        else if (material == Material.GRAY_DYE) {
            if (ghostArmor != null) {
                ItemMeta meta = ghostArmor.getItemMeta();
                if (meta == null) return;
                meta.removeEnchant(Enchantment.LUCK);
                ghostArmor.setItemMeta(meta);
                equipment.setItem(slotType, ghostArmor).update();

            } else {
                player.sendMessage(ChatColor.RED + "You must select an item before disenchanting it!");
            }
            return;

        }

        else if (material == Material.BARRIER) {
            equipment.setItem(slotType, new ItemStack(Material.AIR)).update();
            return;
        }

        equipment.setItem(slotType, clickedItem).update();
        
    }
}
