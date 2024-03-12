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
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage("You must specify a name for the home!");
            return true;
        }

        try {
            plugin.getDatabase().addHome(player, args[0], player.getLocation());
            player.sendMessage("§aSet home " + args[0] + "!");
        } catch (SQLException e) {
            player.sendMessage("An error occurred while setting the home!");
        }

        return true;
    }
}
