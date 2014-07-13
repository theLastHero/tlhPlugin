package net.thelasthero.tlhPlugin;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class playerListener implements Listener {

	tlhPlugin pl;

	public playerListener(tlhPlugin plugin) {
		pl = plugin;
	}
	
	// -------------------------------------------------------------------------------------
	// onPlayerJoin - Fires when a player logins into the server.
	// -------------------------------------------------------------------------------------
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent e) {

		// set the join message to null, don't display a message
		e.setJoinMessage(null);

		// set player
		Player p = e.getPlayer();
		
		// loop through to clear players screen just in case, send empty
		// message
		for (int i = 0; i < 20; i++) {
			p.sendMessage("");
		}

		// If first time joining the server, else
		if (!p.hasPlayedBefore()) {
			// now send our welcome message
			p.sendMessage(ChatColor.GREEN + "┌                                             ┐");
			p.sendMessage(ChatColor.GRAY + "          Welcome to " + ChatColor.DARK_GRAY + "the" + ChatColor.DARK_RED.toString() + ChatColor.BOLD.toString() + "Last" + ChatColor.RED + "Hero");
			p.sendMessage(ChatColor.GREEN + "     Vote: http://minetime.com.au/vote");
			p.sendMessage(ChatColor.GREEN + "  Claim Land: http://thelasyhero.net/claiming");
			p.sendMessage(ChatColor.GREEN + " ");
			p.sendMessage(ChatColor.GREEN + "└                                             ┘");
			
			// teleport player to spawn/start location
			p.teleport(pl.spawnLocation);
			
			
			// Give new players some items
			pl.giveItem(p.getName().toString(),Material.IRON_HELMET, 1, (short) 50, ChatColor.AQUA
					+ "Hero's Starter Helm", ChatColor.RED + "Something to", ChatColor.RED
					+ "help you get started");

			pl.giveItem(p.getName().toString(),Material.IRON_CHESTPLATE, 1, (short) 50, ChatColor.AQUA
					+ "Hero's Starter Chestplate", ChatColor.RED + "Something to", ChatColor.RED
					+ "help you get started");

			pl.giveItem(p.getName().toString(),Material.IRON_LEGGINGS, 1, (short) 50, ChatColor.AQUA
					+ "Hero's Starter Leggings", ChatColor.RED + "Something to", ChatColor.RED
					+ "help you get started");

			pl.giveItem(p.getName().toString(),Material.IRON_BOOTS, 1, (short) 50, ChatColor.AQUA
					+ "Hero's Starter Boots", ChatColor.RED + "Something to", ChatColor.RED
					+ "help you get started");

			pl.giveItem(p.getName().toString(),Material.IRON_SWORD, 1, (short) 50, ChatColor.AQUA
					+ "Hero's Starter Sword", ChatColor.RED + "Something to", ChatColor.RED
					+ "help you get started");

			pl.giveItem(p.getName().toString(),Material.IRON_AXE, 1, (short) 50, ChatColor.AQUA
					+ "Hero's Starter Axe", ChatColor.RED + "Something to", ChatColor.RED
					+ "help you get started");

			pl.giveItem(p.getName().toString(),Material.IRON_PICKAXE, 1, (short) 50, ChatColor.AQUA
					+ "Hero's Starter Pickaxe", ChatColor.RED + "Something to", ChatColor.RED
					+ "help you get started");

			pl.giveItem(p.getName().toString(),Material.COOKED_BEEF, 12, (short) 50, ChatColor.AQUA
					+ "Hero's Starter Food", ChatColor.RED + "Something to", ChatColor.RED
					+ "help you get started");
			
			//player effect for new players joining
			p.getWorld().playEffect(p.getLocation(), Effect.EXPLOSION_HUGE, 4);
		} 
			else
		{
			// now send our welcome message
			p.sendMessage(ChatColor.GREEN + "┌                                             ┐");
			p.sendMessage(ChatColor.GREEN + " ");
			p.sendMessage(ChatColor.GRAY + "          Welcome to " + ChatColor.DARK_GRAY + "the" + ChatColor.DARK_RED.toString() + ChatColor.BOLD.toString() + "Last" + ChatColor.RED + "Hero");
			p.sendMessage(ChatColor.GREEN + "      Website: http://theLastHero.net");
			p.sendMessage(ChatColor.GREEN + "     Vote: http://theLastHero.net/vote");
			p.sendMessage(ChatColor.GREEN + " ");
			p.sendMessage(ChatColor.GREEN + "└                                             ┘");
		}
		
		// Add player to mute hashmap
		pl.muteChat(p, pl.timeMuted);

		// set players variables
		// p.setLevel(10);
		// p.setHealth(20);
		// p.setSaturation(20);
		// p.setFoodLevel(20);
		// p.setExhaustion(0);

	}


}
