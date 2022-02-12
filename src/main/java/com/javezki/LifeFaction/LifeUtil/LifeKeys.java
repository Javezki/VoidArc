package com.javezki.LifeFaction.LifeUtil;

import com.javezki.VoidMain;

import org.bukkit.NamespacedKey;

public class LifeKeys {

    private static VoidMain main = VoidMain.getMain();

    public static NamespacedKey fLifeSword()
    {
        return new NamespacedKey(main, "fLifeSword");
    }

    public static NamespacedKey fLifeTomb()
    {
        return new NamespacedKey(main, "fLifeTomb");
    } 
    
    public static NamespacedKey fLifeChestplate() 
    {
        return new NamespacedKey(main, "fLifeChestPlate");
    }

    public static NamespacedKey fLifeBoots()
    {
        return new NamespacedKey(main, "fLifeBoots");
    }

    public static NamespacedKey fLifeHelmet()
    {
        return new NamespacedKey(main, "fLifeHelmet");
    }
    
    public static NamespacedKey fLifeLeggings()
    {
        return new NamespacedKey(main, "fLifeLeggings");
    }

    public static NamespacedKey fSmallLifeShard()
    {
        return new NamespacedKey(main, "fSmallLifeShard");
    }
}
