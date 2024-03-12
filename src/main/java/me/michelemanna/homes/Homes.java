package me.michelemanna.homes;

import me.michelemanna.homes.commands.GetHomesItemCommand;
import me.michelemanna.homes.commands.HomesCommand;
import me.michelemanna.homes.commands.SetHomeCommand;
import me.michelemanna.homes.listeners.PlayerListener;
import me.michelemanna.homes.managers.DatabaseManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Homes extends JavaPlugin {
    private static Homes instance;

    private DatabaseManager database;

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        getCommand("homes").setExecutor(new HomesCommand(this));
        getCommand("sethome").setExecutor(new SetHomeCommand(this));
        getCommand("gethomesitem").setExecutor(new GetHomesItemCommand());
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);

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

    public static Homes getInstance() {
        return instance;
    }
}