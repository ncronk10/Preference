package me.natertot.preference;

import me.gavvydizzle.minerewards.api.MineRewardsAPI;
import me.gavvydizzle.playerlevels.api.PlayerLevelsAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Preference extends JavaPlugin {

    private static Preference instance;
    private PreferenceMenu preferenceMenu;
    private PlayerLevelsAPI playerLevelsAPI;
    private MineRewardsAPI mineRewardsAPI;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        playerLevelsAPI = new PlayerLevelsAPI();
        mineRewardsAPI = new MineRewardsAPI();

        preferenceMenu = new PreferenceMenu();
        getCommand("pref").setExecutor(new PreferenceCommands());
        getCommand("prefadmin").setExecutor(new AdminCommands());

        getServer().getPluginManager().registerEvents(preferenceMenu,this);
    }

    @Override
    public void onDisable() {
        try {
            DataConfig.save();
        } catch (Exception e) {
            Bukkit.getLogger().severe("[Preferences] Failed to save player data");
            e.printStackTrace();
            return;
        }
        Bukkit.getLogger().info("[Preferences] Successfully saved player data");
    }

    public static Preference getInstance() {
        return instance;
    }

    public PreferenceMenu getPreferenceMenu() {
        return preferenceMenu;
    }

    public PlayerLevelsAPI getPlayerLevelsAPI() {
        return playerLevelsAPI;
    }

    public MineRewardsAPI getMineRewardsAPI() {
        return mineRewardsAPI;
    }
}
