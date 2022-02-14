package com.javezki.OwnerGear.OwnerArmour;


import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OwnerBoots extends OwnerItem{
    
    private static OwnerBoots ownerBootsInstance = new OwnerBoots();

    private OwnerBoots(){}

    public final ItemStack getItem()
    {
        ItemStack ownerBoots = new ItemStack(Material.NETHERITE_BOOTS);

        ItemMeta meta = ownerBoots.getItemMeta();

        meta.displayName(displayName("Owner Boots"));

        String[] rawLore = new String[]
        {
            "The Owner Boots.",
            "A piece of armour infused with admin abuse",
            "its just good lol",
            "",
            "'Sometimes you have to pick the gun up to put the Gun down.'"
        };

        meta.lore(lore(rawLore));

        addEnchants(meta);

        bootEnchants(meta);

        setPersistent(meta);
        
        meta.setUnbreakable(true);

        ownerBoots.setItemMeta(meta);

        return ownerBoots;
    }

    private void bootEnchants(ItemMeta meta) {

        meta.addEnchant(Enchantment.PROTECTION_FALL, 4, true);

        meta.addEnchant(Enchantment.DEPTH_STRIDER, 4, true);
    }

    public static OwnerBoots getInstance()
    {
        return ownerBootsInstance;
    }

    public void onEquip(PlayerArmorChangeEvent ev)
    {
        Player p = ev.getPlayer();

        ItemStack pBoots = p.getInventory().getBoots();

        if (pBoots == null)    return;

        if (!getItem().equals(pBoots))  return;

        p.addPotionEffects(bEffects());
    }
}
