package com.javezki.Factions.LifeFaction.LifeMaterials;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MagicalOak extends LifeMaterial{

    public ItemStack getItem()  {

        ItemStack oakSapling = new ItemStack(Material.OAK_SAPLING);

        ItemMeta meta = oakSapling.getItemMeta();

        meta.displayName(displayName("Magical Oak Sapling"));

        String[] lore = new String[]
        {
            "The Magical Oak Sapling is an ",
            "essential item for many life items",
            "but crucially the Life Tomb"
        };

        meta.lore(lore(lore));

        setPersistentData(meta);

        oakSapling.setItemMeta(meta);

        return oakSapling;
    }
}
