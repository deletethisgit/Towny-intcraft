package intcraft.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.util.ChatTools;
import com.sk89q.intake.CommandException;
import com.sk89q.intake.CommandMapping;
import com.sk89q.intake.Description;
import com.sk89q.intake.InvalidUsageException;
import com.sk89q.intake.InvocationCommandException;
import com.sk89q.intake.argument.Namespace;
import com.sk89q.intake.dispatcher.Dispatcher;
import com.sk89q.intake.dispatcher.SimpleDispatcher;
import com.sk89q.intake.util.auth.AuthorizationException;

public class IntcraftCommandExecutor extends SimpleDispatcher implements CommandExecutor
{	
	private Towny plugin;
	
	private IntcraftDispatcher dispatcherWar;
	private IntcraftDispatcher dispatcherWarFee;
	private IntcraftDispatcher dispatcherDebug;
	
	public IntcraftCommandExecutor(Towny plugin) {
		this.plugin = plugin;
		
		dispatcherWar = new IntcraftDispatcher("war");
		dispatcherWar.registerCommand(new CommandWarInfo(), "info");
		registerCommand(dispatcherWar, "war");
		
		dispatcherWarFee  = new IntcraftDispatcher("warfee");
		dispatcherWarFee.registerCommand(new CommandWarFeeInfo(), "info");
		dispatcherWarFee.registerCommand(new CommandWarFeeGet(), "get");
		registerCommand(dispatcherWarFee, "warfee");
		
		dispatcherDebug = new IntcraftDispatcher("debug");
		dispatcherDebug.registerCommand(new CommandDebugRemoveWarFeeItems(), "removewarfeeitems");
		registerCommand(dispatcherDebug, "debug");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		if(args.length <= 0 || args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("?")) {
			sendCommandUsageHelp(new InvalidUsageException("Please choose a sub-command.", this, Lists.newArrayList(alias), true), sender);
			return false;
		}
		
		if(!contains(args[0])) {
			TownyMessaging.sendErrorMsg(sender, TownySettings.getLangString("msg_err_invalid_sub"));
			return false;
		}
		
		Namespace namespace = new Namespace();
		namespace.put(CommandSender.class, sender);
		namespace.put(Towny.class, plugin);
		
		if(contains(args[0])) {
			try {
				call(Joiner.on(" ").join(args), namespace, ImmutableList.<String>builder().add(alias).build());
			} catch (InvalidUsageException e) {
				sendCommandUsageHelp(e, sender);
				return false;
			} catch (AuthorizationException e) {
				TownyMessaging.sendErrorMsg(sender, TownySettings.getLangString("msg_err_command_disable"));	
				return false;
			} catch (CommandException e) {
				TownyMessaging.sendErrorMsg(sender, e.getMessage());
				return false;
			} catch (InvocationCommandException e) {
				TownyMessaging.sendErrorMsg(sender, e.getMessage());
				return false;
			}
			return true;
		} else {
			
		}
		return false;
	}
	
	private void sendCommandUsageHelp(InvalidUsageException e, CommandSender sender) {
        String commandString = Joiner.on(" ").join(e.getAliasStack());
        Description description = e.getCommand().getDescription();

        if (e.isFullHelpSuggested()) {
            if (e.getCommand() instanceof Dispatcher) {
            	sender.sendMessage(ChatTools.formatTitle(commandString));
            	
            	Dispatcher dispatcher = (Dispatcher) e.getCommand();
     
            	for (CommandMapping mapping : dispatcher.getCommands()) {
            		sender.sendMessage(mapping.getDescription().getHelp());
            	}
            } else {
            	String message = e.getMessage();
            	if(message != null) {
            		TownyMessaging.sendErrorMsg(sender, message);
            	} 
            	TownyMessaging.sendErrorMsg(sender, description.getHelp());
            }
        } else {
        	String message = e.getMessage();
        	if(message != null) {
        		TownyMessaging.sendErrorMsg(sender, message);
        	} 
        	TownyMessaging.sendErrorMsg(sender, description.getUsage());
        }
    }	
}
