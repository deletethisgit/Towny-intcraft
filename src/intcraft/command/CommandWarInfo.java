package intcraft.command;

import java.util.List;

import org.bukkit.command.CommandSender;

import com.google.common.collect.Lists;
import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.util.ChatTools;
import com.sk89q.intake.ImmutableDescription;
import com.sk89q.intake.argument.CommandArgs;

import intcraft.config.IntcraftConfig;

public class CommandWarInfo extends IntcraftCommand {

	public CommandWarInfo() {
		ImmutableDescription.Builder builder = new ImmutableDescription.Builder();
		this.description = builder
				.setShortDescription("List war configuration settings.")
				.setHelp(ChatTools.formatCommand("", "/intcraft war", "info", builder.getShortDescription()))
				.setPermissions(Lists.newArrayList("towny.intcraft.war.info"))
				.setUsageOverride("Usage: /intcraft war info")
				.build();
	}

	@Override
	public boolean execute(CommandArgs args, CommandSender sender, List<String> parentCommands) {
		TownyMessaging.sendMsg(sender, "Enabled: " +  IntcraftConfig.isWarEnabled());
		return true;
	}

}
