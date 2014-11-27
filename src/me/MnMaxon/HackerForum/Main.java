package me.MnMaxon.HackerForum;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {
	public static String dataFolder;
	public static Main plugin;
	public static ArrayList<Player> trackList = new ArrayList<Player>();
	public static ArrayList<Player> groundList = new ArrayList<Player>();

	@Override
	public void onEnable() {
		plugin = this;
		dataFolder = this.getDataFolder().getAbsolutePath();
		getServer().getPluginManager().registerEvents(this, this);

		setupConfig();
	}

	public YamlConfiguration setupConfig() {
		cfgSetter("Website", "https://www.google.com/");
		return Config.Load(dataFolder + "/Config.yml");
	}

	public void cfgSetter(String path, Object value) {
		YamlConfiguration cfg = Config.Load(dataFolder + "/Config.yml");
		if (cfg.get(path) == null)
			cfg.set(path, value);
		Config.Save(cfg, dataFolder + "/Config.yml");
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		YamlConfiguration cfg = setupConfig();
		if (StringUtils.containsIgnoreCase(e.getMessage(), "hacker"))
			e.getPlayer().sendMessage(ChatColor.DARK_PURPLE + cfg.getString("Website"));
	}
}