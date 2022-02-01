package com.javezki.LifeFaction.LifeUtil;

import java.util.ArrayList;
import java.util.List;

import com.javezki.PluginLib.Namespaces;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Nullable;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class FLifeTomb {
    
    public ItemStack getItem()
    {
        ItemStack tomb = new ItemStack(Material.DANDELION);

        ItemMeta meta = tomb.getItemMeta();

        meta.displayName(setDisplayName("Fragmented Tomb of Life"));

        meta.lore(setLore());

        meta.addEnchant(Enchantment.DURABILITY, 10, true);

        setPersistent(meta);

        tomb.setItemMeta(meta);

        return tomb;
        }

    private void setPersistent(ItemMeta meta) {

        PersistentDataContainer pContainer = meta.getPersistentDataContainer();

        pContainer.set(Namespaces.lifeTomb(), PersistentDataType.STRING, "fLifeTomb");

        pContainer.set(Namespaces.isNotVanillaItem(), PersistentDataType.INTEGER, 1);

    }

    private @Nullable List<Component> setLore() {

        Component space = Component.text("");
        
        Component line1 = Component.text("A tomb that grants the user the", TextColor.color(255, 255, 255))
        .decoration(TextDecoration.ITALIC, false);

        Component line2 = Component.text("knowledge to create life items. However,", TextColor.color(255, 255, 255))
        .decoration(TextDecoration.ITALIC, false);

        Component line3 = Component.text("due to it be a fragment, only a fraction of ", TextColor.color(255, 255, 255))
        .decoration(TextDecoration.ITALIC, false);

        Component line4 = Component.text("its power can be harnessed", TextColor.color(255, 255 ,255))
        .decoration(TextDecoration.ITALIC, false);

        List<Component> lore = new ArrayList<>();

        lore.add(space);
        lore.add(line1);
        lore.add(line2);
        lore.add(line3);
        lore.add(line4);

        return lore;
    }

    private @Nullable Component setDisplayName(String display) {
        return Component.text(display, TextColor.color(0, 255, 0))
        .decoration(TextDecoration.ITALIC, false);
    }
}
