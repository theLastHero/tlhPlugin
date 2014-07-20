package net.thelasthero.tlhPlugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.thelasthero.tlhPlugin.playerListener;

public class commandListener implements CommandExecutor{

	tlhPlugin pl;

	public commandListener(tlhPlugin plugin) {
		pl = plugin;
	}
	
	// -------------------------------------------------------------------------------------
	// onCommand
	// -------------------------------------------------------------------------------------
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
			p.teleport(new Location(Bukkit.getWorld("playerShops"), 0, 66, 0));
			return true;
		}
		
		//nameicon command
		if (cmd.getName().equalsIgnoreCase("nameicon")) {
			if(args.length <=0){
				p.sendMessage(ChatColor.DARK_GRAY + "the" + ChatColor.DARK_RED.toString() + ChatColor.BOLD.toString() + "Last" + ChatColor.RED + "Hero" + ChatColor.YELLOW + " - NameIcon");
				p.sendMessage(ChatColor.GREEN + "--------------");
				p.sendMessage(" ");
				//p.sendMessage(ChatColor.GREEN + "/nameicon clear <- Clear your name icon.");
		        p.sendMessage(ChatColor.GREEN + "/nameicon <ICON_NAME> <- Change name icon.");
		        p.sendMessage(ChatColor.WHITE + "Icons: ");
		        p.sendMessage(ChatColor.WHITE + pl.parseUTF8((String)"\u2665") + ChatColor.GRAY + " - heart    " 
		        + ChatColor.WHITE + pl.parseUTF8((String)"\u2666") + ChatColor.GRAY + " - diamond    " 
		        + ChatColor.WHITE + pl.parseUTF8((String)"\u2660") + ChatColor.GRAY + " - spade    " 
		        + ChatColor.WHITE + pl.parseUTF8((String)"\u2663") + ChatColor.GRAY + " - club");
		        
		        p.sendMessage(ChatColor.WHITE + pl.parseUTF8((String)"\u263A") + ChatColor.GRAY + " - smiley    ");
			
			} else {
				if(p.hasPermission("thelasthero.nameicon")){
					if(pl.nameIconLists.contains(args[0])){
						pl.nameIcon.put(p.getUniqueId(), args[0]);
					
					} else {
						p.sendMessage(args[0]+ " is not a vaild or supported icon.");
					}
					
					//set users displayName
					pl.setUserDisplayName(p.getUniqueId());
					
				} else {
					p.sendMessage(ChatColor.RED + "You do not have permission to do that.");
				}
			}
		return true;	
		}
		
		//namecolor commmand
		if (cmd.getName().equalsIgnoreCase("namecolor")) {
			if(args.length <=0){
				p.sendMessage(ChatColor.DARK_GRAY + "the" + ChatColor.DARK_RED.toString() + ChatColor.BOLD.toString() + "Last" + ChatColor.RED + "Hero" + ChatColor.YELLOW + " - NameColor");
				p.sendMessage(ChatColor.GREEN + "--------------");
				p.sendMessage(" ");
				p.sendMessage(ChatColor.GREEN + "/namecolor reset <- Reset to white.");
		        p.sendMessage(ChatColor.GREEN + "/namecolor <COLOR> <- Change to color.");
		        p.sendMessage(ChatColor.WHITE + "Colors: " + ChatColor.AQUA + "aqua, " + 
		            ChatColor.BLUE + "blue, " + 
		            ChatColor.GOLD + "gold, " + ChatColor.GRAY + "gray, " + 
		            ChatColor.GREEN + "green, " + ChatColor.RED + "red, " + 
		            ChatColor.YELLOW + "yellow, " + ChatColor.WHITE + "white, " + 
		            ChatColor.LIGHT_PURPLE + "light_Purple, " + ChatColor.DARK_AQUA + "dark_aqua, " + 
		            ChatColor.DARK_BLUE + "dark_blue, " + ChatColor.DARK_GRAY + "dark_gray, " + 
		            ChatColor.DARK_GREEN + "dark_green, " + ChatColor.DARK_PURPLE + "dark_purple, " + 
		            ChatColor.DARK_RED + "dark_red, ");
			} else {
				if(p.hasPermission("thelasthero.namecolor")){
					if(pl.nameColorLists.contains(args[0])){
					
						//put into hashmap  name/color
						if(args[0].equalsIgnoreCase("reset")){
							pl.nameColor.put(p.getUniqueId(), "light_gray");
							p.sendMessage(ChatColor.GREEN + "Your name color has been reset");
							
						} else {
							pl.nameColor.put(p.getUniqueId(), args[0]);
							//send user a message
							p.sendMessage(ChatColor.GREEN + "Your name color has been change to: " + pl.convertColor(args[0]) + args[0]);
						}
					
						//set users displayName
						pl.setUserDisplayName(p.getUniqueId());
					
						
					} else {
						p.sendMessage(pl.convertColor(args[0]).toString() + "is not a vaild or supported color.");
					}
				} else {
					p.sendMessage(ChatColor.RED + "You do not have permission to do that.");
				}
			}
			
			return true;
		}
		
		
		
		if (cmd.getName().equalsIgnoreCase("test")) {
			p.sendMessage(pl.parseUTF8((String)"\u2660"));
			p.sendMessage(pl.parseUTF8((String)"\u2600"));
			p.sendMessage(pl.parseUTF8((String)"\u2603"));
			p.sendMessage(pl.parseUTF8((String)"\u2604"));
			p.sendMessage(pl.parseUTF8((String)"\u2605"));
			p.sendMessage(pl.parseUTF8((String)"\u2606"));
			p.sendMessage(pl.parseUTF8((String)"\u260E"));
			p.sendMessage(pl.parseUTF8((String)"\u2618"));
			p.sendMessage(pl.parseUTF8((String)"\u261A"));
			p.sendMessage(pl.parseUTF8((String)"\u261B"));
			p.sendMessage(pl.parseUTF8((String)"\u2620"));
			
		}
		
		
		return false;
	}
	
	
	 

}
