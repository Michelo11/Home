package me.michelemanna.homes.commands;

import me.michelemanna.homes.Homes;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class DeleteCommand implements CommandExecutor {
    private final Homes plugin;

    public DeleteCommand(Homes plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(plugin.getConfig().getString("commands.player-only"));
            return true;
        }

        try {
            plugin.getDatabase().removeHome(player, args[0]);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        player.sendMessage(plugin.getConfig().getString("commands.delete-home").replace("%home%", args[0]));

        return true;
    }
}
