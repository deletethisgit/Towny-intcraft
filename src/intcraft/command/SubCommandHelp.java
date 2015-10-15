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
	private List<String> intcraft_help;

	public SubCommandHelp(HashMap<String, SubCommand> subCommands)
	{
		intcraft_help = new ArrayList<String>();
		intcraft_help.add(ChatTools.formatTitle("/intcraft"));
		for(SubCommand subCommand : subCommands.values())
		{
			intcraft_help.add(subCommand.getHelp());
		}
	}

	@Override
	public String getPermission()
	{
		return "towny.intcraft.help";
	}

	@Override
	public String getDescription()
	{
		return null;
	}

	@Override
	public String getHelp()
	{
		return null;
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
		for (String line : intcraft_help)
		{
			player.sendMessage(line);
		}
	}
}
