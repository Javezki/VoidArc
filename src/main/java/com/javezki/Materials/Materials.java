package com.javezki.Materials;

import java.util.ArrayList;
import java.util.List;

import com.javezki.PluginLib.Namespaces;
import com.javezki.Workstations.WorkstationKeys;

import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import net.kyori.adventure.bossbar.BossBar.Color;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public abstract class Materials {

    protected final Component displayName(String displayName) {
        Component name = Component.text(displayName, TextColor.color(217, 217, 217))
                .decoration(TextDecoration.ITALIC, false);

        return name;
    }

    protected final List<Component> lore(String[] rawLore)
    {
        List<Component> lore = new ArrayList<>();

        for (String line : rawLore)
        {
            lore.add(Component.text(line, TextColor.color(217, 217, 217))
            .decoration(TextDecoration.ITALIC, false));
        }

        return lore;
    }

    protected final void setPersistent(ItemMeta meta)
    {
        meta.getPersistentDataContainer().set(Namespaces.isNotCraftingMaterial(), PersistentDataType.INTEGER, 2);

        meta.getPersistentDataContainer().set(Namespaces.isUnrepairable(), PersistentDataType.INTEGER, 1);
    }
}
