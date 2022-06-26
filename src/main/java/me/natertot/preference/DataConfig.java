package me.natertot.preference;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class DataConfig {

    private static File file;
    private static FileConfiguration fileConfiguration;

    static {
        setup();
        save();
    }

    //Finds or generates the config file
    public static void setup() {
        file = new File(Preference.getInstance().getDataFolder(), "data.yml");
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {
        return fileConfiguration;
    }

    public static void save() {
        try {
            fileConfiguration.save(file);
        }
        catch (IOException e) {
            System.out.println("Could not save file");
        }
    }

    public static void reload() {
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public static boolean toggleShopMessages(Player player) {
        boolean newValue = !fileConfiguration.getBoolean(player.getUniqueId() + ".areShopMessagesOff");
        fileConfiguration.set(player.getUniqueId() + ".areShopMessagesOff", newValue);
        return newValue;
    }

    public static boolean toggleRewardsMessages(Player player) {
        boolean newValue = !fileConfiguration.getBoolean(player.getUniqueId() + ".areRewardsMessagesOff");
        fileConfiguration.set(player.getUniqueId() + ".areRewardsMessagesOff", newValue);
        Preference.getInstance().getMineRewardsAPI().setPlayerMessagesOff(player, newValue);
        return newValue;
    }

    public static boolean toggleLevelsMessages(Player player) {
        boolean newValue = !fileConfiguration.getBoolean(player.getUniqueId() + ".areLevelsMessagesOff");
        fileConfiguration.set(player.getUniqueId() + ".areLevelsMessagesOff", newValue);
        Preference.getInstance().getPlayerLevelsAPI().setPlayerMessagesOff(player, newValue);
        return newValue;
    }

    public static void loadPlayerPreferences(Player player) {
        fileConfiguration.addDefault(player.getUniqueId() + ".areShopMessagesOff", false);
        fileConfiguration.addDefault(player.getUniqueId() + ".areRewardsMessagesOff", false);
        fileConfiguration.addDefault(player.getUniqueId() + ".areLevelsMessagesOff", false);

        //TODO - Shops
        Preference.getInstance().getMineRewardsAPI().setPlayerMessagesOff(player, fileConfiguration.getBoolean(player.getUniqueId() + ".areRewardsMessagesOff"));
        Preference.getInstance().getPlayerLevelsAPI().setPlayerMessagesOff(player, fileConfiguration.getBoolean(player.getUniqueId() + ".areLevelsMessagesOff"));
    }


}
