package net.thelasthero.tlhPlugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.kitteh.tag.AsyncPlayerReceiveNameTagEvent;
import org.kitteh.tag.PlayerReceiveNameTagEvent;

public class playerListener implements Listener {

	tlhPlugin pl;

	public playerListener(tlhPlugin plugin) {
		pl = plugin;
	}
	
	
	
/*
	 @EventHandler
	 public void ChatStuff(AsyncPlayerChatEvent event){
	 Player p = event.getPlayer();
	 
	 String pName = p.getName();
	 
	 if (pl.nameColor.containsKey(pName)){
		 if (pl.nameColor.get(pName).equalsIgnoreCase("red")){
			 pName = ChatColor.RED.toString() + pName;
		 } else if (pl.nameColor.get(pName).equalsIgnoreCase("white")){
			 pName = ChatColor.WHITE.toString() + pName;
		 } else if (pl.nameColor.get(pName).equalsIgnoreCase("yellow")){
			 pName = ChatColor.YELLOW.toString() + pName;
		 } else if (pl.nameColor.get(pName).equalsIgnoreCase("aqua")){
			 pName = ChatColor.AQUA.toString() + pName;
		 }
		 
		 
	 }
	 
	 
	 String message = event.getMessage();
	 //event.setCancelled(true);
	 if(p.hasPermission("thelasthero.thief")){
		 p.setDisplayName(pl.thiefPrefix +  pName + ChatColor.WHITE);
	 }
	 if(p.hasPermission("thelasthero.citizen")){
		 p.setDisplayName(pl.citizenPrefix +  pName + ChatColor.WHITE);
	 }
	 if(p.hasPermission("thelasthero.protector")){
		 p.setDisplayName(pl.protectorPrefix +  pName + ChatColor.WHITE);
	 }
	 if(p.hasPermission("thelasthero.doctor")){
		 p.setDisplayName(pl.doctorPrefix +  pName + ChatColor.WHITE);
	 }
	 if(p.hasPermission("thelasthero.leader")){
		 p.setDisplayName(pl.leaderPrefix +  pName + ChatColor.WHITE);
	 }
	 if(p.hasPermission("thelasthero.hero")){
		 p.setDisplayName(pl.heroPrefix +  pName + ChatColor.WHITE);
	 }
	 
	 return;
	 }
*/
	 
	// -------------------------------------------------------------------------------------
	// onPlayerJoin - Fires when a player logins into the server.
	// -------------------------------------------------------------------------------------
	// * Cancel the default welcome/login message
	// * Clear the player chat screen
	// * Send our own welcome messages
	// * Mute player from sending or receive chat during show message time
	// * Teleport player to spawn location if first login
	// * Give first join players kit/items
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoinEvent(PlayerJoinEvent e) {
		
		// set the join message to null, don't display a message
		e.setJoinMessage(null);

		// set player
		final Player p = e.getPlayer();
		
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
			  public void run() {
			    // your code here
			
		
		// loop through to clear players screen just in case, send empty
		// message
		for (int i = 0; i < 20; i++) {
			p.sendMessage("");
		}

		// If first time joining the server, else
		if (!p.hasPlayedBefore()) {
			// now send our welcome message
			p.sendMessage(ChatColor.GRAY + "┌                                             ┐");
			p.sendMessage(ChatColor.GRAY + "          Welcome to " + ChatColor.DARK_GRAY + "the" + ChatColor.DARK_RED.toString() + ChatColor.BOLD.toString() + "Last" + ChatColor.RED + "Hero");
			p.sendMessage(ChatColor.GREEN + "     Vote: http://minetime.com.au/vote");
			p.sendMessage(ChatColor.GREEN + "  Claim Land: http://thelasyhero.net/claiming");
			p.sendMessage(ChatColor.GREEN + " ");
			p.sendMessage(ChatColor.GRAY + "└                                             ┘");
			
			// teleport player to spawn/start location
			p.teleport(pl.spawnLocation);
			
			
			// Give new players some items
			pl.giveItem(p.getUniqueId(),Material.IRON_HELMET, 1, (short) 0, Enchantment.PROTECTION_ENVIRONMENTAL, 2, ChatColor.AQUA
					+ "Hero's Starter Helm", ChatColor.RED + "Something to", ChatColor.RED
					+ "help you get started");

			pl.giveItem(p.getUniqueId(),Material.IRON_CHESTPLATE, 1, (short) 0, Enchantment.PROTECTION_ENVIRONMENTAL, 2, ChatColor.AQUA
					+ "Hero's Starter Chestplate", ChatColor.RED + "Something to", ChatColor.RED
					+ "help you get started");

			pl.giveItem(p.getUniqueId(),Material.IRON_LEGGINGS, 1, (short) 0, Enchantment.PROTECTION_ENVIRONMENTAL, 2, ChatColor.AQUA
					+ "Hero's Starter Leggings", ChatColor.RED + "Something to", ChatColor.RED
					+ "help you get started");

			pl.giveItem(p.getUniqueId(),Material.IRON_BOOTS, 1, (short) 0, Enchantment.PROTECTION_ENVIRONMENTAL, 2, ChatColor.AQUA
					+ "Hero's Starter Boots", ChatColor.RED + "Something to", ChatColor.RED
					+ "help you get started");

			pl.giveItem(p.getUniqueId(),Material.IRON_SWORD, 1, (short) 0, Enchantment.DAMAGE_ALL, 2, ChatColor.AQUA
					+ "Hero's Starter Sword", ChatColor.RED + "Something to", ChatColor.RED
					+ "help you get started");

			pl.giveItem(p.getUniqueId(),Material.IRON_AXE, 1, (short) 0, Enchantment.DIG_SPEED, 2, ChatColor.AQUA
					+ "Hero's Starter Axe", ChatColor.RED + "Something to", ChatColor.RED
					+ "help you get started");

			pl.giveItem(p.getUniqueId(),Material.IRON_PICKAXE, 1, (short) 0, Enchantment.DURABILITY, 2, ChatColor.AQUA
					+ "Hero's Starter Pickaxe", ChatColor.RED + "Something to", ChatColor.RED
					+ "help you get started");

			pl.giveItem(p.getUniqueId(),Material.COOKED_BEEF, 12, (short) 0, null, 0, ChatColor.AQUA
					+ "Hero's Starter Food", ChatColor.RED + "Something to", ChatColor.RED
					+ "help you get started");
			
			pl.giveItem(p.getUniqueId(),Material.GOLD_SPADE, 1, (short) 0, null, 0, ChatColor.AQUA
					+ " ", ChatColor.RED + "Something to", ChatColor.RED
					+ " ");
			
			//player effect for new players joining
			p.getWorld().playEffect(p.getLocation(), Effect.SMOKE, 0);
			//ParticleEffect.HEART.display(p.getLocation(), 0, 1, 0, 5, 10);
			
		} 
			else
		{
			// now send our welcome message
			p.sendMessage(ChatColor.GRAY + "┌                                             ┐");
			p.sendMessage(ChatColor.GREEN + " ");
			p.sendMessage(ChatColor.GRAY + "          Welcome to " + ChatColor.DARK_GRAY + "the" + ChatColor.DARK_RED.toString() + ChatColor.BOLD.toString() + "Last" + ChatColor.RED + "Hero");
			p.sendMessage(ChatColor.GREEN + "      Website: http://theLastHero.net");
			p.sendMessage(ChatColor.GREEN + "     Vote: http://theLastHero.net/vote");
			p.sendMessage(ChatColor.GREEN + " ");
			p.sendMessage(ChatColor.GRAY + "└                                             ┘");
		}
		
		// Add player to mute hashmap
		pl.muteChat(p, pl.timeMuted);

		// set players variables
		// p.setLevel(10);
		// p.setHealth(20);
		// p.setSaturation(20);
		// p.setFoodLevel(20);
		// p.setExhaustion(0);

		//setup playerDisplayName
		pl.setUserDisplayName(p.getUniqueId());
		}
		}, 5);

	}
	
	

	// -------------------------------------------------------------------------------------
	// onPlayerQuit - Fires when a player quits the server.
	// -------------------------------------------------------------------------------------
	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent e){
		e.setQuitMessage(null);
		}

	// -------------------------------------------------------------------------------------
	// playerRespawn - Fires when a player quits the server.
	// -------------------------------------------------------------------------------------
    @EventHandler
    public boolean playerRespawn(PlayerRespawnEvent event) {
    event.setRespawnLocation(pl.spawnLocation);
	return false;
    }
    
    @EventHandler
    public void onNameTag(AsyncPlayerReceiveNameTagEvent event) {
        if (pl.nameColor.containsKey(event.getNamedPlayer().getUniqueId())) { //See if a tag is defined
            event.setTag(pl.convertColor(pl.nameColor.get(event.getNamedPlayer().getUniqueId())) + event.getNamedPlayer().getName()); 
        }
    }
	
	
}
