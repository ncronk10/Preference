package me.natertot.preference;

import com.github.mittenmc.serverutils.ColoredItems;
import com.github.mittenmc.serverutils.Colors;
import org.bukkit.Bukkit;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class PreferenceMenu implements Listener {

    private Inventory inventory;
    private final HashSet<UUID> playersInInventory;
    private final HashMap<Integer, InventoryItem> inventoryItems;

    public PreferenceMenu() {
        playersInInventory = new HashSet<>();
        inventoryItems = new HashMap<>();
        reload();
    }

    public void reload() {
        FileConfiguration config = Preference.getInstance().getConfig();
        config.options().copyDefaults(true);

        config.addDefault("menu.name","Preferences");
        config.addDefault("menu.rows",4);
        config.addDefault("menu.filler","white");
        config.addDefault("menu.items",new HashMap<>());
        Preference.getInstance().saveConfig();

        String inventoryName = Colors.conv(config.getString("menu.name"));
        int inventorySize = config.getInt("menu.rows") * 9;
        ItemStack filler = ColoredItems.getGlassByName(config.getString("menu.filler"));



        for (String key : config.getConfigurationSection("menu.items").getKeys(false)) {


            String path = "menu.items." + key;
            int slot = (config.getInt(path + ".slot"));
            if(slot < 0 || slot >= inventorySize) {
                Bukkit.getLogger().warning("The material "+ slot +" provided is out of bounds in " + path + "!");
                continue;
            }

            InventoryItem inventoryItem = new InventoryItem(
                    config.getString(path + ".name"),
                    config.getStringList(path + ".lore"),
                    config.getString(path + ".material"),
                    config.getStringList(path + ".commands")
            );

            inventoryItems.put(config.getInt(path + ".slot"),inventoryItem);
        }

        inventory = Bukkit.createInventory(null, inventorySize, inventoryName);

        for (int i = 0; i < inventory.getSize();i++) {
            inventory.setItem(i, filler);
        }

        for (int slot : inventoryItems.keySet()) {
            inventory.setItem(slot, inventoryItems.get(slot).getItem());
        }

    }

    public void openInventory(Player player) {
        player.openInventory(inventory);
        playersInInventory.add(player.getUniqueId());
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        if(e.getClickedInventory() == null) return;

        if (playersInInventory.contains(e.getWhoClicked().getUniqueId())) {
            e.setCancelled(true);

            Player p = (Player) e.getWhoClicked();

            if (inventoryItems.containsKey(e.getSlot())) {
                inventoryItems.get(e.getSlot()).runCommands(p);
            }

        }
    }
    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent e) {
        playersInInventory.remove(e.getPlayer().getUniqueId());
    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        playersInInventory.remove(e.getPlayer().getUniqueId());
    }
}
