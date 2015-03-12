package com.kogshole.alpha_bit;

import java.util.List;
import java.util.Locale;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class alpha_bitCommandExecutor implements CommandExecutor{
	
	

    @SuppressWarnings("unused")
	private Alpha_bit plugin; 
	 
	public alpha_bitCommandExecutor(Alpha_bit plugin) {
		this.plugin = plugin;
	}


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String font = "lg";
		Player curplayer = (Player)sender;
		if (cmd.getName().equalsIgnoreCase("ab")){ 


		if (sender instanceof Player) {
	           Player player = (Player) sender;

	           	if (args.length > 4) {
	                  sender.sendMessage("Too many arguments!");
	                  return false;
	               } 
	               if (args.length < 1) {
	                  sender.sendMessage("Not enough arguments!");
	                  return false;
	               }
               		if(args[0].equals("undo")){

	            	    Location loc = ((Player) sender).getLocation();
	            	    AlphaRemoveLetters.restore(loc, null, player);
               			return true;
               		}
    				else if(args[0].equals("help")){
    					sender.sendMessage("/ab [word] | /ab [word] <material> | /ab [undo] | /ab [mats]");
    					
    					return true;
    				}
    				else if(args[0].equals("?")){

    					sender.sendMessage("/ab [word] | /ab [word] <material> | /ab [undo] | /ab [mats]");
    					return true;
    				}
    				else if(args[0].equals("mats")){

    					sender.sendMessage("Materials: STONE, OBSIDIAN, WOOL, COBBLESTONE, DIRT, GRASS, DIAMOND, EMERALD, CLAY, GLASS, SANDSTONE, IRON, GOLD");
    					return true;
    				}
	          

	            	    Location loc = ((Player) sender).getLocation();
	            	    String word;
	            	    word = args[0].toUpperCase(Locale.getDefault());
	            	    String mat = "wood";
	            	    String dirPlace = "north";

		               	if(args.length > 1){
		               		if(args[1].toLowerCase().equals("south")){
		               			dirPlace = "south";
		               		}else if(args[1].toLowerCase().equals("west")){
		               			dirPlace = "west";
		               		}else if(args[1].toLowerCase().equals("east")){
		               			dirPlace = "east";
		               		}else if(args[1].toLowerCase().equals("north")){
		               			dirPlace = "north";
		               		}else if(args[1].toLowerCase().equals("north")){
		               			dirPlace = "north";
		               		}
		               		else if(args[1].toLowerCase().equals("sm") || args[1].toLowerCase().equals("fz")){
		               			font = args[1].toLowerCase();
		            		}
		               		else if(args[1].toLowerCase().equals("pv") || args[1].toLowerCase().equals("fz")){
		               			font = args[1].toLowerCase();
		               			
		            		}else {
		               			mat = args[1].toLowerCase();
		               		}
		               	}
		            	if(args.length > 2){
		            		if(args[1].toLowerCase().equals("sm")){
		            			font = "sm";

		            		
		            		if(args[2].toLowerCase().equals("south")){
		               			dirPlace = "south";

		            		}
		               		
		            		else if(args[2].toLowerCase().equals("west")){
			               			dirPlace = "west";

			            		}
		               			
		               		else if(args[2].toLowerCase().equals("east")){
			               			dirPlace = "east";

			            		}
	               			
		               			else if(args[2].toLowerCase().equals("north")){
		               			dirPlace = "north";

		               			}
	               				else{
	               					mat = args[2].toLowerCase();
	               				}
		            		}
		               		else if(args[1].toLowerCase().equals("pv")){
		               			font = args[1].toLowerCase();
		               			World world = ((Player) sender).getWorld();
		               			List<Player> players = world.getPlayers();
		               			for(int i = 0; i < players.size(); i++){
	
		               				if(players.get(i).getName().equals(args[2])){
		               				curplayer = players.get(i);
		               			}
		               			}

		            		}
		            		if(args[1].toLowerCase().equals("south")){
		               			dirPlace = "south";
		               			mat = args[2].toLowerCase();
		            		}
		               		
		            		else if(args[1].toLowerCase().equals("west")){
			               			dirPlace = "west";
			               			mat = args[2].toLowerCase();
			            		}
		               			
		               		else if(args[1].toLowerCase().equals("east")){
			               			dirPlace = "east";
			               			mat = args[2].toLowerCase();
			            		}
	               			
		               			else if(args[1].toLowerCase().equals("north")){
		               			dirPlace = "north";
		               			mat = args[2].toLowerCase();
		               			}

			               		
			               	}
		            	if(args.length > 3){
		            		if(args[1].toLowerCase().equals("sm")){
		            			font = "sm";

		            		
		            		if(args[2].toLowerCase().equals("south")){
		               			dirPlace = "south";
		               			mat = args[3].toLowerCase();
		            		}
		               		
		            		else if(args[2].toLowerCase().equals("west")){
			               			dirPlace = "west";
			               			mat = args[3].toLowerCase();
			            		}
		               			
		               		else if(args[2].toLowerCase().equals("east")){
			               			dirPlace = "east";
			               			mat = args[3].toLowerCase();
			            		}
	               			
		               			else if(args[2].toLowerCase().equals("north")){
		               			dirPlace = "north";
		               			mat = args[3].toLowerCase();
		               			}
	               				else{
	               					mat = args[2].toLowerCase();
	               				}
		            			
		            		}


		            		if(args[1].toLowerCase().equals("pv")){
		            			font = "pv";
		            			

		            		
		            		if(args[3].toLowerCase().equals("south")){
		               			dirPlace = "south";
		               			font = args[1].toLowerCase();
		               			World world = ((Player) sender).getWorld();
		               			List<Player> players = world.getPlayers();
		               			for(int i = 0; i < players.size(); i++){


		               				if(players.get(i).getName().equals(args[2])){
		               				curplayer = players.get(i);
		               				}
               				}
		               			
		            		}
		               		
		            		else if(args[3].toLowerCase().equals("west")){
			               			dirPlace = "west";
			               			font = args[1].toLowerCase();
			               			World world = ((Player) sender).getWorld();
			               			List<Player> players = world.getPlayers();
			               			for(int i = 0; i < players.size(); i++){

			               				if(players.get(i).getName().equals(args[2])){
			               				curplayer = players.get(i);
			               				}
	               				}
			               		
			            		}
		               			
		               		else if(args[3].toLowerCase().equals("east")){
			               			dirPlace = "east";
			               			font = args[1].toLowerCase();
			               			World world = ((Player) sender).getWorld();
			               			List<Player> players = world.getPlayers();
			               			for(int i = 0; i < players.size(); i++){

			               				if(players.get(i).getName().equals(args[2])){
			               				curplayer = players.get(i);
			               				}
	               				}
			               			
			            		}
	               			
		               			else if(args[3].toLowerCase().equals("north")){
		               			dirPlace = "north";
		               			font = args[1].toLowerCase();
		               			World world = ((Player) sender).getWorld();
		               			List<Player> players = world.getPlayers();
		               			for(int i = 0; i < players.size(); i++){

		               				if(players.get(i).getName().equals(args[2])){
		               				curplayer = players.get(i);
		               				}
               				}
		               			
		               			}
	               				else{

			               			font = args[1].toLowerCase();
			               			World world = ((Player) sender).getWorld();
			               			List<Player> players = world.getPlayers();
			               			for(int i = 0; i < players.size(); i++){

			               				if(players.get(i).getName().equals(args[2])){
			               				curplayer = players.get(i);
			               				}
	               				}
		            			
		            		}	
			               }
		            	}
		               	if(Alpha_bit.perms.has(player, "alpha_bit.ab")) {
		               		
							Alpha_bit.generateWriteLetters(loc, null, player, word, mat, dirPlace, font, curplayer);
		                } else {

			               	sender.sendMessage("I'm sorry " + player.getName() + ", you dont have the alpha_bit.ab permission to use Alpha_Bit.");
		               	}
	            	    
	           		return true;


	           

	        } 
		else {
	           sender.sendMessage("You must be a player!");
	           return false;
	        }


	}else{
		return false;
	}
	}

}

/*
 * Version 1.1
 * By K0Gs
 */