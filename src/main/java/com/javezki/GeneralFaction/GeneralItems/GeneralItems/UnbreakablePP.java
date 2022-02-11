package com.javezki.GeneralFaction.GeneralItems.GeneralItems;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class UnbreakablePP extends GeneralItems{
    

    public final ItemStack getItem()
    {
        ItemStack pp = new ItemStack(Material.WOODEN_SWORD);

        ItemMeta meta = pp.getItemMeta();

        meta.displayName(displayName("Unbreakable PP"));

        String[] lore = new String[]
        {
            "The unbreakable PP requested by PP Kingdom",
            "Its durability stands for the longevity of",
            "PP Kingdom and its allies.",
            "haha pp go brrr",
            "",
            "100 dura cuz why not lmfao"
        };

        meta.lore(lore(lore));

        setPersistentData(meta);

        meta.addEnchant(Enchantment.DURABILITY, 100, true);

        meta.setUnbreakable(true);

        pp.setItemMeta(meta);

        return pp;
    }
}
