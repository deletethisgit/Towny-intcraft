package intcraft.command;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.util.ChatTools;

public class SubCommandWarFee extends SubCommand
{
	private static HashMap<String, SubCommand> subCommands = new HashMap<String, SubCommand>();
	
	public SubCommandWarFee()
	{
		subCommands.put("enabled", new SubCommandWarFeeEnabled());
		subCommands.put("base", new SubCommandWarFeeBase());
		subCommands.put("item", new SubCommandWarFeeItem());
		subCommands.put("ratio", new SubCommandWarFeeRatio());
		subCommands.put("itemcount", new SubCommandWarFeeItemCount());
		subCommands.put("get", new SubCommandWarFeeGet());
		subCommands.put("?", new SubCommandHelp("/intcraft warfee", subCommands));
	}
	
	@Override
	public String getPermission()
	{
		return null;
	}
	
	@Override
	public String getDescription()
	{
		return "List commands related to war fees.";
	}
	
	@Override
	public String getHelp()
	{
		return ChatTools.formatCommand("", "/intcraft", "warfee ?", getDescription());
	}

	@Override
	public boolean isPlayerOnly()
	{
		return true;
	}
	
	@Override
	public void onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if(args.length >= 2)
		{
			boolean match = false;
			
			for(String key : subCommands.keySet())
			{
				if(key.equals(args[1]))
				{
					subCommands.get(key).execute(sender, command, label, args);
					match = true;
				}
			}
			
			if(!match)
			{
				TownyMessaging.sendErrorMsg(sender, TownySettings.getLangString("msg_err_invalid_sub"));
			}
		}
		else
		{
			subCommands.get("?").execute(sender, command, label, args);
		}
	}
}
