package intcraft.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.util.ChatTools;

import intcraft.config.IntcraftConfig;

public class SubCommandWarFeeEnabled extends SubCommand
{
	@Override
	public String getPermission()
	{
		return "towny.intcraft.warfee.enabled";
	}
	
	@Override
	public String getDescription()
	{
		return "Checks if war fees are enabled.";
	}

	@Override
	public String getHelp()
	{
		return ChatTools.formatCommand("", "/intcraft", "warfee enabled", getDescription());
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
		boolean warFeeEnabled = IntcraftConfig.isWarFeeEnabled();
		TownyMessaging.sendMsg(player, "War fee enabled: " + warFeeEnabled);
	}
}