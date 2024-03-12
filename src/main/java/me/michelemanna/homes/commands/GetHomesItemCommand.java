package me.michelemanna.homes.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.item.builder.ItemBuilder;

public class GetHomesItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cYou must be a player to use this command.");
            return true;
        }

        ItemBuilder item = new ItemBuilder(Material.COMPASS)
                .setDisplayName("Home Menu");

        player.getInventory().addItem(item.get());
        player.sendMessage("§aYou have received the Home Menu item.");

        return false;
    }
}
