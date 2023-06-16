package net.justonedev.mc.plugins.skillratingdifficulty;

import net.justonedev.mc.plugins.skillratingdifficulty.Evaluation.MLWeights;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ItemValues {
	
	// ToDo Potions, General Debuff (otherwise too big values for non-important stuff, no? --> no bcs swords are supposed to be in the hundreds
	// ToDo Also Shields
	
	public static final File file = new File(SkillRatingDifficulty.Instance.getDataFolder(), "MaterialConfig.yml");
	public static final YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	
	public static final HashMap<Material, Double> ItemValues = new HashMap<>();
	
	public static double OtherItemsMaxStack1 = 0.5;
	public static double OtherItemsMaxStack16 = 0.2;
	public static double OtherItemsMaxStack64 = 0.1;
	
	public static double GetMaterialValueOf(ItemStack itemStack)
	{
		if(ItemValues.containsKey(itemStack.getType()))
			return ItemValues.get(itemStack.getType()) * itemStack.getAmount();
		
		switch(itemStack.getMaxStackSize())
		{
			case 64:
				return OtherItemsMaxStack64 * itemStack.getAmount();
			case 16:
				return OtherItemsMaxStack16 * itemStack.getAmount();
			default:
				return OtherItemsMaxStack1 * itemStack.getAmount();
		}
	}
	
	public static void Load()
	{
		if(!file.exists())
		{
			cfg.options().copyDefaults(true);
			cfg.addDefault("Weapons.NetheriteSword", 355.0);			// 9
			cfg.addDefault("Weapons.DiamondSword", 315.0);
			cfg.addDefault("Weapons.IronSword", 245.0);
			cfg.addDefault("Weapons.GoldenSword", 135.0);
			cfg.addDefault("Weapons.StoneSword", 115.0);
			cfg.addDefault("Weapons.WoodenSword", 60.0);
			cfg.addDefault("Weapons.Bow", 175.0);
			cfg.addDefault("Weapons.Crossbow", 190.0);
			cfg.addDefault("Weapons.Trident", 334.0);
			
			cfg.addDefault("Tools.NetheritePickaxe", 1.0);			// 6
			cfg.addDefault("Tools.DiamondPickaxe", 1.0);
			cfg.addDefault("Tools.IronPickaxe", 1.0);
			cfg.addDefault("Tools.GoldenPickaxe", 1.0);
			cfg.addDefault("Tools.StonePickaxe", 1.0);
			cfg.addDefault("Tools.WoodenPickaxe", 1.0);
			
			cfg.addDefault("Tools.NetheriteAxe", 1.0);				// 6
			cfg.addDefault("Tools.DiamondAxe", 1.0);
			cfg.addDefault("Tools.IronAxe", 1.0);
			cfg.addDefault("Tools.GoldenAxe", 1.0);
			cfg.addDefault("Tools.StoneAxe", 1.0);
			cfg.addDefault("Tools.WoodenAxe", 1.0);
			
			cfg.addDefault("Tools.NetheriteShovel", 1.0);			// 6
			cfg.addDefault("Tools.DiamondShovel", 1.0);
			cfg.addDefault("Tools.IronShovel", 1.0);
			cfg.addDefault("Tools.GoldenShovel", 1.0);
			cfg.addDefault("Tools.StoneShovel", 1.0);
			cfg.addDefault("Tools.WoodenShovel", 1.0);
			
			cfg.addDefault("Tools.NetheriteHoe", 1.0);				// 6
			cfg.addDefault("Tools.DiamondHoe", 1.0);
			cfg.addDefault("Tools.IronHoe", 1.0);
			cfg.addDefault("Tools.GoldenHoe", 1.0);
			cfg.addDefault("Tools.StoneHoe", 1.0);
			cfg.addDefault("Tools.WoodenHoe", 1.0);
			
			cfg.addDefault("Armor.Head.NetheriteHelmet", 1.0);		// 7
			cfg.addDefault("Armor.Head.DiamondHelmet", 1.0);
			cfg.addDefault("Armor.Head.IronHelmet", 1.0);
			cfg.addDefault("Armor.Head.GoldenHelmet", 1.0);
			cfg.addDefault("Armor.Head.ChainmailHelmet", 1.0);
			cfg.addDefault("Armor.Head.LeatherHelmet", 1.0);
			cfg.addDefault("Armor.Head.Pumpkin", 1.0);
			
			cfg.addDefault("Armor.Chest.NetheriteChestplate", 1.0);	// 7
			cfg.addDefault("Armor.Chest.DiamondChestplate", 1.0);
			cfg.addDefault("Armor.Chest.IronChestplate", 1.0);
			cfg.addDefault("Armor.Chest.GoldenChestplate", 1.0);
			cfg.addDefault("Armor.Chest.ChainmailChestplate", 1.0);
			cfg.addDefault("Armor.Chest.LeatherChestplate", 1.0);
			cfg.addDefault("Armor.Chest.Elytra", 1.0);
			
			cfg.addDefault("Armor.Legs.NetheriteLeggings", 1.0);		// 6
			cfg.addDefault("Armor.Legs.DiamondLeggings", 1.0);
			cfg.addDefault("Armor.Legs.IronLeggings", 1.0);
			cfg.addDefault("Armor.Legs.GoldenLeggings", 1.0);
			cfg.addDefault("Armor.Legs.ChainmailLeggings", 1.0);
			cfg.addDefault("Armor.Legs.LeatherLeggings", 1.0);
			
			cfg.addDefault("Armor.Feet.NetheriteBoots", 1.0);		// 6
			cfg.addDefault("Armor.Feet.DiamondBoots", 1.0);
			cfg.addDefault("Armor.Feet.IronBoots", 1.0);
			cfg.addDefault("Armor.Feet.GoldenBoots", 1.0);
			cfg.addDefault("Armor.Feet.ChainmailBoots", 1.0);
			cfg.addDefault("Armor.Feet.LeatherBoots", 1.0);
			
			cfg.addDefault("Foods.GoldenApple", 9.4);				// 4
			cfg.addDefault("Foods.GoldenCarrot", 2.7);
			cfg.addDefault("Foods.NotchApple", 11.5);
			cfg.addDefault("Foods.OtherFoodRegenLevel", 0.02);
			
			cfg.addDefault("Util.Totem", 1.0);						// 6
			cfg.addDefault("Util.EnderCrystal", 1.0);
			cfg.addDefault("Util.Obsidian", 1.0);
			cfg.addDefault("Util.FlintAndSteel", 1.0);
			cfg.addDefault("Util.LavaBucket", 1.0);
			cfg.addDefault("Util.WaterBucket", 1.0);
			
			cfg.addDefault("Valuables.AncientDebris", 1.0);			// 18
			cfg.addDefault("Valuables.NetheriteScrap", 1.0);
			cfg.addDefault("Valuables.NetheriteIngot", 1.0);
			cfg.addDefault("Valuables.NetheriteBlock", 1.0);
			cfg.addDefault("Valuables.Diamond", 1.0);
			cfg.addDefault("Valuables.GoldIngot", 1.0);
			cfg.addDefault("Valuables.Emerald", 1.0);
			cfg.addDefault("Valuables.IronIngot", 1.0);
			cfg.addDefault("Valuables.DiamondOre", 1.0);
			cfg.addDefault("Valuables.GoldOre", 1.0);
			cfg.addDefault("Valuables.EmeraldOre", 1.0);
			cfg.addDefault("Valuables.IronOre", 1.0);
			cfg.addDefault("Valuables.DiamondBlock", 1.0);
			cfg.addDefault("Valuables.GoldBlock", 1.0);
			cfg.addDefault("Valuables.EmeraldBlock", 1.0);
			cfg.addDefault("Valuables.IronBlock", 1.0);
			cfg.addDefault("Valuables.EnderEye", 1.0);
			cfg.addDefault("Valuables.Enderpearl", 1.0);				// => 87
			
			cfg.addDefault("OtherItemsAmount.MaxStackSize1", 0.5);
			cfg.addDefault("OtherItemsAmount.MaxStackSize16", 0.2);
			cfg.addDefault("OtherItemsAmount.MaxStackSize64", 0.1);
			
			saveCfg();
		}
		
		ItemValues.put(Material.NETHERITE_SWORD, cfg.getDouble("Weapons.NetheriteSword"));
		ItemValues.put(Material.DIAMOND_SWORD, cfg.getDouble("Weapons.DiamondSword"));
		ItemValues.put(Material.IRON_SWORD, cfg.getDouble("Weapons.IronSword"));
		ItemValues.put(Material.GOLDEN_SWORD, cfg.getDouble("Weapons.GoldenSword"));
		ItemValues.put(Material.STONE_SWORD, cfg.getDouble("Weapons.StoneSword"));
		ItemValues.put(Material.WOODEN_SWORD, cfg.getDouble("Weapons.WoodenSword"));
		ItemValues.put(Material.BOW, cfg.getDouble("Weapons.Bow"));
		ItemValues.put(Material.CROSSBOW, cfg.getDouble("Weapons.Crossbow"));
		ItemValues.put(Material.TRIDENT, cfg.getDouble("Weapons.Trident"));
		
		ItemValues.put(Material.NETHERITE_AXE, cfg.getDouble("Tools.NetheriteAxe"));
		ItemValues.put(Material.DIAMOND_AXE, cfg.getDouble("Tools.DiamondAxe"));
		ItemValues.put(Material.IRON_AXE, cfg.getDouble("Tools.IronAxe"));
		ItemValues.put(Material.GOLDEN_AXE, cfg.getDouble("Tools.GoldenAxe"));
		ItemValues.put(Material.STONE_AXE, cfg.getDouble("Tools.StoneAxe"));
		ItemValues.put(Material.WOODEN_AXE, cfg.getDouble("Tools.WoodenAxe"));
		
		ItemValues.put(Material.NETHERITE_PICKAXE, cfg.getDouble("Tools.NetheritePickaxe"));
		ItemValues.put(Material.DIAMOND_PICKAXE, cfg.getDouble("Tools.DiamondPickaxe"));
		ItemValues.put(Material.IRON_PICKAXE, cfg.getDouble("Tools.IronPickaxe"));
		ItemValues.put(Material.GOLDEN_PICKAXE, cfg.getDouble("Tools.GoldenPickaxe"));
		ItemValues.put(Material.STONE_PICKAXE, cfg.getDouble("Tools.StonePickaxe"));
		ItemValues.put(Material.WOODEN_PICKAXE, cfg.getDouble("Tools.WoodenPickaxe"));
		
		ItemValues.put(Material.NETHERITE_SHOVEL, cfg.getDouble("Tools.NetheriteShovel"));
		ItemValues.put(Material.DIAMOND_SHOVEL, cfg.getDouble("Tools.DiamondShovel"));
		ItemValues.put(Material.IRON_SHOVEL, cfg.getDouble("Tools.IronShovel"));
		ItemValues.put(Material.GOLDEN_SHOVEL, cfg.getDouble("Tools.GoldenShovel"));
		ItemValues.put(Material.STONE_SHOVEL, cfg.getDouble("Tools.StoneShovel"));
		ItemValues.put(Material.WOODEN_SHOVEL, cfg.getDouble("Tools.WoodenShovel"));
		
		ItemValues.put(Material.NETHERITE_HOE, cfg.getDouble("Tools.NetheriteHoe"));
		ItemValues.put(Material.DIAMOND_HOE, cfg.getDouble("Tools.DiamondHoe"));
		ItemValues.put(Material.IRON_HOE, cfg.getDouble("Tools.IronHoe"));
		ItemValues.put(Material.GOLDEN_HOE, cfg.getDouble("Tools.GoldenHoe"));
		ItemValues.put(Material.STONE_HOE, cfg.getDouble("Tools.StoneHoe"));
		ItemValues.put(Material.WOODEN_HOE, cfg.getDouble("Tools.WoodenHoe"));
		
		ItemValues.put(Material.NETHERITE_HELMET, cfg.getDouble("Armor.Head.NetheriteHelmet"));
		ItemValues.put(Material.DIAMOND_HELMET, cfg.getDouble("Armor.Head.DiamondHelmet"));
		ItemValues.put(Material.IRON_HELMET, cfg.getDouble("Armor.Head.IronHelmet"));
		ItemValues.put(Material.GOLDEN_HELMET, cfg.getDouble("Armor.Head.GoldenHelmet"));
		ItemValues.put(Material.CHAINMAIL_HELMET, cfg.getDouble("Armor.Head.ChainmailHelmet"));
		ItemValues.put(Material.LEATHER_HELMET, cfg.getDouble("Armor.Head.LeatherHelmet"));
		ItemValues.put(Material.PUMPKIN, cfg.getDouble("Armor.Head.Pumpkin"));
		
		ItemValues.put(Material.NETHERITE_CHESTPLATE, cfg.getDouble("Armor.Chest.NetheriteChestplate"));
		ItemValues.put(Material.DIAMOND_CHESTPLATE, cfg.getDouble("Armor.Chest.DiamondChestplate"));
		ItemValues.put(Material.IRON_CHESTPLATE, cfg.getDouble("Armor.Chest.IronChestplate"));
		ItemValues.put(Material.GOLDEN_CHESTPLATE, cfg.getDouble("Armor.Chest.GoldenChestplate"));
		ItemValues.put(Material.CHAINMAIL_CHESTPLATE, cfg.getDouble("Armor.Chest.ChainmailChestplate"));
		ItemValues.put(Material.LEATHER_CHESTPLATE, cfg.getDouble("Armor.Chest.LeatherChestplate"));
		ItemValues.put(Material.ELYTRA, cfg.getDouble("Armor.Chest.Elytra"));
		
		ItemValues.put(Material.NETHERITE_LEGGINGS, cfg.getDouble("Armor.Legs.NetheriteLeggings"));
		ItemValues.put(Material.DIAMOND_LEGGINGS, cfg.getDouble("Armor.Legs.DiamondLeggings"));
		ItemValues.put(Material.IRON_LEGGINGS, cfg.getDouble("Armor.Legs.IronLeggings"));
		ItemValues.put(Material.GOLDEN_LEGGINGS, cfg.getDouble("Armor.Legs.GoldenLeggings"));
		ItemValues.put(Material.CHAINMAIL_LEGGINGS, cfg.getDouble("Armor.Legs.ChainmailLeggings"));
		ItemValues.put(Material.LEATHER_LEGGINGS, cfg.getDouble("Armor.Legs.LeatherLeggings"));
		
		ItemValues.put(Material.NETHERITE_BOOTS, cfg.getDouble("Armor.Feet.NetheriteBoots"));
		ItemValues.put(Material.DIAMOND_BOOTS, cfg.getDouble("Armor.Feet.DiamondBoots"));
		ItemValues.put(Material.IRON_BOOTS, cfg.getDouble("Armor.Feet.IronBoots"));
		ItemValues.put(Material.GOLDEN_BOOTS, cfg.getDouble("Armor.Feet.GoldenBoots"));
		ItemValues.put(Material.CHAINMAIL_BOOTS, cfg.getDouble("Armor.Feet.ChainmailBoots"));
		ItemValues.put(Material.LEATHER_BOOTS, cfg.getDouble("Armor.Feet.LeatherBoots"));
		
		
		ItemValues.put(Material.GOLDEN_APPLE, cfg.getDouble("Foods.GoldenApple"));
		ItemValues.put(Material.GOLDEN_CARROT, cfg.getDouble("Foods.GoldenCarrot"));
		ItemValues.put(Material.ENCHANTED_GOLDEN_APPLE, cfg.getDouble("Foods.NotchApple"));
		// Other Foods (the ones that dont poison you)
		double multiplier = cfg.getDouble("Foods.OtherFoodRegenLevel");
		ItemValues.put(Material.APPLE, 4 * multiplier);
		ItemValues.put(Material.BAKED_POTATO, 5 * multiplier);
		ItemValues.put(Material.BEETROOT, 1 * multiplier);
		ItemValues.put(Material.BEETROOT_SOUP, 6 * multiplier);
		ItemValues.put(Material.BREAD, 5 * multiplier);
		ItemValues.put(Material.CAKE, 14 * multiplier);	// Full cake
		ItemValues.put(Material.CARROT, 3 * multiplier);
		ItemValues.put(Material.CHORUS_FRUIT, 4 * multiplier);
		ItemValues.put(Material.COOKED_CHICKEN, 6 * multiplier);
		ItemValues.put(Material.COOKED_COD, 5 * multiplier);
		ItemValues.put(Material.COOKED_MUTTON, 6 * multiplier);
		ItemValues.put(Material.COOKED_PORKCHOP, 8 * multiplier);
		ItemValues.put(Material.COOKED_RABBIT, 5 * multiplier);
		ItemValues.put(Material.COOKED_SALMON, 6 * multiplier);
		ItemValues.put(Material.COOKIE, 2 * multiplier);
		ItemValues.put(Material.DRIED_KELP, 1 * multiplier);
		ItemValues.put(Material.GLOW_BERRIES, 2 * multiplier);
		ItemValues.put(Material.HONEY_BOTTLE, 6 * multiplier);
		ItemValues.put(Material.MELON_SLICE, 2 * multiplier);
		ItemValues.put(Material.MUSHROOM_STEW, 6 * multiplier);
		ItemValues.put(Material.POTATO, 1 * multiplier);
		ItemValues.put(Material.PUMPKIN_PIE, 8 * multiplier);
		ItemValues.put(Material.RABBIT_STEW, 10 * multiplier);
		ItemValues.put(Material.BEEF, 3 * multiplier);
		ItemValues.put(Material.CHICKEN, 2 * multiplier);
		ItemValues.put(Material.COD, 2 * multiplier);
		ItemValues.put(Material.MUTTON, 2 * multiplier);
		ItemValues.put(Material.PORKCHOP, 3 * multiplier);
		ItemValues.put(Material.RABBIT, 3 * multiplier);
		ItemValues.put(Material.SALMON, 2 * multiplier);
		ItemValues.put(Material.COOKED_BEEF, 8 * multiplier);
		ItemValues.put(Material.SUSPICIOUS_STEW, 6 * multiplier);
		ItemValues.put(Material.SWEET_BERRIES, 2 * multiplier);
		ItemValues.put(Material.TROPICAL_FISH, 1 * multiplier);
		
		ItemValues.put(Material.TOTEM_OF_UNDYING, cfg.getDouble("Util.Totem"));
		ItemValues.put(Material.END_CRYSTAL, cfg.getDouble("Util.EnderCrystal"));
		ItemValues.put(Material.OBSIDIAN, cfg.getDouble("Util.Obsidian"));
		ItemValues.put(Material.FLINT_AND_STEEL, cfg.getDouble("Util.FlintAndSteel"));
		ItemValues.put(Material.LAVA_BUCKET, cfg.getDouble("Util.LavaBucket"));
		ItemValues.put(Material.WATER_BUCKET, cfg.getDouble("Util.WaterBucket"));
		
		ItemValues.put(Material.ANCIENT_DEBRIS, cfg.getDouble("Valuables.AncientDebris"));
		ItemValues.put(Material.NETHERITE_SCRAP, cfg.getDouble("Valuables.NetheriteScrap"));
		ItemValues.put(Material.NETHERITE_INGOT, cfg.getDouble("Valuables.NetheriteIngot"));
		ItemValues.put(Material.NETHERITE_BLOCK, cfg.getDouble("Valuables.NetheriteBlock"));
		ItemValues.put(Material.DIAMOND, cfg.getDouble("Valuables.Diamond"));
		ItemValues.put(Material.GOLD_INGOT, cfg.getDouble("Valuables.GoldIngot"));
		ItemValues.put(Material.EMERALD, cfg.getDouble("Valuables.Emerald"));
		ItemValues.put(Material.IRON_INGOT, cfg.getDouble("Valuables.IronIngot"));
		ItemValues.put(Material.DIAMOND_ORE, cfg.getDouble("Valuables.DiamondOre"));
		ItemValues.put(Material.GOLD_ORE, cfg.getDouble("Valuables.GoldOre"));
		ItemValues.put(Material.EMERALD_ORE, cfg.getDouble("Valuables.EmeraldOre"));
		ItemValues.put(Material.IRON_ORE, cfg.getDouble("Valuables.IronOre"));
		ItemValues.put(Material.DIAMOND_BLOCK, cfg.getDouble("Valuables.DiamondBlock"));
		ItemValues.put(Material.GOLD_BLOCK, cfg.getDouble("Valuables.GoldBlock"));
		ItemValues.put(Material.EMERALD_BLOCK, cfg.getDouble("Valuables.EmeraldBlock"));
		ItemValues.put(Material.IRON_BLOCK, cfg.getDouble("Valuables.IronBlock"));
		ItemValues.put(Material.ENDER_EYE, cfg.getDouble("Valuables.EnderEye"));
		ItemValues.put(Material.ENDER_PEARL, cfg.getDouble("Valuables.Enderpearl"));
		
		OtherItemsMaxStack1 = cfg.getDouble("OtherItemsAmount.MaxStackSize1");
		OtherItemsMaxStack16 = cfg.getDouble("OtherItemsAmount.MaxStackSize16");
		OtherItemsMaxStack64 = cfg.getDouble("OtherItemsAmount.MaxStackSize64");
		
		MLWeights.NUM_BUCKETS_MATERIAL = ItemValues.size();
	}
	
	static void saveCfg()
	{
		try
		{
			cfg.save(file);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
}
