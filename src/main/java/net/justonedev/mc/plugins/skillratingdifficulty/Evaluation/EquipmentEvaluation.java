package net.justonedev.mc.plugins.skillratingdifficulty.Evaluation;

import net.justonedev.mc.plugins.skillratingdifficulty.ItemValues;
import net.justonedev.mc.plugins.skillratingdifficulty.WeightConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EquipmentEvaluation {
	
	public static double EvaluateEquipment(Player p)
	{
		double rating = 0.0;
		int debugI = 0;
		for (ItemStack i : p.getInventory().getStorageContents())
		{
			rating += EvaluateInventoryItem(i);
		}
		return rating;
	}
	
	public static double EvaluateSkilllevel(Player p)
	{
		return 0.0;
	}
	
	public static double EvaluateCompletePlayerRating(Player p)
	{
		return EvaluateEquipment(p) + EvaluateSkilllevel(p);
	}
	
	public static double EvaluateCompletePlayerAndEnvironmentRating(Player p)
	{
		// Todo add local difficulty
		return EvaluateCompletePlayerRating(p);
	}
	
	public static double EvaluateInventoryItem(ItemStack i)
	{
		if(i == null) return 0;
		double score = 0.0;
		double materialScore = ItemValues.GetMaterialValueOf(i);
		double enchantmentScore = WeightConfiguration.GetEnchantmentValueOf(i);
		// Other stuff like potion or armor equipment
		score = materialScore + enchantmentScore;
		return score;
	}
	
	public static String AsString(double d, int trailDigits)
	{
		if(trailDigits == 0) return "" + (int) d;
		int f = (int) Math.pow(10, trailDigits);
		long temp = (long) (d * f);
		double _new = temp / f;
		String s =  "" + _new;
		if(trailDigits == 1)
		{
			if (s.endsWith(".0")) s = s.replace(".0", "");
			else if (s.endsWith(".1")) s = s.replace(".1", "");
			else if (s.endsWith(".9")) s = ("" + (_new + 0.1)).replace(".0", "");
		}
		return s;
	}

}
