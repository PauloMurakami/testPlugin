package org.example.dev.testplugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.dev.testplugin.command.SpecialPickaxeCommands;
import org.example.dev.testplugin.handlers.BrokenHandlers;

public final class TestPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("Hello World plugin do paulin");

        this.getCommand("picareta").setExecutor(new SpecialPickaxeCommands());
        new BrokenHandlers(this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Plugin shutdown");
    }


}
