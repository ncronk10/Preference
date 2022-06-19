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
        } catch (IOException e) {
            System.out.println("Could not save file");
        }
    }

    public static void reload() {
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public static boolean toggleShopMessages(Player player ) {
        boolean newValue = !fileConfiguration.getBoolean(player.getUniqueId().toString() + ".areShopMessagesOff");
        fileConfiguration.set(player.getUniqueId().toString() + ".areShopMessagesOff", newValue );
        return newValue;
    }
    public static boolean toggleRewardsMessages(Player player ) {
        boolean newValue = !fileConfiguration.getBoolean(player.getUniqueId().toString() + ".areRewardsMessagesOff");
        fileConfiguration.set(player.getUniqueId().toString() + ".areRewardsMessagesOff", newValue);
        return newValue;
    }
    public static boolean toggleLevelsMessages(Player player ) {
        boolean newValue = !fileConfiguration.getBoolean(player.getUniqueId().toString() + ".areLevelsMessagesOff");
        fileConfiguration.set(player.getUniqueId().toString() + ".areLevelsMessagesOff", newValue);
        return newValue;
    }


}
