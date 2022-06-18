package me.natertot.preference;

import org.bukkit.plugin.java.JavaPlugin;

public final class Preference extends JavaPlugin {
    private static Preference instance;
    private PreferenceMenu preferenceMenu;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        preferenceMenu = new PreferenceMenu();
        getCommand("pref").setExecutor(new OpenPreferenceMenuCommand());
        getCommand("prefadmin").setExecutor(new AdminCommands());

        getServer().getPluginManager().registerEvents(preferenceMenu,this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }
    public static Preference getInstance() {
        return instance;
    }
    public PreferenceMenu getPreferenceMenu() {
        return preferenceMenu;
    }
}
