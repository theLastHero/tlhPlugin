package net.thelasthero.tlhPlugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
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
	public Location spawnLocation;

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
		
		//set spawn point
		spawnLocation = new Location(Bukkit.getWorld("World"), 1925.44311, 70, 955.21311, (float) -0.14778212, (float) 0);
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
	// createItem
	// -------------------------------------------------------------------------------------
	public void giveItem(String player, Material material,
			int amount, short data, String name, String... lore) {
		
		Player p = (Player) Bukkit.getPlayer(player);
		
		ItemStack stack = new ItemStack(material, amount, data);
		ItemMeta meta = stack.getItemMeta();

		meta.setDisplayName((name));

		meta.setLore(Arrays.asList(lore));

		stack.setItemMeta(meta);

		p.getInventory().addItem(stack);
		
		//HitSoundsInv.setItem(invPosition, stack);
		//return stack;
	}

}
