package com.javezki.Attributes;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class AttributeEvents implements Listener{
    

    @EventHandler
    public void onDamageHit(EntityDamageByEntityEvent ev)
    {
        new DamageIncrease().damageBoost(ev);
    }
}
