package com.javezki.RandomItems.OwnerGear.OwnerItems;

import com.javezki.RandomItems.OwnerGear.OwnerArmour.OwnerItem;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OwnerSword extends OwnerItem{
    
    private static OwnerSword ownerSwordInstance = new OwnerSword();

    private OwnerSword(){};

    public static OwnerSword getInstance()
    {
        return ownerSwordInstance;
    }

    public final ItemStack getItem()
    {
        ItemStack ownerSword = new ItemStack(Material.NETHERITE_SWORD);

        ItemMeta meta = ownerSword.getItemMeta();

        meta.displayName(displayName("Owner Sword"));

        String[] rawLore = new String[]
        {
            "The Owner Sword.",
            "Built diff according to Nathan",
            "Tiki Taki Admin Abuse go brrrrr",
            "",
            "Those who make peaceful revolution impossible ",
            "will make violent revolution inevitable"
        };

        meta.lore(lore(rawLore));

        swordEnchants(meta);

        setPersistent(meta);
        
        meta.setUnbreakable(true);

        ownerSword.setItemMeta(meta);

        return ownerSword;
    }

    private void swordEnchants(ItemMeta meta) {

        meta.addEnchant(Enchantment.DAMAGE_ALL, 19, true);

        meta.addEnchant(Enchantment.DURABILITY, 9, true);

        meta.addEnchant(Enchantment.SWEEPING_EDGE, 9, true);

        meta.addEnchant(Enchantment.KNOCKBACK, 4, true);
    }
}
