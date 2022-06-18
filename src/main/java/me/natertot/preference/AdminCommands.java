package me.natertot.preference;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.List;
import java.util.prefs.Preferences;

public class AdminCommands implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Preference.getInstance().reloadConfig();
        Preference.getInstance().getPreferenceMenu().reload();
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}

