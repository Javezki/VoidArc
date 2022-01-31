package com.javezki.PluginLib;
 

import com.javezki.VoidMain;

import org.bukkit.NamespacedKey;

public class Namespaces {
    
    public static NamespacedKey corruptedBow()
    {
        return new NamespacedKey(VoidMain.getMain(), "teleportCorruptedBow");
    }

    public static NamespacedKey teleportArrow()
    {
        return new NamespacedKey(VoidMain.getMain(), "teleportArrow");
    }

    public static NamespacedKey teleportShard()
    {
        return new NamespacedKey(VoidMain.getMain(), "teleportShard");
    }

    public static NamespacedKey teleport()
    {
        return new NamespacedKey(VoidMain.getMain(), "teleport");
    }
    public static NamespacedKey cCorruptedTeleportBow()
    {
        return new NamespacedKey(VoidMain.getMain(), "cTeleportBow");
    }
    public static NamespacedKey cTeleportArrow()
    {
        return new NamespacedKey(VoidMain.getMain(), "cTeleportArrow");
    }
    public static NamespacedKey cTeleportShard()
    {
        return new NamespacedKey(VoidMain.getMain(), "cTeleportShard");
    }
    public static NamespacedKey isNotEnchantable()
    {
        return new NamespacedKey(VoidMain.getMain(), "isNotEnchantable");
    }

    public static NamespacedKey lifeArmour()
    {
        return new NamespacedKey(VoidMain.getMain(), "lifeArmour");
    }

    //0 = False, its a vanilla item
    //1 = True, Cannot be crafted into anything
    //2 = Custom Items, Can only be used for custom items
    public static NamespacedKey isNotCraftingMaterial()
    {
        return new NamespacedKey(VoidMain.getMain(), "isNotCraftingMaterials");
    }

    // 0 for no, its repairable through anvil
    // 1 for yes, its repairable through anvil
    public static NamespacedKey isUnrepairable()
    {
        return new NamespacedKey(VoidMain.getMain(), "isUnrepairable");
    }

    public static NamespacedKey isNotVanillaItem()
    {
        return new NamespacedKey(VoidMain.getMain(), "isNotVanillaItem");
    }

    public static NamespacedKey lifeForce()
    {
        return new NamespacedKey(VoidMain.getMain(), "lifeForce");
    }

    public static NamespacedKey lifeTomb()
    {
        return new NamespacedKey(VoidMain.getMain(), "lifeTomb");
    }

}
