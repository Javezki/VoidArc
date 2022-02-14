package com.javezki.Factions.LifeFaction.LifeMaterials;

import java.util.ArrayList;
import java.util.List;

import com.javezki.VoidMain;
import com.javezki.PluginLib.Namespaces;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class LifeMaterial {

    final static String researchKey = "researchLevel";

    private static NamespacedKey researchLevel = new NamespacedKey(VoidMain.getMain(), researchKey);
    

    public Component displayName(String display)
    {
        return Component.text(display, TextColor.color(255, 255, 0))
        .decoration(TextDecoration.ITALIC, false);
    }

    public List<Component> lore(String[] rawLore)
    {  
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(""));
        for (String rawLoreLine : rawLore)
        {
            lore.add(Component.text(rawLoreLine, TextColor.color(255, 255, 255))
            .decoration(TextDecoration.ITALIC, false));
        }
        return lore;
    }

    public void setPersistentData(ItemMeta meta)  {

        

        PersistentDataContainer pContainer = meta.getPersistentDataContainer();

        pContainer.set(Namespaces.isNotVanillaItem(), PersistentDataType.INTEGER, 1);

        pContainer.set(Namespaces.isNotCraftingMaterial(), PersistentDataType.INTEGER, 2);

        pContainer.set(Namespaces.isUnrepairable(), PersistentDataType.INTEGER, 1);

        pContainer.set(researchLevel, PersistentDataType.INTEGER, 5);
    }

    public static NamespacedKey researchLevel()
    {
        return researchLevel;
    }
}
