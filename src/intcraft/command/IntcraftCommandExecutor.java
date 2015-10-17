package intcraft.command;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.towny.command.BaseCommand;

public class IntcraftCommandExecutor extends BaseCommand implements CommandExecutor
{	
	@SuppressWarnings("unused")
	private static Towny plugin;
	private static HashMap<String, SubCommand> subCommands = new HashMap<String, SubCommand>();
	
	public IntcraftCommandExecutor(Towny instance)
	{
		plugin = instance;
		subCommands.put("war", new SubCommandWar());
		subCommands.put("warfee", new SubCommandWarFee());
		subCommands.put("debug", new SubCommandDebug());
		subCommands.put("?", new SubCommandHelp("/intcraft", subCommands));
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if(args.length >= 1)
		{
			boolean match = false;
			
			for(String key : subCommands.keySet())
			{
				if(key.equals(args[0]))
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
		
		return true;	
	}
}
