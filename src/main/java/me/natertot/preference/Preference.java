package me.natertot.preference;

import org.bukkit.plugin.java.JavaPlugin;

public final class Preference extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("pref").setExecutor(new MenuCommand());

        getServer().getPluginManager().registerEvents(new MenuListener(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
