package io.github.deletethisgit.townyintcraft.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class InventoryHelper
{
	public static int getItemCount(Player player, Material item)
	{
		PlayerInventory inventory = player.getInventory();
		int count = 0;
		for(ItemStack stack : inventory.getContents())
		{
			if(stack != null && stack.getType().equals(item))
			{
				count += stack.getAmount();
			}
		}
		return count;
	}
	
	public static void removeItems(Player player, Material item, int amount)
	{
		PlayerInventory inventory = player.getInventory();
		int remaining = amount;
		for(int slot = 0; slot < inventory.getSize(); slot++)
		{
			ItemStack stack = inventory.getItem(slot);
			if(stack != null && stack.getType().equals(item))
			{
				if(remaining >= stack.getAmount())
				{
					remaining -= stack.getAmount();
					inventory.setItem(slot, null);
				}
				else
				{
					stack.setAmount(stack.getAmount() - remaining);
					remaining = 0;
				}
			}
			if(remaining <= 0) break;
		}
		player.updateInventory();
	}
}
