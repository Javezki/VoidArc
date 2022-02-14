package com.javezki.OwnerGear;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import com.javezki.OwnerGear.OwnerArmour.OwnerBoots;
import com.javezki.OwnerGear.OwnerArmour.OwnerChestplate;
import com.javezki.OwnerGear.OwnerArmour.OwnerHelmet;
import com.javezki.OwnerGear.OwnerArmour.OwnerLeggings;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;

public class OwnerEvents implements Listener {

    @EventHandler
    public void onEquip(PlayerArmorChangeEvent ev) {
        removeEffects(ev.getPlayer());

        OwnerBoots.getInstance().onEquip(ev);

        OwnerLeggings.getInstance().onEquip(ev);

        OwnerChestplate.getInstance().onEquip(ev);

        OwnerHelmet.getInstace().onEquip(ev);
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent ev)
    {
        if (!(ev.getDamager() instanceof Player))    return;

        Player p = (Player) ev.getDamager();

        ItemStack[] ownerArmour = new ItemStack[]
        {
            OwnerBoots.getInstance().getItem(),

            OwnerLeggings.getInstance().getItem(),

            OwnerChestplate.getInstance().getItem(),

            OwnerHelmet.getInstace().getItem()
        };

        ItemStack[] playerArmour = p.getInventory().getArmorContents();

        for (ItemStack pArmour : playerArmour)
        {
            for (ItemStack oArmour : ownerArmour)
            {
                if (oArmour.equals(pArmour))
                {
                    ev.setCancelled(true);
                    p.sendMessage("You cannot attack ANYTHING while wearing owner armour!");
                }
            }
        }
    }

    private void removeEffects(Player p) {
        Collection<PotionEffect> effects = new ArrayList<>();

        Collection<PotionEffect> realEffects = new ArrayList<>();

        for (PotionEffect effect : effects) {
            if (effect.getDuration() > 200000)
                realEffects.add(effect);

            p.removePotionEffect(effect.getType());
        }

        p.addPotionEffects(realEffects);
    }

    @EventHandler
    private void onExcalibur(EntityDamageByEntityEvent ev) {
        if (!(ev.getDamager() instanceof Player))
            return;

        Player p = (Player) ev.getDamager();

        if (!p.getInventory().getItemInMainHand().isSimilar(Excalibur.getInstace().getItem()))
            return;

        int chance = new Random().nextInt(0, 20);

        if (chance == 10)
            takeRandomPiece(p);
    }

    private void takeRandomPiece(Player p) {

        PlayerInventory pInventory = p.getInventory();

        ItemStack[] armor = pInventory.getArmorContents();

        int randomPiece = new Random().nextInt(0, 3);

        ItemStack piece = armor[randomPiece];

        if (piece == null)  return;
        

        switch (randomPiece) {
            case 0: {
                pInventory.setHelmet(null);
                pInventory.addItem(piece);
                break;
            }
            case 1: {
                pInventory.setChestplate(null);
                pInventory.addItem(piece);
                break;
            }
            case 2: {
                pInventory.setLeggings(null);
                pInventory.addItem(piece);
                break;
            }
            case 3: {
                pInventory.setBoots(null);
                pInventory.addItem(piece);
                break;
            }
            default: {
                break;
            }
        }
    }
}
