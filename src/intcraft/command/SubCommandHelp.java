package intcraft.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.palmergames.bukkit.util.ChatTools;

public class SubCommandHelp extends SubCommand
{
	private List<String> help;

	public SubCommandHelp(String title, HashMap<String, SubCommand> subCommands)
	{
		this.setPermission(null);
		this.setDescription(null);
		this.setFormattedHelp(null);
		this.setPlayerOnly(true);
		
		help = new ArrayList<String>();
		help.add(ChatTools.formatTitle(title));
		for(SubCommand subCommand : subCommands.values())
		{
			help.add(subCommand.getFormattedHelp());
		}
	}

	@Override
	public void onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		Player player = (Player) sender;
		for (String line : help)
		{
			player.sendMessage(line);
		}
	}
}
