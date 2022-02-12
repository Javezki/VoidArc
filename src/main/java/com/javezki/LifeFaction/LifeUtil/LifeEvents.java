package com.javezki.LifeFaction.LifeUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import com.javezki.LifeFaction.LifeItems.FLifeBoots;
import com.javezki.LifeFaction.LifeItems.FLifeChestplate;
import com.javezki.LifeFaction.LifeItems.FLifeHelmet;
import com.javezki.LifeFaction.LifeItems.FLifeLeggings;
import com.javezki.LifeFaction.LifeItems.FLifeTomb;
import com.javezki.Materials.SmallLifeForce;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.md_5.bungee.api.ChatColor;

public class LifeEvents implements Listener {

    @EventHandler
    public void onSwordHit(EntityDamageByEntityEvent ev)
    {
        new LifeSwordEvent().onHit(ev);
    }

    @EventHandler
    public void lifeArmourEffects(PlayerArmorChangeEvent ev) {
        Collection<PotionEffect> pEff = ev.getPlayer().getActivePotionEffects();

        Collection<PotionEffect> gEffToGive = new ArrayList<PotionEffect>();

        //Check for normal potion effects
        for (PotionEffect potion : pEff) {
            if (potion.getDuration() <= 200000) {
                gEffToGive.add(potion);
            }
        }
        Player p = (Player) ev.getPlayer();
        for (PotionEffect eff : p.getActivePotionEffects()) {
            p.removePotionEffect(eff.getType());
        }
        //Gives potion effects that 
        p.addPotionEffects(gEffToGive);
        gEffToGive.clear();

        PlayerInventory pInv = p.getInventory();

        ItemStack[] armor = pInv.getArmorContents();

        ItemStack[] lifeArmor = {
                new FLifeHelmet().getItem(),
                new FLifeChestplate().getItem(),
                new FLifeLeggings().getItem(),
                new FLifeBoots().getItem()
        };

        if (armor == null)
            return;

        for (int i = 0; i < 4; i++) {
            if (armor[i] == null)
                continue;
            if (armor[i].equals(lifeArmor[0])) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0));
                p.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, 3));
            } else if (armor[i].equals(lifeArmor[1])) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1));
            } else if (armor[i].equals(lifeArmor[2])) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 1));
            } else if (armor[i].equals(lifeArmor[3])) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
            }
        }
    }

    @EventHandler
    public void dropLife(PlayerDeathEvent ev)   {

        DamageCause damage = ev.getPlayer().getLastDamageCause().getCause();

        LivingEntity e = (LivingEntity) ev.getPlayer();

        if (e == null) return;

        if (e.getKiller() == null) return;

        if (!(e.getKiller() instanceof Player)) return;

        if (!(damage == DamageCause.ENTITY_ATTACK)) return;

        Random random = new Random();

        if (random.nextInt(0, 5) == 5)
        {
            World world = ev.getPlayer().getLocation().getWorld();
            world.dropItem(ev.getPlayer().getLocation(), new SmallLifeForce().getItem());
        }
    }

    @EventHandler
    public void onCrafting(PrepareItemCraftEvent ev) {

        //The items needs tomb in off hand
        String[] items = new String[] {
                FLifeEnum.fLifeBoots.name(),
                FLifeEnum.fLifeLeggings.name(),
                FLifeEnum.fLifeChesplate.name(),
                FLifeEnum.fLifeHelmet.name(),
                FLifeEnum.fLifeSmallForce.name(),
                FLifeEnum.fLifeSword.name()
        };

        ItemStack result = ev.getInventory().getResult();

        if (result == null)
            return;

        ItemStack[] cMatrix = ev.getInventory().getMatrix();

        int saplingCount = 0;
        for (ItemStack item : cMatrix) {
            if (item == null)
                continue;

            if (item.equals(new ItemStack(Material.OAK_SAPLING)))
                saplingCount++;
        }

        // Check for saplings or armour due to sapling always having null metadata kinda
        // annoying smh

        boolean armourTomb = false;
        boolean saplingTomb = false;
        if (saplingCount != 9)
            saplingTomb = false;

        else
            armourTomb = true;

        NamespacedKey[] keys = new NamespacedKey[] {
                LifeKeys.fLifeHelmet(),
                LifeKeys.fLifeChestplate(),
                LifeKeys.fLifeLeggings(),
                LifeKeys.fLifeBoots(),
                LifeKeys.fLifeSword()
        };

        for (NamespacedKey key : keys) {
            if (armourTomb) break;
            String temp = ev.getInventory()
                    .getResult()
                    .getItemMeta()
                    .getPersistentDataContainer()
                    .get(key, PersistentDataType.STRING);
            for (String armour : items) {
                if (temp == null)
                    continue;
                if (temp.equals(armour))
                {
                    armourTomb = true;
                    break;
                }
            }
        }

        if (!armourTomb && !saplingTomb)
            return;

        InventoryView inv = ev.getView();

        Player p = (Player) inv.getPlayer();

        ItemStack offHand = inv.getPlayer().getInventory().getItemInOffHand();

        if (offHand == null) {
            p.sendMessage(ChatColor.RED + "Must have a fragmented tomb of life in your off hand!");
            ev.getInventory().setResult(null);
        }
        if (!(offHand.equals(new FLifeTomb().getItem()))) {
            p.sendMessage(ChatColor.RED + "Must have a fragmeneted tomb of life in your off hand!");
            ev.getInventory().setResult(null);
        }
    }


    @EventHandler
    public void onFireDamage(EntityDamageByEntityEvent ev)
    {
        if (!(ev.getEntity() instanceof Player)) return;

        Player damaged = (Player) ev.getEntity();

        ItemStack[] armour = damaged.getInventory().getArmorContents();

        ItemStack[] lifeArmour = new ItemStack[]
        {
            new FLifeBoots().getItem(),
            new FLifeChestplate().getItem(),
            new FLifeLeggings().getItem(),
            new FLifeHelmet().getItem()
        };

        if (!(ev.getDamager() instanceof Player)) return;

        Player damager = (Player) ev.getDamager();

        if(isPlayerFireDamage(damaged, damager, armour, lifeArmour)) ev.setDamage(ev.getDamage() * 1.5);
    }

    @EventHandler
    public void onZombieDamage(EntityDamageByEntityEvent ev)
    {
        if (!(ev.getDamager() instanceof Zombie)) return;

        if (!(ev.getEntity() instanceof Player))    return;

        Player p = (Player) ev.getEntity();

        ItemStack[] armourContents = p.getInventory().getArmorContents();

        for (ItemStack armour : armourContents)
        {
            ItemStack[] allLifeArmour = new ItemStack[]
        {
            new FLifeBoots().getItem(),
            new FLifeChestplate().getItem(),
            new FLifeLeggings().getItem(),
            new FLifeHelmet().getItem()
        };
            for (ItemStack lifeArmour : allLifeArmour)
            {
                if (armour == null) continue;

                if (lifeArmour.equals(armour))  
                {
                    ev.setDamage(ev.getDamage()*0.2);
                    return;
                }
            }
        }

    }

    public boolean isPlayerFireDamage(Player damaged, Player damager, ItemStack[] armour, ItemStack[] lifeArmour)
    {
        boolean hasArmour = false;
        for (ItemStack lifePiece : lifeArmour)
        {
            for (ItemStack playerArmour : armour)
            {
                if (playerArmour == null) continue;
                if (playerArmour.equals(lifePiece))
                {
                    hasArmour = true;
                } 
            }
        }
        if (!hasArmour) return false;

        if(!(damager instanceof Player))  return false;
        
        ItemStack itemInHand = damager.getInventory().getItemInMainHand();

        if (itemInHand == null) return false;

        if (itemInHand.getItemMeta() == null)   return false;

        if (itemInHand.getItemMeta().hasEnchant(Enchantment.FIRE_ASPECT))
        {
            return true;
        }

        return false;
    }
}
