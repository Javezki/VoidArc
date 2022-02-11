package com.javezki.PluginLib;

import com.javezki.DarkFaction.CorruptedItems.CorruptedFlesh;
import com.javezki.DarkFaction.CorruptedItems.CorruptedPower;
import com.javezki.GeneralFaction.GeneralItems.GeneralItems.SoulPaper;
import com.javezki.GeneralFaction.GeneralItems.GeneralItems.UnbreakablePP;
import com.javezki.LifeFaction.LifeUtil.LifeCommands;
import com.javezki.TeleportingBow.TeleportArrow;
import com.javezki.TeleportingBow.TeleportBow;
import com.javezki.Workstations.WorkstationCommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import net.md_5.bungee.api.ChatColor;

public class GiveCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String str,
            @NotNull String[] args) {

        if (!(sender instanceof Player) && args.length <= 2) {
            sender.sendMessage(ChatColor.RED + "Get guud");
            return false;
        }

        Player p = (Player) sender;
        new LifeCommands(args, p).runCommand();

        p.getInventory().addItem(new TeleportBow().getTeleportBow());

        p.getInventory().addItem(new TeleportArrow().getTeleportArrow());

        new WorkstationCommands(p).onCommands();

        p.getInventory().addItem(new UnbreakablePP().getItem());

        p.getInventory().addItem(new SoulPaper().getItem());

        p.getInventory().addItem(new CorruptedPower().getItem());

        p.getInventory().addItem(new CorruptedFlesh().getItem());

        return false;
    }

}