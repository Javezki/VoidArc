package com.javezki.TeleportingBow;

import com.javezki.VoidMain;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class TeleportEvent implements Listener {

    @EventHandler
    private void checkAmmo(EntityShootBowEvent ev) {
        if (!(ev.getEntity() instanceof Player))
            return;
        Player player = (Player) ev.getEntity();

        ItemStack bow = ev.getBow();

        ItemStack arrow = ev.getConsumable();
        

        // Check if its a normal arrow

        if (arrow.equals(new TeleportArrow().getTeleportArrow()) && !(bow.equals(new TeleportBow().getTeleportBow()))) 
        {
            ev.setCancelled(true);
            player.sendMessage(ChatColor.RED + "Must use a Teleporting Bow to shoot the Void Arrow!");
        }

        if (bow.equals(new TeleportBow().getTeleportBow()) && (!(arrow.equals(new TeleportArrow().getTeleportArrow()))))
        {
            ev.setCancelled(true);
            player.sendMessage(ChatColor.RED + "Must use a Void Arrow to shoot the Teleporting Bow!");
        }


    }

    @EventHandler
    private void teleportBowEvent(ProjectileHitEvent ev) {

        if (!(isTeleport(ev.getEntity()))) return;

        Player p = (Player) ev.getEntity().getShooter();

        if (p.getInventory().getItemInMainHand() == null) return;

        if (!(p.getInventory().getItemInMainHand().equals(new TeleportBow().getTeleportBow())))    return;


        if (ev.getHitEntity() == null)
        {
            hitBlockTeleport(ev.getHitBlock().getLocation(), ev.getHitBlockFace(), p);
        }

        if (ev.getHitBlock() == null)
        {
            entityTeleport(ev.getEntity().getLocation(), p);
        }

        ev.getEntity().remove();
        //Damaged
        //Corrupted
        //Reinforced

    }

    private void hitBlockTeleport(Location hitBlock, BlockFace hitBlockFace, Player p)  
    {

        double x = hitBlock.getX();
        
        double y = hitBlock.getY();

        double z = hitBlock.getZ();

        World world = hitBlock.getWorld();

        switch (hitBlockFace)
        {
            case DOWN:
                p.teleport(new Location(world, x + 0.5, y - 1, z + 0.5));
                break;
            case EAST:
                p.teleport(new Location(world, x + 1.5, y , z + 1.5));
                break;
            case NORTH:
                p.teleport(new Location(world, x + 0.5, y + 1, z - 0.5));
                break;
            case SELF:
                p.teleport(new Location(world, x + 0.5, y + 1, z + 0.5));
                break;
            case SOUTH:
                p.teleport(new Location(world, x + 0.5, y + 1, z + 0.5));    
                break;
            case UP:
                p.teleport(new Location(world, x + 0.5, y + 1, z + 0.5));
                break;
            case WEST:
                p.teleport(new Location(world, x - 0.5, y, z + 0.5));
                break;
            default:
                p.teleport(new Location(world, x + 0.5, y + 1, z + 0.5));
                break;
            
        }

        p.damage(6);

        p.sendMessage(ChatColor.DARK_PURPLE + "The corruption eats away at your life.");
    }

    private void entityTeleport(Location entity, Player p)
    {
        p.teleport(entity);

        p.damage(6);

        p.sendMessage(ChatColor.DARK_PURPLE + "The corruption eats away at your life.");
    }

    private boolean isTeleport(Projectile proj)
    {
        if (!(proj instanceof Arrow)) return false;

        if (!(proj.getShooter() instanceof Player)) return false;

        return true;
    }
    
}
