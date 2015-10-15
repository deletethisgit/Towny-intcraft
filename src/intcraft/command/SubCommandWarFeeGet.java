package intcraft.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.TownyUniverse;
import com.palmergames.bukkit.util.ChatTools;

import intcraft.util.WarFeeHelper;

public class SubCommandWarFeeGet extends SubCommand
{
	@Override
	public String getPermission()
	{
		return "towny.intcraft.warfee.get";
	}
	
	@Override
	public String getDescription()
	{
		return "Gets the war fee for the specified nation.";
	}

	@Override
	public String getHelp()
	{
		return ChatTools.formatCommand("", "/intcraft", "warfee get [nation]", getDescription());
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
			String name = args[2];
			try 
			{
				Nation argumentNation = TownyUniverse.getDataSource().getNation(name);
				TownyMessaging.sendMsg(player, argumentNation.getName() + " war fee: " + WarFeeHelper.getActualWarFeeFor(argumentNation));
			} 
			catch (NotRegisteredException e) 
			{
				TownyMessaging.sendErrorMsg(player, e.getMessage());
				return;
			}
		}
		else
		{
			TownyMessaging.sendErrorMsg(player, "Invalid sub command parameters.");
			return;
		}
	}
}
