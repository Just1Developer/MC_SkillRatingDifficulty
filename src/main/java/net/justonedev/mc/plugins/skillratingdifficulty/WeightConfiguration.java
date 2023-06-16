package net.justonedev.mc.plugins.skillratingdifficulty;

import net.justonedev.mc.plugins.skillratingdifficulty.Evaluation.MLWeights;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;

public class WeightConfiguration {

	public static final File file = new File(SkillRatingDifficulty.Instance.getDataFolder(), "WeightConfig.yml");
	public static final YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	
	// World
	
	public static double WorldGlobalDifficulty = 1.0;
	public static double WorldLocalDifficulty = 1.0;
	
	// Skill Rating
	
	public static double SkillDmgDealtEntities = 1.0;
	public static double SkillDmgDealtPlayers = 1.0;
	public static double SkillDmgReceivedEntities = 1.0;
	public static double SkillDmgReceivedPlayers = 1.0;
	
	// Equipment
	
	public static double EquipmentHelmet = 1.0;
	public static double EquipmentChestplate = 1.0;
	public static double EquipmentElytra = 1.0;
	public static double EquipmentLeggings = 1.0;
	public static double EquipmentBoots = 1.0;
	public static double EquipmentOffhandTotem = 1.0;
	/*
	public static double EquipmentSword = 1.0;
	public static double EquipmentAxe = 1.0;
	public static double EquipmentPickaxe = 1.0;
	public static double EquipmentHoe = 1.0;
	public static double EquipmentShovel = 1.0;
	public static double EquipmentBow = 1.0;
	public static double EquipmentCrossbow = 1.0;
	public static double EquipmentTrident = 1.0;
	
	public static double EquipmentInvHelmet = 1.0;
	public static double EquipmentInvChestplate = 1.0;
	public static double EquipmentInvElytra = 1.0;
	public static double EquipmentInvLeggings = 1.0;
	public static double EquipmentInvBoots = 1.0;
	
	public static double EquipmentInvTotem = 1.0;
	public static double EquipmentInvGoldApple = 1.0;
	public static double EquipmentInvNotchApple = 1.0;
	public static double EquipmentInvGoldCarrot = 1.0;
	public static double EquipmentInvPotion = 1.0;
	*/
	
	// Util
	
	public static double UtilPotionLevelExponential = 1.0;
	public static double UtilEnchantmentLevelExponential = 1.0;
	
	public static final HashMap<Enchantment, Double> EnchantmentWeights = new HashMap<>();
	
	public static void Load()
	{
		if(!file.exists())
		{
			cfg.options().copyDefaults(true);
			cfg.addDefault("World.GlobalDifficulty", 1.0);
			cfg.addDefault("World.LocalDifficulty", 1.0);
			
			
			cfg.addDefault("SkillRating.DamageDealtToEntities", 1.0);
			cfg.addDefault("SkillRating.DamageDealtToPlayers", 1.0);
			cfg.addDefault("SkillRating.DamageReceivedFromEntities", 1.0);
			cfg.addDefault("SkillRating.DamageReceivedFromPlayers", 1.0);
			
			
			cfg.addDefault("Equipment.Equipped.Helmet", 1.0);
			cfg.addDefault("Equipment.Equipped.Chestplate", 1.0);
			cfg.addDefault("Equipment.Equipped.Elytra", 1.0);
			cfg.addDefault("Equipment.Equipped.Leggings", 1.0);
			cfg.addDefault("Equipment.Equipped.Boots", 1.0);
			
			/*
			cfg.addDefault("Equipment.Inventory.Helmet", 1.0);
			cfg.addDefault("Equipment.Inventory.Chestplate", 1.0);
			cfg.addDefault("Equipment.Inventory.Elytra", 1.0);
			cfg.addDefault("Equipment.Inventory.Leggings", 1.0);
			cfg.addDefault("Equipment.Inventory.Boots", 1.0);
			cfg.addDefault("Equipment.Inventory.Sword", 1.0);
			cfg.addDefault("Equipment.Inventory.Bow", 1.0);
			cfg.addDefault("Equipment.Inventory.Crossbow", 1.0);
			cfg.addDefault("Equipment.Inventory.Trident", 1.0);
			cfg.addDefault("Equipment.Inventory.Pickaxe", 1.0);
			cfg.addDefault("Equipment.Inventory.Axe", 1.0);
			cfg.addDefault("Equipment.Inventory.Shovel", 1.0);
			cfg.addDefault("Equipment.Inventory.Hoe", 1.0);
			cfg.addDefault("Equipment.Inventory.OffhandTotem", 1.0);
			
			cfg.addDefault("Equipment.Inventory.InventoryTotem", 1.0);
			cfg.addDefault("Equipment.Inventory.GoldCarrot", 1.0);
			cfg.addDefault("Equipment.Inventory.GoldApple", 1.0);
			cfg.addDefault("Equipment.Inventory.NotchApple", 1.0);
			cfg.addDefault("Equipment.Inventory.Potion", 1.0);
			*/
			
			cfg.addDefault("Util.PotionLevelExponential", 1.0);
			cfg.addDefault("Util.EnchantmentLevelExponential", 1.0);
			
			
			cfg.addDefault("Enchantment.Melee.Damage", 1.0);
			cfg.addDefault("Enchantment.Melee.SpiderDamage", 1.0);
			cfg.addDefault("Enchantment.Melee.UndeadDamage", 1.0);
			cfg.addDefault("Enchantment.Melee.Efficiency", 1.0);
			cfg.addDefault("Enchantment.Melee.Fire", 1.0);
			cfg.addDefault("Enchantment.Melee.Knockback", 1.0);
			cfg.addDefault("Enchantment.Melee.Looting", 1.0);
			cfg.addDefault("Enchantment.Melee.SweepingEdge", 1.0);
			
			cfg.addDefault("Enchantment.Range.Damage", 1.0);
			cfg.addDefault("Enchantment.Range.Fire", 1.0);
			cfg.addDefault("Enchantment.Range.Infinity", 1.0);
			cfg.addDefault("Enchantment.Range.Knockback", 1.0);
			cfg.addDefault("Enchantment.Range.Channeling", 1.0);
			cfg.addDefault("Enchantment.Range.Impaling", 1.0);
			cfg.addDefault("Enchantment.Range.Loyalty", 1.0);
			cfg.addDefault("Enchantment.Range.Riptide", 1.0);
			cfg.addDefault("Enchantment.Range.Multishot", 1.0);
			cfg.addDefault("Enchantment.Range.Piercing", 1.0);
			cfg.addDefault("Enchantment.Range.QuickCharge", 1.0);
			
			cfg.addDefault("Enchantment.Armor.Protection", 1.0);
			cfg.addDefault("Enchantment.Armor.BlastProtection", 1.0);
			cfg.addDefault("Enchantment.Armor.FireProtection", 1.0);
			cfg.addDefault("Enchantment.Armor.ProjectileProtection", 1.0);
			cfg.addDefault("Enchantment.Armor.Thorns", 1.0);
			cfg.addDefault("Enchantment.Armor.AquaAffinity", 1.0);
			cfg.addDefault("Enchantment.Armor.Respiration", 1.0);
			cfg.addDefault("Enchantment.Armor.DepthStrider", 1.0);
			cfg.addDefault("Enchantment.Armor.FrostWalker", 1.0);
			cfg.addDefault("Enchantment.Armor.FeatherFalling", 1.0);
			cfg.addDefault("Enchantment.Armor.SoulSpeed", 1.0);
			
			cfg.addDefault("Enchantment.Util.Unbreaking", 1.0);
			cfg.addDefault("Enchantment.Util.LuckOfTheSea", 1.0);
			cfg.addDefault("Enchantment.Util.LuckBlockDrops", 1.0);
			cfg.addDefault("Enchantment.Util.Lure", 1.0);
			cfg.addDefault("Enchantment.Util.Mending", 1.0);
			cfg.addDefault("Enchantment.Util.SilkTouch", 1.0);
			
			cfg.addDefault("Enchantment.Curse.Binding", 1.0);
			cfg.addDefault("Enchantment.Curse.Vanishing", 1.0);
			
			saveCfg();
		}
		
		WorldGlobalDifficulty = cfg.getDouble("World.GlobalDifficulty");
		WorldLocalDifficulty = cfg.getDouble("World.LocalDifficulty");
		
		
		SkillDmgDealtEntities = cfg.getDouble("SkillRating.DamageDealtToEntities");
		SkillDmgDealtPlayers = cfg.getDouble("SkillRating.DamageDealtToPlayers");
		SkillDmgReceivedEntities = cfg.getDouble("SkillRating.DamageReceivedFromEntities");
		SkillDmgReceivedPlayers = cfg.getDouble("SkillRating.DamageReceivedFromPlayers");
		
		EquipmentHelmet = cfg.getDouble("Equipment.Equipped.Helmet");
		EquipmentChestplate = cfg.getDouble("Equipment.Equipped.Chestplate");
		EquipmentElytra = cfg.getDouble("Equipment.Equipped.Elytra");
		EquipmentLeggings = cfg.getDouble("Equipment.Equipped.Leggings");
		EquipmentBoots = cfg.getDouble("Equipment.Equipped.Boots");
		
		/*
		EquipmentInvHelmet = cfg.getDouble("Equipment.Inventory.Helmet");
		EquipmentInvChestplate = cfg.getDouble("Equipment.Inventory.Chestplate");
		EquipmentInvElytra = cfg.getDouble("Equipment.Inventory.Elytra");
		EquipmentInvLeggings = cfg.getDouble("Equipment.Inventory.Leggings");
		EquipmentInvBoots = cfg.getDouble("Equipment.Inventory.Boots");
		EquipmentSword = cfg.getDouble("Equipment.Inventory.Sword");
		EquipmentBow = cfg.getDouble("Equipment.Inventory.Bow");
		EquipmentCrossbow = cfg.getDouble("Equipment.Inventory.Crossbow");
		EquipmentTrident = cfg.getDouble("Equipment.Inventory.Trident");
		EquipmentPickaxe = cfg.getDouble("Equipment.Inventory.Pickaxe");
		EquipmentAxe = cfg.getDouble("Equipment.Inventory.Axe");
		EquipmentShovel = cfg.getDouble("Equipment.Inventory.Shovel");
		EquipmentHoe = cfg.getDouble("Equipment.Inventory.Hoe");
		EquipmentOffhandTotem = cfg.getDouble("Equipment.Inventory.OffhandTotem");
		
		EquipmentInvTotem = cfg.getDouble("Equipment.Inventory.InventoryTotem");
		EquipmentInvGoldCarrot = cfg.getDouble("Equipment.Inventory.GoldCarrot");
		EquipmentInvGoldApple = cfg.getDouble("Equipment.Inventory.GoldApple");
		EquipmentInvNotchApple = cfg.getDouble("Equipment.Inventory.NotchApple");
		EquipmentInvPotion = cfg.getDouble("Equipment.Inventory.Potion");
		*/
		
		UtilPotionLevelExponential = cfg.getDouble("Util.PotionLevelExponential");
		UtilEnchantmentLevelExponential = cfg.getDouble("Util.EnchantmentLevelExponential");
		
		
		EnchantmentWeights.put(Enchantment.DAMAGE_ALL, cfg.getDouble("Enchantment.Melee.Damage"));
		EnchantmentWeights.put(Enchantment.DAMAGE_ARTHROPODS, cfg.getDouble("Enchantment.Melee.SpiderDamage"));
		EnchantmentWeights.put(Enchantment.DAMAGE_UNDEAD, cfg.getDouble("Enchantment.Melee.UndeadDamage"));
		EnchantmentWeights.put(Enchantment.DIG_SPEED, cfg.getDouble("Enchantment.Melee.Efficiency"));
		EnchantmentWeights.put(Enchantment.FIRE_ASPECT, cfg.getDouble("Enchantment.Melee.Fire"));
		EnchantmentWeights.put(Enchantment.KNOCKBACK, cfg.getDouble("Enchantment.Melee.Knockback"));
		EnchantmentWeights.put(Enchantment.LOOT_BONUS_MOBS, cfg.getDouble("Enchantment.Melee.Looting"));
		EnchantmentWeights.put(Enchantment.SWEEPING_EDGE, cfg.getDouble("Enchantment.Melee.SweepingEdge"));
		
		EnchantmentWeights.put(Enchantment.ARROW_DAMAGE, cfg.getDouble("Enchantment.Range.Damage"));
		EnchantmentWeights.put(Enchantment.ARROW_FIRE, cfg.getDouble("Enchantment.Range.Fire"));
		EnchantmentWeights.put(Enchantment.ARROW_INFINITE, cfg.getDouble("Enchantment.Range.Infinity"));
		EnchantmentWeights.put(Enchantment.ARROW_KNOCKBACK, cfg.getDouble("Enchantment.Range.Knockback"));
		EnchantmentWeights.put(Enchantment.CHANNELING, cfg.getDouble("Enchantment.Range.Channeling"));
		EnchantmentWeights.put(Enchantment.IMPALING, cfg.getDouble("Enchantment.Range.Impaling"));
		EnchantmentWeights.put(Enchantment.LOYALTY, cfg.getDouble("Enchantment.Range.Loyalty"));
		EnchantmentWeights.put(Enchantment.RIPTIDE, cfg.getDouble("Enchantment.Range.Riptide"));
		EnchantmentWeights.put(Enchantment.MULTISHOT, cfg.getDouble("Enchantment.Range.Multishot"));
		EnchantmentWeights.put(Enchantment.PIERCING, cfg.getDouble("Enchantment.Range.Piercing"));
		EnchantmentWeights.put(Enchantment.QUICK_CHARGE, cfg.getDouble("Enchantment.Range.QuickCharge"));
		
		EnchantmentWeights.put(Enchantment.PROTECTION_ENVIRONMENTAL, cfg.getDouble("Enchantment.Armor.Protection"));
		EnchantmentWeights.put(Enchantment.PROTECTION_EXPLOSIONS, cfg.getDouble("Enchantment.Armor.BlastProtection"));
		EnchantmentWeights.put(Enchantment.PROTECTION_FIRE, cfg.getDouble("Enchantment.Armor.FireProtection"));
		EnchantmentWeights.put(Enchantment.PROTECTION_PROJECTILE, cfg.getDouble("Enchantment.Armor.ProjectileProtection"));
		EnchantmentWeights.put(Enchantment.THORNS, cfg.getDouble("Enchantment.Armor.Thorns"));
		EnchantmentWeights.put(Enchantment.WATER_WORKER, cfg.getDouble("Enchantment.Armor.AquaAffinity"));
		EnchantmentWeights.put(Enchantment.OXYGEN, cfg.getDouble("Enchantment.Armor.Respiration"));
		EnchantmentWeights.put(Enchantment.SWIFT_SNEAK, cfg.getDouble("Enchantment.Armor.SwiftSneak"));
		EnchantmentWeights.put(Enchantment.DEPTH_STRIDER, cfg.getDouble("Enchantment.Armor.DepthStrider"));
		EnchantmentWeights.put(Enchantment.FROST_WALKER, cfg.getDouble("Enchantment.Armor.FrostWalker"));
		EnchantmentWeights.put(Enchantment.PROTECTION_FALL, cfg.getDouble("Enchantment.Armor.FeatherFalling"));
		EnchantmentWeights.put(Enchantment.SOUL_SPEED, cfg.getDouble("Enchantment.Armor.SoulSpeed"));
		
		EnchantmentWeights.put(Enchantment.DURABILITY, cfg.getDouble("Enchantment.Util.Unbreaking"));
		EnchantmentWeights.put(Enchantment.LUCK, cfg.getDouble("Enchantment.Util.LuckOfTheSea"));
		EnchantmentWeights.put(Enchantment.LOOT_BONUS_BLOCKS, cfg.getDouble("Enchantment.Util.LuckBlockDrops"));
		EnchantmentWeights.put(Enchantment.LURE, cfg.getDouble("Enchantment.Util.Lure"));
		EnchantmentWeights.put(Enchantment.MENDING, cfg.getDouble("Enchantment.Util.Mending"));
		EnchantmentWeights.put(Enchantment.SILK_TOUCH, cfg.getDouble("Enchantment.Util.SilkTouch"));
		
		EnchantmentWeights.put(Enchantment.BINDING_CURSE, cfg.getDouble("Enchantment.Curse.Binding"));
		EnchantmentWeights.put(Enchantment.VANISHING_CURSE, cfg.getDouble("Enchantment.Curse.Vanishing"));
		
		MLWeights.NUM_BUCKETS_ENCHANTMENT = EnchantmentWeights.size();
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
	
	public static double GetEnchantmentValueOf(ItemStack i)
	{
		double score = 0.0;
		for(Enchantment e : i.getEnchantments().keySet())
		{
			double EnchVal = EnchantmentWeights.getOrDefault(e, 1.0);
			double LevelVal = i.getEnchantmentLevel(e) * UtilEnchantmentLevelExponential;
			score += EnchVal * LevelVal;
		}
		return score;
	}
	
}

	/* Enchantments (38)
	
	public static double EnchantmentMeleeDamage = 1.0;
	public static double EnchantmentMeleeSpiderDamage = 1.0;
	public static double EnchantmentMeleeUndeadDamage = 1.0;
	public static double EnchantmentMeleeEfficiency = 1.0;	// Wiki: When applied to an axe it increases the chance that the axe may stun a shield, with the base chance being 25% and a 5% increase for each level of efficiency. (Max enchantment level: 5)
	public static double EnchantmentMeleeFire = 1.0;
	public static double EnchantmentMeleeKnockback = 1.0;
	public static double EnchantmentMeleeLooting = 1.0;
	public static double EnchantmentMeleeSweepingEdge = 1.0;
	
	public static double EnchantmentRangeDamage = 1.0;
	public static double EnchantmentRangeFire = 1.0;
	public static double EnchantmentRangeInfinity = 1.0;
	public static double EnchantmentRangeKnockback = 1.0;
	public static double EnchantmentRangeChanneling = 1.0;
	public static double EnchantmentRangeImpaling = 1.0;
	public static double EnchantmentRangeLoyalty = 1.0;
	public static double EnchantmentRangeRiptide = 1.0;
	public static double EnchantmentRangeMultishot = 1.0;
	public static double EnchantmentRangePiercing = 1.0;
	public static double EnchantmentRangeQuickCharge = 1.0;
	
	public static double EnchantmentArmorProtection = 1.0;
	public static double EnchantmentArmorBlastProtection = 1.0;
	public static double EnchantmentArmorFireProtection = 1.0;
	public static double EnchantmentArmorProjectileProtection = 1.0;
	public static double EnchantmentArmorThorns = 1.0;
	public static double EnchantmentArmorAquaAffinity = 1.0;
	public static double EnchantmentArmorRespiration = 1.0;
	public static double EnchantmentArmorSwiftSneak = 1.0;
	public static double EnchantmentArmorDepthStrider = 1.0;
	public static double EnchantmentArmorFrostWalker = 1.0;
	public static double EnchantmentArmorFeatherFalling = 1.0;
	public static double EnchantmentArmorSoulSpeed = 1.0;
	
	public static double EnchantmentUtilUnbreaking = 1.0;
	public static double EnchantmentUtilLuckOfTheSea = 1.0;
	public static double EnchantmentUtilLuckBlockDrops = 1.0;
	public static double EnchantmentUtilLure = 1.0;
	public static double EnchantmentUtilMending = 1.0;
	public static double EnchantmentUtilSilkTouch = 1.0;
	
	public static double EnchantmentCurseBinding = 1.0;
	public static double EnchantmentCurseVanishing = 1.0;
	*/