package com.javezki.LifeFaction.LifeUtil;

import com.javezki.LifeFaction.LifeItems.FLifeBoots;
import com.javezki.LifeFaction.LifeItems.FLifeChestplate;
import com.javezki.LifeFaction.LifeItems.FLifeHelmet;
import com.javezki.LifeFaction.LifeItems.FLifeLeggings;
import com.javezki.LifeFaction.LifeItems.FLifeTomb;
import com.javezki.LifeFaction.LifeMaterials.LilyPad;
import com.javezki.LifeFaction.LifeMaterials.MagicalOak;
import com.javezki.LifeFaction.LifeMaterials.SugarCane;
import com.javezki.LifeFaction.LifeMaterials.SweetDandelion;
import com.javezki.Materials.SmallLifeForce;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class LifeRecipes {
    
    public Recipe fLifeShard()  
    {
        ShapelessRecipe fLifeShard = new ShapelessRecipe(LifeKeys.fSmallLifeShard(), new SmallLifeForce().getItem());

        fLifeShard.addIngredient(9, Material.OAK_SAPLING);



        return fLifeShard;
    }

    public Recipe fLifeHelmet()
    {
        ShapedRecipe fLifeHelmet = new ShapedRecipe(LifeKeys.fLifeHelmet(), new FLifeHelmet().getItem());

        fLifeHelmet.shape("lll", "lhl", "lll");

        fLifeHelmet.setIngredient('l', new SmallLifeForce().getItem());

        fLifeHelmet.setIngredient('h', Material.IRON_HELMET);

        return fLifeHelmet;
    }

    public Recipe fLifeChestplate()
    {
        ShapedRecipe fLifeChestplate = new ShapedRecipe(LifeKeys.fLifeChestplate(), new FLifeChestplate().getItem());

        fLifeChestplate.shape("lll", "lcl", "lll");

        fLifeChestplate.setIngredient('l', new SmallLifeForce().getItem());

        fLifeChestplate.setIngredient('c', Material.IRON_CHESTPLATE);

        return fLifeChestplate;
    }

    public Recipe fLifeLeggings()
    {
        ShapedRecipe fLifeLeggings = new ShapedRecipe(LifeKeys.fLifeLeggings(), new FLifeLeggings().getItem());

        fLifeLeggings.shape("fff", "flf", "fff");

        fLifeLeggings.setIngredient('f', new SmallLifeForce().getItem());

        fLifeLeggings.setIngredient('l', Material.IRON_LEGGINGS);

        return fLifeLeggings;
    }

    public Recipe fLifeBoots()
    {
        ShapedRecipe fLifeBoots = new ShapedRecipe(LifeKeys.fLifeBoots(), new FLifeBoots().getItem());

        fLifeBoots.shape("lll", "lbl", "lll");

        fLifeBoots.setIngredient('l', new SmallLifeForce().getItem());

        fLifeBoots.setIngredient('b', Material.IRON_BOOTS);

        return fLifeBoots;
    }

    public Recipe fLifeTomb()
    {
        ShapelessRecipe recipe = new ShapelessRecipe(LifeKeys.fLifeTomb(), new FLifeTomb().getItem());

        recipe.addIngredient(new LilyPad().getItem());

        recipe.addIngredient(new MagicalOak().getItem());

        recipe.addIngredient(new SugarCane().getItem());
        
        recipe.addIngredient(new SweetDandelion().getItem());

        return recipe;
    }

    public Recipe[] allLifeRecipes()
    {
        Recipe[] recipes = new Recipe[]
        {
            fLifeShard(),
            fLifeHelmet(),
            fLifeChestplate(),
            fLifeLeggings(),
            fLifeBoots(),
            fLifeTomb()
        };

        return recipes;
    }
}
