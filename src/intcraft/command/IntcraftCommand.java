package intcraft.command;

import java.util.Collections;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.sk89q.intake.CommandCallable;
import com.sk89q.intake.CommandException;
import com.sk89q.intake.Description;
import com.sk89q.intake.InvocationCommandException;
import com.sk89q.intake.argument.Arguments;
import com.sk89q.intake.argument.CommandArgs;
import com.sk89q.intake.argument.CommandContext;
import com.sk89q.intake.argument.Namespace;
import com.sk89q.intake.util.auth.AuthorizationException;

public abstract class IntcraftCommand implements CommandCallable {

	protected Description description;
	
	@Override
	public List<String> getSuggestions(String arguments, Namespace locals) throws CommandException {
		return Collections.emptyList();
	}

	@Override
	public boolean call(String arguments, Namespace namespace, List<String> parentCommands) throws CommandException, InvocationCommandException, AuthorizationException {
		if (!testPermission(namespace)) {
			throw new AuthorizationException();
		}
		CommandSender sender = namespace.get(CommandSender.class);
		String command = parentCommands.get(parentCommands.size()-1);
		CommandContext context = new CommandContext(CommandContext.split(command + " " + arguments), null, false, namespace);
		CommandArgs args = Arguments.viewOf(context);
		return execute(args, sender, parentCommands);
	}
	
	public abstract boolean execute(CommandArgs args, CommandSender sender, List<String> parentCommands) throws CommandException, InvocationCommandException, AuthorizationException;
	
	@Override
	public Description getDescription() {
		return description;
	}

	@Override
	public boolean testPermission(Namespace namespace) {
		if(description.getPermissions().isEmpty()) {
			return true;
		} else {
			CommandSender sender = namespace.get(CommandSender.class);
			if (sender != null) {
				if (sender instanceof ConsoleCommandSender) {
					return true;
				} else if (sender instanceof Player) {
					Player player = (Player) sender;
					for (String perm : description.getPermissions()) {
						if (player.hasPermission(perm)) {
							return true;
						}
					}
					
				}
			} else {
				throw new NullPointerException();
			}
		}
		return false;
	}

}
