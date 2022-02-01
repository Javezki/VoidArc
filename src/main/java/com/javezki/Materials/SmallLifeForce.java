package com.javezki.Materials;

import java.util.ArrayList;
import java.util.List;

import com.javezki.LifeFaction.LifeUtil.FLifeEnum;
import com.javezki.LifeFaction.LifeUtil.LifeKeys;
import com.javezki.PluginLib.Namespaces;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Nullable;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class SmallLifeForce {
    
    public ItemStack getItem()
    {
        ItemStack tinyLifeShard = new ItemStack(Material.LIME_DYE);

        ItemMeta meta = tinyLifeShard.getItemMeta();

        meta.displayName(setDisplayName("Small Life Force"));

        meta.lore(setLore());

        setPersistent(meta);

        tinyLifeShard.setItemMeta(meta);

        return tinyLifeShard;
    }

    private @Nullable List<Component> setLore() {
        Component line1 = Component.text("Harness the power of ", TextColor.color(255, 255, 255))
        .append(Component.text("Life", TextColor.color(0, 255, 0)))
        .append(Component.text(" and gain", TextColor.color(255, 255, 255)))
        .decoration(TextDecoration.ITALIC, false);

        Component line2 = Component.text("unimaginable ", TextColor.color(255, 255, 255))
        .append(Component.text("POWER", TextColor.color(148, 0, 211)))
        .decoration(TextDecoration.ITALIC, false);

        List<Component> lore = new ArrayList<>();

        lore.add(line1);
        lore.add(line2);

        return lore;
    }

    private @Nullable Component setDisplayName(String display) {
        return Component.text(display, TextColor.color(0, 255, 0))
        .decoration(TextDecoration.ITALIC, false);
    }

    private void setPersistent(ItemMeta meta) {

        PersistentDataContainer pContainer = meta.getPersistentDataContainer();

        pContainer.set(LifeKeys.fSmallLifeShard(), PersistentDataType.STRING, FLifeEnum.fLifeSmallForce.name());

        pContainer.set(Namespaces.isNotCraftingMaterial(), PersistentDataType.INTEGER, 2);

        pContainer.set(Namespaces.isNotVanillaItem(), PersistentDataType.INTEGER, 1);
    }
}
