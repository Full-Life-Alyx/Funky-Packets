package me.fuzy_boi.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.Pair;
import me.fuzy_boi.Main;
import me.fuzy_boi.objects.GhostEquipment;
import me.fuzy_boi.objects.MPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public class OnEquip {
    public static void init(ProtocolManager manager) {

        manager.addPacketListener(new PacketAdapter(Main.getInstance(), ListenerPriority.NORMAL, PacketType.Play.Server.ENTITY_EQUIPMENT) {
            @Override
            public void onPacketSending(PacketEvent event) {

                Player player = event.getPlayer();
                PacketContainer packet = event.getPacket();

                //Player changed = (Player) event.getPlayer().getWorld().getEntities().stream().filter(entity -> entity instanceof Player && entity.getEntityId() == packet.getIntegers().read(0)).findFirst().orElse(null);
                Player changed = (Player) manager.getEntityFromID(player.getWorld(), packet.getIntegers().read(0));

                if (changed == null) return;

                UUID uuid = changed.getUniqueId();
                MPlayer mPlayer = MPlayer.get(uuid);

                GhostEquipment equipment = mPlayer.getEquipment();


                List<Pair<EnumWrappers.ItemSlot, ItemStack>> update = packet.getSlotStackPairLists().read(0);
                for (Pair<EnumWrappers.ItemSlot, ItemStack> pair : update) {
                    EnumWrappers.ItemSlot slot = pair.getFirst();
                    if (equipment.getItem(slot) == null) continue;

                    ItemStack item = equipment.getItem(slot);
                    pair.setSecond(item);
                }
                packet.getSlotStackPairLists().write(0, update);

            }
        });
    }
}
