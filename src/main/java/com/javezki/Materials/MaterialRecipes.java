package com.javezki.Materials;

import com.javezki.VoidMain;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

public class MaterialRecipes {

    NamespacedKey key = new NamespacedKey(VoidMain.getMain(), "MaterialRecipes");

    public Recipe compressedBlazeRod()
    {
        ShapedRecipe recipe = new ShapedRecipe(key, new CompressedBlazeRod().getItem());

        recipe.shape("bbb", "bbb", "bbb");

        recipe.setIngredient('b', Material.BLAZE_ROD);

        return recipe;
    }
}
