package me.natertot.preference;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class PreferenceCommands implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return true;

        Player p = (Player) sender;

        if (args.length == 0) {
            Preference.getInstance().getPreferenceMenu().openInventory(p);
        }
        else if (args.length == 1) {

            if (args[0].equalsIgnoreCase("toggleRewards")) {

                if (DataConfig.toggleRewardsMessages(p)) {
                    p.sendMessage(ChatColor.RED + "Turned off reward messages");
                } else {
                    p.sendMessage(ChatColor.GREEN + "Turned on reward messages");
                }
            }
            else if (args[0].equalsIgnoreCase("toggleLevels")) {

                if (DataConfig.toggleLevelsMessages(p)) {
                    p.sendMessage(ChatColor.RED + "Turned off level up messages");
                }
                else {
                    p.sendMessage(ChatColor.GREEN + "Turned on level up messages");
                }
            }
            else if (args[0].equalsIgnoreCase("toggleShops")) {

                if (DataConfig.toggleShopMessages(p)) {
                    p.sendMessage(ChatColor.RED + "Turned off shop messages");
                }
                else {
                    p.sendMessage(ChatColor.GREEN + "Turned on shop messages");
                }
            }

        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Arrays.asList("toggleRewards", "toggleLevels", "toggleShops");
    }
}