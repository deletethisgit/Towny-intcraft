package intcraft.config;

import org.bukkit.Material;

import com.palmergames.bukkit.config.ConfigNodes;
import com.palmergames.bukkit.towny.TownySettings;

public class IntcraftConfig
{
	
	public static boolean isWarEnabled() {
		return TownySettings.getBoolean(ConfigNodes.INTCRAFT_WARENABLED);
	}
	
	public static boolean isWarFeeEnabled() {
		return TownySettings.getBoolean(ConfigNodes.INTCRAFT_WARFEE_ENABLED);
	}
	
	public static int getBaseWarFee()  {
		return TownySettings.getInt(ConfigNodes.INTCRAFT_WARFEE_BASE);
	}
	
	public static int getWarFeeRatio() {
		return TownySettings.getInt(ConfigNodes.INTCRAFT_WARFEE_RATIO);
	}
	
	public static Material getWarFeeItem() {
		String id = TownySettings.getString(ConfigNodes.INTCRAFT_WARFEE_ITEM);
		Material item = Material.matchMaterial(id);
		if(item == null) {
			item = Material.IRON_INGOT;
		}
		return item;
	}
	
	public static int getHomeBlockCooldownHours() {
        return TownySettings.getInt(ConfigNodes.INTCRAFT_HOME_BLOCK_COOLDOWN_HOURS);
	}
	
}
