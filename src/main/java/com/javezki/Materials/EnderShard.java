package com.javezki.Materials;

import java.util.ArrayList;
import java.util.List;

import com.javezki.PluginLib.Namespaces;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class EnderShard {
    

    public ItemStack getEnderShard()    
    {
        ItemStack enderShard = new ItemStack(Material.GOLD_NUGGET);

        ItemMeta meta = enderShard.getItemMeta();
        
        setPersistentData(meta);

        setDisplayName(meta, "Ender Shard");

        setLore(meta);

        enderShard.setItemMeta(meta);

        return enderShard;
    }

    private void setLore(ItemMeta meta)
    {
        Component lore1 = Component.text("An essential crafting component for all ", TextColor.color(211, 211 ,211))
        .decoration(TextDecoration.ITALIC, false);
        Component lore2 = Component.text("VOID ", TextColor.color(255, 0, 255))
        .append(Component.text("related items", TextColor.color(211, 211, 211)))
        .decoration(TextDecoration.ITALIC, false);

        List<Component> lore = new ArrayList<>();

        lore.add(lore1);

        lore.add(lore2);

        meta.lore(lore);
    }

    private void setDisplayName(ItemMeta meta, String str)
    {
        Component name = Component.text(str, TextColor.color(255, 0, 255))
        .decoration(TextDecoration.ITALIC, false);

        meta.displayName(name);
    }

    private void setPersistentData(ItemMeta meta)
    {
        PersistentDataContainer pContainer = meta.getPersistentDataContainer();

        pContainer.set(
            Namespaces.teleport(), 
            PersistentDataType.STRING,
            "teleportShard"
            );

        pContainer.set(
            Namespaces.isNotVanillaItem(), 
            PersistentDataType.INTEGER,
            1
            );
        
        pContainer.set (
            Namespaces.isUnrepairable(), 
            PersistentDataType.INTEGER,
            1
            );
            
        pContainer.set (
            Namespaces.isNotCraftingMaterial(),
            PersistentDataType.INTEGER, 
            2
            );
    }

}
