package com.javezki.OwnerGear.OwnerArmour;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OwnerChestplate extends OwnerItem{
    
    private static OwnerChestplate ownerChestplateInstance = new OwnerChestplate();

    private OwnerChestplate(){}

    public final ItemStack getItem()
    {
        ItemStack ownerChestplate = new ItemStack(Material.NETHERITE_CHESTPLATE);

        ItemMeta meta = ownerChestplate.getItemMeta();

        meta.displayName(displayName("Owner Chestplate"));

        String[] rawLore = new String[]
        {
            "The chestplate of the owner.",
            "Fused with maximum admin abuse and",
            "shouldn't let you die (in theory)",
            "",
            "'If I can't dance to it, it's not my revolution.'"
        };

        meta.lore(lore(rawLore));

        setPersistent(meta);

        meta.setUnbreakable(true);

        addEnchants(meta);

        ownerChestplate.setItemMeta(meta);

        return ownerChestplate;
    }

    public void onEquip(PlayerArmorChangeEvent ev)
    {
        Player p = ev.getPlayer();

        ItemStack pChestplate = ev.getPlayer().getInventory().getChestplate();

        if (pChestplate == null) return;

        if (!getItem().equals(pChestplate)) return;

        p.addPotionEffects(cEffects());
    }

    public static OwnerChestplate getInstance()
    {
        return ownerChestplateInstance;
    }
}
