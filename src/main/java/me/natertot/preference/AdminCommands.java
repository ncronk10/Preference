package me.natertot.preference;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.Arrays;
import java.util.List;

public class AdminCommands implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) return true;

        if (args[0].equalsIgnoreCase("reload")) {
            Preference.getInstance().reloadConfig();
            Preference.getInstance().getPreferenceMenu().reload();
            sender.sendMessage(ChatColor.GREEN + "[Preferences] Reloaded");
        }
        else if (args[0].equalsIgnoreCase("saveData")) {
            DataConfig.save();
            DataConfig.reload();
            sender.sendMessage(ChatColor.GREEN + "[Preferences] Saved data.yml");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Arrays.asList("reload", "saveData");
    }
}
