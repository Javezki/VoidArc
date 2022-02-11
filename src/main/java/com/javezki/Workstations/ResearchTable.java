package com.javezki.Workstations;

import java.util.ArrayList;
import java.util.List;

import com.javezki.VoidMain;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class ResearchTable {
    
    final static String ResearchTable = "RESEARCH_TABLE";

    public ItemStack getItem()
    {
        ItemStack researchTable = new ItemStack(Material.ENCHANTING_TABLE);

        ItemMeta meta = researchTable.getItemMeta();

        PersistentDataContainer pContainer = meta.getPersistentDataContainer();

        pContainer.set(WorkstationKeys.researchTable(), PersistentDataType.STRING, "researchTable");

        meta.displayName(Component.text("Research Table", TextColor.color(255, 0, 0))
        .decoration(TextDecoration.ITALIC, false));


        List<Component> lore = new ArrayList<>();

        lore.add(Component.text("")
        );

        lore.add(Component.text("The research table is a vital step, to ")
        .decoration(TextDecoration.ITALIC, false)
        );

        lore.add(Component.text("AHDAWHDUAHDUAWHHUDW")
        .decoration(TextDecoration.ITALIC, false)
        .decoration(TextDecoration.OBFUSCATED, true)
        );

        
        meta.lore(lore);

        researchTable.setItemMeta(meta);

        return researchTable;
    }

    public Inventory researchTableInventory()
    {
        Inventory researchTable = Bukkit.createInventory(null, 9*3, Component.text("Research Table"));

        ItemStack orangePane = orangePane();

        ItemStack air = new ItemStack(Material.AIR);

        ItemStack bluePane = bluePane();

        ItemStack confirm = confirm();

        ItemStack[] outline = new ItemStack[]
        {
            orangePane, orangePane ,orangePane, orangePane, orangePane, orangePane, orangePane, orangePane, orangePane,
            bluePane, bluePane, air, air, confirm, air, air, bluePane, bluePane,
            orangePane, orangePane ,orangePane, orangePane, orangePane, orangePane, orangePane, orangePane, orangePane
        };

        researchTable.setContents(outline);
        
        return researchTable;
    }

    private ItemStack orangePane() {
        ItemStack orangePane = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);

        ItemMeta meta = orangePane.getItemMeta();

        meta.displayName(Component.text(""));

        orangePane.setItemMeta(meta);

        return orangePane;
    }

    public ItemStack confirm()
    {

        NamespacedKey key = new NamespacedKey(VoidMain.getMain(), ResearchTable);
        ItemStack confirm = new ItemStack(Material.GREEN_STAINED_GLASS);

        ItemMeta meta = confirm.getItemMeta();

        meta.displayName(Component.text("Confirm", TextColor.color(0, 255, 0))
        .decoration(TextDecoration.ITALIC, false));

        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, ResearchTable);

        confirm.setItemMeta(meta);

        return confirm;
    }

    private ItemStack bluePane()
    {
        ItemStack bluePane = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);;

        ItemMeta meta = bluePane.getItemMeta();

        meta.displayName(Component.text(""));

        bluePane.setItemMeta(meta);

        return bluePane;
    }

    public ItemStack[] researchTableContentStacks()
    {
        ItemStack[] content = new ItemStack[]
        {
            bluePane(),
            orangePane(),
        };
        return content;
    }
}
