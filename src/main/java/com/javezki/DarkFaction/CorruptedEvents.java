package com.javezki.DarkFaction;

import com.javezki.DarkFaction.CorruptedItems.CorruptedPower;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class CorruptedEvents implements Listener{
    
    @EventHandler
    private final void corruptedWand(PlayerInteractEvent ev)
    {
        CorruptedPower power = new CorruptedPower();
        power.onUse(ev);
    }
}
