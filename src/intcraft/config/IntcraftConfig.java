package intcraft.config;

import org.bukkit.Material;

import com.palmergames.bukkit.config.ConfigNodes;
import com.palmergames.bukkit.towny.TownySettings;

public class IntcraftConfig
{
	public static int getWarFee() 
	{
		return TownySettings.getInt(ConfigNodes.INTCRAFT_WAR_FEE);
	}
	
	public static int getWarFeeRatio()
	{
		return TownySettings.getInt(ConfigNodes.INTCRAFT_WAR_FEE_RATIO);
	}
	
	public static Material getWarFeeItem()
	{
		String id = TownySettings.getString(ConfigNodes.INTCRAFT_WAR_FEE_ITEM);
		Material item = Material.matchMaterial(id);
		if(item == null)
		{
			item = Material.IRON_INGOT;
		}
		return item;
	}
}
