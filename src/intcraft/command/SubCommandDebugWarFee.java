package intcraft.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.util.ChatTools;

import intcraft.config.IntcraftConfig;

public class SubCommandDebugWarFee extends SubCommand
{
	@Override
	public String getPermission()
	{
		return "towny.intcraft.debug.warfee";
	}
	
	@Override
	public String getDescription()
	{
		return "Gets the amount of war fee items required to start a war.";
	}

	@Override
	public String getHelp()
	{
		return ChatTools.formatCommand(TownySettings.getLangString("admin_sing"), "/intcraft", "debug warfee", getDescription());
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
		int warFee = IntcraftConfig.getWarFee();
		TownyMessaging.sendMsg(player, "War fee: " + warFee);
	}
}
