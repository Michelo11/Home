package me.michelemanna.homes.commands;

import me.michelemanna.homes.Homes;
import me.michelemanna.homes.gui.HomeMenus;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomesCommand implements CommandExecutor {
    private final Homes plugin;

    public HomesCommand(Homes plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        HomeMenus.openHomes(player);

        return true;
    }
}