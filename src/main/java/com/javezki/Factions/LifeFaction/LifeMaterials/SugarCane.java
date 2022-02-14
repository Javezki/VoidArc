package com.javezki.Factions.LifeFaction.LifeMaterials;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SugarCane extends LifeMaterial{
    
    public ItemStack getItem()
    {
        ItemStack sugarCane = new ItemStack(Material.SUGAR_CANE);

        ItemMeta meta = sugarCane.getItemMeta();

        meta.displayName(displayName("Glowing Sugar Cane"));

        String[] lore = new String[]
        {
            "The Glowing Sugar Cane has magical properties.",
            "One say they can be used to heal any poison ",
            "and wound",
            "Important ingredient for the Life Tomb."
        };

        meta.lore(lore(lore));

        setPersistentData(meta);

        sugarCane.setItemMeta(meta);

        return sugarCane;
    }
}
