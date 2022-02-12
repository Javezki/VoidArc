package com.javezki.LifeFaction.LifeItems;

import com.javezki.LifeFaction.LifeUtil.FLifeEnum;
import com.javezki.LifeFaction.LifeUtil.LifeKeys;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class FLifeBoots extends LifeItems{
    
    public ItemStack getItem()  {

        ItemStack lifeBoots = new ItemStack(Material.IRON_BOOTS);

        ItemMeta meta = lifeBoots.getItemMeta();

        meta.displayName(setDisplayName("Fragmented Life Boots"));

        setMetaData(meta);

        PersistentDataContainer pContainer = meta.getPersistentDataContainer();

        pContainer.set(LifeKeys.fLifeBoots(), PersistentDataType.STRING, FLifeEnum.fLifeBoots.name());

        meta.lore(setArmourLore());

        lifeBoots.setItemMeta(meta);

        return lifeBoots;
    }
}
