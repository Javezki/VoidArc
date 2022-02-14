package com.javezki.OwnerGear.OwnerArmour;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OwnerLeggings extends OwnerItem{

    private static OwnerLeggings ownerLeggingsInstance = new OwnerLeggings();
    
    private OwnerLeggings()
    {

    }

    public final ItemStack getItem()
    {
        ItemStack ownerLeggings = new ItemStack(Material.NETHERITE_LEGGINGS);

        ItemMeta meta = ownerLeggings.getItemMeta();

        meta.displayName(displayName("Owner Leggings"));

        String[] rawLore = new String[]
        {
            "The Owner Leggings.",
            "Infused with the power of admin abuse",
            "this leggings wields untold power",
            "",
            "'The first duty of a man is to think for himself'"
        };

        meta.lore(lore(rawLore));

        addEnchants(meta);

        setPersistent(meta);

        meta.setUnbreakable(true);

        ownerLeggings.setItemMeta(meta);
        
        return ownerLeggings;
    }

    public void onEquip(PlayerArmorChangeEvent ev)
    {
        Player p = ev.getPlayer();
        
        ItemStack pLeggings = p.getInventory().getLeggings();

        if (pLeggings == null)  return;

        if (!getItem().equals(pLeggings)) return;

        p.addPotionEffects(lEffects());
    }

    public static OwnerLeggings getInstance()
    {
        return ownerLeggingsInstance;
    }
}
