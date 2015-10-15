package intcraft.command;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.util.ChatTools;

public class SubCommandDebug extends SubCommand
{
	private static HashMap<String, SubCommand> subCommands = new HashMap<String, SubCommand>();
	
	public SubCommandDebug()
	{
		subCommands.put("warfee", new SubCommandDebugWarFee());
		subCommands.put("warfeeitem", new SubCommandDebugWarFeeItem());
		subCommands.put("warfeeitemratio", new SubCommandDebugWarFeeItemRatio());
		subCommands.put("warfeeitemcount", new SubCommandDebugWarFeeItemCount());
		subCommands.put("removewarfeeitems", new SubCommandDebugRemoveWarFeeItems());
		subCommands.put("?", new SubCommandDebugHelp(subCommands));
	}
	
	@Override
	public String getPermission()
	{
		return "towny.intcraft.debug";
	}
	
	@Override
	public String getDescription()
	{
		return "List commands for debugging.";
	}
	
	@Override
	public String getHelp()
	{
		return ChatTools.formatCommand(TownySettings.getLangString("admin_sing"), "/intcraft", "debug ?", getDescription());
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
