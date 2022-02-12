package com.javezki.Materials;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CompressedBlazeRod extends Materials{
    

    public final ItemStack getItem()
    {
        ItemStack blazerod = new ItemStack(Material.BLAZE_ROD);

        ItemMeta meta = blazerod.getItemMeta();

        meta.displayName(displayName("Compressed Blaze Rod (x9)"));

        String[] lore = new String[]
        {
            "The power of the nether, is compressed",
            "and refined into a greater catalyst",
            "A great way to store any sort of magical power",
            "and is essential for a lot of magical items"
        };

        meta.lore(lore(lore));

        blazerod.setItemMeta(meta);

        setPersistent(meta);

        return blazerod;
    } 
}
