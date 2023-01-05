package me.fuzy_boi.listeners;

import me.fuzy_boi.Main;
import me.fuzy_boi.inventories.InvConcealer;
import me.fuzy_boi.objects.GhostEquipment;
import me.fuzy_boi.objects.MPlayer;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;


public class OnGuiConcealer implements Listener {
    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getClickedInventory() == null) return;
        if (!(event.getClickedInventory().getHolder() instanceof InvConcealer)) return;
        Inventory inv = event.getClickedInventory();
        int slot = event.getSlot();
        event.setCancelled(true);

        MPlayer mPlayer = MPlayer.get(player);
        GhostEquipment equipment = mPlayer.getEquipment();
        ItemStack mainHand = equipment.getMainHand();
        ItemStack offHand  = equipment.getOffHand();
        //player.sendMessage(String.valueOf(slot));

        switch (slot) {
            case 14: {
                if (mainHand == null)
                    equipment.setMainHand(new ItemStack(Material.AIR));
                else
                    equipment.setMainHand(null);

                break;
            }
            case 12: {
                if (offHand == null)
                    equipment.setOffHand(new ItemStack(Material.AIR));
                else
                    equipment.setOffHand(null);

                break;
            }
            case 11: {
                if (event.getCursor() == null) {
                    inv.setItem(slot, new ItemStack(Material.AIR));
                    equipment.setOffHand(null);
                }
                ItemStack cursor = new ItemStack(event.getCursor());

                ItemMeta meta = cursor.getItemMeta();
                if (meta == null) return;
                meta.getPersistentDataContainer().set(new NamespacedKey(Main.getInstance(), "tag"), PersistentDataType.STRING, "illegal");
                cursor.setItemMeta(meta);

                if (offHand == null) return;
                inv.setItem(slot, cursor);
                equipment.setOffHand(cursor);

                break;
            }
            case 15: {
                if (event.getCursor() == null) {
                    inv.setItem(slot, new ItemStack(Material.AIR));
                    equipment.setMainHand(null);
                }
                ItemStack cursor = new ItemStack(event.getCursor());

                ItemMeta meta = cursor.getItemMeta();
                if (meta == null) return;
                meta.getPersistentDataContainer().set(new NamespacedKey(Main.getInstance(), "tag"), PersistentDataType.STRING, "illegal");
                cursor.setItemMeta(meta);

                if (mainHand == null) return;
                inv.setItem(slot, cursor);
                equipment.setMainHand(cursor);

                break;
            }
            case 10: {
                if (offHand == null) return;
                ItemMeta meta = offHand.getItemMeta();
                if (meta == null) return;
                if (meta.hasEnchants()) {
                    for (Enchantment enchantment : meta.getEnchants().keySet())
                        meta.removeEnchant(enchantment);
                        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                } else {
                    meta.addEnchant(Enchantment.LUCK, 1, true);
                }
                offHand.setItemMeta(meta);
                equipment.setOffHand(offHand);

                break;
            }
            case 16: {
                if (mainHand == null) return;
                ItemMeta meta = mainHand.getItemMeta();
                if (meta == null) return;
                if (meta.hasEnchants()) {
                    for (Enchantment enchantment : meta.getEnchants().keySet())
                        meta.removeEnchant(enchantment);
                } else {
                    meta.addEnchant(Enchantment.LUCK, 1, true);
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                }
                mainHand.setItemMeta(meta);
                equipment.setMainHand(mainHand);
                break;
            }
        }
        //10, 16 off, main


        InvConcealer.update(equipment, inv);
        player.updateInventory();

    }

    /*
    Logger logger = Main.getPlugin().getLogger();
    @EventHandler
    public void a(InventoryInteractEvent event) {
        logger.info(event.getEventName() + " triggered");
    }
    @EventHandler
    public void b(InventoryClickEvent event) {
        logger.info(event.getEventName() + " triggered");
    }
    @EventHandler
    public void c(InventoryPickupItemEvent event) {
        logger.info(event.getEventName() + " triggered");
    }
    @EventHandler
    public void d(InventoryMoveItemEvent event) {
        logger.info(event.getEventName() + " triggered");
    }
    @EventHandler
    public void e(InventoryOpenEvent event) {
        logger.info(event.getEventName() + " triggered");
    }
    @EventHandler
    public void f(InventoryClickEvent event) {
        logger.info(event.getEventName() + " triggered");
    }
    @EventHandler
    public void a(InventoryCreativeEvent event) {
        logger.info(event.getEventName() + " triggered");
    }
    */
}
