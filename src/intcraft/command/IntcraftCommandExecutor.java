package intcraft.command;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.TownySettings;

import intcraft.util.InventoryHelper;

public class IntcraftCommandExecutor implements CommandExecutor
{	
	@SuppressWarnings("unused")
	private static Towny plugin;
	private static HashMap<String, SubCommand> subCommands = new HashMap<String, SubCommand>();
	
	public IntcraftCommandExecutor(Towny instance)
	{
		plugin = instance;
		subCommands.put("debug", new SubCommandDebug());
		subCommands.put("?", new SubCommandHelp(subCommands));
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
	
	public void addSubCommand(String name, SubCommand subCommand)
	{
		subCommands.put(name, subCommand);
	}

	public void removeIngots(Player player, int quantity)
	{
		InventoryHelper.removeItems(player, Material.IRON_INGOT, quantity);	
	}
}
