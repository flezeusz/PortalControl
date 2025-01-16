package pl.flezy.portal.TabCompleter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PortalTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
        List<String> arguments = new ArrayList<>();

        if (strings.length == 1){
            arguments.add("nether");
            arguments.add("end");
            arguments.add("reload");
        }

        if (strings.length == 2){
            if (strings[0].equalsIgnoreCase("nether") || strings[0].equalsIgnoreCase("end")){
                arguments.add("on");
                arguments.add("off");
            }
        }

        if (!arguments.isEmpty()){
            arguments.removeIf(arg -> !arg.toLowerCase().contains(strings[strings.length - 1].toLowerCase()));
        }
        return arguments;
    }
}
