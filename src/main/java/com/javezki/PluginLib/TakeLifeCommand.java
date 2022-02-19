package com.javezki.PluginLib;

import com.javezki.Factions.GeneralFaction.GeneralItems.GeneralItems.SoulPaper;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TakeLifeCommand implements CommandExecutor{

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String str,
            @NotNull String[] args) {
        

                if (!(sender instanceof Player))  return false;

                if (args.length <= 1 )    return false;

                Player p = (Player) sender;

                if (!p.isOp())  return false;

                Player target = Bukkit.getServer().getPlayer(args[0]);

                if (target == null) return false;

                double targetMaxLife = target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();

                target.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(targetMaxLife - 2);



                p.getInventory().addItem(new SoulPaper().getItem());

        return true;
    }
    
    
}
