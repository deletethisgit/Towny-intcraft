package intcraft.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.util.ChatTools;

import intcraft.config.IntcraftConfig;

public class SubCommandWarFeeEnabled extends SubCommand
{
	public SubCommandWarFeeEnabled()
	{
		this.setPermission("towny.intcraft.warfee.enabled");
		this.setDescription("Checks if war fees are enabled.");
		this.setFormattedHelp(ChatTools.formatCommand("", "/intcraft", "warfee enabled", this.getDescription()));
		this.setPlayerOnly(true);
	}

	@Override
	public void onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		Player player = (Player) sender;
		boolean warFeeEnabled = IntcraftConfig.isWarFeeEnabled();
		TownyMessaging.sendMsg(player, "War fee enabled: " + warFeeEnabled);
	}
}
