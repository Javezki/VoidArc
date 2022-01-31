package com.javezki.LifeFaction;

import com.javezki.Materials.SmallLifeForce;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class LifeCraftingRecipes {
    
    public Recipe fLifeShard()  
    {
        ShapelessRecipe fLifeShard = new ShapelessRecipe(LifeKeys.fSmallLifeShard(), new SmallLifeForce().getItem());

        fLifeShard.addIngredient(9, Material.OAK_SAPLING);



        return fLifeShard;
    }

    public Recipe fLifeHelmet()
    {
        ShapedRecipe fLifeHelmet = new ShapedRecipe(LifeKeys.fLifeHelmet(), new LifeHelmet().getItem());

        fLifeHelmet.shape("lll", "lhl", "lll");

        fLifeHelmet.setIngredient('l', new SmallLifeForce().getItem());

        fLifeHelmet.setIngredient('h', Material.IRON_HELMET);

        return fLifeHelmet;
    }

    public Recipe fLifeChestplate()
    {
        ShapedRecipe fLifeChestplate = new ShapedRecipe(LifeKeys.fLifeChestplate(), new LifeChestplate().getItem());

        fLifeChestplate.shape("lll", "lcl", "lll");

        fLifeChestplate.setIngredient('l', new SmallLifeForce().getItem());

        fLifeChestplate.setIngredient('c', Material.IRON_CHESTPLATE);

        return fLifeChestplate;
    }

    public Recipe fLifeLeggings()
    {
        ShapedRecipe fLifeLeggings = new ShapedRecipe(LifeKeys.fLifeLeggings(), new LifeLeggings().getItem());

        fLifeLeggings.shape("fff", "flf", "fff");

        fLifeLeggings.setIngredient('f', new SmallLifeForce().getItem());

        fLifeLeggings.setIngredient('l', Material.IRON_LEGGINGS);

        return fLifeLeggings;
    }

    public Recipe fLifeBoots()
    {
        ShapedRecipe fLifeBoots = new ShapedRecipe(LifeKeys.fLifeBoots(), new LifeBoots().getItem());

        fLifeBoots.shape("lll", "lbl", "lll");

        fLifeBoots.setIngredient('l', new SmallLifeForce().getItem());

        fLifeBoots.setIngredient('b', Material.IRON_BOOTS);

        return fLifeBoots;
    }
}
