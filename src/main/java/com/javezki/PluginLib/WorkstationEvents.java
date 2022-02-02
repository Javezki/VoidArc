package com.javezki.PluginLib;

import com.javezki.VoidMain;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRecipeDiscoverEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;


public class WorkstationEvents implements Listener{
    
    @EventHandler
    private void notRepairable(PrepareAnvilEvent ev)
    {   
        AnvilInventory anvInv = ev.getInventory();
        InventoryView invView = ev.getView();
        if (anvInv.getFirstItem() == null) return;

        Integer anvMeta = InteractionMetaData.getNotRepairable(anvInv.getFirstItem());

        if (anvMeta == null || anvMeta == 0)   return;

        if (anvMeta == 1)    
        {
            if (anvInv.getFirstItem() != null) invView.getBottomInventory().addItem(anvInv.getFirstItem());
            if (anvInv.getSecondItem() != null) invView.getBottomInventory().addItem(anvInv.getSecondItem());
            anvInv.setFirstItem(null);
            anvInv.setSecondItem(null);
            invView.getPlayer().sendMessage(ChatColor.RED + "You cannot use this item here!");
        }

        if (anvMeta == 2)
        {
            Integer status = 0;
            if (anvInv.getSecondItem() != null) status = InteractionMetaData.getNotRepairable(anvInv.getSecondItem());
            if (anvInv.getSecondItem() == null) return;
            if (status == null)
            {
                if (!(anvInv.getSecondItem().getType().name().toLowerCase().equals("enchanted_book")))
                {
                    anvInv.setResult(null);
                    invView.getPlayer().sendMessage(ChatColor.RED + "You cannot combine these two items!");
                }
            }
        }
    }


    @EventHandler

    //Craftable 0 == Can be used for anything
    //Craftable 1 == Cannot be used
    //Craftable 2 == Can only be used for custom items
    private void notCraftable(PrepareItemCraftEvent ev)
    {
        //Get matrix
        ItemStack[] cMatrix = ev.getInventory().getMatrix();
        //Check for null and check for item data 
        //Is Crafting Material checks what can it craft
        for (ItemStack item : cMatrix)
        {   
            if (item == null) continue;
            Integer isUnCraftable = InteractionMetaData.getIsCraftingMaterial(item);
            if (isUnCraftable == null) return;
            Bukkit.getLogger().info("Not Craftable?: " + isUnCraftable);
            switch (isUnCraftable)
            {   
                case 0:
                    return;
                case 1: 
                    ev.getInventory().setResult(null);
                    break;
                case 2:
                    onlyDisplayCustom(ev.getInventory());
                    break;
                default:
                    return;
            }
        }
        
    }

    @EventHandler
    private void notEnchantable(PrepareItemEnchantEvent ev)
    {
        Integer nbt = InteractionMetaData.getEnchantable(ev.getItem());
        if (nbt == null)   return;
        if (nbt == 0)   return;
        if (nbt == 1)
        {
            InventoryView iView = ev.getView();
            iView.getTopInventory().setItem(0, null);
            iView.getBottomInventory().setItem(iView.getBottomInventory().firstEmpty(), ev.getItem());
            ev.getEnchanter().sendMessage(ChatColor.RED + "You cannot enchant this item!");
        }
    }

    //isNotVanillaItem = 0 is false
    //isNotVanillaItem = 1 true
    public void onlyDisplayCustom(CraftingInventory cInv)
    {
        //Get result then check if its vanilla item or not
        ItemStack result = cInv.getResult();
        if (result == null) return;
        Integer isNotVanilla = InteractionMetaData.getVanillaItem(result);
        if (isNotVanilla == null)
        {
            cInv.setResult(null);
            return;
        }
        Bukkit.getLogger().info("Is a vanilla item?: " + isNotVanilla);
        if (isNotVanilla == 0)
        {
            cInv.setResult(null);
            return;
        }
    }
}   
