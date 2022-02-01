package com.javezki.Workstations;

import com.javezki.WorkstationLocations.ResearchTableLocation;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.Event.Result;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ResearchTableEvent implements Listener{
    
    @EventHandler
    private void researchTablePlace(BlockPlaceEvent ev)
    {

        ItemStack researchTable = ev.getItemInHand();

        if (researchTable == null)  return;

        if (researchTable.equals(new ResearchTable().getItem()))
        {
            new ResearchTableLocation(ev.getBlockPlaced().getLocation());
        }
    }

    @EventHandler
    private void interactWithResearchTable(PlayerInteractEvent ev)
    {
        if (!(ev.getAction().equals(Action.RIGHT_CLICK_BLOCK))) return;

        if (!(ev.getClickedBlock()).equals(new ItemStack(Material.ENCHANTING_TABLE))) return;
        boolean isResearchTable = false;
        for (Location locations : ResearchTableLocation.getlocations())
        {   

            if (ev.getClickedBlock().getLocation().equals(locations)) isResearchTable = true;
        }

        if (!isResearchTable) return;
        
        ev.setUseInteractedBlock(Result.DENY);

        Inventory researchTable = Bukkit.createInventory(null, InventoryType.ENCHANTING);


    }
}
