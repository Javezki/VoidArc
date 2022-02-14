package com.javezki.RandomItems.OwnerGear.OwnerArmour;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.javezki.PluginLib.Namespaces;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public abstract class OwnerItem {

    private int duration = Integer.MAX_VALUE;
    
    protected final Component displayName(String name)
    {
        return Component.text(name, TextColor.color(238, 130, 238))
        .decoration(TextDecoration.BOLD, true)
        .decoration(TextDecoration.ITALIC, false);
    }

    protected final List<Component> lore(String[] rawLore)
    {
        List<Component> lore = new ArrayList<>();

        for (String line : rawLore)
        {
            lore.add(
                Component.text(line, TextColor.color(238, 130, 238))
                .decoration(TextDecoration.ITALIC, false)
                .decoration(TextDecoration.BOLD, true)
            );
        }

        return lore;
    }

    protected final void setPersistent(ItemMeta meta)
    {
        PersistentDataContainer container = meta.getPersistentDataContainer();

        container.set(Namespaces.isNotCraftingMaterial(), PersistentDataType.INTEGER, 1);

        container.set(Namespaces.isUnrepairable(), PersistentDataType.INTEGER, 1);

        container.set(Namespaces.isNotCraftingMaterial(), PersistentDataType.INTEGER, 1);
    }

    protected final void addEnchants(ItemMeta meta)
    {
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 20, true);

        meta.addEnchant(Enchantment.DURABILITY, 20, true);

        meta.addEnchant(Enchantment.THORNS, 10, true);
    }

    private final PotionEffect luck()
    {
        return new PotionEffect(PotionEffectType.LUCK, duration, 4);
    }

    private final PotionEffect saturation()
    {
        return new PotionEffect(PotionEffectType.SATURATION, duration, 4);
    }

    private final PotionEffect heroEffect()
    {
        return new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, duration, 9);
    }

    private final PotionEffect glowing()
    {
        return new PotionEffect(PotionEffectType.GLOWING, duration, 0);
    }

    protected final Collection<PotionEffect> bEffects()
    {
        Collection<PotionEffect> effects = new ArrayList<>();

        effects.add(new PotionEffect(PotionEffectType.SPEED, duration, 3));

        effects.add(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, duration, 4));

        effects.add(luck());

        effects.add(saturation());

        effects.add(heroEffect());

        effects.add(glowing());

        return effects;
    }

    protected final Collection<PotionEffect> lEffects()
    {
        Collection<PotionEffect> effects = new ArrayList<>();

        effects.add(luck());

        effects.add(saturation());

        effects.add(heroEffect());

        effects.add(glowing());

        effects.add(new PotionEffect(PotionEffectType.FAST_DIGGING, duration, 4));

        effects.add(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, duration, 3));

        return effects;
    }

    protected final Collection<PotionEffect> cEffects()
    {
        Collection<PotionEffect> effects = new ArrayList<>();

        effects.add(luck());

        effects.add(saturation());

        effects.add(heroEffect());

        effects.add(glowing());

        effects.add(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, duration, 0));

        effects.add(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, duration, 3));

        effects.add(new PotionEffect(PotionEffectType.REGENERATION, duration, 6));

        return effects;
    }

    protected final Collection<PotionEffect> hEffects()
    {
        Collection<PotionEffect> effects = new ArrayList<>();

        effects.add(luck());

        effects.add(saturation());

        effects.add(heroEffect());

        effects.add(glowing());

        effects.add(new PotionEffect(PotionEffectType.HEALTH_BOOST, duration, 14));

        effects.add(new PotionEffect(PotionEffectType.NIGHT_VISION, duration, 0));

        effects.add(new PotionEffect(PotionEffectType.HEAL, 1, 40));

        return effects;
    }


    /*
            p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 3));

        p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 9));

        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 3));

        p.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, Integer.MAX_VALUE, 19));

        p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0));

        p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 3));

        p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 10));

        p.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, 19));

        p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0));

        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 3));

        p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 0));

        p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, Integer.MAX_VALUE, 4));

        p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, 4));

        p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, Integer.MAX_VALUE, 4));

    */
}
