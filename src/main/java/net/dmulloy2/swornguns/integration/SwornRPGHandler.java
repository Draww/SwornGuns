/**
 * (c) 2014 dmulloy2
 */
package net.dmulloy2.swornguns.integration;

import java.util.logging.Level;

import lombok.Getter;
import net.dmulloy2.integration.IntegrationHandler;
import net.dmulloy2.swornguns.SwornGuns;
import net.dmulloy2.swornrpg.SwornRPG;
import net.dmulloy2.swornrpg.types.PlayerData;
import net.dmulloy2.util.Util;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

/**
 * @author dmulloy2
 */

public class SwornRPGHandler extends IntegrationHandler
{
	private @Getter boolean enabled;
	private @Getter SwornRPG swornRPG;

	private final SwornGuns plugin;
	public SwornRPGHandler(SwornGuns plugin)
	{
		this.plugin = plugin;
		this.setup();
	}

	@Override
	public final void setup()
	{
		try
		{
			PluginManager pm = plugin.getServer().getPluginManager();
			if (pm.getPlugin("SwornRPG") != null)
			{
				swornRPG = (SwornRPG) pm.getPlugin("SwornRPG");
				plugin.getLogHandler().log("SwornRPG integration successful!");
				enabled = true;
			}
		}
		catch (Throwable ex)
		{
			plugin.getLogHandler().debug(Level.WARNING, Util.getUsefulStack(ex, "setting up SwornRPG integration"));
			enabled = false;
		}
	}

	public final PlayerData getPlayerData(Player player)
	{
		try
		{
			if (enabled && swornRPG != null)
				return swornRPG.getPlayerDataCache().getData(player);
		} catch (Throwable ex) { }
		return null;
	}
}