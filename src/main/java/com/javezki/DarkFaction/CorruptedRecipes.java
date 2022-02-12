package com.javezki.DarkFaction;

import com.javezki.VoidMain;
import com.javezki.DarkFaction.CorruptedItems.CorruptedItem;
import com.javezki.DarkFaction.CorruptedItems.CorruptedPower;
import com.javezki.Materials.CompressedBlazeRod;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

public class CorruptedRecipes {
    
    NamespacedKey key = new NamespacedKey(VoidMain.getMain(), "CorruptedRecipes");

    public Recipe corruptedWand()
    {
        ShapedRecipe recipe = new ShapedRecipe(key, new CorruptedPower().getItem());

        recipe.shape(" rb", "ibr","bi " );

        recipe.setIngredient('b', new CompressedBlazeRod().getItem());

        recipe.setIngredient('r', Material.ROTTEN_FLESH);

        recipe.setIngredient('i', Material.IRON_INGOT);

        return recipe;
    }
}
