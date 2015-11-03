package intcraft.command;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;
import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.TownyUniverse;
import com.palmergames.bukkit.util.ChatTools;
import com.sk89q.intake.CommandException;
import com.sk89q.intake.ImmutableDescription;
import com.sk89q.intake.InvalidUsageException;
import com.sk89q.intake.InvocationCommandException;
import com.sk89q.intake.argument.CommandArgs;
import com.sk89q.intake.argument.MissingArgumentException;
import com.sk89q.intake.util.auth.AuthorizationException;

import intcraft.util.WarFees;

public class CommandWarFeeGet extends IntcraftCommand {

	public CommandWarFeeGet() {
		ImmutableDescription.Builder builder = new ImmutableDescription.Builder();
		this.description = builder
				.setShortDescription("Gets the war fee of the specified nation.")
				.setHelp(ChatTools.formatCommand("", "/intcraft warfee", "get [nation]", builder.getShortDescription()))
				.setPermissions(Lists.newArrayList("towny.intcraft.warfee.get"))
				.setUsageOverride("Usage: /intcraft warfee get [nation]")
				.build();
	}

	@Override
	public boolean execute(CommandArgs args, CommandSender sender, List<String> parentCommands) throws CommandException, InvocationCommandException, AuthorizationException {
		if (!(sender instanceof Player)) {
			TownyMessaging.sendErrorMsg(TownySettings.getLangString("msg_player_only"));
			return false;
		}
		Player player = (Player) sender;
	
		String nationArg;
		try {
			nationArg = args.next();
		} catch (MissingArgumentException e) {
			throw new InvalidUsageException(e.getMessage(), this, parentCommands, false, e);
		}
		
		Nation nation;
		try {
			nation = TownyUniverse.getDataSource().getNation(nationArg);
		} 
		catch (NotRegisteredException e) {
			TownyMessaging.sendErrorMsg(player, e.getMessage());
			return false;
		}
		
		TownyMessaging.sendMsg(player, nation.getName() + " war fee: " + WarFees.getTrueWarFee(nation));
		return true;
	}

}
