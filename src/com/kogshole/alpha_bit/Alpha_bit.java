package com.kogshole.alpha_bit;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;




public class Alpha_bit extends JavaPlugin implements Listener {
    public void onDisable() {
    	log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
        
    }
    private static final Logger log = Logger.getLogger("Minecraft");
    public static Permission perms = null;


    
    public void onEnable() {
        
    	CoreProtectAPI CoreProtect = getCoreProtect();
    	if (CoreProtect!=null){ 
    	  CoreProtect.testAPI(); 
    	}
    	
    	getServer().getPluginManager().registerEvents(this, this);
    	getCommand("ab").setExecutor(new alpha_bitCommandExecutor(this));
     

        setupPermissions();
 

    
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
    	String thePath = "./plugins/alpha_bit/playerData/";
		String theFile = player.getName().toString() + ".dat";
		File myPath = new File(thePath);
		File myFile = new File(thePath + theFile);
		if(myPath.exists()){
			System.out.println("Directory Exists");
		}else{
			boolean wasDirecotyMade = myPath.mkdirs();
			if(wasDirecotyMade)System.out.println("Direcoty Created");
			else System.out.println("Sorry could not create directory");
		}
		

		myFile.delete();


		try {
			
			if ( myFile.createNewFile() ) {
				System.out.println("Success!");
			} else {
				System.out.println("Failure!");
			}
		} catch ( IOException ioe ) { ioe.printStackTrace(); }
		
		
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        this.getServer().broadcastMessage("Come back soon to Creativeplots, " + player.getName().toString() + "!");

		
		
    }


    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
    
	public static void generateWriteLetters(Location loc, Random par2Random, Player player, String word, String mat, String dir, String font, Player curplayer) {
		WriteLetters.generate(loc, null, player, word, mat, dir, font, curplayer);		
	}
    
    private CoreProtectAPI getCoreProtect() {
    	Plugin plugin = getServer().getPluginManager().getPlugin("CoreProtect");
    	     
    	if (plugin == null || !(plugin instanceof CoreProtect)) {
    	    return null;
    	}

    	CoreProtectAPI CoreProtect = ((CoreProtect)plugin).getAPI();
    	if (CoreProtect.isEnabled()==false){
    	    return null;
    	}

    	if (CoreProtect.APIVersion() < 2){
    	    return null;
    	}
    	         
    	return CoreProtect;
    	}
	
}
/*
 * Version 1.0 
 * By K0Gs
 */
