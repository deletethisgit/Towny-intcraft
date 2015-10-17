package intcraft.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.util.ChatTools;

import intcraft.config.IntcraftConfig;

public class SubCommandWarFeeRatio extends SubCommand
{
	public SubCommandWarFeeRatio()
	{
		this.setPermission("towny.intcraft.warfee.ratio");
		this.setDescription("Gets the amount that the war fee is reduced by per resident in target nation.");
		this.setFormattedHelp(ChatTools.formatCommand("", "/intcraft", "warfee ratio", this.getDescription()));
		this.setPlayerOnly(true);
	}

	@Override
	public void onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		Player player = (Player) sender;
		int warFeeRatio = IntcraftConfig.getWarFeeRatio();
		TownyMessaging.sendMsg(player, "War fee ratio: " + warFeeRatio);
	}
}
