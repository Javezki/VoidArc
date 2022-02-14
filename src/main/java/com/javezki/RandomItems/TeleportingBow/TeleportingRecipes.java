package com.javezki.RandomItems.TeleportingBow;

import com.javezki.Materials.EnderShard;
import com.javezki.PluginLib.Namespaces;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class TeleportingRecipes {
    
    public Recipe teleportBowRecipe()
    {   
        ItemStack bow = new TeleportBow().getTeleportBow();
        ShapedRecipe recipe = new ShapedRecipe(Namespaces.cTeleportBow(), bow);
        recipe.shape("sps", "pbp", "sps");
        recipe.setIngredient('s', new EnderShard().getEnderShard());
        recipe.setIngredient('p', Material.ENDER_PEARL);
        recipe.setIngredient('b', Material.BOW);

        return recipe;
    }

    public Recipe teleportArrowRecipe()  
    {
        ItemStack arrows = new TeleportArrow().getTeleportArrow();
        arrows.setAmount(9);
        ShapedRecipe recipe = new ShapedRecipe(Namespaces.cTeleportArrow(), arrows);
        recipe.shape("aaa", "asa", "aaa");
        recipe.setIngredient('a', Material.ARROW);
        recipe.setIngredient('s', new EnderShard().getEnderShard());

        return recipe;
    }

    public Recipe enderShard()
    {
        ItemStack shards = new EnderShard().getEnderShard();
        shards.setAmount(9);
        ShapelessRecipe recipe = new ShapelessRecipe(Namespaces.cTeleportShard(), shards);
        recipe.addIngredient(1, Material.ENDER_PEARL);
        return recipe;
    }
}
