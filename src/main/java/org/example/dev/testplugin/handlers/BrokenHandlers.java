package org.example.dev.testplugin.handlers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.example.dev.testplugin.TestPlugin;
import org.bukkit.block.Block;
import org.example.dev.testplugin.objects.SpecialPickaxe;

import java.util.*;

public class BrokenHandlers implements Listener {


    public BrokenHandlers(TestPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

//    @EventHandler(priority = EventPriority.HIGHEST)
//    public void onTorchPlace(BlockPlaceEvent event){
//        Block block = event.getBlock();
//        String mensagem = "";
//        if(block.getType() != Material.TORCH) {
//            return;
//        }
//        Bukkit.getLogger().info(mensagem);
//
//    }

//    @EventHandler(priority = EventPriority.HIGHEST)
//    public void onTorchBreak(BlockBreakEvent event){
//        Block block = event.getBlock();
////        Player player = event.getPlayer();
//        String mensagem = "";
//        if(block.getType() != Material.TORCH) {
//            return;
//        }
//        Bukkit.getLogger().info(mensagem);
//    }
//    @EventHandler(priority = EventPriority.HIGHEST)
//    public void onBreakAllBlocks(BlockBreakEvent event){
//        Block block = event.getBlock();
//        Player player = event.getPlayer();
//        if (player != null && player.getEquipment() != null) {
//            ItemStack itemInMainHand = player.getEquipment().getItemInHand();
//            if (itemInMainHand != null) {
//                Bukkit.getLogger().info(itemInMainHand.toString());
//            } else {
//                Bukkit.getLogger().warning("O item na mão principal é nulo.");
//            }
//        } else {
//            Bukkit.getLogger().warning("O equipamento do jogador é nulo.");
//        }
//    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInHand();
        if(item.getType() == Material.DIAMOND_PICKAXE){
            UUID itemId = SpecialPickaxe.getUniqueId(item);
            ItemMeta meta = item.getItemMeta();
            List<String> lore = meta.getLore();
            if (lore != null && lore.size() > 2) {
                String blockCountLine = lore.get(2);
                if (blockCountLine.startsWith("§7Blocos minerados: ")) {
                    int blockCount = Integer.parseInt(blockCountLine.split(": ")[1]);
                    blockCount++;
                    lore.set(2, "§7Blocos minerados: " + blockCount);
                    meta.setLore(lore);
                    item.setItemMeta(meta);
                    if (blockCount % SpecialPickaxe.BLOCKS_PER_UPGRADE == 0) {
                        SpecialPickaxe.upgradeRandomEnchantment(item);
                        player.sendMessage("Sua picareta foi aprimorada!");
                    }
                }
            }
        }
    }
}
