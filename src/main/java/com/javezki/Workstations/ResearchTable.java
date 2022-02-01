package com.javezki.Workstations;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ResearchTable {
    

    public ItemStack getItem()
    {
        ItemStack researchTable = new ItemStack(Material.ENCHANTING_TABLE);

        ItemMeta meta = researchTable.getItemMeta();

        PersistentDataContainer pContainer = meta.getPersistentDataContainer();

        pContainer.set(WorkstationKeys.researchTable(), PersistentDataType.STRING, "researchTable");

        return researchTable;
    }
}
