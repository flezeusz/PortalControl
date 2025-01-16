package pl.flezy.portal.Commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.flezy.portal.Main;

import java.util.Objects;

public class PortalCommand implements CommandExecutor {
    private final static Main plugin = Main.getInstance();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
        FileConfiguration config = plugin.getConfig();

        if (strings.length >= 1){

            if (strings[0].equalsIgnoreCase("reload")){
                configReload();
                commandSender.sendMessage(ChatColor.GREEN + "Pomyślnie zreloadowałeś config");
                return true;
            }

        }

        if (strings.length >= 2){

            switch (strings[0].toLowerCase()){

                case "nether":

                    if (strings[1].equalsIgnoreCase("on")){
                        if (!config.getBoolean("nether")) {
                            config.set("nether", true);
                            plugin.saveConfig();
                            commandSender.sendMessage(ChatColor.YELLOW + "Włączyłeś nether");
                            announcement("announcement.netherOn");
                            return true;
                        }
                        commandSender.sendMessage(ChatColor.RED + "Nether jest już włączony");
                        return true;
                    }

                    if (strings[1].equalsIgnoreCase("off")){
                        if (config.getBoolean("nether")) {
                            config.set("nether", false);
                            plugin.saveConfig();
                            if (config.getBoolean("teleport.nether")) {
                                Location spawn = Objects.requireNonNull(Bukkit.getWorld("world")).getSpawnLocation();
                                for (Player player : Bukkit.getOnlinePlayers()) {
                                    if (!player.hasPermission("flezy.portal.bypass") &&
                                            player.getLocation().getWorld() == Bukkit.getWorld("world_nether")) {
                                        Location bed = player.getBedSpawnLocation();
                                        player.teleport(Objects.requireNonNullElse(bed, spawn));
                                    }
                                }
                            }
                            commandSender.sendMessage(ChatColor.YELLOW + "Wyłączyłeś nether");
                            announcement("announcement.netherOff");
                            return true;
                        }
                        commandSender.sendMessage(ChatColor.RED + "Nether jest już wyłączony");
                        return true;
                    }
                    break;

                case "end":

                    if (strings[1].equalsIgnoreCase("on")){
                        if (!config.getBoolean("end")) {
                            config.set("end", true);
                            plugin.saveConfig();
                            commandSender.sendMessage(ChatColor.YELLOW + "Włączyłeś end");
                            announcement("announcement.endOn");
                            return true;
                        }
                        commandSender.sendMessage(ChatColor.RED + "End jest już włączony");
                        return true;
                    }

                    if (strings[1].equalsIgnoreCase("off")){
                        if (config.getBoolean("end")) {
                            config.set("end", false);
                            plugin.saveConfig();
                            if (config.getBoolean("teleport.end")) {
                                Location spawn = Objects.requireNonNull(Bukkit.getWorld("world")).getSpawnLocation();
                                for (Player player : Bukkit.getOnlinePlayers()) {
                                    if (!player.hasPermission("flezy.portal.bypass") &&
                                            player.getLocation().getWorld() == Bukkit.getWorld("world_the_end")) {
                                        Location bed = player.getBedSpawnLocation();
                                        player.teleport(Objects.requireNonNullElse(bed, spawn));
                                    }
                                }
                            }
                            commandSender.sendMessage(ChatColor.YELLOW + "Wyłączyłeś end");
                            announcement("announcement.endOff");
                            return true;
                        }
                        commandSender.sendMessage(ChatColor.RED + "End jest już wyłączony");
                        return true;
                    }
                    break;

            }
        }
        commandSender.sendMessage(ChatColor.RED+"Niepoprawne użycie komendy");
        return true;
    }

    private void configReload(){
        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();

        if (!config.getBoolean("nether")) {
            if (config.getBoolean("teleport.nether")) {
                Location spawn = Objects.requireNonNull(Bukkit.getWorld("world")).getSpawnLocation();
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!player.hasPermission("flezy.portal.bypass") &&
                            player.getLocation().getWorld() == Bukkit.getWorld("world_nether")) {
                        Location bed = player.getBedSpawnLocation();
                        player.teleport(Objects.requireNonNullElse(bed, spawn));
                    }
                }
            }
        }

        if (!config.getBoolean("end")) {
            if (config.getBoolean("teleport.end")) {
                Location spawn = Objects.requireNonNull(Bukkit.getWorld("world")).getSpawnLocation();
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!player.hasPermission("flezy.portal.bypass") &&
                            player.getLocation().getWorld() == Bukkit.getWorld("world_the_end")) {
                        Location bed = player.getBedSpawnLocation();
                        player.teleport(Objects.requireNonNullElse(bed, spawn));
                    }
                }
            }
        }

    }

    private void announcement(String path){
        FileConfiguration config = plugin.getConfig();
        if(config.getBoolean(path+".enabled")){
            for (Player online : Bukkit.getOnlinePlayers()){
                online.sendTitle(
                        ChatColor.translateAlternateColorCodes('&',config.getString(path+".title","Ustaw Title w Configu")),
                        ChatColor.translateAlternateColorCodes('&',config.getString(path+".subtitle","Ustaw Subtitle w Configu")),
                        config.getInt(path+".timing.fade_in"),
                        config.getInt(path+".timing.stay"),
                        config.getInt(path+".timing.fade_out"));
                if (config.getBoolean(path+".sound.enabled")){
                    try {
                        Sound sound = Sound.valueOf(config.getString(path+".sound.name"));
                        online.playSound(online.getLocation(),sound,1,1);
                    } catch (IllegalArgumentException ignored) {
                    }
                }
            }
        }
    }
}
