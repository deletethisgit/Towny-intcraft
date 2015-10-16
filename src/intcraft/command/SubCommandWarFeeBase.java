package intcraft.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.util.ChatTools;

import intcraft.config.IntcraftConfig;

public class SubCommandWarFeeBase extends SubCommand
{
	@Override
	public String getPermission()
	{
		return "towny.intcraft.warfee.base";
	}
	
	@Override
	public String getDescription()
	{
		return "Gets the base amount of war fee items required to start a war.";
	}

	@Override
	public String getHelp()
	{
		return ChatTools.formatCommand("", "/intcraft", "warfee base", getDescription());
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
		int warFee = IntcraftConfig.getBaseWarFee();
		TownyMessaging.sendMsg(player, "Base war fee: " + warFee);
	}
}