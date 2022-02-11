package com.javezki.DarkFaction.CorruptedItems;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.kyori.adventure.text.Component;

public class CorruptedPower extends CorruptedItem {

    public ItemStack getItem() {
        ItemStack rod = new ItemStack(Material.BLAZE_ROD);

        ItemMeta meta = rod.getItemMeta();

        meta.displayName(displayName("Corrupted Power Harnesser"));

        String[] lore = new String[] {
                "To temporarily harness the power of corruption, ",
                "you must use a catalyst to do so.",
                "Although flawed, will allow you to barely harness the strength",
                "of corruption."
        };

        meta.lore(lore(lore));

        setPersistentData(meta);

        rod.setItemMeta(meta);

        return rod;
    }

    public final void onUse(PlayerInteractEvent ev) {
        if (!(ev.getAction().equals(Action.RIGHT_CLICK_AIR)) ||
                !(ev.getAction().equals(Action.RIGHT_CLICK_BLOCK)))
            return;

        Player p = ev.getPlayer();

        PlayerInventory inv = p.getInventory();

        if (inv.getItemInMainHand() == null)
            return;

        if (!(inv.getItemInMainHand().equals(new CorruptedPower().getItem())))
            return;

        for (ItemStack rottenFlesh : inv.getStorageContents()) {
            if (Material.ROTTEN_FLESH.equals(rottenFlesh.getType())) {
                if (isCorruptedFlesh(rottenFlesh) == true) {
                    giveStatusEffects(p);
                    rottenFlesh.setAmount(1);
                    inv.remove(rottenFlesh);
                    return;
                }
            }
        }
    }

    private final void giveStatusEffects(Player p) {

        p.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 180*20, 3));
        p.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK, 360*20, 1));
        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 90 * 20, 0));
    }

    private final boolean isCorruptedFlesh(ItemStack rottenFlesh) {

        final ItemStack corruptedFlesh = new CorruptedFlesh().getItem();

        final List<Component> lore = corruptedFlesh.getItemMeta().lore();

        final Component displayName = corruptedFlesh.getItemMeta().displayName();

        final ItemMeta meta = rottenFlesh.getItemMeta();

        if (!meta.lore().equals(lore))
            return false;

        if (!meta.displayName().equals(displayName))
            return false;

        return true;
    }
}
