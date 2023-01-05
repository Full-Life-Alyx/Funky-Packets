package me.fuzy_boi.inventories;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class InvWardrobe implements InventoryHolder {

    private Inventory inv = getInventory();


    private static String translate(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    @Override
    public Inventory getInventory() {
        if(inv != null) return(inv);
        Inventory inv = Bukkit.createInventory(this, 54, ChatColor.GRAY + "GhostDrobe");
        ItemStack item1 = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack item2 = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack item3 = new ItemStack(Material.GRAY_DYE, 1);
        {//ItemMeta
            ItemMeta meta3 = item3.getItemMeta();
            //DisplayName

            meta3.setDisplayName("Disenchant");
            item3.setItemMeta(meta3);
        }
        ItemStack item4 = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack item5 = new ItemStack(Material.DIAMOND_HELMET, 1);
        ItemStack item6 = new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1);
        ItemStack item7 = new ItemStack(Material.GOLDEN_LEGGINGS, 1);
        ItemStack item8 = new ItemStack(Material.GOLDEN_CHESTPLATE, 1);
        ItemStack item9 = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack item10 = new ItemStack(Material.NETHERITE_HELMET, 1);
        ItemStack item11 = new ItemStack(Material.CHAINMAIL_LEGGINGS, 1);
        ItemStack item12 = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack item13 = new ItemStack(Material.NETHERITE_LEGGINGS, 1);
        ItemStack item14 = new ItemStack(Material.GOLDEN_HELMET, 1);
        ItemStack item15 = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        ItemStack item16 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
        {//ItemMeta
            ItemMeta meta16 = item16.getItemMeta();
            //DisplayName
            meta16.setDisplayName(" ");
            item16.setItemMeta(meta16);
        }
        ItemStack item17 = new ItemStack(Material.NETHERITE_CHESTPLATE, 1);
        ItemStack item18 = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemStack item19 = new ItemStack(Material.NETHERITE_BOOTS, 1);
        ItemStack item20 = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack item21 = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
        ItemStack item22 = new ItemStack(Material.CHAINMAIL_BOOTS, 1);
        ItemStack item23 = new ItemStack(Material.DIAMOND_BOOTS, 1);
        ItemStack item24 = new ItemStack(Material.CHAINMAIL_HELMET, 1);
        ItemStack item25 = new ItemStack(Material.DIAMOND, 1);
        {//ItemMeta
            ItemMeta meta25 = item25.getItemMeta();
            //DisplayName
            meta25.setDisplayName(translate("&9Enchant"));
            {//Enchantments
                meta25.addEnchant(Enchantment.LUCK, 1, true);
            }
            meta25.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            item25.setItemMeta(meta25);
        }
        ItemStack item26 = new ItemStack(Material.GOLDEN_BOOTS, 1);
        ItemStack item27 = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack item28 = new ItemStack(Material.BARRIER, 1);
        {//ItemMeta
            ItemMeta meta28 = item28.getItemMeta();
            //DisplayName
            meta28.setDisplayName("None");
            item28.setItemMeta(meta28);
        }
        ItemStack item29 = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
        {
            ItemMeta meta29 = item29.getItemMeta();
            meta29.setDisplayName(ChatColor.RED + "Reset Armor");
            item29.setItemMeta(meta29);
        }
        {//Set items to the inventory
            //Multiple items
            {
                for(int i : new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 45, 46, 52, 53})
                    inv.setItem(i, item16);
                for(int i : new int[]{10, 19, 28, 37})
                    inv.setItem(i, item28);
                for(int i : new int[]{17, 26, 35, 44})
                    inv.setItem(i, item25);
                for(int i : new int[]{9, 18, 27, 36})
                    inv.setItem(i, item3);
                for(int i : new int[]{47, 48, 49, 50, 51})
                    inv.setItem(i, item29);
            }
            //Single items
            {
                inv.setItem(31, item2);
                inv.setItem(20, item1);
                inv.setItem(43, item19);
                inv.setItem(38, item18);
                inv.setItem(25, item17);
                inv.setItem(23, item15);
                inv.setItem(15, item14);
                inv.setItem(34, item13);
                inv.setItem(22, item12);
                inv.setItem(30, item11);
                inv.setItem(16, item10);
                inv.setItem(29, item27);
                inv.setItem(42, item26);
                inv.setItem(12, item24);
                inv.setItem(41, item23);
                inv.setItem(39, item22);
                inv.setItem(32, item21);
                inv.setItem(13, item20);
                inv.setItem(24, item8);
                inv.setItem(33, item7);
                inv.setItem(40, item9);
                inv.setItem(11, item4);
                inv.setItem(21, item6);
                inv.setItem(14, item5);
            }
        }
        return inv;
    }

}