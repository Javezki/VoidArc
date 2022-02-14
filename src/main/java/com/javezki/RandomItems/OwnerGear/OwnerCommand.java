package com.javezki.RandomItems.OwnerGear;

import com.javezki.RandomItems.OwnerGear.OwnerArmour.OwnerBoots;
import com.javezki.RandomItems.OwnerGear.OwnerArmour.OwnerChestplate;
import com.javezki.RandomItems.OwnerGear.OwnerArmour.OwnerHelmet;
import com.javezki.RandomItems.OwnerGear.OwnerArmour.OwnerLeggings;
import com.javezki.RandomItems.OwnerGear.OwnerItems.OwnerSword;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class OwnerCommand {
    
    private CommandSender sender;
    
    private String str;

    private Command cmd;

    private String[] args;

    public OwnerCommand(CommandSender sender, Command cmd, String str, String[] args)
    {
        this.sender = sender;

        this.cmd = cmd;

        this.str = str;

        this.args = args;
    }

    public void giveOwnerSet()
    {
        if (!(sender instanceof Player))
        {
            consoleGive();
            return;
        }

        Player p = (Player) sender;

        if (args.length > 1) p = Bukkit.getServer().getPlayer(args[0]);

        if (p == null ) 
        {
            sender.sendMessage("Please input a valid player!");
            return;
        }

        ItemStack[] ownerGear = new ItemStack[]
        {
            OwnerBoots.getInstance().getItem(),
            OwnerLeggings.getInstance().getItem(),
            OwnerChestplate.getInstance().getItem(),
            OwnerHelmet.getInstace().getItem(),
            OwnerSword.getInstance().getItem()
        };

        for (ItemStack item : ownerGear)
        {
            p.getInventory().addItem(item);
        }

        


    }

    private void consoleGive() {


    }
}
