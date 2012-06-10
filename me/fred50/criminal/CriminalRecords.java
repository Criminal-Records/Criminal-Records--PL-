package me.fred50.criminal;

import java.util.Arrays;
import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CriminalRecords extends JavaPlugin {

	public final static HashMap<String,Integer> counts = new HashMap<>();
	
	
	@Override
	public void onDisable() {
		
		System.out.println("[Criminal Records] CR v"
				+ getDescription().getVersion() + " zostal wylaczony!");
				
		
		saveConfig();
		
	}
	
	@Override
	public void onEnable() {
		
		loadConfiguration();		
						
		System.out.println("[Criminal Records] CR v"
				+ getDescription().getVersion() + " zostal wlaczony!");
		
	}
	
	
	public void loadConfiguration() {
		
		
	    if (!getConfig().contains("Players")) {
		String[] players43 = {};
		
		getConfig().addDefault("Players",
				Arrays.asList(players43));
		
	    }
	    
		int list056;
		list056 = 0;

		int limit;
		limit = getConfig().getStringList("Players").size();
		while (limit > list056) {
			
			String loadplayer = getConfig().getStringList(
					"Players").get(list056);
			
			int playerammount = getConfig().getInt("CR." + loadplayer + ".Ammount");
			
			CriminalRecords.counts.put(loadplayer, playerammount);
			
			list056++;
		}
	    
	    
		getConfig().options().copyDefaults(true);
		saveConfig();

	    if (!getConfig().contains("cfg")) {
		String[] config = {};
		
		getConfig().addDefault("cfg",
				Arrays.asList(config));
		
	    }
	    
		int list055;
		list055 = 0;

		int limit2;
		limit2 = getConfig().getStringList("cfg").size();
		while (limit2 > list055) {
			
			String loadplayer = getConfig().getStringList(
					"cfg").get(list055);
			
			int playerammount = getConfig().getInt("CR." + loadplayer + ".Ammount");
			
			CriminalRecords.counts.put(loadplayer, playerammount);
			
			list055++;
		}
	    
	    
		getConfig().options().copyDefaults(true);
		saveConfig();
		
	}
	
	
    @Override
	public boolean onCommand(CommandSender sender, Command command,
			String commandLabel, String args[]) {
		String commandName = command.getName().toLowerCase();
		final CommandSender player;
		if (sender instanceof CommandSender) {
			player = (CommandSender) sender;
		} else {
			return true;
		}
		
		if (commandName.equals("cr")) {

			if (args.length == 0) {
				if(player.hasPermission("criminal.menu")){
					player.sendMessage(ChatColor.WHITE + "----" + ChatColor.GREEN + "Criminal Records (PL)" + ChatColor.WHITE + "----");
					player.sendMessage(ChatColor.GREEN + "/Cr dodaj <nick>");
					player.sendMessage(ChatColor.GREEN + "/Cr zobacz <nick>");
					player.sendMessage(ChatColor.GREEN + "/Cr usun <nick>");
				}else{
					player.sendMessage(ChatColor.RED + "Nie posiadasz uprawnien!");
				}

			}

			if (args.length == 1) {
				if(player.hasPermission("criminal.menu")){
					player.sendMessage(ChatColor.WHITE + "----" + ChatColor.GREEN + "Criminal Records (PL)" + ChatColor.WHITE + "----");
					player.sendMessage(ChatColor.GREEN + "/Cr dodaj <nick>");
					player.sendMessage(ChatColor.GREEN + "/Cr zobacz <nick>");
					player.sendMessage(ChatColor.GREEN + "/Cr usun <nick>");
				}
			}

			if (args.length == 2) {
				boolean correct1 = false;
				String whatisit1;
				Player targetPlayer = player.getServer().getPlayer(args[1]);
				whatisit1 = args[0];

				if (whatisit1.equalsIgnoreCase("zobacz")) {
					if(player.hasPermission("criminal.menu.view")){
						correct1 = true;
						if(!getConfig().contains("Players." + targetPlayer.getName() + ".Infractions")){
							getConfig().get("Players." + targetPlayer.getName() + ".Infractions");
							player.sendMessage(ChatColor.DARK_PURPLE + "[CR] " + ChatColor.GOLD + "Ten gracz ma " + getConfig().getInt("Players." + targetPlayer.getName() +".Infractions") + " punktow przestepstw!");
						}else{
							getConfig().get("Players." + targetPlayer.getName() + ".Infractions");
							player.sendMessage(ChatColor.DARK_PURPLE + "[CR] " + ChatColor.GOLD + "Ten gracz ma " + getConfig().getInt("Players." + targetPlayer.getName() +".Infractions") + " punktow przestepstw!");
						}
					}else{
						player.sendMessage(ChatColor.RED + "Nie posiadasz uprawnien!");
					}
				}


				if (whatisit1.equalsIgnoreCase("usun")) {
					if(player.hasPermission("criminal.menu.clear")){
						correct1 = true;
						
						if (!getConfig().contains("Players." + targetPlayer.getName() + ".Infractions")) {
						    getConfig().addDefault("Players." + targetPlayer.getName() + ".Infractions", 0);
							getConfig().options().copyDefaults(true);
							player.sendMessage(ChatColor.DARK_PURPLE + "[CR] " + ChatColor.GOLD + "Pomyslnie usunales przestepstwo z konta " + targetPlayer.getName() + "!");
							saveConfig();
						} else {
							getConfig().set("Players." + targetPlayer.getName() + ".Infractions", 0);
							getConfig().options().copyDefaults(true);
							player.sendMessage(ChatColor.DARK_PURPLE + "[CR] " + ChatColor.GOLD + "Pomyslnie usunales przestepstwo z konta " + targetPlayer.getName() + "!");
							saveConfig();
						}
					}else{
						player.sendMessage(ChatColor.RED + "Nie posiadasz uprawnien!");
					}
				}



				if (whatisit1.equalsIgnoreCase("dodaj")) {
					if(player.hasPermission("criminal.menu.add")){
						correct1 = true;
						
						if (!getConfig().contains("Players." + targetPlayer.getName() + ".Infractions")) {
						    getConfig().addDefault("Players." + targetPlayer.getName() + ".Infractions", 1);
						    player.sendMessage(ChatColor.DARK_PURPLE + "[CR] " + ChatColor.GOLD + "Pomyslnie dodano przestepstwo do konta " + targetPlayer.getName() + "!");
						    getConfig().get("Players." + targetPlayer.getName() + ".Infractions");
						    player.sendMessage(ChatColor.DARK_PURPLE + "[CR] " + ChatColor.GOLD + "Ten gracz teraz ma " + getConfig().getInt("Players." + targetPlayer.getName() +".Infractions") + " punktow przestepstw!");
							getConfig().options().copyDefaults(true);
							saveConfig();
						} else {
							int current = getConfig().getInt("Players." + targetPlayer.getName() + ".Infractions");
							getConfig().set("Players." + targetPlayer.getName() + ".Infractions", current + 1);
							getConfig().options().copyDefaults(true);
							saveConfig();
							player.sendMessage(ChatColor.DARK_PURPLE + "[CR] " + ChatColor.GOLD + "Pomyslnie dodano przestepstwo do konta " + targetPlayer.getName() + "!");
							getConfig().get("Players." + targetPlayer.getName() + ".Infractions");
							player.sendMessage(ChatColor.DARK_PURPLE + "[CR] " + ChatColor.GOLD + "Ten gracz teraz ma " + getConfig().getInt("Players." + targetPlayer.getName() +".Infractions") + " punktow przestepstw!");
						}
					}else{
						player.sendMessage(ChatColor.DARK_PURPLE + "[CR] " + ChatColor.RED + "Nie posiadasz uprawnien!");
					}
				}

				if (correct1 == false) {
					if(player.hasPermission("criminal.menu")){
						player.sendMessage(ChatColor.WHITE + "----" + ChatColor.GREEN + "Criminal Records (PL)" + ChatColor.WHITE + "----");
						player.sendMessage(ChatColor.GREEN + "/Cr dodaj <nick>");
						player.sendMessage(ChatColor.GREEN + "/Cr zobacz <nick>");
						player.sendMessage(ChatColor.GREEN + "/Cr usun <nick>");
					}
				}

			}

		}
		return false;

	}  
    

}