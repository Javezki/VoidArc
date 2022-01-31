package com.javezki.LifeFaction;

import com.javezki.PluginLib.Namespaces;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class LifeChestplate extends LifeArmour {


    public ItemStack getItem() {
        ItemStack lifeChestplate = new ItemStack(Material.IRON_CHESTPLATE);

        ItemMeta meta = lifeChestplate.getItemMeta();

        meta.displayName(setDisplayName("Fragmented Life Chestplate"));

        meta.lore(setLore());

        setMetaData(meta);

        PersistentDataContainer pContainer = meta.getPersistentDataContainer();

        pContainer.set(LifeKeys.fLifeChestplate(), PersistentDataType.STRING, FLifeEnum.fLifeChesplate.name());

        lifeChestplate.setItemMeta(meta);
        
        return lifeChestplate;
    }
}
