package com.javezki.Factions.LifeFaction.LifeItems;


import com.javezki.Factions.LifeFaction.LifeUtil.FLifeEnum;
import com.javezki.Factions.LifeFaction.LifeUtil.LifeKeys;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;


public class FLifeLeggings extends LifeItems {
    
    public ItemStack getItem()
    {
        ItemStack lifeLeggings = new ItemStack(Material.IRON_LEGGINGS);

        ItemMeta meta = lifeLeggings.getItemMeta();

        meta.displayName(setDisplayName("Fragmented Life Leggings"));

        setMetaData(meta);

        PersistentDataContainer pContainer = meta.getPersistentDataContainer();

        pContainer.set(LifeKeys.fLifeLeggings(), PersistentDataType.STRING, FLifeEnum.fLifeLeggings.name());

        meta.lore(setArmourLore());

        lifeLeggings.setItemMeta(meta);
        
        return lifeLeggings;
    }
}
