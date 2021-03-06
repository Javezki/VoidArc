package com.javezki.Factions.LifeFaction.LifeItems;

import com.javezki.Factions.LifeFaction.LifeUtil.FLifeEnum;
import com.javezki.Factions.LifeFaction.LifeUtil.LifeKeys;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class FLifeChestplate extends LifeItems {


    public ItemStack getItem() {
        ItemStack lifeChestplate = new ItemStack(Material.IRON_CHESTPLATE);

        ItemMeta meta = lifeChestplate.getItemMeta();

        meta.displayName(setDisplayName("Fragmented Life Chestplate"));

        meta.lore(setArmourLore());

        setMetaData(meta);

        PersistentDataContainer pContainer = meta.getPersistentDataContainer();

        pContainer.set(LifeKeys.fLifeChestplate(), PersistentDataType.STRING, FLifeEnum.fLifeChesplate.name());

        lifeChestplate.setItemMeta(meta);
        
        return lifeChestplate;
    }
}
