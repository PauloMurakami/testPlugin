package org.example.dev.testplugin.objects;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class SpecialPickaxe {
    private final ItemStack item;
    private final UUID uniqueId;
    private static final Map<UUID, ItemStack> itensStackReference = new HashMap<>();

    public static final int BLOCKS_PER_UPGRADE = 100;

    public SpecialPickaxe() {
        this.uniqueId = UUID.randomUUID();
        this.item = createSpecialPickaxe();
        itensStackReference.put(this.uniqueId, item);
    }

    private ItemStack createSpecialPickaxe() {
        ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta meta = pickaxe.getItemMeta();

        if (meta != null) {
            meta.setDisplayName("§6Picareta Especial");
            List<String> lore = new ArrayList<>();
            lore.add("§7Esta é uma picareta muito especial");
            lore.add("§7com encantamentos poderosos.");
            lore.add("§7Blocos minerados: 0");

            meta.setLore(lore);
            pickaxe.setItemMeta(meta);
        }

        pickaxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, 10);
        pickaxe.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        pickaxe.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 1);

        // Adiciona o UUID ao NBT do item
        NBTItem nbti = new NBTItem(pickaxe);
        nbti.setString("uniqueId",uniqueId.toString());
        nbti.setInteger("blockCounts",0);

        return nbti.getItem();
    }

    public static void upgradeRandomEnchantment(ItemStack pickaxe) {
        List<Enchantment> validEnchantments = Arrays.asList(
                Enchantment.DIG_SPEED,
                Enchantment.DURABILITY,
                Enchantment.LOOT_BONUS_BLOCKS
        );

        Random random = new Random();
        Enchantment randomEnchantment = validEnchantments.get(random.nextInt(validEnchantments.size()));
        ItemMeta meta = pickaxe.getItemMeta();

        if (meta != null) {
            int currentLevel = pickaxe.getEnchantmentLevel(randomEnchantment);
            meta.addEnchant(randomEnchantment, currentLevel + 1, true);
            pickaxe.setItemMeta(meta);
        }
    }

    public ItemStack getItem() {
        return item;
    }

    public static UUID getUniqueId(ItemStack item) {
        if (item != null) {
            NBTItem nbti = new NBTItem(item);
            String uuidString = nbti.getString("uniqueId");
            if (!uuidString.isEmpty()) {
                return UUID.fromString(uuidString);
            }
        }
        return null;
    }

}
