package intcraft.command;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.util.ChatTools;

import intcraft.config.IntcraftConfig;

public class SubCommandWarFeeItem extends SubCommand
{
	public SubCommandWarFeeItem()
	{
		this.setPermission("towny.intcraft.warfee.item");
		this.setDescription("Gets the war fee item.");
		this.setFormattedHelp(ChatTools.formatCommand("", "/intcraft", "warfee item", this.getDescription()));
		this.setPlayerOnly(true);
	}

	@Override
	public void onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		Player player = (Player) sender;
		Material warFeeItem = IntcraftConfig.getWarFeeItem();
		TownyMessaging.sendMsg(player, "War fee item: " + warFeeItem);
	}
}
