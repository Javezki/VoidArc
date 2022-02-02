package com.javezki.Workstations;

import java.util.ArrayList;
import java.util.List;

import com.javezki.VoidMain;
import com.javezki.LifeFaction.LifeMaterials.LifeMaterial;
import com.javezki.LifeFaction.LifeMaterials.SweetDandelion;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.Event.Result;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.md_5.bungee.api.ChatColor;

public class ResearchTableEvent implements Listener {

    @EventHandler
    private void researchTablePlace(BlockPlaceEvent ev) {

        ItemStack researchTable = ev.getItemInHand();

        VoidMain.getResearchLocationConfig().set("Location ID: " + ev.getBlockPlaced().getLocation().toBlockKey(),
                ev.getBlockPlaced().getLocation());

        VoidMain.saveCustomConfig(VoidMain.getResearchLocationConfig(), VoidMain.getCustomFile());

        if (researchTable == null)
            return;

        if (researchTable.isSimilar(new ResearchTable().getItem())) {
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void interactWithResearchTable(PlayerInteractEvent ev) {

        if (!(ev.getAction().equals(Action.RIGHT_CLICK_BLOCK)))
            return;

        if (!(ev.getClickedBlock().getType()).equals(Material.ENCHANTING_TABLE))
            return;

        Location l = VoidMain.getResearchLocationConfig()
                .getLocation("Location ID: " + ev.getClickedBlock().getLocation().toBlockKey());

        if (l == null)
            return;

        ev.setUseInteractedBlock(Result.DENY);

        ev.getPlayer().openInventory(new ResearchTable().researchTableInventory());
    }

    @EventHandler
    public void researchTableInventory(InventoryClickEvent ev) {

        Bukkit.getLogger().info("It ran");

        if (ev.getCurrentItem() == null)
            return;

        if (!(ev.getClickedInventory().contains(new ResearchTable().confirm())))
            return;

        Player p = (Player) ev.getView().getPlayer();

        // Check if the item clicked is a window pane
        for (ItemStack illegalItem : new ResearchTable().researchTableContentStacks()) {
            if (ev.getCurrentItem().equals(illegalItem)) {
                ev.setCancelled(true);
            }
        }

        if (ev.getCurrentItem().equals(new ResearchTable().confirm())) {
            ev.setCancelled(true);

            ItemStack left1 = ev.getInventory().getItem(11);
            ItemStack left2 = ev.getInventory().getItem(12);

            Inventory researchTable = ev.getInventory();

            Material researching = theLifeMaterial(left1, left2);

            ItemStack researchingItem = new ItemStack(Material.IRON_AXE);

            if (researchTable.getItem(11) != null)
                researchingItem = researchTable.getItem(11);

            else if (researchTable.getItem(12) != null)
                researchingItem = researchTable.getItem(12);

            // Check if researching value is null
            if (researching == null) {
                p.sendMessage(ChatColor.RED + "This item cannot be researched (yet?)");
                return;
            }

            // Dandelion researching for Sweet Dandelion
            if (researching.equals(Material.DANDELION)) {

                Integer researchLevel = checkResearchLevel(researchingItem);

                ItemStack researchedItem = setResearchLevel(researchLevel, researchingItem, p);

                researchedItem.setAmount(1);

                p.getInventory().addItem(researchedItem);

                researchTable.setItem(15, researchedItem);;
            }

            else if (researching.equals(Material.OAK_SAPLING)) {
                // Sapling
            } else if (researching.equals(Material.LILY_PAD)) {
                // Lilypad
            } else if (researching.equals(Material.SUGAR_CANE)) {
                // Sugar Cane
            }
        }
    }

    // Sets the inventory contents of the research table to desired results
    private void setInventoryContents(Inventory researchTable, Player p) {

        ItemStack leftSlot = researchTable.getItem(11);

        Bukkit.getLogger().info("The left slot: " + leftSlot);

        if (leftSlot == null) {
            leftSlot = new ItemStack(Material.AIR);
        } else if (leftSlot != null) {
            leftSlot.setAmount(researchTable.getItem(11).getAmount() - 1);
        }

        Bukkit.getLogger().info("New left slot: " + leftSlot);

        ItemStack rightSlot = researchTable.getItem(12);

        Bukkit.getLogger().info("The right slot: " + rightSlot);

        if (rightSlot == null) {
            rightSlot = new ItemStack(Material.AIR);
        }
        else if (rightSlot != null) {
            rightSlot.setAmount(researchTable.getItem(12).getAmount() - 1);
        } 

        Bukkit.getLogger().info("The new right slot: " + rightSlot);

        researchTable.setItem(11, leftSlot);
        researchTable.setItem(12, rightSlot);
        if (researchTable.getItem(14) != null)
            p.getInventory().addItem(researchTable.getItem(14));
        if (researchTable.getItem(15) != null)
            p.getInventory().addItem(researchTable.getItem(15));
    }

    // changes research level of researching items
    private ItemStack setResearchLevel(Integer researchLevel, ItemStack researching, Player p) {
        if (researchLevel == null) {
            p.sendMessage(ChatColor.BLUE + "Research level increased by 1");
            ItemStack researchingItem = researching;
            ItemMeta meta = researchingItem.getItemMeta();
            meta.getPersistentDataContainer().set(LifeMaterial.researchLevel(), PersistentDataType.INTEGER, 1);

            meta.displayName(Component.text("Deciphering...", TextColor.color(255, 255, 0)));

            List<Component> lore = new ArrayList<>();

            lore.add(Component.text(ChatColor.MAGIC + "ADJAWHDJADWHDAD", TextColor.color(255, 255, 0)));
            lore.add(Component.text(ChatColor.MAGIC + "ADJAWHDJADWHDAD", TextColor.color(255, 255, 0)));
            lore.add(Component.text(ChatColor.MAGIC + "ADJAWHDJADWHDAD", TextColor.color(255, 255, 0)));

            meta.lore(lore);

            researchingItem.setItemMeta(meta);

            Bukkit.getLogger().info("The researching item: " + researchingItem);
            return researchingItem;
        }

        // Successfully created Sweet Dandelion
        else if (researchLevel.equals(4)) {
            p.sendMessage(ChatColor.GREEN + "Successfully researched sweet dandelion!");
            return new SweetDandelion().sweetDandelion();
        }

        // The material is already maxed research level
        else if (researchLevel.equals(5)) {
            p.sendMessage(ChatColor.RED + "This material is already maxed research!");
            return researching;
        }

        // Increases research level by 1 through item meta and persistent data
        else {
            p.sendMessage(ChatColor.BLUE + "Research level increased by 1");
            ItemMeta meta = researching.getItemMeta();
            meta.getPersistentDataContainer().set(LifeMaterial.researchLevel(), PersistentDataType.INTEGER,
                    researchLevel + 1);
            researching.setItemMeta(meta);

            meta.displayName(Component.text("Deciphering...", TextColor.color(255, 255, 0))
                    .decoration(TextDecoration.ITALIC, false));

            List<Component> lore = new ArrayList<>();

            lore.add(Component.text(ChatColor.MAGIC + "ADJAWHDJADWHDAD", TextColor.color(255, 255, 0)));
            lore.add(Component.text(ChatColor.MAGIC + "ADJAWHDJADWHDAD", TextColor.color(255, 255, 0)));
            lore.add(Component.text(ChatColor.MAGIC + "ADJAWHDJADWHDAD", TextColor.color(255, 255, 0)));

            meta.lore(lore);

            researching.setItemMeta(meta);
        }
        return researching;
    }

    // Gets the research level of said item
    private Integer checkResearchLevel(ItemStack researching) {
        Integer researchLevel = researching.getItemMeta()
                .getPersistentDataContainer()
                .get(LifeMaterial.researchLevel(), PersistentDataType.INTEGER);

        return researchLevel;
    }

    // Get the material type if it is one of the items in the lifeMaterials
    // itemstack
    public Material theLifeMaterial(ItemStack left1, ItemStack left2) {

        Material[] lifeMaterials = new Material[] {
                Material.OAK_SAPLING,
                Material.DANDELION,
                Material.LILY_PAD,
                Material.SUGAR_CANE
        };
        for (Material types : lifeMaterials) {

            if (left1 == null)
                ;
            else if (left1.getType().equals(types)) {
                return types;
            }
            if (left2 == null)
                ;
            else if (left2.getType().equals(types)) {
                return types;
            }
        }
        return null;
    }
}
