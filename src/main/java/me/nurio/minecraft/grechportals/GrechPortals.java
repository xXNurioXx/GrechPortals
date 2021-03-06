package me.nurio.minecraft.grechportals;

import lombok.Getter;
import me.nurio.minecraft.grechportals.listeners.PlayerJoinAreaListener;
import me.nurio.minecraft.grechportals.portals.WorldPortalsFactory;
import me.nurio.minecraft.worldareas.GrechAreas;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class GrechPortals extends JavaPlugin {

    @Getter private static Plugin plugin;
    @Getter private WorldPortalsFactory portalsFactory;

    @Override
    public void onEnable() {
        plugin = this;
        portalsFactory = new WorldPortalsFactory();

        ConfigLoader configLoader = new ConfigLoader(
            plugin,
            GrechAreas.getWorldAreaFactory(),
            portalsFactory
        );
        configLoader.load(); // Will load portals on the portalsFactory.

        PlayerJoinAreaListener joinAreaListener = new PlayerJoinAreaListener(
            plugin,
            portalsFactory
        );
        Bukkit.getPluginManager().registerEvents(joinAreaListener, plugin);
    }

}