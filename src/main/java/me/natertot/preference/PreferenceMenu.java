package me.natertot.preference;

import com.github.mittenmc.serverutils.Colors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;
import java.util.prefs.Preferences;

public class PreferenceMenu implements Listener {
    private ItemStack shard, paper, torch, beacon, epearl;
    private final HashSet<UUID> playersInInventory;
    private int shardSlot, paperSlot, torchSlot, beaconSlot, epearlSlot;

    public PreferenceMenu() {
        playersInInventory = new HashSet<>();
        reload();
    }

    public void reload() {
        FileConfiguration config = Preference.getInstance().getConfig();
        config.options().copyDefaults(true);

        config.addDefault("menu.rewardsToggle.slot",10);
        config.addDefault("menu.rewardsToggle.name","Toggle Rewards");
        config.addDefault("menu.rewardsToggle.lore", new ArrayList<>());
        config.addDefault("menu.rewardsToggle.material","PRISMARINE_SHARD");
        ArrayList<String> lore = new ArrayList<>();
        Preference.getInstance().saveConfig();
        shardSlot = config.getInt("menu.rewardsToggle.slot");
        shard = new ItemStack(Material.getMaterial(config.getString("menu.rewardsToggle.material")));
        ItemMeta shardMeta = shard.getItemMeta();
        shardMeta.setDisplayName(Colors.conv(config.getString("menu.rewardsToggle.name")));

        for(String str : config.getStringList("menu.rewardsToggle.lore")) {
            lore.add(Colors.conv(str));
        }
        shardMeta.setLore(lore);
        shard.setItemMeta(shardMeta);
        lore.clear();

        config.addDefault("menu.messagesToggle.slot",12);
        config.addDefault("menu.messagesToggle.name","Toggle Messages");
        config.addDefault("menu.messagesToggle.lore", new ArrayList<>());
        config.addDefault("menu.messagesToggle.material","PAPER");
        ArrayList<String> lore1 = new ArrayList<>();
        Preference.getInstance().saveConfig();
        paperSlot = config.getInt("menu.rewardsToggle.slot");
        paper = new ItemStack(Material.getMaterial(config.getString("menu.messagesToggle.material")));
        ItemMeta paperMeta = paper.getItemMeta();
        shardMeta.setDisplayName(Colors.conv(config.getString("menu.messagesToggle.name")));
        for(String str : config.getStringList("menu.messagesToggle.lore")) {
            lore1.add(Colors.conv(str));
        }
        paperMeta.setLore(lore1);
        paper.setItemMeta(paperMeta);
        lore1.clear();

        config.addDefault("menu.invToggle.slot",14);
        config.addDefault("menu.invToggle.name","Toggle Full Inventory");
        config.addDefault("menu.invToggle.lore", new ArrayList<>());
        config.addDefault("menu.invToggle.material","TORCH");
        ArrayList<String> lore2 = new ArrayList<>();
        Preference.getInstance().saveConfig();
        torchSlot = config.getInt("menu.invToggle.slot");
        torch = new ItemStack(Material.getMaterial(config.getString("menu.invToggle.material")));
        ItemMeta torchMeta = torch.getItemMeta();
        torchMeta.setDisplayName(Colors.conv(config.getString("menu.invToggle.name")));
        for(String str : config.getStringList("menu.invToggle.lore")) {
            lore2.add(Colors.conv(str));
        }
        torchMeta.setLore(lore2);
        paper.setItemMeta(torchMeta);
        lore2.clear();

        config.addDefault("menu.shopsToggle.slot",16);
        config.addDefault("menu.shopsToggle.name","Toggle Shops");
        config.addDefault("menu.shopsToggle.lore", new ArrayList<>());
        config.addDefault("menu.shopsToggle.material","BEACON");
        ArrayList<String> lore3 = new ArrayList<>();
        Preference.getInstance().saveConfig();
        beaconSlot = config.getInt("menu.rewardsToggle.slot");
        beacon = new ItemStack(Material.getMaterial(config.getString("menu.shopsToggle.material")));
        ItemMeta beaconMeta = beacon.getItemMeta();
        beaconMeta.setDisplayName(Colors.conv(config.getString("menu.shopsToggle.name")));
        for(String str : config.getStringList("menu.shopsToggle.lore")) {
            lore3.add(Colors.conv(str));
        }
        paperMeta.setLore(lore3);
        beacon.setItemMeta(paperMeta);
        lore3.clear();

        config.addDefault("menu.visionToggle.slot",26);
        config.addDefault("menu.visionToggle.name","Toggle Night Vision");
        config.addDefault("menu.visionToggle.lore", new ArrayList<>());
        config.addDefault("menu.visionToggle.material","ENDER_PEARL");
        ArrayList<String> lore4 = new ArrayList<>();
        Preference.getInstance().saveConfig();
        epearlSlot = config.getInt("menu.visionToggle.slot");
        epearl = new ItemStack(Material.getMaterial(config.getString("menu.visionToggle.material")));
        ItemMeta epearlMeta = epearl.getItemMeta();
        epearlMeta.setDisplayName(Colors.conv(config.getString("menu.visionToggle.name")));
        for(String str : config.getStringList("menu.visionToggle.lore")) {
            lore4.add(Colors.conv(str));
        }
        epearlMeta.setLore(lore4);
        epearl.setItemMeta(epearlMeta);
        lore4.clear();


    }

    public void openInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 36, "Preferences");

        inventory.setItem(10, shard);

        inventory.setItem(12, paper);

        inventory.setItem(14, torch);

        inventory.setItem(16, beacon);

        inventory.setItem(26, epearl);

        player.openInventory(inventory);
        playersInInventory.add(player.getUniqueId());
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        if(e.getClickedInventory() == null) return;

        if (playersInInventory.contains(e.getWhoClicked().getUniqueId())) {
            e.setCancelled(true);

            Player p = (Player) e.getWhoClicked();

            if (e.getSlot() == shardSlot) {
                //System.out.println("You have toggled rewards.");
                p.sendMessage(ChatColor.GREEN + "You have toggled rewards.");
            }
            else if (e.getSlot() == paperSlot) {
                //System.out.println("You have toggled messages.");
                p.sendMessage(ChatColor.GREEN + "You have toggled messages.");
            }
            else if (e.getSlot() == torchSlot) {
                //System.out.println("You have toggled shops.");
                p.sendMessage(ChatColor.GREEN + "You have toggled shops.");
            }
            else if (e.getSlot() == beaconSlot) {
                //System.out.println("You have toggled full inventory.");
                p.sendMessage(ChatColor.GREEN + "You have toggled full inventory.");
            }
            else if (e.getSlot() == epearlSlot) {
                p.sendMessage(ChatColor.GREEN + "You have toggled night vision");
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
