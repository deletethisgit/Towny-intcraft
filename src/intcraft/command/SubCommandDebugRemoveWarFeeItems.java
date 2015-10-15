package intcraft.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.util.ChatTools;

import intcraft.config.IntcraftConfig;
import intcraft.util.InventoryHelper;

public class SubCommandDebugRemoveWarFeeItems extends SubCommand
{
	@Override
	public String getPermission()
	{
		return "towny.intcraft.debug.removewarfeeitems";
	}
	
	@Override
	public String getDescription()
	{
		return "Removes the specified amount of war fee items from your inventory.";
	}

	@Override
	public String getHelp()
	{
		return ChatTools.formatCommand(TownySettings.getLangString("admin_sing"), "/intcraft", "debug removewarfeeitems [amount]", getDescription());
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
		if(args.length >= 3)
		{
			int amount = 0;
			
			try
			{
				amount = Integer.parseInt(args[2]);
			}
			catch (NumberFormatException e)
			{
				TownyMessaging.sendErrorMsg(sender, TownySettings.getLangString("msg_error_must_be_int"));
				return;
			}
			
			InventoryHelper.removeItems(player, IntcraftConfig.getWarFeeItem(), amount);
		}
		else
		{
			TownyMessaging.sendErrorMsg(player, "Invalid sub command parameters.");
		}
	}
}
