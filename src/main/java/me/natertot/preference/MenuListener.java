package me.natertot.preference;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuListener implements Listener {

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equalsIgnoreCase("Preferences")) {

            if (e.getCurrentItem() == null) {
                return;
            }

            if (e.getCurrentItem().getType() == Material.PRISMARINE_SHARD) {
                //System.out.println("You have toggled rewards.");
                p.sendMessage(ChatColor.GREEN + "You have toggled rewards.");
            }
            else if (e.getCurrentItem().getType() == Material.PAPER) {
                //System.out.println("You have toggled messages.");
                p.sendMessage(ChatColor.GREEN + "You have toggled messages.");
            }
            else if (e.getCurrentItem().getType() == Material.COAL) {
                //System.out.println("You have toggled shops.");
                p.sendMessage(ChatColor.GREEN + "You have toggled shops.");
            }
            else if (e.getCurrentItem().getType() == Material.BEACON) {
                //System.out.println("You have toggled full inventory.");
                p.sendMessage(ChatColor.GREEN + "You have toggled full inventory.");
            }

            e.setCancelled(true);
        }
    }
}
