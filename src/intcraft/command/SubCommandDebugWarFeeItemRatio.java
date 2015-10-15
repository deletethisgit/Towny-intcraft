package intcraft.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.util.ChatTools;

import intcraft.config.IntcraftConfig;

public class SubCommandDebugWarFeeItemRatio extends SubCommand
{
	@Override
	public String getPermission()
	{
		return "towny.intcraft.debug.warfeeitemratio";
	}
	
	@Override
	public String getDescription()
	{
		return "Gets the amount that the war fee is reduced by per resident.";
	}

	@Override
	public String getHelp()
	{
		return ChatTools.formatCommand(TownySettings.getLangString("admin_sing"), "/intcraft", "debug warfeeitemratio", getDescription());
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
		int warFeeRatio = IntcraftConfig.getWarFeeRatio();
		TownyMessaging.sendMsg(player, "War fee ratio: " + warFeeRatio);
	}
}
