package pl.flezy.portal;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import pl.flezy.portal.Commands.PortalCommand;
import pl.flezy.portal.Listeners.PortalListener;
import pl.flezy.portal.TabCompleter.PortalTabCompleter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main extends JavaPlugin {

    private static Main instance;

    public List<Player> message = new ArrayList<>();

    @Override
    public void onEnable(){
        instance = this;

        Objects.requireNonNull(getCommand("portal")).setExecutor(new PortalCommand());

        Objects.requireNonNull(getCommand("portal")).setTabCompleter(new PortalTabCompleter());

        getServer().getPluginManager().registerEvents(new PortalListener(),this);

        getLogger().info("\u001B[1mPlugin wykonany przez Flezy! \u001B[94mhttps://flezy.pl\u001B[0m");

        saveDefaultConfig();
        reloadConfig();
    }

    public static Main getInstance(){
        return instance;
    }
}
