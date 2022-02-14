package com.javezki.RandomItems.TeleportingBow;

import java.util.ArrayList;
import java.util.List;

import com.javezki.PluginLib.Namespaces;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class TeleportBow {

 

    public ItemStack getTeleportBow()    {

        ItemStack teleportBow = new ItemStack(Material.BOW);

        ItemMeta meta = teleportBow.getItemMeta();

        specialProperties(meta);
        persistentContainer(meta);

        meta.setUnbreakable(true);        

        teleportBow.setItemMeta(meta);

        return teleportBow;
    }

    private void specialProperties(ItemMeta meta) 
    {
        setDisplayName(meta);
        setLore(meta);
    }
    
    private void setDisplayName(ItemMeta meta) 
    {
        
        final TextComponent displayName = Component.text("Fragmented Teleporting Bow", TextColor.color(255, 0, 255))
        .decoration(TextDecoration.ITALIC, false);

        meta.displayName(displayName);
    }

    public void setLore(ItemMeta meta)   
    {
        List<Component> lore = new ArrayList<>();

        Component line1 = Component.text("The mysterious powers of this bow allow", TextColor.color(255, 255, 255))
        .decoration(TextDecoration.ITALIC, false);
        Component line2 = Component.text("the wielder to harness the power of the", TextColor.color(255, 255, 255))
        .decoration(TextDecoration.ITALIC, false)
        .append(Component.text(" VOID", TextColor.color(255, 0, 255)));
        Component line3 = Component.text("however, the bow has a large rebound effect on", TextColor.color(255, 255, 255))
        .decoration(TextDecoration.ITALIC, false);
        Component line4 = Component.text("the user due to its ", TextColor.color(255, 255 ,255))
        .append(Component.text("fragmented ", TextColor.color(255, 0 , 255)))
        .append(Component.text("nature", TextColor.color(255, 255, 255)))
        .decoration(TextDecoration.ITALIC, false);
  

        lore.add(line1);
        lore.add(line2);
        lore.add(line3);
        lore.add(line4);
        meta.lore(lore);
    }

    public enum teleportBow{
        cTeleportBow;
    }

    private void persistentContainer(ItemMeta meta)  
    {
        PersistentDataContainer pContainer = meta.getPersistentDataContainer();

        pContainer.set(Namespaces.cTeleportBow(),
            PersistentDataType.STRING, 
            teleportBow.cTeleportBow.name()
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
