package com.javezki.LifeFaction.LifeUtil;

import com.javezki.LifeFaction.LifeItems.FLifeBoots;
import com.javezki.LifeFaction.LifeItems.FLifeChestplate;
import com.javezki.LifeFaction.LifeItems.FLifeHelmet;
import com.javezki.LifeFaction.LifeItems.FLifeLeggings;
import com.javezki.LifeFaction.LifeItems.FLifeTomb;
import com.javezki.LifeFaction.LifeMaterials.LilyPad;
import com.javezki.LifeFaction.LifeMaterials.MagicalOak;
import com.javezki.LifeFaction.LifeMaterials.SugarCane;
import com.javezki.LifeFaction.LifeMaterials.SweetDandelion;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class LifeCommands {
    
    private String[] args;

    private Player target;

    public LifeCommands(String[] args, Player target)
    {
        this.args = args;
        this.target = target;
    }

    public final void runCommand()
    {
        String param = args[0].toLowerCase();

        Inventory inv  = target.getInventory();

        switch (param){
            case "life":
            {

            }
            case "flifetomb":
            {
                inv.addItem(new FLifeTomb().getItem());
            }
            case "flifeboots":
            {
                inv.addItem(new FLifeBoots().getItem());
            }
            case "flifeleggings":
            {
                inv.addItem(new FLifeLeggings().getItem());
            }
            case "flifechestplate":
            {
                inv.addItem(new FLifeChestplate().getItem());
            }
            case "fLifehelmet":
            {
                inv.addItem(new FLifeHelmet().getItem());
            }
            case "lilypad":
            {
                inv.addItem(new LilyPad().getItem());
            }
            case "magicaloak":
            {
                inv.addItem(new MagicalOak().getItem());
            }
            case "sugarcane":
            {
                inv.addItem(new SugarCane().getItem());
            }
            case "sweetdandelion":
            {
                inv.addItem(new SweetDandelion().getItem());
            }
            default:
            {
                return;
            }
        }

    }
}
