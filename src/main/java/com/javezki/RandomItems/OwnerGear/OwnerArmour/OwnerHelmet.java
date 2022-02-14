package com.javezki.RandomItems.OwnerGear.OwnerArmour;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OwnerHelmet extends OwnerItem{

    private static OwnerHelmet ownerHelmetInstance = new OwnerHelmet();

    private OwnerHelmet(){}
    
    public final ItemStack getItem()
    {
        ItemStack ownerHelmet = new ItemStack(Material.NETHERITE_HELMET);

        ItemMeta meta = ownerHelmet.getItemMeta();

        meta.displayName(displayName("Owner Helmet"));

        String[] rawLore = new String[]
        {
            "The final bosses armour. Incredibly strong",
            "because of the admin abuse put into it",
            "",
            "'The king is dead! Long live the king'"
        };

        meta.lore(lore(rawLore));

        meta.setUnbreakable(true);

        addEnchants(meta);

        helmetEnchants(meta);

        setPersistent(meta);

        ownerHelmet.setItemMeta(meta);

        return ownerHelmet;
    }

    private final void helmetEnchants(ItemMeta meta) {

        meta.addEnchant(Enchantment.WATER_WORKER, 0, false);

        meta.addEnchant(Enchantment.OXYGEN, 4, false);
    }

    public final void onEquip(PlayerArmorChangeEvent ev)
    {
        Player p = ev.getPlayer();

        ItemStack pHelmet = p.getInventory().getHelmet();

        if (pHelmet == null) return;

        if (!getItem().equals(pHelmet)) return;

        p.addPotionEffects(hEffects());
    }



    public static OwnerHelmet getInstace()
    {
        return ownerHelmetInstance;
    }
}
