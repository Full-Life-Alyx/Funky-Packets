package me.fuzy_boi.objects;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.Pair;
import me.fuzy_boi.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class GhostEquipment {

    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;

    private ItemStack mainHand;
    private ItemStack offHand;

    UUID uuid;

    public GhostEquipment(MPlayer mPlayer, ItemStack helmet, ItemStack chestplate,
                          ItemStack leggings, ItemStack boots,
                          ItemStack mainHand, ItemStack offHand) {
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
        this.mainHand = mainHand;
        this.offHand = offHand;
        this.uuid = mPlayer.getUuid();


    }
    public GhostEquipment(MPlayer mPlayer) {
        this.helmet = null;
        this.chestplate = null;
        this.leggings = null;
        this.boots = null;
        this.mainHand = null;
        this.offHand = null;

        this.uuid = mPlayer.getUuid();
    }

    public GhostEquipment clear() {
        this.helmet = null;
        this.chestplate = null;
        this.leggings = null;
        this.boots = null;
        this.mainHand = null;
        this.offHand = null;
        return this;
    }


    public GhostEquipment update() {

        List<Pair<EnumWrappers.ItemSlot, ItemStack>> items = new ArrayList<>();
        Player player = Bukkit.getPlayer(uuid);
        if (player == null)
            throw new IllegalStateException("Player must be online");
        PlayerInventory inv = player.getInventory();

        addNotNullItem(items, helmet, EnumWrappers.ItemSlot.HEAD, inv);
        addNotNullItem(items, chestplate, EnumWrappers.ItemSlot.CHEST, inv);
        addNotNullItem(items, leggings, EnumWrappers.ItemSlot.LEGS, inv);
        addNotNullItem(items, boots, EnumWrappers.ItemSlot.FEET, inv);
        addNotNullItem(items, mainHand, EnumWrappers.ItemSlot.MAINHAND, inv);
        addNotNullItem(items, offHand, EnumWrappers.ItemSlot.OFFHAND, inv);

        ProtocolManager manager = Main.getProtocolManager();
        PacketContainer packet = manager.createPacket(PacketType.Play.Server.ENTITY_EQUIPMENT);
        packet.getSlotStackPairLists().write(0, items);

        packet.getIntegers().write(0, player.getEntityId());
        for (Player loopPlayer : Bukkit.getOnlinePlayers()) {
            if (loopPlayer == player) continue;
            try {
                manager.sendServerPacket(loopPlayer, packet);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        /*getAPlayer().getPlayer().sendMessage("Ghost Equipment: \n" +
                "Helmet Type: " + (helmet == null ? "NULL" : helmet.getType()) + "\n" +
                "Chestplate Type: " + (chestplate == null ? "NULL" : chestplate.getType()) + "\n" +
                "Leggings Type: " + (leggings == null ? "NULL" : leggings.getType()) + "\n" +
                "Boots Type: " + (boots == null ? "NULL" : boots.getType()) + "\n" +
                "MainHand Type: " + (mainHand == null ? "NULL" : mainHand.getType()) + "\n" +
                "OffHand Type: " + (offHand == null ? "NULL" : offHand.getType()));*/

        return this;
    }


    //TODO BAD NAME
    private void addNotNullItem(List<Pair<EnumWrappers.ItemSlot, ItemStack>> items, ItemStack item, EnumWrappers.ItemSlot slot, PlayerInventory inv) {
        if (item != null)
            items.add(new Pair<>(slot, item));
        else
            //TODO find method for wrapper => original
            items.add(new Pair<>(slot, inv.getItem(EquipmentSlot.values()[slot.ordinal()])));
    }

    public ItemStack getItem(EnumWrappers.ItemSlot slotType) {

        if (slotType == null)
            return null;

        switch (slotType) {
            case HEAD: {
                return getHelmet();
            }
            case CHEST: {
                return getChestplate();
            }
            case LEGS: {
                return getLeggings();
            }
            case FEET: {
                return getBoots();
            }
            case MAINHAND: {
                return getMainHand();
            }
            case OFFHAND: {
                return getOffHand();
            }
        }
        return null;
    }
    public GhostEquipment setItem(EnumWrappers.ItemSlot slotType, ItemStack item) {

        switch (slotType) {
            case HEAD: {
                setHelmet(item);
                break;
            }
            case CHEST: {
                setChestplate(item);
                break;
            }
            case LEGS: {
                setLeggings(item);
                break;
            }
            case FEET: {
                setBoots(item);
                break;
            }
            case MAINHAND: {
                setMainHand(item);
                break;
            }
            case OFFHAND: {
                setOffHand(item);
                break;
            }
        }
        return this;
    }

    public ItemStack getHelmet() {
        return helmet;
    }
    public ItemStack getChestplate() {
        return chestplate;
    }
    public ItemStack getLeggings() {
        return leggings;
    }
    public ItemStack getBoots() {
        return boots;
    }
    public ItemStack getMainHand() {
        return mainHand;
    }
    public ItemStack getOffHand() {
        return offHand;
    }

    public void setHelmet(ItemStack helmet) {
        this.helmet = helmet;
    }
    public void setChestplate(ItemStack chestplate) {
        this.chestplate = chestplate;
    }
    public void setLeggings(ItemStack leggings) {
        this.leggings = leggings;
    }
    public void setBoots(ItemStack boots) {
        this.boots = boots;
    }
    public void setMainHand(ItemStack mainHand) {
        this.mainHand = mainHand;
    }
    public void setOffHand(ItemStack offHand) {
        this.offHand = offHand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GhostEquipment that = (GhostEquipment) o;
        return Objects.equals(helmet, that.helmet) && Objects.equals(chestplate, that.chestplate) && Objects.equals(leggings, that.leggings) && Objects.equals(boots, that.boots) && Objects.equals(mainHand, that.mainHand) && Objects.equals(offHand, that.offHand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(helmet, chestplate, leggings, boots, mainHand, offHand);
    }
}
