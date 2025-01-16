package pl.flezy.portal.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import pl.flezy.portal.Main;

public class PortalListener implements Listener {
    private final static Main plugin = Main.getInstance();

    @EventHandler
    private void onPortal(PlayerPortalEvent event){
        Player player = event.getPlayer();

        if (!player.hasPermission("flezy.portal.bypass")){
            FileConfiguration config = plugin.getConfig();
            Location destination = event.getTo();
            if (destination != null) {

                if (destination.getWorld() == Bukkit.getWorld("world_nether") &&
                        !config.getBoolean("nether")) {
                    event.setCancelled(true);

                    if (config.getBoolean("message.enabled")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("message.message", "&cTen wymiar jest zablokowany")));
                    }

                } else if (destination.getWorld() == Bukkit.getWorld("world_the_end") &&
                        !config.getBoolean("end")) {
                    event.setCancelled(true);

                    if (config.getBoolean("message.enabled")) {
                        if (!plugin.message.contains(player)) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("message.message", "&cTen wymiar jest zablokowany")));
                            plugin.message.add(player);
                            Bukkit.getScheduler().runTaskLater(plugin, () -> plugin.message.remove(player), 100);
                        }
                    }

                }

            }
        }
    }
}
