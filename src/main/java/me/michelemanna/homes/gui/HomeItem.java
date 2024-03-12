package me.michelemanna.homes.gui;

import me.michelemanna.homes.Homes;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;

import java.sql.SQLException;

public class HomeItem extends AbstractItem {
    private final Homes plugin;

    private final String name;
    private final Location location;

    public HomeItem(Homes plugin, String name, Location location) {
        this.plugin = plugin;
        this.name = name;
        this.location = location;
    }

    @Override
    public ItemProvider getItemProvider() {
        return new ItemBuilder(Material.DARK_OAK_DOOR)
                .setDisplayName(name);
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
        if (clickType.equals(ClickType.DROP)) {
            try {
                plugin.getDatabase().removeHome(player, name);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            player.sendMessage("§aYour home §e" + name + "§a has been removed.");
            player.closeInventory();
            return;
        }

        player.teleport(location);
        player.sendMessage("§aYou have been teleported to your home §e" + name + "§a.");
    }
}
