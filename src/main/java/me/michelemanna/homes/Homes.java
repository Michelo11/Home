package me.michelemanna.homes;

import me.michelemanna.homes.commands.HomesCommand;
import me.michelemanna.homes.commands.SetHomeCommand;
import me.michelemanna.homes.managers.DatabaseManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Homes extends JavaPlugin {

    private DatabaseManager database;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        getCommand("homes").setExecutor(new HomesCommand(this));
        getCommand("sethome").setExecutor(new SetHomeCommand(this));

        try {
            database = new DatabaseManager(this.getConfig().getString("database"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
    }

    public DatabaseManager getDatabase() {
        return database;
    }
}