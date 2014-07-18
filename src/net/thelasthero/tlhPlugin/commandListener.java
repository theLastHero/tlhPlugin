package net.thelasthero.tlhPlugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandListener implements CommandExecutor{

	tlhPlugin pl;

	public commandListener(tlhPlugin plugin) {
		pl = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s,
			String[] args) {
		
		//if not sent by a player ignore
		if (!(sender instanceof Player)) {
			return true;
		}
		
		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("spawn")) {
			p.teleport(pl.spawnLocation);
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("shops")) {
			p.teleport(pl.shopSpawnA);
			return true;
		}
		
		
		return false;
	}

}
