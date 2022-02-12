package com.javezki;

import java.io.File;
import java.io.IOException;

import com.javezki.LifeFaction.LifeUtil.LifeRecipes;
import com.javezki.Materials.MaterialRecipes;
import com.javezki.Attributes.AttributeEvents;
import com.javezki.DarkFaction.CorruptedEvents;
import com.javezki.DarkFaction.CorruptedRecipes;
import com.javezki.GeneralFaction.GeneralItems.GeneralEvents.GeneralEvent;
import com.javezki.LifeFaction.LifeUtil.LifeEvents;
import com.javezki.PluginLib.GiveCommand;
import com.javezki.PluginLib.TakeLifeCommand;
import com.javezki.PluginLib.WorkstationEvents;
import com.javezki.TeleportingBow.TeleportingRecipes;
import com.javezki.Workstations.ResearchTableEvent;
import com.javezki.Workstations.WorkstationRecipes;
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
    
    private static File researchLocationFile;

    private static FileConfiguration researchLocationConfig;

    private static VoidMain plugin;
    @Override
    public void onEnable()  {
        plugin = this;
        getServer().getLogger().info(ChatColor.BLUE + "Initializing Teleport Bow Command...");
        this.getCommand("ccg").setExecutor(new GiveCommand());
        this.getCommand("cct").setExecutor(new TakeLifeCommand());
        getServer().getLogger().info(ChatColor.BLUE + "Intializing Event Methods...");
        getServer().getPluginManager().registerEvents(new TeleportEvent(), this);
        getServer().getPluginManager().registerEvents(new WorkstationEvents(), this);
        getServer().getPluginManager().registerEvents(new LifeEvents(), this);
        getServer().getPluginManager().registerEvents(new ResearchTableEvent(), this);
        getServer().getPluginManager().registerEvents(new CorruptedEvents(), this);
        getServer().getPluginManager().registerEvents(new AttributeEvents(), this);
        getServer().getPluginManager().registerEvents(new GeneralEvent(), this);
        getServer().getLogger().info(ChatColor.BLUE + "Initializing Crafting Methods...");
        recipes();
        getServer().getLogger().info(ChatColor.BLUE + "Initializing save file");
        createResearchLocationConfig();
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
        LifeRecipes lifeCraft = new LifeRecipes();
        WorkstationRecipes workCraft = new WorkstationRecipes();
        CorruptedRecipes corruptedCraft = new CorruptedRecipes();
        MaterialRecipes materialCraft = new MaterialRecipes();
        getServer().addRecipe(materialCraft.compressedBlazeRod());
        getServer().addRecipe(workCraft.researchTable());
        getServer().addRecipe(endCraft.teleportBowRecipe());
        getServer().addRecipe(endCraft.enderShard());
        getServer().addRecipe(endCraft.teleportArrowRecipe());
        getServer().addRecipe(lifeCraft.fLifeBoots());
        getServer().addRecipe(lifeCraft.fLifeChestplate());
        getServer().addRecipe(lifeCraft.fLifeLeggings());
        getServer().addRecipe(lifeCraft.fLifeHelmet());
        getServer().addRecipe(lifeCraft.fLifeShard());
        getServer().addRecipe(lifeCraft.fLifeTomb());
        getServer().addRecipe(corruptedCraft.corruptedWand());
    }

    public static FileConfiguration getResearchLocationConfig()  
    {
        return researchLocationConfig;
    }

    private void createResearchLocationConfig()
    {
        researchLocationFile = new File(plugin.getDataFolder(), "/researchTableLocation.yml");
        researchLocationConfig = YamlConfiguration.loadConfiguration(researchLocationFile);
        saveResearchLocationConfig(researchLocationConfig, researchLocationFile);
        if (!researchLocationFile.exists()) {
            saveResource("researchTableLocation.yml", false);
        }
    }

    public static File getResearchLocationFile()
    {
        return researchLocationFile;
    }

    public static void saveResearchLocationConfig(FileConfiguration ymlConfig, File ymlFile)
    {
        try{
            ymlConfig.save(ymlFile);
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
