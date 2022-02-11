package com.javezki.Workstations;


import org.bukkit.entity.Player;

public class WorkstationCommands {

    private final Player target;
    
    public WorkstationCommands(Player target)
    {
        this.target = target;
    }

    public final void onCommands()
    {
        target.getInventory().addItem(new ResearchTable().getItem());
    }
}
