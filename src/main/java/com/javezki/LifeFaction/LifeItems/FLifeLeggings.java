package com.javezki.LifeFaction.LifeItems;


import com.javezki.LifeFaction.LifeUtil.FLifeEnum;
import com.javezki.LifeFaction.LifeUtil.LifeKeys;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;


public class FLifeLeggings extends LifeArmour {
    
    public ItemStack getItem()
    {
        ItemStack lifeLeggings = new ItemStack(Material.IRON_LEGGINGS);

        ItemMeta meta = lifeLeggings.getItemMeta();

        meta.displayName(setDisplayName("Fragmented Life Leggings"));

        setMetaData(meta);

        PersistentDataContainer pContainer = meta.getPersistentDataContainer();

        pContainer.set(LifeKeys.fLifeLeggings(), PersistentDataType.STRING, FLifeEnum.fLifeLeggings.name());

        meta.lore(setLore());

        lifeLeggings.setItemMeta(meta);
        
        return lifeLeggings;
    }
}
