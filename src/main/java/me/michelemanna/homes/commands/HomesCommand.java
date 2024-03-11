package me.michelemanna.homes.commands;

import me.michelemanna.homes.Homes;
import me.michelemanna.homes.gui.HomeItem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.xenondevs.invui.gui.PagedGui;
import xyz.xenondevs.invui.gui.structure.Markers;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.window.Window;

import java.sql.SQLException;
import java.util.Map;
import java.util.stream.Collectors;

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

        Map<String, Location> homes = null;

        try {
            homes = plugin.getDatabase().getHomes(player);
        } catch (SQLException e) {
            player.sendMessage("An error occurred while getting your homes!");
            return true;
        }

        if (homes.isEmpty()) {
            player.sendMessage("You don't have any homes set!");
            return true;
        }

        PagedGui gui = PagedGui.items()
                .setStructure(
                        "# # # # # # # # #",
                        "# . . . . . . . #",
                        "# . . . . . . . #",
                        "# # # # # # # # #"
                )
                .setContent(homes.entrySet().stream()
                        .map(home -> new HomeItem(plugin, home.getKey(), home.getValue()))
                        .collect(Collectors.toList()))
                .addIngredient('#', new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setDisplayName(""))
                .addIngredient('.', Markers.CONTENT_LIST_SLOT_HORIZONTAL).build();

        Window window = Window.single()
                .setViewer(player)
                .setTitle("Homes")
                .setGui(gui)
                .build();

        window.open();

        return true;
    }
}