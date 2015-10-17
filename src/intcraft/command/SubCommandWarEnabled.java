package intcraft.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.util.ChatTools;

import intcraft.config.IntcraftConfig;

public class SubCommandWarEnabled extends SubCommand
{
	public SubCommandWarEnabled()
	{
		this.setPermission("towny.intcraft.war.enabled");
		this.setDescription("Checks if war is enabled.");
		this.setFormattedHelp(ChatTools.formatCommand("", "/intcraft", "war enabled", this.getDescription()));
		this.setPlayerOnly(true);
	}

	@Override
	public void onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		Player player = (Player) sender;
		boolean warEnabled = IntcraftConfig.isWarEnabled();
		TownyMessaging.sendMsg(player, "War enabled: " + warEnabled);
	}
}
