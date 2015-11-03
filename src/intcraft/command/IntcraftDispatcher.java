package intcraft.command;

import com.palmergames.bukkit.util.ChatTools;
import com.sk89q.intake.Description;
import com.sk89q.intake.ImmutableDescription;
import com.sk89q.intake.dispatcher.SimpleDispatcher;

public class IntcraftDispatcher extends SimpleDispatcher {

	private final Description description;
	
	public IntcraftDispatcher(String subCommand) {
		ImmutableDescription.Builder builder = new ImmutableDescription.Builder();
		this.description = builder
				.setShortDescription("List " + subCommand + " commands.")
				.setHelp(ChatTools.formatCommand("", "/intcraft", subCommand, builder.getShortDescription()))
				.build();
	}
	
    @Override
    public Description getDescription() {
        return description;
    }
}
