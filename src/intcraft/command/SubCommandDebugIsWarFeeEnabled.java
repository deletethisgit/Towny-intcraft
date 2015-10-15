package intcraft.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.util.ChatTools;

import intcraft.config.IntcraftConfig;

public class SubCommandDebugIsWarFeeEnabled extends SubCommand
{
	@Override
	public String getPermission()
	{
		return "towny.intcraft.debug.iswarfeeenabled";
	}
	
	@Override
	public String getDescription()
	{
		return "Checks if the war fee is enabled.";
	}

	@Override
	public String getHelp()
	{
		return ChatTools.formatCommand(TownySettings.getLangString("admin_sing"), "/intcraft", "debug iswarfeeenabled", getDescription());
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
		boolean warFeeEnabled = IntcraftConfig.isWarFeeEnabled();
		TownyMessaging.sendMsg(player, "War fee enabled: " + warFeeEnabled);
	}
}
