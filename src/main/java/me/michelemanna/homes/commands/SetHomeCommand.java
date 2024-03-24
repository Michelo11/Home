package me.michelemanna.homes.commands;

import me.michelemanna.homes.Homes;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class SetHomeCommand implements CommandExecutor {
    private final Homes plugin;

    public SetHomeCommand(Homes plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(plugin.getMessage("commands.player-only"));
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(plugin.getMessage("commands.sethome-usage"));
            return true;
        }

        try {
            plugin.getDatabase().addHome(player, args[0], player.getLocation());
            player.sendMessage(plugin.getMessage("commands.sethome-success").replace("%name%", args[0]));
        } catch (SQLException e) {
            player.sendMessage(plugin.getMessage("commands.sethome-error"));
        }

        return true;
    }
}
