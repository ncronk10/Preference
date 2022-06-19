package me.natertot.preference;

import com.github.mittenmc.serverutils.Colors;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class InventoryItem {
    private final ItemStack item;
    private final List<String> commands;


    public InventoryItem(String name, List<String> lore, String material, List<String> commands) {


        Material material1 = Material.getMaterial(material);
        if (material1 == null) {
            material1 = Material.DIRT;
            Bukkit.getLogger().warning("The material "+ material +" provided is invalid!");
        }
        item = new ItemStack(material1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Colors.conv(name));
        for(String str : lore) {
            lore.add(Colors.conv(str));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);

        this.commands = commands;
    }

    public ItemStack getItem() {
        return item;
    }

    public void runCommands(Player player) {
        for (String command : commands) {
            Bukkit.dispatchCommand(player, command);
        }

    }

}
