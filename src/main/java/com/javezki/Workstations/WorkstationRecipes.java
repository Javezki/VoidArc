package com.javezki.Workstations;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

public class WorkstationRecipes {
    
    public Recipe researchTable()
    {
        ShapedRecipe recipe = new ShapedRecipe(WorkstationKeys.researchTable(),  new ResearchTable().getItem());

        recipe.shape("beb", "rtr", "nnn");

        recipe.setIngredient('b', Material.BOOK);

        recipe.setIngredient('e', Material.ENDER_PEARL);

        recipe.setIngredient('r', Material.BLAZE_ROD);

        recipe.setIngredient('n', Material.NETHERITE_INGOT);

        recipe.setIngredient('t', Material.ENCHANTING_TABLE);

        return recipe;
    }
}
