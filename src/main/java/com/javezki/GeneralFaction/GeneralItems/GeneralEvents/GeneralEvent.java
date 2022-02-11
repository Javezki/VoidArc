package com.javezki.GeneralFaction.GeneralItems.GeneralEvents;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class GeneralEvent implements Listener{
    
    @EventHandler
    public void onSoulPaper(PlayerInteractEvent ev) 
    {
        SoulPaperEvent SPE = new SoulPaperEvent(ev);

        if (!SPE.isSoulPaper()) return;

        SPE.addLife();
    }
}
