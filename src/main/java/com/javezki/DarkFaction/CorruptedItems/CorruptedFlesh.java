package com.javezki.DarkFaction.CorruptedItems;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CorruptedFlesh extends CorruptedItem {

    public ItemStack getItem() {
        ItemStack flesh = new ItemStack(Material.ROTTEN_FLESH);

        ItemMeta meta = flesh.getItemMeta();

        meta.displayName(displayName("Corrupted Flesh"));

        String[] lore = new String[]
        {
            "Heavily exposed to corrupted energies,",
            "this piece of flesh can be used to create",
            "evil things, but also serve as a balance for the",
            "creation of overpowered life items"
        };

        meta.lore(lore(lore));

        setPersistentData(meta);

        flesh.setItemMeta(meta);

        return flesh;
    }
}
