package com.javezki.LifeFaction;


import com.javezki.PluginLib.Namespaces;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class LifeHelmet extends LifeArmour   {

    public ItemStack getItem() {
        ItemStack lifeHelmet = new ItemStack(Material.IRON_HELMET);

        ItemMeta meta = lifeHelmet.getItemMeta();

        meta.displayName(setDisplayName("Corrupted Life Helmet"));

        meta.lore(setLore());

        setMetaData(meta);

        PersistentDataContainer pContainer = meta.getPersistentDataContainer();

        pContainer.set(LifeKeys.fLifeHelmet(), PersistentDataType.STRING, FLifeEnum.fLifeHelmet.name());

        lifeHelmet.setItemMeta(meta);

        return lifeHelmet;
    }
}
