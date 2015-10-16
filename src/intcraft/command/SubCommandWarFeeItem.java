package intcraft.command;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.util.ChatTools;

import intcraft.config.IntcraftConfig;

public class SubCommandWarFeeItem extends SubCommand
{
	@Override
	public String getPermission()
	{
		return "towny.intcraft.warfee.item";
	}
	
	@Override
	public String getDescription()
	{
		return "Gets the war fee item.";
	}

	@Override
	public String getHelp()
	{
		return ChatTools.formatCommand("", "/intcraft", "warfee item", getDescription());
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
		Material warFeeItem = IntcraftConfig.getWarFeeItem();
		TownyMessaging.sendMsg(player, "War fee item: " + warFeeItem);
	}
}