package net.sunthecourier.simpletell.commands;

import net.sunthecourier.simpletell.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.bukkit.Bukkit.getServer;

public class SimpleTell implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            return false;
        }
        String[] newArgs = Utils.reparseArguments(args);

        Player player = getServer().getPlayer(newArgs[0]);

        if (player == null) {
            sender.sendMessage(ChatColor.RED + "User not found!");
            return true;
        }
        StringBuilder str = new StringBuilder();
        for (int i = 1; i < newArgs.length; i++) {
            str.append(Utils.parseColors(newArgs[i]));
            str.append(" ");
        }
        player.sendMessage(str.toString());
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 0) {
            return null;
        } else if (args.length == 1) {
            return getServer().getOnlinePlayers().stream().map(HumanEntity::getName).filter(s -> s.contains(args[0])).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }
}
