package intcraft.command;

import java.util.List;

import org.bukkit.command.CommandSender;

import com.google.common.collect.Lists;
import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.util.ChatTools;
import com.sk89q.intake.ImmutableDescription;
import com.sk89q.intake.argument.CommandArgs;

import intcraft.config.IntcraftConfig;

public class CommandHomeBlockCooldownInfo extends IntcraftCommand {

	public CommandHomeBlockCooldownInfo() {
		ImmutableDescription.Builder builder = new ImmutableDescription.Builder();
		this.description = builder
				.setShortDescription("List home block cooldown settings.")
				.setHelp(ChatTools.formatCommand("", "/intcraft hbcooldown", "info", builder.getShortDescription()))
				.setPermissions(Lists.newArrayList("towny.intcraft.hbcooldown.info"))
				.setUsageOverride("Usage: /intcraft hbcooldown info")
				.build();
	}

	@Override
	public boolean execute(CommandArgs args, CommandSender sender, List<String> parentCommands) {
		TownyMessaging.sendMsg(sender, "Hours: " + IntcraftConfig.getHomeBlockCooldownHours());
		return true;
	}

}
