package com.javezki.PluginLib;


import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class InteractionMetaData {

    public static Integer getIsCraftingMaterial(ItemStack item) {
        try {
            return item.getItemMeta()
                .getPersistentDataContainer()
                .get(Namespaces.isNotCraftingMaterial(), PersistentDataType.INTEGER);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return 0;
        }

    }

    public static Integer getNotRepairable(ItemStack item) {
        PersistentDataContainer pContainer = item.getItemMeta()
                .getPersistentDataContainer();
        try {
            return pContainer.get(Namespaces.isUnrepairable(),
             PersistentDataType.INTEGER);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return 0;
        }

    }

    public static Integer getVanillaItem(ItemStack item) {
        PersistentDataContainer pContainer = item.getItemMeta().getPersistentDataContainer();
        try {
            return pContainer.get(Namespaces.isNotVanillaItem(),
            PersistentDataType.INTEGER);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            return 0;
        }

    }
    public static Integer getEnchantable(ItemStack item) {
        PersistentDataContainer pContainer = item.getItemMeta().getPersistentDataContainer();
        try {
            return pContainer.get(Namespaces.isNotEnchantable(),
            PersistentDataType.INTEGER);
        }   catch (NullPointerException ex) {
            ex.printStackTrace();
            return 0;
        }

    }

}
