package intcraft.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.util.ChatTools;

import intcraft.config.IntcraftConfig;
import intcraft.util.InventoryHelper;

public class SubCommandDebugWarFeeItemCount extends SubCommand
{
	@Override
	public String getPermission()
	{
		return "towny.intcraft.debug.warfeeitemcount";
	}
	
	@Override
	public String getDescription()
	{
		return "Gets the number of war fee items in your inventory.";
	}

	@Override
	public String getHelp()
	{
		return ChatTools.formatCommand(TownySettings.getLangString("admin_sing"), "/intcraft", "debug warfeeitemcount", getDescription());
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
		int warFeeItemCount = InventoryHelper.getItemCount(player, IntcraftConfig.getWarFeeItem());
		TownyMessaging.sendMsg(player,  IntcraftConfig.getWarFeeItem().toString() + ": " + warFeeItemCount);
	}
}
