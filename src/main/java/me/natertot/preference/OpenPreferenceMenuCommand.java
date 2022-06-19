package me.natertot.preference;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenPreferenceMenuCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (sender instanceof Player) {


            Player p = (Player) sender;
            Preference.getInstance().getPreferenceMenu().openInventory(p);

        }

        return true;
    }
}