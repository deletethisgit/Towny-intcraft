package intcraft.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.palmergames.bukkit.util.ChatTools;

public class SubCommandDebugHelp extends SubCommand
{
	private List<String> intcraft_debug_help;

	public SubCommandDebugHelp(HashMap<String, SubCommand> subCommands)
	{
		intcraft_debug_help = new ArrayList<String>();
		intcraft_debug_help.add(ChatTools.formatTitle("/intcraft debug"));
		for(SubCommand subCommand : subCommands.values())
		{
			intcraft_debug_help.add(subCommand.getHelp());
		}
	}

	@Override
	public String getPermission()
	{
		return "towny.intcraft.debug.help";
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
		for (String line : intcraft_debug_help)
		{
			player.sendMessage(line);
		}
	}
}
