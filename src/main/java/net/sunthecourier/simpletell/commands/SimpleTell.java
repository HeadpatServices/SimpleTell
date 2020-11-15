package net.sunthecourier.simpletell.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.sunthecourier.simpletell.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import services.headpat.spigotextensions.brigadier.BrigadierExecutor;
import services.headpat.spigotextensions.brigadier.argumenttypes.PlayerArgumentType;

import static services.headpat.spigotextensions.brigadier.argumenttypes.PlayerArgumentType.player;

public class SimpleTell extends BrigadierExecutor {
	public SimpleTell() {
		super(LiteralArgumentBuilder.<CommandSender>literal("simpletell")
				.then(RequiredArgumentBuilder.<CommandSender, Player>argument("player", player())
						.then(RequiredArgumentBuilder.<CommandSender, String>argument("string", StringArgumentType.greedyString())
								.executes(context -> {
									Player player = PlayerArgumentType.getPlayer(context, "player");
									String str = StringArgumentType.getString(context, "string");
									player.sendMessage(Utils.covertColorCodes(str));
									return 1;
								})
						)
				)
		);
	}
}
