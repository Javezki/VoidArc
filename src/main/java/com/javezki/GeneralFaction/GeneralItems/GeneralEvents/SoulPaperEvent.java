package com.javezki.GeneralFaction.GeneralItems.GeneralEvents;

import com.javezki.GeneralFaction.GeneralItems.GeneralItems.SoulPaper;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class SoulPaperEvent {

    private PlayerInteractEvent ev;

    public SoulPaperEvent(PlayerInteractEvent ev){
        this.ev = ev;
    }

    public final void addLife()
    {
        final Player p = ev.getPlayer();

        p.sendMessage(ChatColor.GREEN + "Successfully used Soul Paper!");

        final double playerCurrentHealth = p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();

        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(playerCurrentHealth + 2);

        p.getInventory().remove(new SoulPaper().getItem());
    }

    public boolean isSoulPaper() {

        Player p = ev.getPlayer();

        ItemStack itemInHand = p.getInventory().getItemInMainHand();

        if (new SoulPaper().getItem().equals(itemInHand)) return true;

        return false;
    }
}
