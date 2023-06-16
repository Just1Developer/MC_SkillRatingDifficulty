package net.justonedev.mc.plugins.skillratingdifficulty;

import net.justonedev.mc.plugins.skillratingdifficulty.Evaluation.EquipmentEvaluation;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class SkillRatingDifficulty extends JavaPlugin implements CommandExecutor {
	
	public static SkillRatingDifficulty Instance;
	
	/**
	* ToDo
	 * Individual Mob Damage
	 * 		-> Respect the enchantments (like projectile protection)
	 * 	Also add a mob damage that ignores armor, perhaps
	 * 	Also add a mob damage that is blocked completely by armor, 50% leather, 60% chain, 70% iron... netherite 99%, gold 100%-110% (auto thorns or heal/reduce other damage)
	 * 	Damage that is ignored only when full armor set, blocks part damage when part set is there -> only highest amount of same material count, 2 iron and 2 diamond does not mean 2x2 same material, it means just 2 same material, same as if you left one away
	 * Ofc also equipment
	 * Mob replacement on spawn if too weak
	 * Limit Skill rating influence
	 * 	Weights
	 * 	Also make configurable if final or normal damage when taking / dealing
	 * 	Also maybe on dealing damage, dmg dealt 60% final damage weight and 40% theory damage weight for calc
	 * 	Damage dealt > Damage taken => harder to cheat / manipulate stats, since final/theory damage is weighted differently in taking damage
	* */
	
	@Override
	public void onEnable() {
		Instance = this;
		// Plugin startup logic
		WeightConfiguration.Load();
		ItemValues.Load();
		this.getCommand("skillrating").setExecutor(this);
		this.getCommand("skill").setExecutor(this);
		this.getCommand("reloadconfig").setExecutor(this);
		this.getCommand("reloadcfg").setExecutor(this);
		this.getCommand("rlcfg").setExecutor(this);
		StartVisualSkillRatingUpdater();
	}
	
	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}
	
	int visualskillrating;
	public void StartVisualSkillRatingUpdater()
	{
		visualskillrating = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§eSkill Rating: §c" + EquipmentEvaluation.AsString(EquipmentEvaluation.EvaluateCompletePlayerRating(p), 1)));
				}
			}
		}, 0, 35);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equals("skillrating") || command.getName().equals("skill"))
		{
			if(sender instanceof ConsoleCommandSender && args.length == 0)
			{
				sender.sendMessage("§cThe self version of this command is only available for players.");
				return false;
			}
			if(!(sender instanceof Player) && args.length == 0)
			{
				sender.sendMessage("§cThe self version of this command is only available for players.");
				return false;
			}
			if(args.length > 1)
			{
				sender.sendMessage("§e/skillrating [Name of online player]");
				return false;
			}
			
			Player p = null;
			String prefix = "§e";
			if(args.length == 1)
			{
				// Not the best implementation
				for(Player p2 : Bukkit.getOnlinePlayers())
				{
					if(p2.getName().equalsIgnoreCase(args[0]))
					{
						p = p2;
						prefix += p2.getName();
						if(prefix.toLowerCase().endsWith("s")) prefix += "' ";
						else prefix += "'s ";
						break;
					}
				}
			}
			else
			{
				p = (Player) sender;
			}
			
			if(p == null)
			{
				sender.sendMessage("§cCould not find the player " + args[0]);
				return false;
			}
			
			String text = prefix + "Skill Rating: " + EquipmentEvaluation.AsString(EquipmentEvaluation.EvaluateCompletePlayerRating(p), 1);
			Bukkit.broadcastMessage(text);
			//p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(text));
		}
		else if(command.getName().toLowerCase().startsWith("reload") || command.getName().equalsIgnoreCase("rlcfg"))
		{
			WeightConfiguration.EnchantmentWeights.clear();
			WeightConfiguration.Load();
			ItemValues.ItemValues.clear();
			ItemValues.Load();
			sender.sendMessage("§eAll SkillRating Configurations have been reloaded.");
		}
		return true;
	}
}
