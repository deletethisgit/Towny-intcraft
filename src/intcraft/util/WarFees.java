package intcraft.util;

import com.palmergames.bukkit.towny.object.Nation;

import intcraft.config.IntcraftConfig;

public class WarFees
{
	
	public static int getTrueWarFee(Nation nation) {
		int warFee = IntcraftConfig.getBaseWarFee();
		int warFeeRatio = IntcraftConfig.getWarFeeRatio();
		return Math.max(0, warFee - (warFeeRatio * nation.getNumResidents()));
	}
	
}
