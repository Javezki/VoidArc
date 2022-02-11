package com.javezki.Attributes;

import java.util.List;

import com.javezki.VoidMain;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

public class DamageIncrease extends Attributes{

    private NamespacedKey key = new NamespacedKey(VoidMain.getMain(), damageIncrease.DAMAGE_BOOST.name());
    
    public final Component addAttribute(int modifier)
    {
        return createAttribute("Damage Increase", modifier);
    }

    public enum damageIncrease
    {
        DAMAGE_BOOST
    }

    public final void setPersistentData(ItemMeta meta, int modifier)
    {
        
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, modifier);
    }

    public final void damageBoost(EntityDamageByEntityEvent ev)
    {
        if (!(ev.getDamager() instanceof Player))   return;

        Player p = (Player) ev.getDamager();

        if (p.getInventory().getItemInMainHand().getItemMeta() == null) return;

        List<Component> lore = p.getInventory().getItemInMainHand().getItemMeta().lore();

        if (lore == null)   return;

        Integer modifier = p.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer()
        .get(key, PersistentDataType.INTEGER);

        if (modifier == null) return;

        for (Component line : lore)
        {
            if (!(getPlainText(line).contains("Damage Increase"))) continue;

            ev.setDamage(ev.getDamage() + modifier);
        }
    }

    private final String getPlainText(Component comp)
    {
        return PlainTextComponentSerializer.plainText().serialize(comp);
    }

}
