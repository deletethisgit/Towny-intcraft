package intcraft.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.util.ChatTools;

import intcraft.config.IntcraftConfig;

public class SubCommandWarEnabled extends SubCommand
{
	@Override
	public String getPermission()
	{
		return "towny.intcraft.war.enabled";
	}
	
	@Override
	public String getDescription()
	{
		return "Checks if war is enabled.";
	}

	@Override
	public String getHelp()
	{
		return ChatTools.formatCommand("", "/intcraft", "war enabled", getDescription());
	}

	@Override
	public boolean isPlayerOnly()
	{
		return true;
	}

	@Override
	public void onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		Player player = (Player) sender;
		boolean warEnabled = IntcraftConfig.isWarEnabled();
		TownyMessaging.sendMsg(player, "War enabled: " + warEnabled);
	}
}
