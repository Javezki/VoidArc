package com.javezki.TeleportingBow;

import com.javezki.LifeFaction.LifeArmour.FLifeBoots;
import com.javezki.LifeFaction.LifeArmour.FLifeChestplate;
import com.javezki.LifeFaction.LifeArmour.FLifeHelmet;
import com.javezki.LifeFaction.LifeArmour.FLifeLeggings;
import com.javezki.LifeFaction.LifeUtil.FLifeTomb;
import com.javezki.Materials.EnderShard;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import net.md_5.bungee.api.ChatColor;

public class TeleportBowCommand implements CommandExecutor  {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) 
    {
        if (!(sender instanceof Player))
        {
            sender.sendMessage(ChatColor.RED + "Only players may execute this command!");
            return false;
        }

        if (args.length == 0)
        {
            sender.sendMessage(ChatColor.RED + "Fuck off I'm tired");
            return false;
        }

        Player p = (Player) sender;
        int count = 1;
        if (args.length == 2)
        {
            try{
                count = Integer.parseInt(args[1]);
            }
            catch(NumberFormatException ex)
            {
                p.sendMessage(ChatColor.RED + "Invalid Count! Please input a valid number.");
                return false;
            }
        }



        switch (args[0].toLowerCase())
        {
            case "teleportarrow":
                giveTeleportArrow(p, count);
                return true;
            case "teleportbow":
                giveTeleportBow(p, count);
                return true;
            case "endershard":
                giveEnderShard(p, count);
                return true;
            case "lifehelmet":
                giveLifeHelmet(p, count);
                return true;
            case "lifechestplate":
                giveLifeChestplate(p, count);
                return true;
            case "lifeleggings":
                giveLifeLeggings(p, count);
                return true;
            case "lifeboots": 
                giveLifeBoots(p, count);
                return true;
            case "lifetomb":
                giveLifeTomb(p);
            default:
                p.sendMessage(ChatColor.RED + "Invalid Parameter! Please try again");
                return false;
        }
    }

    private void giveLifeTomb(Player p) {
        p.getInventory().addItem(new FLifeTomb().getItem());
    }

    private void giveLifeBoots(Player p, int count) {

        for (int i = 0; i < count; i++)
        {
            p.getInventory().addItem(new FLifeBoots().getItem());
        }
        p.sendMessage(ChatColor.GREEN + "Given " + count + " Life Boot(s)!");
    }

    private void giveLifeLeggings(Player p, int count) {

        for (int i = 0; i < count; i++)
        {
            p.getInventory().addItem(new FLifeLeggings().getItem());
        }
        p.sendMessage(ChatColor.GREEN + "Given " + count + " Life Legging(s)!");
    }

    private void giveLifeChestplate(Player p, int count) {
        for (int i = 0; i < count; i++)
        {
            p.getInventory().addItem(new FLifeChestplate().getItem());
        }
        p.sendMessage(ChatColor.GREEN + "Given " + count + " Life Chesplate(s)!" );
    }

    public void giveTeleportArrow(Player p, int count)
    {
        for (int i = 0; i < count; i++)
        {
            p.getInventory().addItem(new TeleportArrow().getTeleportArrow());
        }

        p.sendMessage(ChatColor.GREEN + "Given " + count + " Teleport Arrow(s)!");
    }

    public void giveTeleportBow(Player p, int count)
    {
        for (int i = 0; i < count; i++)
        {
            p.getInventory().addItem(new TeleportBow().getTeleportBow());
        }
  
        p.sendMessage(ChatColor.GREEN + "Given " + count + " Teleport Bow(s)!");
    }

    public void giveEnderShard(Player p, int count)
    {
        if (count > 2048)
        {
            p.sendMessage("cringe");
            return;
        }
        for (int i = 0; i < count; i++)
        {
            p.getInventory().addItem(new EnderShard().getEnderShard());
        }
        
        p.sendMessage(ChatColor.GREEN + "Given " + count + " Ender Shard(s)!");
    }

    public void giveLifeHelmet(Player p, int count)
    {
        for (int i = 0; i < count; i++)
        {
            p.getInventory().addItem(new FLifeHelmet().getItem());
        }
        p.sendMessage(ChatColor.GREEN + "Given " + count + " Life Helmet(s)");
    }

    
}