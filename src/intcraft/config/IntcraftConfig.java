package intcraft.config;

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
}
