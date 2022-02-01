package com.javezki.Workstations;

import com.javezki.VoidMain;

import org.bukkit.NamespacedKey;

public class WorkstationKeys {

    private static VoidMain main = VoidMain.getMain();
    
    public static NamespacedKey researchTable()
    {
        return new NamespacedKey(main, Workstations.RESEARCHTABLE.name());
    }
}
