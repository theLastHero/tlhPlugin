package net.thelasthero.tlhPlugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

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
			p.sendMessage(ChatColor.GREEN + " ");
			p.sendMessage(ChatColor.GRAY + "          Welcome to " + ChatColor.DARK_GRAY + "the" + ChatColor.DARK_RED.toString() + ChatColor.BOLD.toString() + "Last" + ChatColor.RED + "Hero");
			p.sendMessage(ChatColor.GREEN + "      Website: http://minetime.com.au");
			p.sendMessage(ChatColor.GREEN + "     Vote: http://minetime.com.au/vote");
			p.sendMessage(ChatColor.GREEN + " ");
			p.sendMessage(ChatColor.GREEN + "└                                             ┘");
			
			// teleport player to spawn/start location
			p.teleport(pl.spawnLocation);

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
