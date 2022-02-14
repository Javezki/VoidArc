package com.javezki.OwnerGear;

import java.util.ArrayList;
import java.util.List;

import com.javezki.VoidMain;
import com.javezki.LifeFaction.LifeItems.FLifeSword;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class Excalibur {

    private static Excalibur excaliburInstance = new Excalibur();

    private Excalibur(){}

    public static Excalibur getInstace() {
        return excaliburInstance;
    }

    public final ItemStack getItem()
    {
        ItemStack excalibur = new ItemStack(Material.NETHERITE_SWORD);

        ItemMeta meta = excalibur.getItemMeta();

        meta.displayName(displayName("Excalibur"));

        List<Component> lore = new ArrayList<>();

        lore.add(Component.text("The legendary excalibur is the only way", TextColor.color(255, 218, 0))
        .decoration(TextDecoration.ITALIC, false));

        lore.add(Component.text("to end it once and for all...", TextColor.color(255, 218, 0))
        .decoration(TextDecoration.ITALIC, false));

        meta.lore(lore);

        meta.addEnchant(Enchantment.DAMAGE_ALL, 9, true);

        meta.setUnbreakable(true);

        meta.addEnchant(Enchantment.DURABILITY, 9, true);
        
        meta.addEnchant(Enchantment.FIRE_ASPECT, 4, true);

        excalibur.setItemMeta(meta);

        return excalibur;
    }

    private final Component displayName(String str)
    {
        return Component.text(str, TextColor.color(255, 215, 0))
        .decoration(TextDecoration.ITALIC, false)
        .decoration(TextDecoration.BOLD, true);
    }

    public final Recipe getRecipe()
    {
        NamespacedKey key = new NamespacedKey(VoidMain.getMain(), "ExcaliburRecipe");
        ShapedRecipe recipe = new ShapedRecipe(key, getItem());

        recipe.shape(" l ", "lel", " l ");

        recipe.setIngredient('l', new FLifeSword().getItem());

        recipe.setIngredient('e', Material.DRAGON_EGG);

        return recipe;
    }

}
