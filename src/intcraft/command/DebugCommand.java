package intcraft.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.util.ChatTools;

import intcraft.config.IntcraftConfig;
import intcraft.util.InventoryHelper;

public class DebugCommand implements CommandExecutor
{
	public enum SubCommand
	{
		INGOT_COUNT("ingotcount", 0, "", "Count the amount of iron ingots in your inventory."),
		REMOVE_INGOTS("removeingots", 1, " [amount]", "Remove the specified amount of iron ingots from your inventory."),
		WAR_FEE("warfee", 0, "", "Gets the war fee from the config file."),
		WAR_FEE_RATIO("warfeeratio", 0, "", "Gets the war fee ratio from the config file."),
		WAR_FEE_ITEM("warfeeitem", 0, "", "Gets the war fee item from the config file.");
		
		private String commandName;
		private int argCount;
		private String helpArgs;
		private String description;
		
		SubCommand(String command, int argCount, String helpArgs, String description)
		{
			this.commandName = command;
			this.argCount = argCount;
			this.helpArgs = helpArgs;
			this.description = description;
		}
		
		public boolean checkArgCount(String[] args)
		{
			// Minus one because the sub-command itself is the first argument
			if(args.length - 1 == this.argCount)
			{
				return true;
			}
			return false;
		}
		
		public String toString()
		{
			return this.commandName;
		}
		
		public String getHelpArgs()
		{
			return this.helpArgs;
		}
		
		public String getDescription()
		{
			return this.description;
		}
		
		public static SubCommand byString(String string)
		{
			for(SubCommand subCommand : values())
			{
				if(subCommand.toString().equals(string))
				{
					return subCommand;
				}
			}
			return null;
		}
	}
	
	@SuppressWarnings("unused")
	private static Towny plugin;
	private static final List<String> intcraftdebug_help = new ArrayList<String>();
	static
	{
		intcraftdebug_help.add(ChatTools.formatTitle("/intcraftdebug"));
		for(SubCommand subCommand : SubCommand.values())
		{
			intcraftdebug_help.add(ChatTools.formatCommand(TownySettings.getLangString("admin_sing"), "/intcraftdebug", subCommand.toString() + subCommand.getHelpArgs(), subCommand.getDescription()));
		}
	}
	
	public DebugCommand(Towny instance)
	{
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{	
		if(!(sender instanceof Player)) {return false;}
		Player player = (Player) sender;
		
		if(args.length == 0 || args[0].equalsIgnoreCase("?"))
		{
			for (String line : intcraftdebug_help)
			{
				player.sendMessage(line);
			}
			return true;
		}
		
		SubCommand subCommand = SubCommand.byString(args[0]);
		
		if(subCommand != null)
		{
			switch(subCommand)
			{
			case INGOT_COUNT:
				if(subCommand.checkArgCount(args))
				{
					ingotCount(player);
					return true;
				}
				else
				{
					TownyMessaging.sendErrorMsg(player, "Eg: /intcraftdebug " + subCommand.toString());
				}
				break;
			case REMOVE_INGOTS:
				if(subCommand.checkArgCount(args)) 
				{
					removeIngots(player, Integer.parseInt(args[1]));
					return true;
				}
				else
				{
					TownyMessaging.sendErrorMsg(player, "Eg: /intcraftdebug " + subCommand.toString() + subCommand.getHelpArgs());
				}
				break;
			case WAR_FEE:
				if(subCommand.checkArgCount(args)) 
				{
					warFee(player);
					return true;
				}
				else
				{
					TownyMessaging.sendErrorMsg(player, "Eg: /intcraftdebug " + subCommand.toString());
				}
				break;
			case WAR_FEE_RATIO:
				if(subCommand.checkArgCount(args)) 
				{
					warFeeRatio(player);
					return true;
				}
				else
				{
					TownyMessaging.sendErrorMsg(player, "Eg: /intcraftdebug " + subCommand.toString());
				}
				break;
			case WAR_FEE_ITEM:
				if(subCommand.checkArgCount(args)) 
				{
					warFeeItem(player);
					return true;
				}
				else
				{
					TownyMessaging.sendErrorMsg(player, "Eg: /intcraftdebug " + subCommand.toString());
				}
				break;
			}
		}
		else
		{
			TownyMessaging.sendErrorMsg(player, "Invalid subcommand!");
			return true;
		}
		
		return true;
	}
	
	public void ingotCount(Player player)
	{
		int ingotCount = InventoryHelper.getItemCount(player, Material.IRON_INGOT);
		TownyMessaging.sendMsg(player, "Ingots: " + ingotCount);
	}
	
	public void removeIngots(Player player, int quantity)
	{
		InventoryHelper.removeItems(player, Material.IRON_INGOT, quantity);	
	}
	
	public void warFee(Player player)
	{
		int warFee = IntcraftConfig.getWarFee();
		TownyMessaging.sendMsg(player, "War fee: " + warFee);
	}
	
	public void warFeeRatio(Player player)
	{
		int warFeeRatio = IntcraftConfig.getWarFeeRatio();
		TownyMessaging.sendMsg(player, "War fee ratio: " + warFeeRatio);
	}
	
	public void warFeeItem(Player player)
	{
		Material warFeeItem = IntcraftConfig.getWarFeeItem();
		TownyMessaging.sendMsg(player, "War fee item: " + warFeeItem);
	}
}
