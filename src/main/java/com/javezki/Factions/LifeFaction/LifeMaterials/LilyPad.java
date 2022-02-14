package com.javezki.Factions.LifeFaction.LifeMaterials;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LilyPad extends LifeMaterial {

    public ItemStack getItem() {
        ItemStack lilypad = new ItemStack(Material.LILY_PAD);

        ItemMeta meta = lilypad.getItemMeta();

        meta.displayName(displayName("Lovely Lily"));

        String[] lore = new String[] {
                "The Lovely Lily is a common ingredient brewed from",
                "research. The lily has a very attractive appeal to it",
                "even though it is just a flower.",
                "Neede for crafting life tomb."
        };

        meta.lore(lore(lore));

        setPersistentData(meta);

        lilypad.setItemMeta(meta);

        return lilypad;
    }
}
