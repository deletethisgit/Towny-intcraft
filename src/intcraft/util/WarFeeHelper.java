package intcraft.util;

import com.palmergames.bukkit.towny.object.Nation;

import intcraft.config.IntcraftConfig;

public class WarFeeHelper
{
	public static int getActualWarFeeFor(Nation nation)
	{
		int warFee = IntcraftConfig.getBaseWarFee();
		int warFeeRatio = IntcraftConfig.getWarFeeRatio();
		return Math.max(0, warFee - (warFeeRatio * nation.getNumResidents()));
	}
}
