package org.example.dev.testplugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example.dev.testplugin.objects.SpecialPickaxe;

public class SpecialPickaxeCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player player = (Player) commandSender;
            SpecialPickaxe specialPickaxe = new SpecialPickaxe();
            player.getInventory().addItem(specialPickaxe.getItem());
        }
        return true;
    }
}
