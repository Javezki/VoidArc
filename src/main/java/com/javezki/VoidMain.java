package com.javezki;

import java.io.File;
import java.io.IOException;

import com.javezki.LifeFaction.LifeUtil.LifeCraftingRecipes;
import com.javezki.LifeFaction.LifeUtil.LifeEvents;
import com.javezki.PluginLib.WorkstationEvents;
import com.javezki.TeleportingBow.TeleportingRecipes;
import com.javezki.Workstations.ResearchTableEvent;
import com.javezki.TeleportingBow.TeleportBowCommand;
import com.javezki.TeleportingBow.TeleportEvent;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

/**
 * Hello world!
 *
 */

public class VoidMain extends JavaPlugin
{
    
    private static File customConfigFile;

    private static FileConfiguration customConfig;

    private static VoidMain plugin;
    @Override
    public void onEnable()  {
        plugin = this;
        getServer().getLogger().info(ChatColor.BLUE + "Initializing Teleport Bow Command...");
        this.getCommand("ccg").setExecutor(new TeleportBowCommand());
        getServer().getLogger().info(ChatColor.BLUE + "Intializing Event Methods...");
        getServer().getPluginManager().registerEvents(new TeleportEvent(), this);
        getServer().getPluginManager().registerEvents(new WorkstationEvents(), this);
        getServer().getPluginManager().registerEvents(new LifeEvents(), this);
        getServer().getPluginManager().registerEvents(new ResearchTableEvent(), this);
        getServer().getLogger().info(ChatColor.BLUE + "Initializing Crafting Methods...");
        recipes();
        getServer().getLogger().info(ChatColor.BLUE + "Initializing save file");
        createCustomConfig();
        getServer().getLogger().info(ChatColor.BLUE + "BowMain Initialized Properly!");
    }


    @Override 
    public void onDisable() {
        getServer().getLogger().info("Goodbye");
    }

    public static VoidMain getMain()   {
        return plugin;
    }

    private void recipes()  {
        TeleportingRecipes endCraft = new TeleportingRecipes();
        LifeCraftingRecipes lifeCraft = new LifeCraftingRecipes();
        getServer().addRecipe(endCraft.teleportBowRecipe());
        getServer().addRecipe(endCraft.enderShard());
        getServer().addRecipe(endCraft.teleportArrowRecipe());
        getServer().addRecipe(lifeCraft.fLifeBoots());
        getServer().addRecipe(lifeCraft.fLifeChestplate());
        getServer().addRecipe(lifeCraft.fLifeLeggings());
        getServer().addRecipe(lifeCraft.fLifeHelmet());
        getServer().addRecipe(lifeCraft.fLifeShard());
    }

    public static FileConfiguration getResearchLocationConfig()  
    {
        return customConfig;
    }

    private void createCustomConfig()
    {
        customConfigFile = new File(plugin.getDataFolder(), "/researchTableLocation.yml");
        customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
        saveCustomConfig(customConfig, customConfigFile);
        if (!customConfigFile.exists()) {
            saveResource("researchTableLocation.yml", false);
        }
    }

    public static File getCustomFile()
    {
        return customConfigFile;
    }

    public static void saveCustomConfig(FileConfiguration ymlConfig, File ymlFile)
    {
        try{
            ymlConfig.save(ymlFile);
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
