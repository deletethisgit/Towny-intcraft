package intcraft.command;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;
import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.util.ChatTools;
import com.sk89q.intake.CommandException;
import com.sk89q.intake.ImmutableDescription;
import com.sk89q.intake.InvalidUsageException;
import com.sk89q.intake.InvocationCommandException;
import com.sk89q.intake.argument.ArgumentParseException;
import com.sk89q.intake.argument.CommandArgs;
import com.sk89q.intake.argument.MissingArgumentException;
import com.sk89q.intake.util.auth.AuthorizationException;

import intcraft.config.IntcraftConfig;
import intcraft.util.PlayerInventories;

public class CommandDebugRemoveWarFeeItems extends IntcraftCommand {

	public CommandDebugRemoveWarFeeItems() {
		ImmutableDescription.Builder builder = new ImmutableDescription.Builder();
		this.description = builder
				.setShortDescription("Removes the specified amount of war fee items from your inventory.")
				.setHelp(ChatTools.formatCommand(TownySettings.getLangString("admin_sing"), "/intcraft debug", "removewarfeeitems [quantity]", builder.getShortDescription()))
				.setPermissions(Lists.newArrayList("towny.intcraft.debug.removewarfeeitems"))
				.setUsageOverride("Usage: /intcraft debug removewarfeeitems [quantity]")
				.build();
	}

	@Override
	public boolean execute(CommandArgs args, CommandSender sender, List<String> parentCommands) throws CommandException, InvocationCommandException, AuthorizationException {
		if (!(sender instanceof Player)) {
			TownyMessaging.sendErrorMsg(TownySettings.getLangString("msg_player_only"));
			return false;
		}
		Player player = (Player) sender;
	
		int quantity = 0;
		try {
			quantity = args.nextInt();
		} catch (MissingArgumentException | ArgumentParseException e) {
			throw new InvalidUsageException(e.getMessage(), this, parentCommands, false, e);
		}

		int removed = PlayerInventories.removeItems(player, IntcraftConfig.getWarFeeItem(), quantity);
		TownyMessaging.sendMsg(sender, "Removed items: " +  removed + " " + IntcraftConfig.getWarFeeItem().toString());
		return true;
	}

}
