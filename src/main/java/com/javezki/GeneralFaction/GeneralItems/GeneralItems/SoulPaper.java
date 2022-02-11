package com.javezki.GeneralFaction.GeneralItems.GeneralItems;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SoulPaper extends GeneralItems {
    
    public ItemStack getItem()
    {
        ItemStack soul = new ItemStack(Material.PAPER);

        ItemMeta meta = soul.getItemMeta();

        meta.displayName(displayName("Soul Paper"));

        String[] lore = new String[]
        {
            "A fragment of someone's soul.",
            "",
            "Nathan was too busy to make cool lore for this",
            "so deal with it"
        };

        meta.lore(lore(lore));

        setPersistentData(meta);

        soul.setItemMeta(meta);

        return soul;
    }
}
