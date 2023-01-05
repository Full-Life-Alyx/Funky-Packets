package me.fuzy_boi.inventories;

import me.fuzy_boi.Main;
import me.fuzy_boi.objects.GhostEquipment;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class InvConcealer implements InventoryHolder {

    private Inventory inv = getInventory();

    @Override
    public Inventory getInventory() {
        if(inv != null) return(inv);
        Inventory inv = Bukkit.createInventory(this, 27, ChatColor.GRAY + "Concealer");
        ItemStack item1 = new ItemStack(Material.GRAY_DYE, 1);
        {
            ItemMeta meta = item1.getItemMeta();
            meta.setDisplayName(ChatColor.RED + "ERROR");
            List<String> lore = new ArrayList<>();
            lore.add("Something happened that prevents");
            lore.add("this option from loading");
            lore.add("Please contact the developer about this issue");
            meta.setLore(lore);
            item1.setItemMeta(meta);
        }
        ItemStack item2 = new ItemStack(Material.GRAY_DYE, 1);
        {//ItemMeta
            ItemMeta meta = item2.getItemMeta();

            meta.setDisplayName(ChatColor.RED + "ERROR");
            List<String> lore = new ArrayList<>();
            lore.add("Something happened that prevents");
            lore.add("this option from loading");
            lore.add("Please contact the developer about this issue");
            meta.setLore(lore);
            item2.setItemMeta(meta);
        }
        ItemStack item4 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
        {
            ItemMeta meta = item4.getItemMeta();
            meta.setDisplayName(" ");

            item4.setItemMeta(meta);
        }
        {//Set items to the inventory
            //Multiple items
            for(int i : new int[]{12, 14})
                inv.setItem(i, item2);
            for(int i : new int[]{0, 1, 2, 3, 4, 5, 7, 6, 8, 9, 10, 13, 16, 17, 18, 19, 20, 21, 22, 23, 24,25, 26})
                inv.setItem(i, item4);
            for (int i: new int[]{10, 16})
                inv.setItem(i, item1);
        }
        return(inv);
    }


    //TODO Replace with smth more object oriented ._.
    public static void update(GhostEquipment equipment, Inventory inv) {
        Logger logger = Main.getInstance().getLogger();
        //main
        updateUi(inv, equipment.getMainHand(), 15, 14, 16);
        //off
        updateUi(inv, equipment.getOffHand(), 11, 12, 10);
        equipment.update();

    }
    //god i love extract
    private static void updateUi(Inventory inv, ItemStack heldItem,
                                 int displaySlot, int toggleSlot, int enchantSlot) {

        ItemStack deny = new ItemStack(Material.BARRIER);
        {
            ItemMeta meta = deny.getItemMeta();
            meta.setDisplayName(" ");
            deny.setItemMeta(meta);

            meta.setDisplayName(" ");
            deny.setItemMeta(meta);
        }

        ItemStack toggleItem;
        ItemStack enchantItem;

        if (heldItem != null) {
            //notnull main
            toggleItem = getToggleItem(Material.LIME_DYE, ChatColor.GREEN + "Concealer", "Click to disable!");

            if (heldItem.getType().isAir()) {
                inv.setItem(displaySlot, null);
            } else {
                inv.setItem(displaySlot, heldItem);
            }
            if (heldItem.hasItemMeta()) {
                ItemMeta meta = heldItem.getItemMeta();
                if (meta.hasEnchants()) {
                    enchantItem = getToggleItem(Material.LIME_DYE, ChatColor.DARK_PURPLE + "Enchant", "Click to disenchant!");
                } else {
                    enchantItem = getToggleItem(Material.GRAY_DYE, ChatColor.GRAY + "Enchant", "Click to enchant!");
                }
            } else
                enchantItem = getToggleItem(Material.GRAY_DYE, ChatColor.RED + "Enchant", "This item cannot be enchanted!");
        } else {
            //null, main
            toggleItem = getToggleItem(Material.GRAY_DYE, ChatColor.RED + "Concealer", "Click to enable!");
            enchantItem = getToggleItem(Material.GRAY_DYE, ChatColor.RED + "Enchant", "You must input an item to enchant!");
            inv.setItem(displaySlot, deny);
        }
        inv.setItem(toggleSlot, toggleItem);
        inv.setItem(enchantSlot, enchantItem);
    }

    //AUTO GENERATED
    private static ItemStack getToggleItem(Material material, String name, String lore) {
        ItemStack ToggleItem;
        ToggleItem = new ItemStack(material);
        ItemMeta meta = ToggleItem.getItemMeta();
        meta.setDisplayName(name);
        List<String> loreList = new ArrayList<>();
        loreList.add(ChatColor.GRAY + lore);
        meta.setLore(loreList);
        ToggleItem.setItemMeta(meta);
        return ToggleItem;
    }

}