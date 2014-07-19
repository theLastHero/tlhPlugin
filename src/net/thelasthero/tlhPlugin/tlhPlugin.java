package net.thelasthero.tlhPlugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


/* 
 * 
 * 
 * 
 * 
 * 
 * 
 */
public class tlhPlugin extends JavaPlugin {
	
	// hashmaps
	// ----------
	public Map<String, Long> muted = new HashMap<String, Long>();// mute list hashmap
		
	// longs & ints
	// ---------------
	public long timeMuted = 5000; // How long they muted for at login
	
	// locations
	// -----------
	public Location spawnLocation, shopSpawnA, shopSpawnB, shopSpawnC;

	String thiefPrefix = ChatColor.GRAY.toString() + "[" + ChatColor.WHITE.toString() + "Thief" + ChatColor.GRAY.toString() + "] ";
	String citizenPrefix = ChatColor.GRAY.toString() + "[" + ChatColor.DARK_PURPLE.toString() + "Citizen" + ChatColor.GRAY.toString() + "] ";
	String protectorPrefix = ChatColor.GRAY.toString() + "[" + ChatColor.RED.toString() + "Protector" + ChatColor.GRAY.toString() + "] ";
	String doctorPrefix = ChatColor.GRAY.toString() + "[" + ChatColor.BLUE.toString() + "Doctor" + ChatColor.GRAY.toString() + "] ";
	String leaderPrefix = ChatColor.GRAY.toString() + "[" + ChatColor.YELLOW.toString() + "Leader" + ChatColor.GRAY.toString() + "] ";
	String heroPrefix = ChatColor.GRAY.toString() + "[" + ChatColor.GREEN.toString() + "Hero" + ChatColor.GRAY.toString() + "] ";
	
	// others
	//-------
	public static tlhPlugin instance;
	
	
	// -------------------------------------------------------------------------------------
	// onDisable
	// -------------------------------------------------------------------------------------	
	@Override
	public void onDisable() {
		// Display disabled message to console
		getLogger().info("===[tlh]=== tlhPlugin Disabled ===");
	}
	

	// -------------------------------------------------------------------------------------
	// onEnable
	// -------------------------------------------------------------------------------------	
	@Override
	public void onEnable() {

		instance = this;
		
		// Display enabled message to console
		getLogger().info("===[tlh]=== tlhPlugin Enabled ===");

		// Register playerListener
		Bukkit.getServer().getPluginManager()
				.registerEvents(new playerListener(this), this);
		//register command listener for /spawn
		getCommand("spawn").setExecutor(new commandListener(this));
		getCommand("shops").setExecutor(new commandListener(this));
		
		//set spawn point
		spawnLocation = new Location(Bukkit.getWorld("World"), 1925.44311, 77, 955.21311, (float) -0.14778212, (float) 0);
		
		//set shop spawn locations
		//shopSpawnA = new Location(Bukkit.getWorld("playerShops"), 0, 66, 0, (float) -0.14778212, (float) 0);
		//shopSpawnB = new Location(Bukkit.getWorld("playerShops"), 0, 66, 0, (float) -0.14778212, (float) 0);
		//shopSpawnC = new Location(Bukkit.getWorld("playerShops"), 0, 66, 0, (float) -0.14778212, (float) 0);

		// Strings
		thiefPrefix = ChatColor.GRAY.toString() + "[" + ChatColor.WHITE.toString() + "Thief" + ChatColor.GRAY.toString() + "] "+ ChatColor.WHITE.toString();
		citizenPrefix = ChatColor.GRAY.toString() + "[" + ChatColor.DARK_PURPLE.toString() + "Citizen" + ChatColor.GRAY.toString() + "] "+ ChatColor.WHITE.toString();
		protectorPrefix = ChatColor.GRAY.toString() + "[" + ChatColor.RED.toString() + "Protector" + ChatColor.GRAY.toString() + "] "+ ChatColor.WHITE.toString();
		doctorPrefix = ChatColor.GRAY.toString() + "[" + ChatColor.BLUE.toString() + "Doctor" + ChatColor.GRAY.toString() + "] "+ ChatColor.WHITE.toString();
		leaderPrefix = ChatColor.GRAY.toString() + "[" + ChatColor.YELLOW.toString() + "Leader" + ChatColor.GRAY.toString() + "] "+ ChatColor.WHITE.toString();
		heroPrefix = ChatColor.GRAY.toString() + "[" + ChatColor.GREEN.toString() + "Hero" + ChatColor.GRAY.toString() + "] "+ ChatColor.WHITE.toString();
		
	}
	
	
	// -------------------------------------------------------------------------------------
	// muteChat
	// -------------------------------------------------------------------------------------
	public void muteChat(Player player, long delay) {
		muted.put(player.getName(), System.currentTimeMillis() + delay);
	}
	
	
	// -------------------------------------------------------------------------------------
	// getInstance
	// -------------------------------------------------------------------------------------
	public static Plugin getInstance() {
		// TODO Auto-generated method stub
		return instance;
	}
	
	
	// -------------------------------------------------------------------------------------
	// toTitleCase
	// -------------------------------------------------------------------------------------
	public static String toTitleCase(String givenString) {
		String[] arr = givenString.split(" ");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			sb.append(Character.toUpperCase(arr[i].charAt(0))).append(arr[i].substring(1)).append(" ");
		}
		return sb.toString().trim();
	}
	
	
	// -------------------------------------------------------------------------------------
	// giveItem
	// -------------------------------------------------------------------------------------
	public void giveItem(String player, Material material,
			int amount, short data, Enchantment enchant, int enchantLevel, String name, String... lore) {
		
		if (Bukkit.getPlayer(player) instanceof Player && Bukkit.getPlayer(player).isOnline()) {
			Player p = (Player) Bukkit.getPlayer(player);
		
			//create new stack
			ItemStack stack = new ItemStack(material, amount, data);
			
			//set meta and lore data
			ItemMeta meta = stack.getItemMeta();
			meta.setDisplayName((name));
			meta.setLore(Arrays.asList(lore));
			stack.setItemMeta(meta);
			
			//add enchantments
			if(!(enchant == null)){
				stack.addEnchantment(enchant, enchantLevel);
			}
		
			//give item to player
			p.getInventory().addItem(stack);
		
		}
	}

}
