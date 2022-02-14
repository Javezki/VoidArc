package com.javezki.Factions.LifeFaction.LifeMaterials;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SweetDandelion extends LifeMaterial{


    public ItemStack getItem()
    {
        ItemStack dandelion = new ItemStack(Material.DANDELION);

        ItemMeta meta = dandelion.getItemMeta();

        meta.displayName(displayName("Sweet Dandelion"));

        String[] lore = new String[]
        {
            "A very sweet smelling dandelion.",
            "",
            "A key ingredient to create the Life Tomb"
        };

        meta.lore(lore(lore));

        setPersistentData(meta);

        dandelion.setItemMeta(meta);

        return dandelion;
    }
}
