package com.javezki.Armour;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class CorruptedArmour {
    

    public ItemStack ironCorruptedHelmet()  
    {
        ItemStack iCHelmet = new ItemStack(Material.IRON_HELMET);

        ItemMeta meta = iCHelmet.getItemMeta();

        PersistentDataContainer pContainer = meta.getPersistentDataContainer();

        setDisplayName(meta, "Corrupted Iron Helmet");

        return iCHelmet;
    }

    public void setDisplayName(ItemMeta meta, String display)
    {
        meta.displayName(Component.text(display, TextColor.color(255, 0, 255))
        .decoration(TextDecoration.ITALIC, false)
        );
    }

    public void setPersistence()    
    {
        
    }
}
