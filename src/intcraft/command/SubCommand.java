package intcraft.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.TownySettings;

public abstract class SubCommand
{
	private String permission;
	private String description;
	private String formattedHelp;
	private boolean playerOnly;
	
	public abstract void onCommand(CommandSender sender, Command command, String label, String[] args);
	
	public boolean execute(CommandSender sender, Command command, String label, String[] args)
	{
		if(getPermission()!= null && !sender.hasPermission(getPermission()))
		{
			TownyMessaging.sendErrorMsg(sender, TownySettings.getLangString("msg_err_command_disable"));
		}
		else
		{
			if(sender instanceof Player)
			{
				onCommand(sender, command, label, args);
			}
			else
			{
				if(!isPlayerOnly())
				{
					onCommand(sender, command, label, args);	
				}
				else
				{
					TownyMessaging.sendErrorMsg(sender, TownySettings.getLangString("msg_player_only"));
				}
			}
		}

		return true;
	}

	public String getPermission()
	{
		return permission;
	}

	public void setPermission(String permission)
	{
		this.permission = permission;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getFormattedHelp()
	{
		return formattedHelp;
	}

	public void setFormattedHelp(String formattedHelp)
	{
		this.formattedHelp = formattedHelp;
	}

	public boolean isPlayerOnly()
	{
		return playerOnly;
	}

	public void setPlayerOnly(boolean playerOnly)
	{
		this.playerOnly = playerOnly;
	}
}
