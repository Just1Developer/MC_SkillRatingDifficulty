package net.justonedev.mc.plugins.skillratingdifficulty.Evaluation;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TrainingDataObject {

	public ItemStack[] itemStacks;
	
	public double[] materialTargetValues, enchantmentTargetValues;
	
	public TrainingDataObject()
	{
		itemStacks = new ItemStack[0];
		this.materialTargetValues = new double[itemStacks.length];
		this.enchantmentTargetValues = new double[itemStacks.length];
	}
	public TrainingDataObject(Inventory Inventory)
	{
		itemStacks = Inventory.getContents();
		this.materialTargetValues = new double[itemStacks.length];
		this.enchantmentTargetValues = new double[itemStacks.length];
	}
	public TrainingDataObject(ItemStack[] Items)
	{
		itemStacks = Items;
		this.materialTargetValues = new double[itemStacks.length];
		this.enchantmentTargetValues = new double[itemStacks.length];
	}
	public TrainingDataObject(Inventory Inventory, double[] materialTargetValues, double[] enchantmentTargetValues)
	{
		this.materialTargetValues = materialTargetValues;
		this.enchantmentTargetValues = enchantmentTargetValues;
		itemStacks = Inventory.getContents();
	}
	public TrainingDataObject(ItemStack[] Items, double[] materialTargetValues, double[] enchantmentTargetValues)
	{
		this.materialTargetValues = materialTargetValues;
		this.enchantmentTargetValues = enchantmentTargetValues;
		itemStacks = Items;
	}

}
