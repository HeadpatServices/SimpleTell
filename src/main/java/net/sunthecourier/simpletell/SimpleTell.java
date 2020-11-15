package net.sunthecourier.simpletell;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleTell extends JavaPlugin {
	@Getter
	private static SimpleTell instance;

	@Override
	public void onEnable() {
		net.sunthecourier.simpletell.commands.SimpleTell simpleTell = new net.sunthecourier.simpletell.commands.SimpleTell();
		getCommand("simpletell").setExecutor(simpleTell);
		getCommand("simpletell").setTabCompleter(simpleTell);
	}

	@Override
	public void onLoad() {
		instance = this;
	}
}
