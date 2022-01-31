package com.javezki.TeleportingBow;

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

public class TeleportArrow {
    
    public ItemStack getTeleportArrow() 
    {
        ItemStack arrow = new ItemStack(Material.ARROW);

        ItemMeta meta = arrow.getItemMeta();
        
        setLore(meta);

        setDisplayName(meta);

        persistentContainer(meta);

        arrow.setItemMeta(meta);

        return arrow;
    }

    //Set the display name
    private void setDisplayName(ItemMeta meta)  
    {
        meta.displayName(Component.text("Void Arorw", TextColor.color(255, 0, 255))
            .decoration(TextDecoration.ITALIC, false)
            );
    }

    //Set lore using components exdee
    private void setLore(ItemMeta meta)
    {

        List<Component> lore = new ArrayList<>();

        Component line1 = Component.text("An arrow that contains the power of the ", TextColor.color(255, 255, 255))
        .decoration(TextDecoration.ITALIC, false)
        .append(Component.text("VOID ", TextColor.color(255, 0, 255)));

        Component line2 = Component.text("and serves as a catalyst for the ", TextColor.color(255, 255, 255))
        .decoration(TextDecoration.ITALIC, false)
        .append(Component.text("Teleporting Bow's", TextColor.color(255, 0, 255)));

        lore.add(line1);
        lore.add(line2);

        meta.lore(lore);
    }


    
    private void persistentContainer(ItemMeta meta)   
    {
        PersistentDataContainer pContainer = meta.getPersistentDataContainer();

        pContainer.set(Namespaces.teleport(),
            PersistentDataType.STRING,
            "teleportArrow"
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
            1
        );

        pContainer.set(
            Namespaces.isNotEnchantable(), 
            PersistentDataType.INTEGER, 
            1
        );

    }
}
