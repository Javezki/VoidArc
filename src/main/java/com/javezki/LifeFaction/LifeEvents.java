package com.javezki.LifeFaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import com.javezki.Materials.SmallLifeForce;
import com.javezki.PluginLib.Namespaces;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.md_5.bungee.api.ChatColor;

public class LifeEvents implements Listener {

    @EventHandler
    public void lifeArmourEffects(InventoryCloseEvent ev) {
        Collection<PotionEffect> pEff = ev.getPlayer().getActivePotionEffects();

        Collection<PotionEffect> gEffToGive = new ArrayList<PotionEffect>();

        for (PotionEffect potion : pEff) {
            if (potion.getDuration() < 9600) {
                gEffToGive.add(potion);
            }
        }
        Player p = (Player) ev.getPlayer();
        for (PotionEffect eff : p.getActivePotionEffects()) {
            p.removePotionEffect(eff.getType());
        }
        p.addPotionEffects(gEffToGive);
        gEffToGive.clear();

        PlayerInventory pInv = p.getInventory();

        ItemStack[] armor = pInv.getArmorContents();

        ItemStack[] lifeArmor = {
                new LifeHelmet().getItem(),
                new LifeChestplate().getItem(),
                new LifeLeggings().getItem(),
                new LifeBoots().getItem()
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

    public void onMessage(AsyncChatEvent ev) {
    }

    @EventHandler
    public void dropLife(PlayerDeathEvent ev)   {
        Random random = new Random();

        if (random.nextBoolean())
        {
            World world = ev.getPlayer().getLocation().getWorld();
            world.dropItem(ev.getPlayer().getLocation(), new SmallLifeForce().getItem());
        }
    }

    @EventHandler
    public void onCrafting(PrepareItemCraftEvent ev) {

        String[] items = new String[] {
                FLifeEnum.fLifeBoots.name(),
                FLifeEnum.fLifeLeggings.name(),
                FLifeEnum.fLifeChesplate.name(),
                FLifeEnum.fLifeHelmet.name(),
                FLifeEnum.fLifeSmallForce.name()
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
                LifeKeys.fLifeBoots()
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
        Bukkit.getLogger().info("Whats in off hand?: " + offHand);
        if (!(offHand.equals(new FLifeTomb().getItem()))) {
            p.sendMessage(ChatColor.RED + "Must have a fragmeneted tomb of life in your off hand!");
            ev.getInventory().setResult(null);
        }
    }
}
