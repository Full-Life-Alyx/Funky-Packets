package me.fuzy_boi;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import me.fuzy_boi.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main plugin;
    private static ProtocolManager protocolManager;

    @Override
    public void onEnable() {
        plugin = this;
        protocolManager = ProtocolLibrary.getProtocolManager();

        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new OnUseWardrobe(), this);
        pluginManager.registerEvents(new OnGUIWardrobe(), this);
        pluginManager.registerEvents(new OnJoin(), this);
        pluginManager.registerEvents(new OnUseConcealer(), this);
        pluginManager.registerEvents(new OnGuiConcealer(), this);

        //TODO not static?
        OnEquip.init(protocolManager);



    }

    public static Main getInstance() {
        return plugin;
    }

    public static ProtocolManager getProtocolManager() {
        return protocolManager;
    }

    @Override
    public void onDisable() {

    }
}
