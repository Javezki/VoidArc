package com.javezki.LifeFaction;

import com.javezki.PluginLib.Namespaces;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class LifeBoots extends LifeArmour{
    
    public ItemStack getItem()  {

        ItemStack lifeBoots = new ItemStack(Material.IRON_BOOTS);

        ItemMeta meta = lifeBoots.getItemMeta();

        meta.displayName(setDisplayName("Fragmented Life Boots"));

        setMetaData(meta);

        PersistentDataContainer pContainer = meta.getPersistentDataContainer();

        pContainer.set(LifeKeys.fLifeBoots(), PersistentDataType.STRING, FLifeEnum.fLifeBoots.name());

        meta.lore(setLore());

        lifeBoots.setItemMeta(meta);

        return lifeBoots;
    }
}
