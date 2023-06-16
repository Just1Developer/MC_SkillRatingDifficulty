package net.justonedev.mc.plugins.skillratingdifficulty.Evaluation;

import net.justonedev.mc.plugins.skillratingdifficulty.ItemValues;
import net.justonedev.mc.plugins.skillratingdifficulty.WeightConfiguration;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Objects;

public class MLWeights {
	
	public static int NUM_BUCKETS_MATERIAL = 10;	// Material amount?
	public static int NUM_BUCKETS_ENCHANTMENT = 10;	// Material amount?
	
	public static double[][] ConvertItems(ItemStack[] itemStacks)
	{
		ArrayList<double[]> items = new ArrayList<>();
		
		for(ItemStack it : itemStacks)
		{
			String[] enchants = new String[it.getEnchantments().size()];
			int[] enchLevels = new int[enchants.length];
			int i = 0;
			for(Enchantment e : it.getEnchantments().keySet())
			{
				enchants[i] = e.toString();
				enchLevels[i] = it.getEnchantments().get(e);
				i++;
			}
			items.add(featureVector(it.getType(), enchants, enchLevels, it.getMaxStackSize()));
		}
		
		return items.toArray(new double[0][0]);
	}
	
	/* example target vals
		double[] enchantmentTargetValues = {0.5, 0.2, 1.0};
		double[] materialTargetValues = {3.5, 5.0, 7.0};
	 */
	
	
	// Todo confirm output is buckets
	
	public static double[][] MLWeightTrainer(TrainingDataObject MaterialData)
	{
		return MLWeightTrainer(MaterialData.itemStacks, MaterialData.materialTargetValues, MaterialData.enchantmentTargetValues);
	}
	public static double[][] MLWeightTrainer(Inventory Inventory, double[] materialTargetValues, double[] enchantmentTargetValues)
	{
		return MLWeightTrainer(ConvertItems(Inventory.getContents()), materialTargetValues, enchantmentTargetValues);
	}
	public static double[][] MLWeightTrainer(ItemStack[] itemFeatures, double[] materialTargetValues, double[] enchantmentTargetValues)
	{
		return MLWeightTrainer(ConvertItems(itemFeatures), materialTargetValues, enchantmentTargetValues);
	}
	public static double[][] MLWeightTrainer(double[][] itemFeatures, double[] materialTargetValues, double[] enchantmentTargetValues)
	{
		// Example data with feature hashing for materials [removed and replaced with method args]
		
		// Example target values for material and enchantment [removed and replaced with method args]
		
		// Create regression models
		OLSMultipleLinearRegression materialRegression = new OLSMultipleLinearRegression();
		OLSMultipleLinearRegression enchantmentRegression = new OLSMultipleLinearRegression();
		
		// Set data for each model
		materialRegression.newSampleData(materialTargetValues, itemFeatures);
		enchantmentRegression.newSampleData(enchantmentTargetValues, itemFeatures);
		
		// Calculate regression parameters for each model
		double[] materialBeta = materialRegression.estimateRegressionParameters();
		double[] enchantmentBeta = enchantmentRegression.estimateRegressionParameters();
		
		// Using the models to make predictions
		double[] exampleItem = featureVector(Material.GOLD_INGOT, new String[]{"Earth", "Wind"}, new int[]{1, 1}, 64);
		
		// Predict material value
		double materialValue = materialBeta[0]; // this is the intercept
		for (int i = 1; i < materialBeta.length; i++) {
			materialValue += materialBeta[i] * exampleItem[i - 1];
		}
		System.out.println("Predicted material value of example item: " + materialValue);
		
		// Predict enchantment value
		double enchantmentValue = enchantmentBeta[0]; // this is the intercept
		for (int i = 1; i < enchantmentBeta.length; i++) {
			enchantmentValue += enchantmentBeta[i] * exampleItem[i - 1];
		}
		System.out.println("Predicted enchantment value of example item: " + enchantmentValue);
		
		return new double[][] { materialBeta, enchantmentBeta };
	}
	
	/*
	private static double[] featureVector(String material, String[] enchantments, int[] levels) {
		int hashBucketMaterial = Objects.hashCode(material) % NUM_BUCKETS_MATERIAL;
		double[] features = new double[NUM_BUCKETS_MATERIAL + NUM_BUCKETS_ENCHANTMENT + enchantments.length];
		features[hashBucketMaterial] = 1;
		
		for (int i = 0; i < enchantments.length; i++) {
			int hashBucketEnchantment = Objects.hashCode(enchantments[i]) % NUM_BUCKETS_ENCHANTMENT;
			features[NUM_BUCKETS_MATERIAL + hashBucketEnchantment] = 1;
			features[NUM_BUCKETS_MATERIAL + NUM_BUCKETS_ENCHANTMENT + i] = levels[i];
		}
		return features;
	}
	*/
	private static double[] featureVector(Material material, String[] enchantments, int[] levels, int maxStackSize) {
		int hashBucketMaterial = Objects.hashCode(material.toString()) % NUM_BUCKETS_MATERIAL;
		double[] features = new double[NUM_BUCKETS_MATERIAL + NUM_BUCKETS_ENCHANTMENT + enchantments.length + 3]; // +3 for the fallback materials
		
		// Check if material has individual weight
		if (materialHasIndividualWeight(material)) {
			features[hashBucketMaterial] = 1;
		} else {
			// Assign to fallback category based on max stack size
			int fallbackIndex = determineFallbackCategory(maxStackSize);
			features[NUM_BUCKETS_MATERIAL + NUM_BUCKETS_ENCHANTMENT + enchantments.length + fallbackIndex] = 1;
		}
		
		for (int i = 0; i < enchantments.length; i++) {
			int hashBucketEnchantment = Objects.hashCode(enchantments[i]) % NUM_BUCKETS_ENCHANTMENT;
			features[NUM_BUCKETS_MATERIAL + hashBucketEnchantment] = 1;
			features[NUM_BUCKETS_MATERIAL + NUM_BUCKETS_ENCHANTMENT + i] = levels[i];
		}
		
		return features;
	}
	
	private static boolean materialHasIndividualWeight(Material material) {
		// Implement logic to check if material has individual weight
		// For example, you can have a Set of materials with individual weights and check if material is in that set
		return ItemValues.ItemValues.containsKey(material);
	}
	
	private static int determineFallbackCategory(int maxStackSize) {
		// Implement logic to determine fallback category based on max stack size
		// For example:
		// if (maxStackSize <= 16) return 0; // first fallback category
		// else if (maxStackSize <= 32) return 1; // second fallback category
		// else return 2; // third fallback category
		if(maxStackSize == 64) return 0;
		if(maxStackSize == 16) return 1;
		return 2;
	}
	
	// Also by GPT-4:
	public static double extractMaterialWeight(Material material, double[] materialBeta) { return extractMaterialWeight(material.toString(), materialBeta); }
	public static double extractMaterialWeight(String material, double[] materialBeta) {
		int hashBucketMaterial = Objects.hashCode(material) % NUM_BUCKETS_MATERIAL;
		// Note: The +1 is because index 0 is reserved for the intercept term
		return materialBeta[hashBucketMaterial + 1];
	}
	
	public static double extractEnchantmentWeight(Enchantment enchantment, double[] enchantmentBeta) { return extractEnchantmentWeight(enchantment.toString(), enchantmentBeta); }
	public static double extractEnchantmentWeight(String enchantment, double[] enchantmentBeta) {
		int hashBucketEnchantment = Objects.hashCode(enchantment) % NUM_BUCKETS_ENCHANTMENT;
		// Note: The +1 is because index 0 is reserved for the intercept term
		//       The + NUM_BUCKETS_MATERIAL is because enchantment features are placed after material features
		return enchantmentBeta[hashBucketEnchantment + 1 + NUM_BUCKETS_MATERIAL];
	}
	
	
	public static void StartLearning()
	{
		
		// Run 1: Only material, basically
		
		ItemStack[] items1 =
				{
				
				};
		double[] materialTargetValues1 =
				{
				
				};
		
		double[] enchantmentTargetValues1 =
				{
				
				};
		
		double[][] ResultingData = MLWeightTrainer(items1, materialTargetValues1, enchantmentTargetValues1);
		PrintResultingData(ResultingData);
		
		// Run 2: Only Enchantments, basically
		
		ItemStack[] items2 =
				{
				
				};
		double[] materialTargetValues2 =
				{
				
				};
		double[] enchantmentTargetValues2 =
				{
				
				};
		
		ResultingData = MLWeightTrainer(items2, materialTargetValues2, enchantmentTargetValues2);
		PrintResultingData(ResultingData);
		
		// Run 3: Everything, see how it works out
		
		ItemStack[] items3 =
				{
				
				};
		double[] materialTargetValues3 =
				{
				
				};
		double[] enchantmentTargetValues3 =
				{
				
				};
		
		ResultingData = MLWeightTrainer(items3, materialTargetValues3, enchantmentTargetValues3);
		PrintResultingData(ResultingData);
	}
	
	public static void PrintResultingData(double[][] ResultingData)
	{
		// Material weights
		Bukkit.broadcastMessage(">>> Section: Material Weights --------------------");
		for(Material m : ItemValues.ItemValues.keySet())
		{
			int hashBucketMaterial = Objects.hashCode(m.toString()) % NUM_BUCKETS_MATERIAL;
			Bukkit.broadcastMessage("Material: " + m + "   -   " + ResultingData[0][hashBucketMaterial]);
		}
		Bukkit.broadcastMessage("Fallback Stacksize: 64   -   " + ResultingData[0][ResultingData[0].length - 3]);
		Bukkit.broadcastMessage("Fallback Stacksize: 16   -   " + ResultingData[0][ResultingData[0].length - 2]);
		Bukkit.broadcastMessage("Fallback Stacksize: 1   -   " + ResultingData[0][ResultingData[0].length - 1]);
		Bukkit.broadcastMessage(" ");
		Bukkit.broadcastMessage(">>> Section: Enchantment Weights --------------------");
		for(Enchantment e : WeightConfiguration.EnchantmentWeights.keySet())
		{
			int hashBucketMaterial = Objects.hashCode(e.toString()) % NUM_BUCKETS_ENCHANTMENT;
			Bukkit.broadcastMessage("Enchantment: " + e + "   -   " + ResultingData[1][hashBucketMaterial]);
		}
		Bukkit.broadcastMessage(">>> Done ------------------------------------------");
		
	}
	
}
