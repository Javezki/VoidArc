package com.javezki.LifeFaction.LifeItems;

import java.util.ArrayList;
import java.util.List;

import com.javezki.PluginLib.Namespaces;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class LifeArmour {
    
    public void setMetaData(ItemMeta meta) {
        
        PersistentDataContainer pContainer = meta.getPersistentDataContainer();
        
        meta.addEnchant(Enchantment.THORNS, 3, false);

        meta.setUnbreakable(true);

        pContainer.set(Namespaces.isNotCraftingMaterial(), PersistentDataType.INTEGER, 1);

        pContainer.set(Namespaces.isNotVanillaItem(), PersistentDataType.INTEGER, 1);

        pContainer.set(Namespaces.isNotEnchantable(), PersistentDataType.INTEGER, 0);

        pContainer.set(Namespaces.isUnrepairable(), PersistentDataType.INTEGER, 2);

    }

    public Component setDisplayName(String display) {
        return Component.text(display, TextColor.color(0, 255, 0))
        .decoration(TextDecoration.ITALIC, false);
    }

    public List<Component> setLore()
    {
        Component line1 = Component.text("A piece of armour that is crafted with a shallow ", TextColor.color(255, 255, 255))
        .decoration(TextDecoration.ITALIC, false);

        Component line2 = Component.text("understanding of the beginning of Life", TextColor.color(255, 255, 255))
        .decoration(TextDecoration.ITALIC, false);

        Component line3 = Component.text("");

        List<Component> lore = new ArrayList<>();

        lore.add(line3);
        lore.add(line1);
        lore.add(line2);


        return lore;
    }

    public ItemStack[] getLifeArmour()
    {
        ItemStack[] lifeArmour = new ItemStack[]
        {
            new FLifeHelmet().getItem(),
            new FLifeChestplate().getItem(),
            new FLifeLeggings().getItem(),
            new FLifeBoots().getItem()
        };

        return lifeArmour;  
    }


}
