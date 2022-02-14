package com.javezki.Workstations;

import java.util.ArrayList;
import java.util.List;

import com.javezki.VoidMain;
import com.javezki.Factions.LifeFaction.LifeMaterials.LifeMaterial;
import com.javezki.Factions.LifeFaction.LifeMaterials.LilyPad;
import com.javezki.Factions.LifeFaction.LifeMaterials.MagicalOak;
import com.javezki.Factions.LifeFaction.LifeMaterials.SugarCane;
import com.javezki.Factions.LifeFaction.LifeMaterials.SweetDandelion;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.Event.Result;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
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


    //Handles all events occuring when a research table is placed
    @EventHandler
    private void researchTablePlace(BlockPlaceEvent ev) {

        ItemStack researchTable = ev.getItemInHand();

        if (researchTable == null)
            return;

        if (researchTable.isSimilar(new ResearchTable().getItem())) {
            VoidMain.getResearchLocationConfig().set("Location ID: " + ev.getBlockPlaced().getLocation().toBlockKey(),
                    ev.getBlockPlaced().getLocation());

            VoidMain.saveResearchLocationConfig(VoidMain.getResearchLocationConfig(), VoidMain.getResearchLocationFile());
        }
    }


    //Handles all events whenever a player interacts with an inventory
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


    //Handles all events within the Research Table Inventory
    @EventHandler
    public void researchTableInventory(InventoryClickEvent ev) {

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

            Inventory researchTable = ev.getInventory();

            int itemCount = getAmountOfItems(researchTable);

            if (itemCount > 1) {
                p.sendMessage(ChatColor.RED + "You can only research 1 item at a time!");
                return;
            }

            ItemStack left1 = ev.getInventory().getItem(11);
            ItemStack left2 = ev.getInventory().getItem(12);
            ItemStack researchingItem = new ItemStack(Material.AIR);

            Material researching = theLifeMaterial(left1, left2);

            if (p.getLevel() < 10)
            {
                p.sendMessage(ChatColor.RED + "You need at least 10 levels to attempt a research!");
                return;
            }
            else    p.setLevel(p.getLevel() -10);
            

            // Check if researching value is null
            if (researching == null) {
                p.sendMessage(ChatColor.RED + "This item cannot be researched (yet?)");
                return;
            }

            researchingItem = getResearchingTypeItem(researchingItem, researchTable);

            ItemStack researchedItem = new ItemStack(Material.AIR);

            Integer researchLevel = checkResearchLevel(researchingItem);

            // Dandelion researching for Sweet Dandelion
            if (researching.equals(Material.DANDELION))
                researchedItem = setDandelion(researchLevel, researchingItem, p);
            // Oak Sapling research
            else if (researching.equals(Material.OAK_SAPLING))
                researchedItem = setMagicSapling(researchLevel, researchingItem, p);
            // Lily Pad research
            else if (researching.equals(Material.LILY_PAD))
                researchedItem = setLilyPad(researchLevel, researchingItem, p);
            // Sugar Cane Research
            else if (researching.equals(Material.SUGAR_CANE))
                researchedItem = setSugarCane(researchLevel, researchingItem, p);

            // Sets the Research Table contents
            setResearchedItem(researchTable, researchedItem);
        }
    }

    private ItemStack setSugarCane(Integer researchLevel, ItemStack researchingItem, Player p) {
        ItemStack researchedItem = setResearchLevel(researchLevel, researchingItem, p);

        if (researchedItem == null)
            researchedItem = new SugarCane().getItem();

        return researchedItem;
    }

    private ItemStack setLilyPad(Integer researchLevel, ItemStack researchingItem, Player p) {
        ItemStack researchedItem = setResearchLevel(researchLevel, researchingItem, p);

        if (researchedItem == null)
            researchedItem = new LilyPad().getItem();

        return researchedItem;
    }

    private ItemStack setMagicSapling(Integer researchLevel, ItemStack researchingItem, Player p) {
        ItemStack researchedItem = setResearchLevel(researchLevel, researchingItem, p);

        if (researchedItem == null)
            researchedItem = new MagicalOak().getItem();

        return researchedItem;
    }

    private ItemStack setDandelion(Integer researchLevel, ItemStack researchingItem, Player p) {
        ItemStack researchedItem = setResearchLevel(researchLevel, researchingItem, p);

        if (researchedItem == null)
            researchedItem = new SweetDandelion().getItem();

        return researchedItem;
    }

    private ItemStack getResearchingTypeItem(ItemStack researchingItem, Inventory researchTable) {
        if (researchTable.getItem(11) != null)
            researchingItem = researchTable.getItem(11);

        else if (researchTable.getItem(12) != null)
            researchingItem = researchTable.getItem(12);

        return researchingItem;

    }

    private int getAmountOfItems(Inventory researchTable) {

        int itemCount = 0;
        if (researchTable.getItem(11) != null)
            itemCount += researchTable.getItem(11).getAmount();

        if (researchTable.getItem(12) != null)
            itemCount += researchTable.getItem(12).getAmount();

        return itemCount;
    }

    //Handles all events when a research table's inventory closes
    @EventHandler
    private void onInventoryClose(InventoryCloseEvent ev) {

        if (!ev.getInventory().contains(new ResearchTable().confirm()))
            return;

        ItemStack[] inventory = new ItemStack[] {
                ev.getInventory().getItem(11),
                ev.getInventory().getItem(12),
                ev.getInventory().getItem(14),
                ev.getInventory().getItem(15)
        };

        for (ItemStack item : inventory) {
            if (item == null)
                continue;

            ev.getPlayer().getInventory().addItem(item);
        }
    }

    //Handles all events on research table breaking
    @EventHandler
    private void onResearchTableBreak(BlockBreakEvent ev) {
        if (!ev.getBlock().getType().equals(Material.ENCHANTING_TABLE))
            return;

        if (VoidMain.getResearchLocationConfig().getLocation("Location ID: " + ev.getBlock().getLocation().toBlockKey()) == null) return;
        
        VoidMain.getResearchLocationConfig().set("Location ID: " + ev.getBlock().getLocation().toBlockKey(), null);
        VoidMain.saveResearchLocationConfig(VoidMain.getResearchLocationConfig(), VoidMain.getResearchLocationFile());

        ev.setDropItems(false);

        ev.getBlock().getWorld().dropItem(ev.getBlock().getLocation(), new ResearchTable().getItem());
    }

    private void setResearchedItem(Inventory researchTable, ItemStack researchedItem) {
        researchedItem.setAmount(1);

        researchTable.setItem(15, researchedItem);

        researchTable.setItem(11, null);

        researchTable.setItem(12, null);
    }

    private ItemStack setResearchLevel(Integer researchLevel, ItemStack researching, Player p) {
        if (researchLevel == null) {
            p.sendMessage(ChatColor.BLUE + "Research level increased by 1");

            return newDecipheringItem(researching);
        }

        // Successfully created Sweet Dandelion
        else if (researchLevel.equals(4)) {
            p.sendMessage(ChatColor.GREEN + "Successfully researched *some placeholder here idfk*!");
            return null;
        }

        // The material is already maxed research level
        else if (researchLevel.equals(5)) {
            p.sendMessage(ChatColor.RED + "This material is already maxed research!");
            return researching;
        }

        // Increases research level by 1 through item meta and persistent data
        else {
            p.sendMessage(ChatColor.BLUE + "Research level increased by 1");
            return increasedResearchLevelItem(researching, researchLevel);
        }
    }

    private ItemStack increasedResearchLevelItem(ItemStack researching, Integer researchLevel) {
        ItemMeta meta = researching.getItemMeta();
        meta.getPersistentDataContainer().set(LifeMaterial.researchLevel(), PersistentDataType.INTEGER,
                researchLevel + 1);
        researching.setItemMeta(meta);

        meta.displayName(Component.text("Deciphering...", TextColor.color(255, 255, 0))
                .decoration(TextDecoration.ITALIC, false));

        List<Component> lore = new ArrayList<>();

        lore.add(Component.text(ChatColor.MAGIC + "ADJAWHDJADWHDADASDADADADAD", TextColor.color(255, 255, 0)));
        lore.add(Component.text(ChatColor.MAGIC + "ADJAWHDJADWHDADADADADADASDAWD", TextColor.color(255, 255, 0)));
        lore.add(Component.text(ChatColor.MAGIC + "ADJAWHDJADWHDAwDAWDASDAWDAWAD", TextColor.color(255, 255, 0)));

        meta.lore(lore);

        researching.setItemMeta(meta);

        return researching;
    }

    private ItemStack newDecipheringItem(ItemStack researching) {
        ItemStack researchingItem = researching;
        ItemMeta meta = researchingItem.getItemMeta();
        meta.getPersistentDataContainer().set(LifeMaterial.researchLevel(), PersistentDataType.INTEGER, 1);

        meta.displayName(Component.text("Deciphering...", TextColor.color(255, 255, 0)));

        List<Component> lore = new ArrayList<>();

        lore.add(Component.text(ChatColor.MAGIC + "ADAWDSAWDSAWDSAWDSAWDSAFAWDADAADADADA",
                TextColor.color(255, 255, 0)));
        lore.add(Component.text(ChatColor.MAGIC + "ADJAWADADAwADADAADADAADADADHDJADWHDAD",
                TextColor.color(255, 255, 0)));
        lore.add(Component.text(ChatColor.MAGIC + "ADJAWHADADADWASDAWdsAwDSDJADWHDAD", TextColor.color(255, 255, 0)));

        meta.lore(lore);

        researchingItem.setItemMeta(meta);

        return researchingItem;
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
