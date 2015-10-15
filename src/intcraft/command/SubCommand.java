package intcraft.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.TownySettings;

public abstract class SubCommand
{
	public abstract String getPermission();
	
	public abstract String getDescription();
	
	public abstract String getHelp();
	
	public abstract boolean isPlayerOnly();
	
	public boolean execute(CommandSender sender, Command command, String label, String[] args)
	{
		if(!sender.hasPermission(getPermission()))
		{
			TownyMessaging.sendErrorMsg(sender, TownySettings.getLangString("msg_err_command_disable"));
		}
		else
		{
			if(isPlayerOnly())
			{
				if(sender instanceof Player)
				{
					onCommand(sender, command, label, args);
				}
			}
			else
			{
				onCommand(sender, command, label, args);
			}
		}
		
		return true;
	}
	
	public abstract void onCommand(CommandSender sender, Command command, String label, String[] args);
}
