package net.thelasthero.tlhPlugin;

import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import org.kitteh.tag.TagAPI;


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
	public Map<UUID, Long> muted = new HashMap<UUID, Long>();// mute list hashmap
	public HashMap<UUID, String> nameColor = new HashMap<UUID, String>();// namecolor list hashmap
	public HashMap<UUID, String> nameIcon = new HashMap<UUID, String>();//
	public HashMap<UUID, String> iconColor = new HashMap<UUID, String>();
	public HashMap<UUID, String> soundsPlayerHitAnything = new HashMap<UUID, String>();
	public HashMap<UUID, String> soundsPlayerHitPlayer = new HashMap<UUID, String>();
	public HashMap<UUID, String> soundsPlayerHitMob = new HashMap<UUID, String>();
	public HashMap<UUID, String> soundsPlayerKillPlayer = new HashMap<UUID, String>();
	public HashMap<UUID, String> soundsPlayerKillMob = new HashMap<UUID, String>();
	
	// longs & ints
	// ---------------
	public long timeMuted = 5000; // How long they muted for at login
	
	// locations
	// -----------
	public Location spawnLocation, shopSpawnA, shopSpawnB, shopSpawnC;

	public static String thiefPrefix = ChatColor.GRAY.toString() + "[" + ChatColor.WHITE.toString() + "Thief" + ChatColor.GRAY.toString() + "] " + ChatColor.GRAY.toString();
	public static String citizenPrefix = ChatColor.GRAY.toString() + "[" + ChatColor.DARK_PURPLE.toString() + "Citizen" + ChatColor.GRAY.toString() + "] " + ChatColor.GRAY.toString();
	public static String protectorPrefix = ChatColor.GRAY.toString() + "[" + ChatColor.RED.toString() + "Protector" + ChatColor.GRAY.toString() + "] " + ChatColor.GRAY.toString();
	public static String doctorPrefix = ChatColor.GRAY.toString() + "[" + ChatColor.BLUE.toString() + "Doctor" + ChatColor.GRAY.toString() + "] " + ChatColor.GRAY.toString();
	public static String leaderPrefix = ChatColor.GRAY.toString() + "[" + ChatColor.YELLOW.toString() + "Leader" + ChatColor.GRAY.toString() + "] " + ChatColor.GRAY.toString();
	public static String heroPrefix = ChatColor.GRAY.toString() + "[" + ChatColor.GREEN.toString() + "Hero" + ChatColor.GRAY.toString() + "] " + ChatColor.GRAY.toString();
	
	// others
	//-------
	public static tlhPlugin instance;
	public List<String> nameColorLists = new ArrayList<String>();
	public List<String> nameIconLists = new ArrayList<String>();
	
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
		getCommand("namecolor").setExecutor(new commandListener(this));
		getCommand("nameicon").setExecutor(new commandListener(this));
		getCommand("sounds").setExecutor(new commandListener(this));
		getCommand("test").setExecutor(new commandListener(this));
		
		//set spawn point
		spawnLocation = new Location(Bukkit.getWorld("World"), 1925.44311, 77, 955.21311, (float) -0.14778212, (float) 0);
		
		//set shop spawn locations
		//shopSpawnA = new Location(Bukkit.getWorld("playerShops"), 0, 66, 0, (float) -0.14778212, (float) 0);
		//shopSpawnB = new Location(Bukkit.getWorld("playerShops"), 0, 66, 0, (float) -0.14778212, (float) 0);
		//shopSpawnC = new Location(Bukkit.getWorld("playerShops"), 0, 66, 0, (float) -0.14778212, (float) 0);

		//list of color names for /namecolor command
		nameColorLists.add("aqua");
		nameColorLists.add("blue");
		nameColorLists.add("gold");
		nameColorLists.add("gray");
		nameColorLists.add("green");
		nameColorLists.add("red");
		nameColorLists.add("yellow");
		nameColorLists.add("white");
		nameColorLists.add("light_Purple");
		nameColorLists.add("dark_aqua");
		nameColorLists.add("dark_blue");
		nameColorLists.add("dark_gray");
		nameColorLists.add("dark_green");
		nameColorLists.add("dark_purple");
		nameColorLists.add("dark_red");
		 
		 //list of icons
		 nameIconLists.add("heart");
		 nameIconLists.add("diamond");
		 nameIconLists.add("spade");
		 nameIconLists.add("club");

		 nameIconLists.add("smiley");
		 nameIconLists.add("peace");
		 nameIconLists.add("skull");
		 nameIconLists.add("dharma");

		 nameIconLists.add("star");
		 nameIconLists.add("snowman");
		 
		 try {
				nameColor = SLAPI.load("plugins/thelasthero/namecolor.bin");
		            } catch(Exception e) {
		                //handle the exception
		                e.printStackTrace();
		            }
		 try {
				nameIcon = SLAPI.load("plugins/thelasthero/nameicon.bin");
		            } catch(Exception e) {
		                //handle the exception
		                e.printStackTrace();
		            }
		 try {
				iconColor = SLAPI.load("plugins/thelasthero/iconcolor.bin");
		            } catch(Exception e) {
		                //handle the exception
		                e.printStackTrace();
		            }
		
	}
	
	
	// -------------------------------------------------------------------------------------
	// muteChat
	// -------------------------------------------------------------------------------------
	public void muteChat(Player p, long delay) {
		muted.put(p.getUniqueId(), System.currentTimeMillis() + delay);
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
	public void giveItem(UUID uuid, Material material,
			int amount, short data, Enchantment enchant, int enchantLevel, String name, String... lore) {
		
		if (Bukkit.getServer().getPlayer(uuid) instanceof Player && Bukkit.getServer().getPlayer(uuid).isOnline()) {
			Player p = Bukkit.getServer().getPlayer(uuid);
		
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
	
	
	// -------------------------------------------------------------------------------------
	// setUserDisplayName
	// -------------------------------------------------------------------------------------
	public void setUserDisplayName(UUID uuid){
		
		Player p = Bukkit.getServer().getPlayer(uuid);
		
		//setup playerName
		String playerName = p.getName();
		
		//setup prefix
		//-----------------
		String namePrefixString = thiefPrefix;
		
		if(p.hasPermission("thelasthero.thief")){
			namePrefixString = thiefPrefix;
		 }
		 if(p.hasPermission("thelasthero.citizen")){
			 namePrefixString = citizenPrefix;
		 }
		 if(p.hasPermission("thelasthero.protector")){
			 namePrefixString = protectorPrefix;
		 }
		 if(p.hasPermission("thelasthero.doctor")){
			 namePrefixString = doctorPrefix;
		 }
		 if(p.hasPermission("thelasthero.leader")){
			 namePrefixString = leaderPrefix;
		 }
		 if(p.hasPermission("thelasthero.hero")){
			 namePrefixString = heroPrefix;
		 }
		 //----------------
		 
		 
		 //setup nameColor
		 //----------------
		 String nameColorString = "white";
		 if (nameColor.containsKey(p.getUniqueId())){
			 nameColorString = nameColor.get(p.getUniqueId());
		 } 
		 
		 nameColorString = convertColor(nameColorString);
		 //------------------
		 
		 
		 //setup nameIcon
		 //------------------
		 String nameIconString = "";
		 if (nameIcon.containsKey(p.getUniqueId())){
			 nameIconString = nameIcon.get(p.getUniqueId());
		 }
		 
		 nameIconString = convertIcon(nameIconString);
		 //------------------
		 
		 
		 //setup iconVolor
		 //------------------
		 String iconColorString = "white";
		 if (iconColor.containsKey(p.getUniqueId())){
			 iconColorString = iconColor.get(p.getUniqueId());
		 }
		 
		 iconColorString =  convertColor(iconColorString);
		 
		 //------------------
		 
		 
		 
		 //setup displayName
		 //------------------
		 String displayNameString = "";
		 displayNameString = namePrefixString + ChatColor.WHITE.toString() + iconColorString + nameIconString + nameColorString + playerName + ChatColor.WHITE.toString() + iconColorString + nameIconString;
		 
		 
		 //setup tabName
		 //------------------
		 String tabName = "";
		 tabName = nameColorString + playerName;
		 
		 if (tabName.length() > 16){
			 tabName = tabName.substring(0,16);
		 }
		 
		 
		 //set displayname in game
		 p.setDisplayName(displayNameString);
		 //set tab list name in game
		 p.setPlayerListName(tabName);
		 //set nametag in game
		 TagAPI.refreshPlayer(p.getPlayer());
		 
		 
		 /*
		 p.setDisplayName(namePrefix + cName + p.getName().toString() + ChatColor.WHITE);	 
		 
		 String tabName = cName + p.getName().toString();
		 
		 if (tabName.length() > 16){
			 tabName = tabName.substring(0, 16);
			}
		 
		 
		 
		 */
		 
			try {
				SLAPI.save(nameColor,"plugins/thelasthero/namecolor.bin");
		            } catch(Exception e) {
		                 e.printStackTrace();
		            }
			try {
				SLAPI.save(nameIcon,"plugins/thelasthero/nameicon.bin");
		            } catch(Exception e) {
		                 e.printStackTrace();
		            }
			try {
				SLAPI.save(nameIcon,"plugins/thelasthero/iconcolor.bin");
		            } catch(Exception e) {
		                 e.printStackTrace();
		            }
	}


	// -------------------------------------------------------------------------------------
	// convertColor
	// -------------------------------------------------------------------------------------
	public String convertColor(String colorString){
	
		//get color into a string
		String returnColor = ChatColor.WHITE.toString();
	 
		if (colorString.equalsIgnoreCase("aqua")){
			returnColor = ChatColor.AQUA.toString();
		} else if (colorString.equalsIgnoreCase("black")){
			returnColor = ChatColor.BLACK.toString();
		} else if (colorString.equalsIgnoreCase("blue")){
			returnColor = ChatColor.BLUE.toString();
		} else if (colorString.equalsIgnoreCase("gold")){
			returnColor = ChatColor.GOLD.toString();
		}else if (colorString.equalsIgnoreCase("gray")){
			returnColor = ChatColor.GRAY.toString();
		}else if (colorString.equalsIgnoreCase("green")){
			returnColor = ChatColor.GREEN.toString();
		}else if (colorString.equalsIgnoreCase("red")){
			returnColor = ChatColor.RED.toString();
		}else if (colorString.equalsIgnoreCase("yellow")){
			returnColor = ChatColor.YELLOW.toString();
		}else if (colorString.equalsIgnoreCase("purple")){
			returnColor = ChatColor.LIGHT_PURPLE.toString();
		}else if (colorString.equalsIgnoreCase("white")){
			returnColor = ChatColor.WHITE.toString();
		}else if (colorString.equalsIgnoreCase("dark_aqua")){
			returnColor = ChatColor.DARK_AQUA.toString();
		}else if (colorString.equalsIgnoreCase("dark_blue")){
			returnColor = ChatColor.DARK_BLUE.toString();
		}else if (colorString.equalsIgnoreCase("dark_green")){
			returnColor = ChatColor.DARK_GREEN.toString();
		}else if (colorString.equalsIgnoreCase("dark_gray")){
			returnColor = ChatColor.DARK_GRAY.toString();
		}else if (colorString.equalsIgnoreCase("dark_purple")){
			returnColor = ChatColor.DARK_PURPLE.toString();
		}else if (colorString.equalsIgnoreCase("dark_red")){
			returnColor = ChatColor.DARK_RED.toString();
		}
		return returnColor;
	}
	
	
	// -------------------------------------------------------------------------------------
	// convertIcon
	// -------------------------------------------------------------------------------------
	public String convertIcon(String iconString){
		
		//get color into a string
		String returnIcon = "";
	 
		if (iconString.equalsIgnoreCase("heart")){
			returnIcon = parseUTF8((String)"\u2665");
		}else if (iconString.equalsIgnoreCase("diamond")){
			returnIcon = parseUTF8((String)"\u2666");
		}else if (iconString.equalsIgnoreCase("spade")){
			returnIcon = parseUTF8((String)"\u2660");
		}else if (iconString.equalsIgnoreCase("club")){
			returnIcon = parseUTF8((String)"\u2663");
		}else if (iconString.equalsIgnoreCase("smiley")){
			returnIcon = parseUTF8((String)"\u263A");
		}else if (iconString.equalsIgnoreCase("peace")){
			returnIcon = parseUTF8((String)"\u262E");
		}else if (iconString.equalsIgnoreCase("skull")){
			returnIcon = parseUTF8((String)"\u2620");
		}else if (iconString.equalsIgnoreCase("dharma")){
			returnIcon = parseUTF8((String)"\u2638");
		}else if (iconString.equalsIgnoreCase("star")){
			returnIcon = parseUTF8((String)"\u2605");
		}else if (iconString.equalsIgnoreCase("snowman")){
			returnIcon = parseUTF8((String)"\u2603");
		}
			return returnIcon;
		}
			
	

	// -------------------------------------------------------------------------------------
	// save
	// -------------------------------------------------------------------------------------
	public void save(HashMap<UUID, String> nameColor, String path) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
			oos.writeObject(nameColor);
			oos.flush();
			oos.close();
			//Handle I/O exceptions
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	

	// -------------------------------------------------------------------------------------
	// parseUTF8
	// -------------------------------------------------------------------------------------
	public String parseUTF8(String instr)
	  {
	    char[] in = instr.toCharArray();
	    int len = in.length;
	    int off = 0;
	    char[] convtBuf = new char[len];
	    
	    char[] out = convtBuf;
	    int outLen = 0;
	    int end = off + len;
	    while (off < end)
	    {
	      char aChar = in[(off++)];
	      if (aChar == '\\')
	      {
	        aChar = in[(off++)];
	        if (aChar == 'u')
	        {
	          int value = 0;
	          for (int i = 0; i < 4; i++)
	          {
	            aChar = in[(off++)];
	            switch (aChar)
	            {
	            case '0': 
	            case '1': 
	            case '2': 
	            case '3': 
	            case '4': 
	            case '5': 
	            case '6': 
	            case '7': 
	            case '8': 
	            case '9': 
	              value = (value << 4) + aChar - 48;
	              break;
	            case 'a': 
	            case 'b': 
	            case 'c': 
	            case 'd': 
	            case 'e': 
	            case 'f': 
	              value = (value << 4) + 10 + aChar - 97;
	              break;
	            case 'A': 
	            case 'B': 
	            case 'C': 
	            case 'D': 
	            case 'E': 
	            case 'F': 
	              value = (value << 4) + 10 + aChar - 65;
	              break;
	            case ':': 
	            case ';': 
	            case '<': 
	            case '=': 
	            case '>': 
	            case '?': 
	            case '@': 
	            case 'G': 
	            case 'H': 
	            case 'I': 
	            case 'J': 
	            case 'K': 
	            case 'L': 
	            case 'M': 
	            case 'N': 
	            case 'O': 
	            case 'P': 
	            case 'Q': 
	            case 'R': 
	            case 'S': 
	            case 'T': 
	            case 'U': 
	            case 'V': 
	            case 'W': 
	            case 'X': 
	            case 'Y': 
	            case 'Z': 
	            case '[': 
	            case '\\': 
	            case ']': 
	            case '^': 
	            case '_': 
	            case '`': 
	            default: 
	              throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
	            }
	          }
	          out[(outLen++)] = ((char)value);
	        }
	        else
	        {
	          if (aChar == 't') {
	            aChar = '\t';
	          } else if (aChar == 'r') {
	            aChar = '\r';
	          } else if (aChar == 'n') {
	            aChar = '\n';
	          } else if (aChar == 'f') {
	            aChar = '\f';
	          }
	          out[(outLen++)] = aChar;
	        }
	      }
	      else
	      {
	        out[(outLen++)] = aChar;
	      }
	    }
	    return new String(out, 0, outLen);
	  }
	
	
}
