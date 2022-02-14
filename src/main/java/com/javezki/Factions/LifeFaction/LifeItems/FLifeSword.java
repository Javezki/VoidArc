package com.javezki.Factions.LifeFaction.LifeItems;

import com.javezki.VoidMain;
import com.javezki.Factions.LifeFaction.LifeUtil.FLifeEnum;
import com.javezki.Materials.SmallLifeForce;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class FLifeSword extends LifeItems{

    NamespacedKey key = new NamespacedKey(VoidMain.getMain(), "fLifeSword");
    
    public final ItemStack getItem()
    {
        ItemStack sword = new ItemStack(Material.IRON_SWORD);

        ItemMeta meta = sword.getItemMeta();

        meta.displayName(setDisplayName("Fragmented Life Sword"));

        String[] lore = new String[]
        {
            "The fragmented life sword is the bane of ",
            "all hostile mobs. This sword is your main weapon",
            "to end the mob uprising once and for all."
        };

        meta.lore(setLore(lore));

        setMetaData(meta);

        meta.getPersistentDataContainer()
        .set(key, PersistentDataType.STRING, FLifeEnum.fLifeSword.name());

        meta.addEnchant(Enchantment.DAMAGE_UNDEAD, 5, false);

        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier("Base Attack", 12, Operation.ADD_NUMBER));

        sword.setItemMeta(meta);

        return sword;
    }

    
    public Recipe lifeSwordRecipe()
    {
        ShapedRecipe recipe = new ShapedRecipe(key, new FLifeSword().getItem());

        recipe.shape("lll", "lsl", "lll");

        recipe.setIngredient('l', new SmallLifeForce().getItem());

        recipe.setIngredient('s', Material.IRON_SWORD);

        return recipe;
    }
}
