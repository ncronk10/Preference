package me.natertot.preference;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MenuCommand implements CommandExecutor {
    ItemStack shard = new ItemStack(Material.PRISMARINE_SHARD, 1);
    ItemStack paper = new ItemStack(Material.PAPER, 1);
    ItemStack coal = new ItemStack(Material.COAL, 1);
    ItemStack beacon = new ItemStack(Material.BEACON, 1);
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (sender instanceof Player) {

            Player p = (Player) sender;
            Inventory inventory = Bukkit.createInventory(p, 27, "Preferences");

            // Rewards item

            ItemMeta shardMeta = shard.getItemMeta();
            shardMeta.setDisplayName(ChatColor.GREEN +"Toggle Rewards");
            shard.setItemMeta(shardMeta);
            inventory.setItem(10, shard);

            //Messages item

            ItemMeta paperMeta = paper.getItemMeta();
            paperMeta.setDisplayName(ChatColor.GREEN +"Toggle Messages");
            paper.setItemMeta(paperMeta);
            inventory.setItem(12, paper);

            //Shops item

            ItemMeta coalMeta = coal.getItemMeta();
            coalMeta.setDisplayName(ChatColor.GREEN +"Toggle Shops");
            coal.setItemMeta(coalMeta);
            inventory.setItem(14, coal);

            //Full Inventory item
            ItemMeta coalMeta = coal.getItemMeta();
            beaconMeta.setDisplayName(ChatColor.GREEN +"Toggle Full Inventory");
            beacon.setItemMeta(beaconMeta);
            inventory.setItem(16, beacon);

            p.openInventory(inventory);

        }

        return true;
    }
}