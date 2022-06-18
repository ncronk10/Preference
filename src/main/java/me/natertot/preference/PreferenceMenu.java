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
        ArrayList<String> lore = new ArrayList<>();

        config.addDefault("menu.rewardsToggle.slot",10);
        config.addDefault("menu.rewardsToggle.name","Toggle Rewards");
        config.addDefault("menu.rewardsToggle.lore", new ArrayList<>());
        config.addDefault("menu.rewardsToggle.material","PRISMARINE_SHARD");
        config.addDefault("menu.messagesToggle.slot",12);
        config.addDefault("menu.messagesToggle.name","Toggle Messages");
        config.addDefault("menu.messagesToggle.lore", new ArrayList<>());
        config.addDefault("menu.messagesToggle.material","PAPER");
        config.addDefault("menu.invToggle.slot",14);
        config.addDefault("menu.invToggle.name","Toggle Full Inventory");
        config.addDefault("menu.invToggle.lore", new ArrayList<>());
        config.addDefault("menu.invToggle.material","TORCH");
        config.addDefault("menu.shopsToggle.slot",16);
        config.addDefault("menu.shopsToggle.name","Toggle Shops");
        config.addDefault("menu.shopsToggle.lore", new ArrayList<>());
        config.addDefault("menu.shopsToggle.material","BEACON");
        config.addDefault("menu.visionToggle.slot",26);
        config.addDefault("menu.visionToggle.name","Toggle Night Vision");
        config.addDefault("menu.visionToggle.lore", new ArrayList<>());
        config.addDefault("menu.visionToggle.material","ENDER_PEARL");
        Preference.getInstance().saveConfig();

        //Rewards
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

        //Messages
        paperSlot = config.getInt("menu.rewardsToggle.slot");
        paper = new ItemStack(Material.getMaterial(config.getString("menu.messagesToggle.material")));
        ItemMeta paperMeta = paper.getItemMeta();
        shardMeta.setDisplayName(Colors.conv(config.getString("menu.messagesToggle.name")));
        for(String str : config.getStringList("menu.messagesToggle.lore")) {
            lore.add(Colors.conv(str));
        }
        paperMeta.setLore(lore);
        paper.setItemMeta(paperMeta);
        lore.clear();

        //Inventory
        torchSlot = config.getInt("menu.invToggle.slot");
        torch = new ItemStack(Material.getMaterial(config.getString("menu.invToggle.material")));
        ItemMeta torchMeta = torch.getItemMeta();
        torchMeta.setDisplayName(Colors.conv(config.getString("menu.invToggle.name")));
        for(String str : config.getStringList("menu.invToggle.lore")) {
            lore.add(Colors.conv(str));
        }
        torchMeta.setLore(lore);
        paper.setItemMeta(torchMeta);
        lore.clear();

        //Shops
        beaconSlot = config.getInt("menu.rewardsToggle.slot");
        beacon = new ItemStack(Material.getMaterial(config.getString("menu.shopsToggle.material")));
        ItemMeta beaconMeta = beacon.getItemMeta();
        beaconMeta.setDisplayName(Colors.conv(config.getString("menu.shopsToggle.name")));
        for(String str : config.getStringList("menu.shopsToggle.lore")) {
            lore.add(Colors.conv(str));
        }
        paperMeta.setLore(lore);
        beacon.setItemMeta(paperMeta);
        lore.clear();

        //Night Vision
        epearlSlot = config.getInt("menu.visionToggle.slot");
        epearl = new ItemStack(Material.getMaterial(config.getString("menu.visionToggle.material")));
        ItemMeta epearlMeta = epearl.getItemMeta();
        epearlMeta.setDisplayName(Colors.conv(config.getString("menu.visionToggle.name")));
        for(String str : config.getStringList("menu.visionToggle.lore")) {
            lore.add(Colors.conv(str));
        }
        epearlMeta.setLore(lore);
        epearl.setItemMeta(epearlMeta);
        lore.clear();


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
