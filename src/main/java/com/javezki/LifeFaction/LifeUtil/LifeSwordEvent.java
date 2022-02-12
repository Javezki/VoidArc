package com.javezki.LifeFaction.LifeUtil;

import com.javezki.LifeFaction.LifeItems.FLifeSword;

import org.bukkit.Particle;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class LifeSwordEvent {
    

    public void onHit(EntityDamageByEntityEvent ev)
    {
        if (!(ev.getDamager() instanceof Player))   return;

        if (!(ev.getEntity() instanceof Monster))   return;

        Player p = (Player) ev.getDamager();

        ItemStack lifeSword = p.getInventory().getItemInMainHand();

        if (lifeSword == null)  return;

        if (!(new FLifeSword().getItem().equals(lifeSword))) return;

        ev.setDamage(ev.getDamage() * 2);

        p.getLocation().getWorld().spawnParticle(Particle.WATER_SPLASH, ev.getEntity().getLocation(), 10);
    }

}
