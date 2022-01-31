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
import org.bukkit.persistence.PersistentDataType;

import net.md_5.bungee.api.ChatColor;

public class TeleportEvent implements Listener {

    @EventHandler
    private void checkAmmo(EntityShootBowEvent ev) {
        if (!(ev.getEntity() instanceof Player))
            return;
        Player player = (Player) ev.getEntity();

        // Namespaced key instance main and teleport
        NamespacedKey key = new NamespacedKey(VoidMain.getMain(), "teleport");

        // Get persistentData key for bow
        String bow = ev.getBow()
                .getItemMeta()
                .getPersistentDataContainer()
                .get(key, PersistentDataType.STRING);

        // Get persistentData key for arrow
        String arrow = ev.getConsumable()
                .getItemMeta()
                .getPersistentDataContainer()
                .get(key, PersistentDataType.STRING);

        // Check if its a normal arrow
        if (bow ==null ) {
            bow = "bow";
        }
        if (arrow == null) {
            arrow = "arrow";
        }

        if (!(isBowAndArrowValid(bow, arrow, player)))
        {
            ev.setCancelled(true);
            return;
        }


    }

    @EventHandler
    private void teleportBowEvent(ProjectileHitEvent ev) {

        if (!(isTeleport(ev.getEntity()))) return;

        Player p = (Player) ev.getEntity().getShooter();

        if (!(p.getInventory().contains(new TeleportBow().getTeleportBow())))    return;


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

    private boolean isBowAndArrowValid(String bow, String arrow, Player player)
    {
        if (bow.equals("bow") && arrow.equals("teleportArrow")) {
            player.sendMessage("You must use a Teleport Bow to use this arrow!");
            return false;
        }

        if (arrow.equals("arrow") && bow.equals("teleportBow")) {
            player.sendMessage(ChatColor.RED + "You must use Void Arrows to shoot the bow!");
            return false;
        }
        return true;
    }
    
}
