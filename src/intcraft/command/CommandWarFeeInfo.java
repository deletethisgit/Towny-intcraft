package intcraft.command;

import java.util.List;

import org.bukkit.command.CommandSender;

import com.google.common.collect.Lists;
import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.util.ChatTools;
import com.sk89q.intake.ImmutableDescription;
import com.sk89q.intake.argument.CommandArgs;

import intcraft.config.IntcraftConfig;

public class CommandWarFeeInfo extends IntcraftCommand {

	public CommandWarFeeInfo() {
		ImmutableDescription.Builder builder = new ImmutableDescription.Builder();
		this.description = builder
				.setShortDescription("List war fee configuration settings.")
				.setHelp(ChatTools.formatCommand("", "/intcraft warfee", "info", builder.getShortDescription()))
				.setPermissions(Lists.newArrayList("towny.intcraft.warfee.info"))
				.setUsageOverride("Usage: /intcraft warfee info")
				.build();
	}

	@Override
	public boolean execute(CommandArgs args, CommandSender sender, List<String> parentCommands) {
		TownyMessaging.sendMsg(sender, "Enabled: " + IntcraftConfig.isWarFeeEnabled());
		TownyMessaging.sendMsg(sender, "Base price: " + IntcraftConfig.getBaseWarFee());
		TownyMessaging.sendMsg(sender, "Price reduction/resident ratio: " + IntcraftConfig.getWarFeeRatio());
		return true;
	}

}
