package me.fuzy_boi.objects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class MPlayer {

    private UUID uuid;
    private GhostEquipment equipment;


    public MPlayer(UUID uuid) {
        this.uuid = uuid;
        this.equipment = new GhostEquipment(this);

        map.put(uuid, this);

    }
    public UUID getUuid() {
        return uuid;
    }
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public GhostEquipment getEquipment() {
        return equipment;
    }
    public void setEquipment(GhostEquipment equipment) {
        this.equipment = equipment;
    }

    static HashMap<UUID, MPlayer> map = new HashMap<>();

    public static HashMap<UUID, MPlayer> getMap() {
        return map;
    }
    public static MPlayer get(UUID uuid) {
        return map.get(uuid);
    }
    public static MPlayer get(Player player) {
        return map.get(player.getUniqueId());
    }



}
