package com.kogshole.alpha_bit;
import java.io.File;
import java.io.IOException;
import java.util.*;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.GlobalRegionManager;
import com.worldcretornica.plotme.PMCommand;
import com.worldcretornica.plotme.Plot;
import com.worldcretornica.plotme.PlotManager;
import com.worldcretornica.plotme.PlotMe;
public class WriteLetters extends JavaPlugin implements Listener{






	public static int[] range(int start, int length) {
		int[] result = new int[0];
		if(start <= length){
			int[] result1 = new int[length - start + 1];
			for (int i = start; i <= length; i++) {
				result1[i - start] = i;
				result = result1;
			}}
		else if(length <= start){
			int[] result2 = new int[start - length + 1];
			for (int i = start; i >= length; i--) {
				result2[start - i] = i;
				result = result2;
			}}
		return result;
	}



	public enum Alphabet {
		A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z;

		public static int getNum(String targ) {
			return valueOf(targ).ordinal();
		}

		public static int getNum(char targ) {
			return valueOf(String.valueOf(targ)).ordinal();
		}    
	}


	public boolean canBuild(Player player){
		if(getWorldGuard().canBuild(player,
				player.getLocation().getBlock().getRelative(0, 0, 0))){
			return true;
		}
		return false;

	}


	public static boolean isInteger(String s) {
		try { 
			Integer.parseInt(s); 
		} catch(NumberFormatException e) { 
			return false; 
		}

		return true;
	}







	@SuppressWarnings("deprecation")
	public static  boolean generate(Location loc, Random par2Random, Player player, String word, String mat, String dir, String font, Player curplayer){


		Material mats = Material.WOOD;
		String thePath = "./plugins/alpha_bit/playerData/";
		String theFile = player.getName().toString() + ".dat";
		File myPath = new File(thePath);
		File myFile = new File(thePath + theFile);
		Boolean regionBuild = null;
		Boolean regionBlockBuild = null;
		Boolean plotBuild = null;
		byte finDir = 0;

		if(myPath.exists()){
			System.out.println("Directory Exists");
		}else{
			boolean wasDirecotyMade = myPath.mkdirs();
			if(wasDirecotyMade)System.out.println("Direcoty Created");
			else System.out.println("Sorry could not create directory");
		}


		try {


			if ( myFile.createNewFile() ) {
				System.out.println("Success!");
			} else {
				System.out.println("Failure!");
			}
		} catch ( IOException ioe ) { ioe.printStackTrace(); }


		AlphaWriteFile data = new AlphaWriteFile(thePath + theFile, true);
		try {
			data.writeToFile("nnn");
		} catch (IOException e) {
			e.printStackTrace();
		}



		if(font.equals("sm") || font.equals("fz")){
		if(mat.equals("wood")){
			mats = Material.WOOD;
		}
		if(mat.equals("nether")){
			mats = Material.NETHER_BRICK;
		}
		if(mat.equals("cobble")){
			mats = Material.COBBLESTONE;
		}
		if(mat.equals("brick")){
			mats = Material.BRICK;
		}
		if(mat.equals("stone")){
			mats = Material.SMOOTH_BRICK;
		}
		if(mat.equals("sandstone")){
			mats = Material.SANDSTONE;
		}
		if(mat.equals("quartz")){
			mats = Material.QUARTZ_BLOCK;
		}
		}
		else{
			try{
			mats = Material.matchMaterial(mat);
			}
			catch(NullPointerException e){
				mats = Material.WOOD;
			}
			if(mats == null){
				mats = Material.WOOD;
			}
		}

		String[][] smpieces = new String[][]{
				{"!-@",
					"_~_",
				"_ _"}, //a
				{"_-@",
					"_-@",
				"_~^"},//b
				{"!-@",
					"_  ",
				"%~^"},//c
				{"_-@",
					"_ _",
				"_~^"},//d
				{"_--",
					"_~ ",
				"_~~"},//e
				{"_--",
					"_~ ",
				"_  "},//f
				{"!-@",
					"_ ~",
				"%~_"},//g
				{"_ _",
					"_~_",
				"_ _"},//h
				{"-_-",
					" _ ",
				"~_~"},//i
				{"  _",
					"  _",
				"%~^"},//j
				{"_ !",
					"__^",
				"_ @"},//k
				{"_  ",
					"_  ",
				"_~~"},//l
				{"@ !",
					"___",
				"_ _"},//m
				{"@ _",
					"_@_",
				"_ %"},//n
				{"!-@",
					"_ _",
				"%~^"},//o
				{"_-@",
					"_~^",
				"_  "},//p
				{"!-@",
					"_ _",
				"%!_"},//q
				{"_-@",
					"_~^",
				"_ @"},//r
				{"~--",
					" -%",
				"~~^"},//s
				{"-_-",
					" _ ",
				" _ "},//t
				{"_ _",
					"_ _",
				"%_^"},//u
				{"@ !",
					"_ _",
				"%~^"},//v
				{"_ _",
					"___",
				"%-^"},//w
				{"%~^",
					" _ ",
				"!-@"},//x
				{"%~^",
					" _ ",
				" _ "},//y
				{"--_",
					" !^",
				"!_~"},//z
				{"~_ ",
					" _ ",
				"~_~"},//1
				{"!-@",
					" ~^",
				"!~~"},//2
				{"!-@",
					" -@",
				"%~^"},//3
				{"_ _",
					"_~_",
				"  _"},//4
				{"_--",
					"--@",
				"%~^"},//5
				{"!--",
					"_-@",
				"%~^"},//6
				{"--_",
					"  ^",
				" ! "},//7
				{"!-@",
					"!-@",
				"%~^"},//8
				{"!-@",
					"%~_",
				"~~^"},//9
				{"!-@",
					"_%_",
				"%~^"},	//0

				{"  #  ",
					"  #  ",
					"  #  ",
					"     ",
					"  #  "
				},// Symbol !


				{"  #  ",
					"  #  ",
					"  #  ",
					"     ",
					"  #  "
				},// Symbol ! 

				{" # # ",
					"#####",
					" # # ",
					"#####",
					" # # "
				},// Symbol @ 

				{" ### ",
					"# #  ",
					" ### ",
					"  # #",
					" ### "
				},// Symbol # 

				{"##  #",
					"## # ",
					"  #  ",
					" # ##",
					"#  ##"
				},// Symbol % 

				{"     ",
					"  #  ",
					" # # ",
					"#   #",
					"     "
				},// Symbol ^ 

				{"  ## ",
					" #  #",
					" ### ",
					"#  # #",
					"### #"
				},// Symbol & 

				{"  #  ",
					"#####",
					" ### ",
					"## ##",
					"# # #"
				},// Symbol * 

				{"  ## ",
					" #   ",
					" #   ",
					" #   ",
					"  ## "
				},// Symbol ( 

				{" ##  ",
					"   # ",
					"   # ",
					"   # ",
					" ##  "
				},// Symbol ) 

				{"     ",
					"  #  ",
					"     ",
					"  #  ",
					"     "
				},// Symbol : 

				{"     ",
					"  #  ",
					"     ",
					"  #  ",
					" #   "
				},// Symbol ;  

				{"  #  ",
					" #   ",
					"     ",
					"     ",
					"     "
				},// Symbol !  

				{"     ",
					"     ",
					"     ",
					"     ",
					"#####"
				},// Symbol _
		};
		String[][] lgpieces = new String[][]{

				{"  #  ",
					" # #  ",
					"#   #",
					"#####",
					"#   #"
				},// Letter A

				{"#### ",
					"#   #",
					"#### ",
					"#   #",
					"#### "
				},// Letter b

				{" ### ",
					"#   #",
					"#    ",
					"#   #",
					" ### "
				},// Letter c

				{"#### ",
					"#   #",
					"#   #",
					"#   #",
					"#### "
				},// Letter D

				{"#####",
					"#    ",
					"###  ",
					"#    ",
					"#####"
				},// Letter E

				{"#####",
					"#    ",
					"###  ",
					"#    ",
					"#    "
				},// Letter F

				{"#####",
					"#    ",
					"# ## ",
					"#   #",
					"#####"
				},// Letter G

				{"#   #",
					"#   #",
					"#####",
					"#   #",
					"#   #"
				},// Letter H

				{"#####",
					"  #  ",
					"  #  ",
					"  #  ",
					"#####"
				},// Letter I

				{"    #",
					"    #",
					"    #",
					"#   #",
					" ### "
				},// Letter J

				{" #  #",
					" # # ",
					" ##  ",
					" # # ",
					" #  #"
				},// Letter K

				{"#    ",
					"#    ",
					"#    ",
					"#    ",
					"#####"
				},// Letter L

				{"#   #",
					"## ##",
					"# # #",
					"#   #",
					"#   #"
				},// Letter M

				{"#   #",
					"##  #",
					"# # #",
					"#  ##",
					"#   #"
				},// Letter N

				{" ### ",
					"#   #",
					"#   #",
					"#   #",
					" ### "
				},// Letter O

				{"#### ",
					"#   #",
					"####",
					"#    ",
					"#    "
				},// Letter P

				{" ###",
					"#   #",
					"# # #",
					"#  ##",
					" ### #"
				},// Letter Q

				{"#### ",
					"#   #",
					"#### ",
					"#   #",
					"#   #"
				},// Letter R

				{"#####",
					"#    ",
					"#####",
					"    #",
					"#####"
				},// Letter S

				{"#####",
					"  #  ",
					"  #  ",
					"  #  ",
					"  #  "
				},// Letter T

				{"#   #",
					"#   #",
					"#   #",
					"#   #",
					" ### "
				},// Letter U

				{"#   #",
					"#   #",
					"#   #",
					" # # ",
					"  #  "
				},// Letter V

				{"#   #",
					"#   #",
					"# # #",
					"## ##",
					"#   #"
				},// Letter W

				{"#   #",
					" # # ",
					"  #  ",
					" # # ",
					"#   #"
				},// Letter X


				{"#   #",
					" # # ",
					"  #  ",
					"  #  ",
					"  #  "
				},// Letter Y

				{"#####",
					"   # ",
					"  #  ",
					" #   ",
					"#####"
				},// Letter Z

				{"  #  ",
					" ##  ",
					"  #  ",
					"  #  ",
					"#####"
				},// number 1

				{" ### ",
					"#  # ",
					"  #  ",
					" #   ",
					"#####"
				},// number 2

				{"#### ",
					"    #",
					" ### ",
					"    #",
					"#### "
				},// number 3

				{"  ## ",
					" # # ",
					"#####",
					"   # ",
					"   # "
				},// number 4

				{"#####",
					"##   ",
					"  ###",
					"#   #",
					" ### "
				},// number 5

				{" ### ",
					"#    ",
					"# ## ",
					"#   #",
					" ### "
				},// number 6

				{"#####",
					"   # ",
					"  #  ",
					" #   ",
					"#    "
				},// number 7

				{" ### ",
					"#   #",
					" ### ",
					"#   #",
					" ### "
				},// number 8

				{" ### ",
					"#   #",
					" ### ",
					"  #  ",
					" #   "
				},// number 9

				{" ### ",
				 "#  ##",
				 "# # #",
				 "##  #",
				 " ### "
				},// number 0 

				{"  #  ",
					"  #  ",
					"  #  ",
					"     ",
					"  #  "
				},// Symbol !


				{" ### ",
				 "##  #",
				 "# ## ",
				 "#   #",
				 " ### "
				},// Symbol ! 

				{" # # ",
					"#####",
					" # # ",
					"#####",
					" # # "
				},// Symbol @ 

				{" ### ",
					"# #  ",
					" ### ",
					"  # #",
					" ### "
				},// Symbol # 

				{"##  #",
					"## # ",
					"  #  ",
					" # ##",
					"#  ##"
				},// Symbol % 

				{"     ",
					"  #  ",
					" # # ",
					"#   #",
					"     "
				},// Symbol ^ 

				{"  ## ",
					" #  #",
					" ### ",
					"#  # #",
					"### #"
				},// Symbol & 

				{"  #  ",
					"#####",
					" ### ",
					"## ##",
					"#   #"
				},// Symbol * 

				{"  ## ",
					" #   ",
					" #   ",
					" #   ",
					"  ## "
				},// Symbol ( 

				{" ##  ",
					"   # ",
					"   # ",
					"   # ",
					" ##  "
				},// Symbol ) 

				{"     ",
					"  #  ",
					"     ",
					"  #  ",
					"     "
				},// Symbol : 

				{"     ",
					"  #  ",
					"     ",
					"  #  ",
					" #   "
				},// Symbol ;  

				{"  #  ",
					" #   ",
					"     ",
					"     ",
					"     "
				},// Symbol !  

				{"     ",
					"     ",
					"     ",
					"     ",
					"#####"
				},// Symbol _

		};


		String[][] pieces = new String[][]{};

		String curlet = "";
		int curdigit = 0;
		int cnt = 6;
		int cnt4 = 4;
		int wordLength = word.length();
		int start = cnt * wordLength / 2;
		int lgstart = cnt * wordLength / 2;
		int smstart = cnt4 * wordLength / 2; 
		if (font.equals("lg")){
			pieces = lgpieces;
			start = lgstart;
		}
		if (font.equals("sm") || font.equals("pv")){
			pieces = smpieces;
			start = smstart;

		}
		if (font.equals("fz")){
			pieces = lgpieces;
			start = lgstart;

		}
		//player.sendMessage(Integer.toString(wordLength));
		//player.sendMessage(player.getName());
		//loop for letters
		regionBuild = true;
		regionBlockBuild = true;
		plotBuild = true;
		for (int letters : range(0, word.length())){ 
			if(letters < word.length()){
				 if(word.substring(letters, letters + 1).equals("1")){
					curlet = word.substring(letters, letters + 1);
					curdigit = 26;
					//player.sendMessage(curlet + " " + curdigit);

				}
					else if(word.substring(letters, letters + 1).equals("2")){
						curlet = word.substring(letters, letters + 1);
						curdigit = 27;
						//player.sendMessage(curlet + " " + curdigit);

					}
					else if(word.substring(letters, letters + 1).equals("3")){
						curlet = word.substring(letters, letters + 1);
						curdigit = 28;
						//player.sendMessage(curlet + " " + curdigit);

					}
					else if(word.substring(letters, letters + 1).equals("4")){
						curlet = word.substring(letters, letters + 1);
						curdigit = 29;
						//player.sendMessage(curlet + " " + curdigit);

					}
					else if(word.substring(letters, letters + 1).equals("5")){
						curlet = word.substring(letters, letters + 1);
						curdigit = 30;
						//player.sendMessage(curlet + " " + curdigit);

					}
					else if(word.substring(letters, letters + 1).equals("6")){
						curlet = word.substring(letters, letters + 1);
						curdigit = 31;
						//player.sendMessage(curlet + " " + curdigit);

					}
					else if(word.substring(letters, letters + 1).equals("7")){
						curlet = word.substring(letters, letters + 1);
						curdigit = 32;
						//player.sendMessage(curlet + " " + curdigit);
		
					}
					else if(word.substring(letters, letters + 1).equals("8")){
						curlet = word.substring(letters, letters + 1);
						curdigit = 33;
						//player.sendMessage(curlet + " " + curdigit);
				
					}
					else if(word.substring(letters, letters + 1).equals("9")){
						curlet = word.substring(letters, letters + 1);
						curdigit = 34;
						//player.sendMessage(curlet + " " + curdigit);
			
					}
				else if(word.substring(letters, letters + 1).equals("0")){
					curlet = word.substring(letters, letters + 1);
					curdigit = 35;
					//player.sendMessage(curlet + " " + curdigit);
		
				}
				else if(word.substring(letters, letters + 1).equals("!")){
					curlet = word.substring(letters, letters + 1);

					////player.sendMessage(curlet);
					curdigit = 36;
				}
				else if(word.substring(letters, letters + 1).equals("@")){
					curlet = word.substring(letters, letters + 1);

					//player.sendMessage(curlet);
					curdigit = 37;
				}
				else if(word.substring(letters, letters + 1).equals("#")){
					curlet = word.substring(letters, letters + 1);

					//player.sendMessage(curlet);
					curdigit = 38;
				}
				else if(word.substring(letters, letters + 1).equals("$")){
					curlet = word.substring(letters, letters + 1);

					//player.sendMessage(curlet);
					curdigit = 39;
				}
				else if(word.substring(letters, letters + 1).equals("%")){
					curlet = word.substring(letters, letters + 1);

					//player.sendMessage(curlet);
					curdigit = 40;
				}
				else if(word.substring(letters, letters + 1).equals("^")){
					curlet = word.substring(letters, letters + 1);

					//player.sendMessage(curlet);
					curdigit = 41;
				}
				else if(word.substring(letters, letters + 1).equals("&")){
					curlet = word.substring(letters, letters + 1);

					//player.sendMessage(curlet);
					curdigit = 42;
				}
				else if(word.substring(letters, letters + 1).equals("*")){
					curlet = word.substring(letters, letters + 1);

					//player.sendMessage(curlet);
					curdigit = 43;
				}
				else if(word.substring(letters, letters + 1).equals("(")){
					curlet = word.substring(letters, letters + 1);

					//player.sendMessage(curlet);
					curdigit = 44;
				}
				else if(word.substring(letters, letters + 1).equals(")")){
					curlet = word.substring(letters, letters + 1);

					//player.sendMessage(curlet);
					curdigit = 45;
				}
				else if(word.substring(letters, letters + 1).equals(":")){
					curlet = word.substring(letters, letters + 1);

					//player.sendMessage(curlet);
					curdigit = 46;
				}
				else if(word.substring(letters, letters + 1).equals(";")){
					curlet = word.substring(letters, letters + 1);

					//player.sendMessage(curlet);
					curdigit = 47;
				}
				else if(word.substring(letters, letters + 1).equals("'")){
					curlet = word.substring(letters, letters + 1);

					//player.sendMessage(curlet);
					curdigit = 48;
				}
				else if(word.substring(letters, letters + 1).equals("_")){
					curlet = word.substring(letters, letters + 1);

					//player.sendMessage(curlet);
					curdigit = 49;
				}
				else {
					curlet = word.substring(letters, letters + 1);

					//player.sendMessage(curlet);
					curdigit = Alphabet.getNum(curlet);
				}

				//player.sendMessage(Integer.toString(curdigit));

				//loop for array pieces
				if(dir.equals("north")){
					for (int jr : range(0, pieces[curdigit].length - 1)){
						//player.sendMessage(Integer.toString(jr));
						//loop for array part
						for(int let : range(0, pieces[curdigit][jr].length())){
							//player.sendMessage(Integer.toString(let));
							//player.sendMessage(pieces[curdigit][jr]);

							if(let < pieces[curdigit][jr].length()){
								String letnow = pieces[curdigit][jr].substring(let, let + 1);

								//player.sendMessage(letnow);
								int x1 = loc.getBlockX() ;
								int y1 = loc.getBlockY() ;
								int z1 = loc.getBlockZ() - 1;
								int x1cp = curplayer.getLocation().getBlockX() ;
								int y1cp = curplayer.getLocation().getBlockY() ;
								int z1cp = curplayer.getLocation().getBlockZ() - 1;
								finDir = 0;
								if(mat.equals("wood")){
									mats = Material.WOOD;
								}
								if(mat.equals("nether")){
									mats = Material.NETHER_BRICK;
								}
								if(mat.equals("cobble")){
									mats = Material.COBBLESTONE;
								}
								if(mat.equals("brick")){
									mats = Material.BRICK;
								}
								if(mat.equals("stone")){
									mats = Material.SMOOTH_BRICK;
								}
								if(mat.equals("sandstone")){
									mats = Material.SANDSTONE;
								}
								if(mat.equals("quartz")){
									mats = Material.QUARTZ_BLOCK;
								}
								if(!(letnow.equals(" "))){
									if(letnow.equals("#")){
										//player.sendMessage("#");
										if(font.equals("fz")){

											if(mat.equals("wood")){
												mats = Material.WOOD_STAIRS;
											}
											if(mat.equals("brick")){

												mats = Material.BRICK_STAIRS;
											}
											if(mat.equals("cobble")){

												mats = Material.COBBLESTONE_STAIRS;
											}
											if(mat.equals("stone")){

												mats = Material.SMOOTH_STAIRS;
											}
											if(mat.equals("nether")){

												mats = Material.NETHER_BRICK_STAIRS;
											}
											if(mat.equals("sandstone")){

												mats = Material.SANDSTONE_STAIRS;
											}
											if(mat.equals("quartz")){

												mats = Material.QUARTZ_STAIRS;
											}

											finDir = 0;
										}
										else{
										if(mat.equals("wood")){
											mats = Material.WOOD;
										}
										if(mat.equals("brick")){

											mats = Material.BRICK;
										}
										if(mat.equals("cobble")){

											mats = Material.COBBLESTONE;
										}
										if(mat.equals("stone")){

											mats = Material.SMOOTH_BRICK;
										}
										if(mat.equals("nether")){

											mats = Material.NETHER_BRICK;
										}
										if(mat.equals("sandstone")){

											mats = Material.SANDSTONE;
										}
										if(mat.equals("quartz")){

											mats = Material.QUARTZ_BLOCK;
										}
										}
									}
									else if(letnow.equals("!")){
										//player.sendMessage("!");

										if(mat.equals("wood")){
											mats = Material.WOOD_STAIRS;
										}
										else if(mat.equals("brick")){

											mats = Material.BRICK_STAIRS;
										}
										else if(mat.equals("cobble")){

											mats = Material.COBBLESTONE_STAIRS;
										}
										else if(mat.equals("stone")){

											mats = Material.SMOOTH_STAIRS;
										}
										else if(mat.equals("nether")){

											mats = Material.NETHER_BRICK_STAIRS;
										}
										else if(mat.equals("sandstone")){

											mats = Material.SANDSTONE_STAIRS;
										}
										else if(mat.equals("quartz")){

											mats = Material.QUARTZ_STAIRS;
										}
										else{
											mats = Material.WOOD_STAIRS;
										}

										finDir = 0;
									}
									else if(letnow.equals("@")){
										//player.sendMessage("@");

										if(mat.equals("wood")){
											mats = Material.WOOD_STAIRS;
										}
										else if(mat.equals("brick")){

											mats = Material.BRICK_STAIRS;
										}
										else if(mat.equals("cobble")){

											mats = Material.COBBLESTONE_STAIRS;
										}
										else if(mat.equals("stone")){

											mats = Material.SMOOTH_STAIRS;
										}
										else if(mat.equals("nether")){

											mats = Material.NETHER_BRICK_STAIRS;
										}
										else if(mat.equals("sandstone")){

											mats = Material.SANDSTONE_STAIRS;
										}
										else if(mat.equals("quartz")){

											mats = Material.QUARTZ_STAIRS;
										}
										else{
											mats = Material.WOOD_STAIRS;
										}
										finDir = 1;
									}
									else if(letnow.equals("%")){
										//player.sendMessage("%");

										if(mat.equals("wood")){
											mats = Material.WOOD_STAIRS;
										}
										else if(mat.equals("brick")){

											mats = Material.BRICK_STAIRS;
										}
										else if(mat.equals("cobble")){

											mats = Material.COBBLESTONE_STAIRS;
										}
										else if(mat.equals("stone")){

											mats = Material.SMOOTH_STAIRS;
										}
										else if(mat.equals("nether")){

											mats = Material.NETHER_BRICK_STAIRS;
										}
										else if(mat.equals("sandstone")){

											mats = Material.SANDSTONE_STAIRS;
										}
										else if(mat.equals("quartz")){

											mats = Material.QUARTZ_STAIRS;
										}
										else{
											mats = Material.WOOD_STAIRS;
										}
										finDir = 4;

									}
									else if(letnow.equals("^")){
										//player.sendMessage("^");

										if(mat.equals("wood")){
											mats = Material.WOOD_STAIRS;
										}
										else if(mat.equals("brick")){

											mats = Material.BRICK_STAIRS;
										}
										else if(mat.equals("cobble")){

											mats = Material.COBBLESTONE_STAIRS;
										}
										else if(mat.equals("stone")){

											mats = Material.SMOOTH_STAIRS;
										}
										else if(mat.equals("nether")){

											mats = Material.NETHER_BRICK_STAIRS;
										}
										else if(mat.equals("sandstone")){

											mats = Material.SANDSTONE_STAIRS;
										}
										else if(mat.equals("quartz")){

											mats = Material.QUARTZ_STAIRS;
										}
										else{
											mats = Material.WOOD_STAIRS;
										}
										finDir = 5;

									}
									else if(letnow.equals("_")){
										//player.sendMessage("_");

										if(mat.equals("wood")){
											mats = Material.WOOD;
										}
										else if(mat.equals("nether")){
											mats = Material.NETHER_BRICK;
										}
										else if(mat.equals("cobble")){
											mats = Material.COBBLESTONE;
										}
										else if(mat.equals("brick")){
											mats = Material.BRICK;
										}
										else if(mat.equals("stone")){
											mats = Material.SMOOTH_BRICK;
										}
										else if(mat.equals("sandstone")){
											mats = Material.SANDSTONE;
										}
										else if(mat.equals("quartz")){
											mats = Material.QUARTZ_BLOCK;
										}
										else{
											mats = Material.WOOD;
										}
										finDir = 0;
									}
									else if(letnow.equals("-")){
										//player.sendMessage("-");
										//finDir = 10;
										if(mat.equals("wood")){
											finDir = 10;
										}
										else if(mat.equals("brick")){
											finDir = 12;
										}
										else if(mat.equals("cobble")){
											finDir = 11;
										}
										else if(mat.equals("stone")){
											finDir = 13;
										}
										else if(mat.equals("nether")){
											finDir = 14;
										}
										else if(mat.equals("sandstone")){
											finDir = 9;
										}
										else if(mat.equals("quartz")){
											finDir = 15;
										}
										else{
											finDir = 10;
											mats = Material.STEP;
										}

										mats = Material.STEP;
									}
									else if(letnow.equals("~")){
										//player.sendMessage("~");
										if(mat.equals("wood")){
											finDir = 2;
										}
										else if(mat.equals("brick")){
											finDir = 4;
										}
										else if(mat.equals("cobble")){
											finDir = 3;
										}
										else if(mat.equals("stone")){
											finDir = 5;
										}
										else if(mat.equals("nether")){
											finDir = 6;
										}
										else if(mat.equals("sandstone")){
											finDir = 1;
										}
										else if(mat.equals("quartz")){
											finDir = 7;
										}
										else{
											finDir = 2;
											mats = Material.STEP;
										}

										mats = Material.STEP;
									}
									else {
									}

									World world = loc.getWorld();

									Block currentBlock;
									Block curplayerBlock;
									// Get the block that we are currently looping over.
									if(font.equals("sm") || font.equals("pv")){

										currentBlock = world.getBlockAt(x1 - smstart + let, y1 + 3 - jr, z1);
										curplayerBlock = world.getBlockAt(x1cp - smstart + let, y1cp + 3, z1cp);
									}
									else{
									currentBlock = world.getBlockAt(x1 - start + let, y1 + 4 - jr, z1);
									curplayerBlock = null;
									}




									if(canBuildHere(player.getLocation(), player)){
										if(canBuildHere(currentBlock.getLocation(), player)){
											if(isPlotAllowed(player, currentBlock) == true){
												if(font.equals("sm")){

													try {
														data.writeToFile(Integer.toString(x1 - smstart + let) 
																+ "," + Integer.toString(y1 + 3 - jr) 
																+ "," + Integer.toString(z1) 
																+ "," + currentBlock.getType().toString());
													} catch (IOException e) {
														e.printStackTrace();
													}
													currentBlock.setType(mats);
													currentBlock.setData(finDir);
													//player.sendMessage("Mats: " + mats.name().toString() + " data: " + finDir);
													logBlock(player.getName().toString(), currentBlock.getLocation(), currentBlock.getTypeId(), currentBlock.getData() );
													
												}
												else if(font.equals("pv")){

													curplayer.sendBlockChange(curplayerBlock.getLocation(), mats, finDir);
													player.sendBlockChange(currentBlock.getLocation(), mats, finDir);
												}
												else{
												try {
													data.writeToFile(Integer.toString(x1 - start + let) 
															+ "," + Integer.toString(y1 + 4 - jr) 
															+ "," + Integer.toString(z1) 
															+ "," + currentBlock.getType().toString());
												} catch (IOException e) {
													e.printStackTrace();
												}
												currentBlock.setType(mats);
												currentBlock.setData(finDir);
												//player.sendMessage("Mats: " + mats.name().toString() + " data: " + finDir);
												logBlock(player.getName().toString(), currentBlock.getLocation(), currentBlock.getTypeId(), currentBlock.getData() );
												}
											}else{
												//player.sendMessage("You are not allowed to place blocks in this plot!");
												plotBuild = false;
											}
										}else{
											//player.sendMessage("You are not allowed to place blocks in this region!");
											regionBlockBuild = false;
										}
									}else{
										//player.sendMessage("You are not allowed to build in this region!");
										regionBuild = false;

									}





								}
					
							}
					


						}
					}}
				else if(dir.equals("south")){
					for (int jr : range(0,pieces[curdigit].length - 1)){
						//player.sendMessage(Integer.valueOf(((int[])range(0,pieces[curdigit].length - 1))[jr]).toString());

						//player.sendMessage(Integer.toString(jr));
						//loop for array part
						for(int let : range(0,pieces[curdigit][jr].length())){
							//player.sendMessage(Integer.valueOf(((int[])range(0,pieces[curdigit][jr].length()))[let]).toString());
							//player.sendMessage(Integer.toString(let));
							//player.sendMessage(pieces[curdigit][jr]);
							if(let < pieces[curdigit][jr].length()){
								//player.sendMessage("let is less");
								String letnow = pieces[curdigit][jr].substring(let, let + 1);
								//player.sendMessage(letnow);
								//player.sendMessage(letnow);
								int x1 = loc.getBlockX() ;//- (pieces[curdigit][jr].length() * cnt) + cnt2; 
								int y1 = loc.getBlockY() ;
								int z1 = loc.getBlockZ() + 1;
								finDir = 0;
								if(mat.equals("wood")){
									mats = Material.WOOD;
								}
								if(mat.equals("nether")){
									mats = Material.NETHER_BRICK;
								}
								if(mat.equals("cobble")){
									mats = Material.COBBLESTONE;
								}
								if(mat.equals("brick")){
									mats = Material.BRICK;
								}
								if(mat.equals("stone")){
									mats = Material.SMOOTH_BRICK;
								}
								if(mat.equals("sandstone")){
									mats = Material.SANDSTONE;
								}
								if(mat.equals("quartz")){
									mats = Material.QUARTZ_BLOCK;
								}
								if(!(letnow.equals(" "))){
									if(letnow.equals("#")){
										//player.sendMessage("#");
										if(font.equals("fz")){

											if(mat.equals("wood")){
												mats = Material.WOOD_STAIRS;
											}
											if(mat.equals("brick")){

												mats = Material.BRICK_STAIRS;
											}
											if(mat.equals("cobble")){

												mats = Material.COBBLESTONE_STAIRS;
											}
											if(mat.equals("stone")){

												mats = Material.SMOOTH_STAIRS;
											}
											if(mat.equals("nether")){

												mats = Material.NETHER_BRICK_STAIRS;
											}
											if(mat.equals("sandstone")){

												mats = Material.SANDSTONE_STAIRS;
											}
											if(mat.equals("quartz")){

												mats = Material.QUARTZ_STAIRS;
											}

											finDir = 1;
										}
										else{
										if(mat.equals("wood")){
											mats = Material.WOOD;
										}
										if(mat.equals("brick")){

											mats = Material.BRICK;
										}
										if(mat.equals("cobble")){

											mats = Material.COBBLESTONE;
										}
										if(mat.equals("stone")){

											mats = Material.SMOOTH_BRICK;
										}
										if(mat.equals("nether")){

											mats = Material.NETHER_BRICK;
										}
										if(mat.equals("sandstone")){

											mats = Material.SANDSTONE;
										}
										if(mat.equals("quartz")){

											mats = Material.QUARTZ_BLOCK;
										}
										}
									}
									else if(letnow.equals("!")){
										//player.sendMessage("!");

										if(mat.equals("wood")){
											mats = Material.WOOD_STAIRS;
										}
										else if(mat.equals("brick")){

											mats = Material.BRICK_STAIRS;
										}
										else if(mat.equals("cobble")){

											mats = Material.COBBLESTONE_STAIRS;
										}
										else if(mat.equals("stone")){

											mats = Material.SMOOTH_STAIRS;
										}
										else if(mat.equals("nether")){

											mats = Material.NETHER_BRICK_STAIRS;
										}
										else if(mat.equals("sandstone")){

											mats = Material.SANDSTONE_STAIRS;
										}
										else if(mat.equals("quartz")){

											mats = Material.QUARTZ_STAIRS;
										}
										else{
											mats = Material.WOOD;
										}

										finDir = 1;
									}
									else if(letnow.equals("@")){
										//player.sendMessage("@");

										if(mat.equals("wood")){
											mats = Material.WOOD_STAIRS;
										}
										else if(mat.equals("brick")){

											mats = Material.BRICK_STAIRS;
										}
										else if(mat.equals("cobble")){

											mats = Material.COBBLESTONE_STAIRS;
										}
										else if(mat.equals("stone")){

											mats = Material.SMOOTH_STAIRS;
										}
										else if(mat.equals("nether")){

											mats = Material.NETHER_BRICK_STAIRS;
										}
										else if(mat.equals("sandstone")){

											mats = Material.SANDSTONE_STAIRS;
										}
										else if(mat.equals("quartz")){

											mats = Material.QUARTZ_STAIRS;
										}
										else{
											mats = Material.WOOD_STAIRS;
										}
										finDir = 0;
									}
									else if(letnow.equals("%")){
										//player.sendMessage("%");

										if(mat.equals("wood")){
											mats = Material.WOOD_STAIRS;
										}
										else if(mat.equals("brick")){

											mats = Material.BRICK_STAIRS;
										}
										else if(mat.equals("cobble")){

											mats = Material.COBBLESTONE_STAIRS;
										}
										else if(mat.equals("stone")){

											mats = Material.SMOOTH_STAIRS;
										}
										else if(mat.equals("nether")){

											mats = Material.NETHER_BRICK_STAIRS;
										}
										else if(mat.equals("sandstone")){

											mats = Material.SANDSTONE_STAIRS;
										}
										else if(mat.equals("quartz")){

											mats = Material.QUARTZ_STAIRS;
										}
										else{
											mats = Material.WOOD_STAIRS;
										}
										finDir = 5;

									}
									else if(letnow.equals("^")){
										//player.sendMessage("^");

										if(mat.equals("wood")){
											mats = Material.WOOD_STAIRS;
										}
										else if(mat.equals("brick")){

											mats = Material.BRICK_STAIRS;
										}
										else if(mat.equals("cobble")){

											mats = Material.COBBLESTONE_STAIRS;
										}
										else if(mat.equals("stone")){

											mats = Material.SMOOTH_STAIRS;
										}
										else if(mat.equals("nether")){

											mats = Material.NETHER_BRICK_STAIRS;
										}
										else if(mat.equals("sandstone")){

											mats = Material.SANDSTONE_STAIRS;
										}
										else if(mat.equals("quartz")){

											mats = Material.QUARTZ_STAIRS;
										}
										else{
											mats = Material.WOOD_STAIRS;
										}
										finDir = 4;

									}
									else if(letnow.equals("_")){
										//player.sendMessage("_");

										if(mat.equals("wood")){
											mats = Material.WOOD;
										}
										else if(mat.equals("nether")){
											mats = Material.NETHER_BRICK;
										}
										else if(mat.equals("cobble")){
											mats = Material.COBBLESTONE;
										}
										else if(mat.equals("brick")){
											mats = Material.BRICK;
										}
										else if(mat.equals("stone")){
											mats = Material.SMOOTH_BRICK;
										}
										else if(mat.equals("sandstone")){
											mats = Material.SANDSTONE;
										}
										else if(mat.equals("quartz")){
											mats = Material.QUARTZ_BLOCK;
										}
										else{
											mats = Material.WOOD;
										}
										finDir = 0;
									}
									else if(letnow.equals("-")){
										//player.sendMessage("-");
										if(mat.equals("wood")){
											finDir = 10;
										}
										else if(mat.equals("brick")){
											finDir = 12;
										}
										else if(mat.equals("cobble")){
											finDir = 11;
										}
										else if(mat.equals("stone")){
											finDir = 13;
										}
										else if(mat.equals("nether")){
											finDir = 14;
										}
										else if(mat.equals("sandstone")){
											finDir = 9;
										}
										else if(mat.equals("quartz")){
											finDir = 15;
										}
										else{
											finDir = 10;
											mats = Material.STEP;
										}

										mats = Material.STEP;
									}
									else if(letnow.equals("~")){
										//player.sendMessage("~");
										if(mat.equals("wood")){
											finDir = 2;
										}
										else if(mat.equals("brick")){
											finDir = 4;
										}
										else if(mat.equals("cobble")){
											finDir = 3;
										}
										else if(mat.equals("stone")){
											finDir = 5;
										}
										else if(mat.equals("nether")){
											finDir = 6;
										}
										else if(mat.equals("sandstone")){
											finDir = 1;
										}
										else if(mat.equals("quartz")){
											finDir = 7;
										}
										else{
											finDir = 2;
											mats = Material.STEP;
										}

										mats = Material.STEP;
									}
									else {
									}

									World world = loc.getWorld();



									Block currentBlock;
									// Get the block that we are currently looping over.
									if(font.equals("sm") || font.equals("pv")){

										currentBlock = world.getBlockAt(x1 + smstart - let, y1 + 3 - jr, z1);
	
									}
									else{
									currentBlock = world.getBlockAt(x1 + start - let, y1 + 4 - jr, z1);
									}




									if(canBuildHere(player.getLocation(), player)){
										if(canBuildHere(currentBlock.getLocation(), player)){
											if(isPlotAllowed(player, currentBlock) == true){
												if(font.equals("sm")){

													try {
														data.writeToFile(Integer.toString(x1 + smstart - let) 
																+ "," + Integer.toString(y1 + 3 - jr) 
																+ "," + Integer.toString(z1) 
																+ "," + currentBlock.getType().toString());
													} catch (IOException e) {
														e.printStackTrace();
													}
													currentBlock.setType(mats);
													currentBlock.setData(finDir);
													//player.sendMessage("Mats: " + mats.name().toString() + " data: " + finDir);
													logBlock(player.getName().toString(), currentBlock.getLocation(), currentBlock.getTypeId(), currentBlock.getData() );
													
												}
												else if(font.equals("pv")){

													curplayer.sendBlockChange(currentBlock.getLocation(), mats, finDir);
													player.sendBlockChange(currentBlock.getLocation(), mats, finDir);
												}
												
												else{
												try {
													data.writeToFile(Integer.toString(x1 + start - let) 
															+ "," + Integer.toString(y1 + 4 - jr) 
															+ "," + Integer.toString(z1) 
															+ "," + currentBlock.getType().toString());
												} catch (IOException e) {
													e.printStackTrace();
												}
												currentBlock.setType(mats);
												currentBlock.setData(finDir);
												//player.sendMessage("Mats: " + mats.name().toString() + " data: " + finDir);
												logBlock(player.getName().toString(), currentBlock.getLocation(), currentBlock.getTypeId(), currentBlock.getData() );
												}
											}else{
												//player.sendMessage("You are not allowed to place blocks in this plot!");
												plotBuild = false;
											}
										}else{
											//player.sendMessage("You are not allowed to place blocks in this region!");
											regionBlockBuild = false;
										}
									}else{
										//player.sendMessage("You are not allowed to build in this region!");
										regionBuild = false;
										//return false;
									}




								}

							}



						}
					}
				}
				else if(dir.equals("west")){
					for (int jr : range(0,pieces[curdigit].length - 1)){
						//player.sendMessage(Integer.valueOf(((int[])range(0,pieces[curdigit].length - 1))[jr]).toString());

						//player.sendMessage(Integer.toString(jr));
						//loop for array part
						for(int let : range(0,pieces[curdigit][jr].length())){
							//player.sendMessage(Integer.valueOf(((int[])range(0,pieces[curdigit][jr].length()))[let]).toString());
							//player.sendMessage(Integer.toString(let));
							//player.sendMessage(pieces[curdigit][jr]);
							if(let < pieces[curdigit][jr].length()){
								//player.sendMessage("let is less");
								String letnow = pieces[curdigit][jr].substring(let, let + 1);
								//player.sendMessage(letnow);
								//player.sendMessage(letnow);
								int x1 = loc.getBlockX() -1;//- (pieces[curdigit][jr].length() * cnt) + cnt2; 
								int y1 = loc.getBlockY() ;
								int z1 = loc.getBlockZ();

								finDir = 0;
								if(mat.equals("wood")){
									mats = Material.WOOD;
								}
								if(mat.equals("nether")){
									mats = Material.NETHER_BRICK;
								}
								if(mat.equals("cobble")){
									mats = Material.COBBLESTONE;
								}
								if(mat.equals("brick")){
									mats = Material.BRICK;
								}
								if(mat.equals("stone")){
									mats = Material.SMOOTH_BRICK;
								}
								if(mat.equals("sandstone")){
									mats = Material.SANDSTONE;
								}
								if(mat.equals("quartz")){
									mats = Material.QUARTZ_BLOCK;
								}
								if(!(letnow.equals(" "))){
									if(letnow.equals("#")){
										//player.sendMessage("#");
										if(font.equals("fz")){

											if(mat.equals("wood")){
												mats = Material.WOOD_STAIRS;
											}
											if(mat.equals("brick")){

												mats = Material.BRICK_STAIRS;
											}
											if(mat.equals("cobble")){

												mats = Material.COBBLESTONE_STAIRS;
											}
											if(mat.equals("stone")){

												mats = Material.SMOOTH_STAIRS;
											}
											if(mat.equals("nether")){

												mats = Material.NETHER_BRICK_STAIRS;
											}
											if(mat.equals("sandstone")){

												mats = Material.SANDSTONE_STAIRS;
											}
											if(mat.equals("quartz")){

												mats = Material.QUARTZ_STAIRS;
											}

											finDir = 3;
										}
										else{
										if(mat.equals("wood")){
											mats = Material.WOOD;
										}
										if(mat.equals("brick")){

											mats = Material.BRICK;
										}
										if(mat.equals("cobble")){

											mats = Material.COBBLESTONE;
										}
										if(mat.equals("stone")){

											mats = Material.SMOOTH_BRICK;
										}
										if(mat.equals("nether")){

											mats = Material.NETHER_BRICK;
										}
										if(mat.equals("sandstone")){

											mats = Material.SANDSTONE;
										}
										if(mat.equals("quartz")){

											mats = Material.QUARTZ_BLOCK;
										}
										}
									}
									else if(letnow.equals("!")){
										//player.sendMessage("!");

										if(mat.equals("wood")){
											mats = Material.WOOD_STAIRS;
										}
										else if(mat.equals("brick")){

											mats = Material.BRICK_STAIRS;
										}
										else if(mat.equals("cobble")){

											mats = Material.COBBLESTONE_STAIRS;
										}
										else if(mat.equals("stone")){

											mats = Material.SMOOTH_STAIRS;
										}
										else if(mat.equals("nether")){

											mats = Material.NETHER_BRICK_STAIRS;
										}
										else if(mat.equals("sandstone")){

											mats = Material.SANDSTONE_STAIRS;
										}
										else if(mat.equals("quartz")){

											mats = Material.QUARTZ_STAIRS;
										}
										else{
											mats = Material.WOOD_STAIRS;
										}

										finDir = 3;
									}
									else if(letnow.equals("@")){
										//player.sendMessage("@");

										if(mat.equals("wood")){
											mats = Material.WOOD_STAIRS;
										}
										else if(mat.equals("brick")){

											mats = Material.BRICK_STAIRS;
										}
										else if(mat.equals("cobble")){

											mats = Material.COBBLESTONE_STAIRS;
										}
										else if(mat.equals("stone")){

											mats = Material.SMOOTH_STAIRS;
										}
										else if(mat.equals("nether")){

											mats = Material.NETHER_BRICK_STAIRS;
										}
										else if(mat.equals("sandstone")){

											mats = Material.SANDSTONE_STAIRS;
										}
										else if(mat.equals("quartz")){

											mats = Material.QUARTZ_STAIRS;
										}
										else{
											mats = Material.WOOD_STAIRS;
										}
										finDir = 2;
									}
									else if(letnow.equals("%")){
										//player.sendMessage("%");

										if(mat.equals("wood")){
											mats = Material.WOOD_STAIRS;
										}
										else if(mat.equals("brick")){

											mats = Material.BRICK_STAIRS;
										}
										else if(mat.equals("cobble")){

											mats = Material.COBBLESTONE_STAIRS;
										}
										else if(mat.equals("stone")){

											mats = Material.SMOOTH_STAIRS;
										}
										else if(mat.equals("nether")){

											mats = Material.NETHER_BRICK_STAIRS;
										}
										else if(mat.equals("sandstone")){

											mats = Material.SANDSTONE_STAIRS;
										}
										else if(mat.equals("quartz")){

											mats = Material.QUARTZ_STAIRS;
										}
										else{
											mats = Material.WOOD_STAIRS;
										}
										finDir = 7;

									}
									else if(letnow.equals("^")){
										//player.sendMessage("^");

										if(mat.equals("wood")){
											mats = Material.WOOD_STAIRS;
										}
										else if(mat.equals("brick")){

											mats = Material.BRICK_STAIRS;
										}
										else if(mat.equals("cobble")){

											mats = Material.COBBLESTONE_STAIRS;
										}
										else if(mat.equals("stone")){

											mats = Material.SMOOTH_STAIRS;
										}
										else if(mat.equals("nether")){

											mats = Material.NETHER_BRICK_STAIRS;
										}
										else if(mat.equals("sandstone")){

											mats = Material.SANDSTONE_STAIRS;
										}
										else if(mat.equals("quartz")){

											mats = Material.QUARTZ_STAIRS;
										}
										else{
											mats = Material.WOOD_STAIRS;
										}
										finDir = 6;

									}
									else if(letnow.equals("_")){
										//player.sendMessage("_");

										if(mat.equals("wood")){
											mats = Material.WOOD;
										}
										else if(mat.equals("nether")){
											mats = Material.NETHER_BRICK;
										}
										else if(mat.equals("cobble")){
											mats = Material.COBBLESTONE;
										}
										else if(mat.equals("brick")){
											mats = Material.BRICK;
										}
										else if(mat.equals("stone")){
											mats = Material.SMOOTH_BRICK;
										}
										else if(mat.equals("sandstone")){
											mats = Material.SANDSTONE;
										}
										else if(mat.equals("quartz")){
											mats = Material.QUARTZ_BLOCK;
										}
										else{
											mats = Material.WOOD;
										}
										finDir = 0;
									}
									else if(letnow.equals("-")){
										//player.sendMessage("-");
										if(mat.equals("wood")){
											finDir = 10;
										}
										else if(mat.equals("brick")){
											finDir = 12;
										}
										else if(mat.equals("cobble")){
											finDir = 11;
										}
										else if(mat.equals("stone")){
											finDir = 13;
										}
										else if(mat.equals("nether")){
											finDir = 14;
										}
										else if(mat.equals("sandstone")){
											finDir = 9;
										}
										else if(mat.equals("quartz")){
											finDir = 15;
										}
										else{
											finDir = 10;
											mats = Material.STEP;
										}

										mats = Material.STEP;
									}
									else if(letnow.equals("~")){
										//player.sendMessage("~");
										if(mat.equals("wood")){
											finDir = 2;
										}
										else if(mat.equals("brick")){
											finDir = 4;
										}
										else if(mat.equals("cobble")){
											finDir = 3;
										}
										else if(mat.equals("stone")){
											finDir = 5;
										}
										else if(mat.equals("nether")){
											finDir = 6;
										}
										else if(mat.equals("sandstone")){
											finDir = 1;
										}
										else if(mat.equals("quartz")){
											finDir = 7;
										}
										else{
											finDir = 2;
											mats = Material.STEP;
										}

										mats = Material.STEP;
									}

									World world = loc.getWorld();


									// Get the block that we are currently looping over.
									Block currentBlock;
									if(font.equals("sm") || font.equals("pv")){

										currentBlock = world.getBlockAt(x1, y1 + 3 - jr, z1 + smstart - let);
	
									}
									else{
									currentBlock = world.getBlockAt(x1, y1 + 4 - jr, z1 + start - let);
									}

	


									if(canBuildHere(player.getLocation(), player)){
										if(canBuildHere(currentBlock.getLocation(), player)){
											if(isPlotAllowed(player, currentBlock) == true){
												if(font.equals("sm")){

													try {
														data.writeToFile(Integer.toString(x1) 
																+ "," + Integer.toString(y1 + 3 - jr) 
																+ "," + Integer.toString(z1 + smstart - let) 
																+ "," + currentBlock.getType().toString());
													} catch (IOException e) {
														e.printStackTrace();
													}
													currentBlock.setType(mats);
													currentBlock.setData(finDir);
													//player.sendMessage("Mats: " + mats.name().toString() + " data: " + finDir);
													logBlock(player.getName().toString(), currentBlock.getLocation(), currentBlock.getTypeId(), currentBlock.getData() );
													
												}
												else if(font.equals("pv")){

													curplayer.sendBlockChange(currentBlock.getLocation(), mats, finDir);
													player.sendBlockChange(currentBlock.getLocation(), mats, finDir);
												}
												
												else{
												try {
													data.writeToFile(Integer.toString(x1) 
															+ "," + Integer.toString(y1 + 4 - jr) 
															+ "," + Integer.toString(z1 + start - let) 
															+ "," + currentBlock.getType().toString());
												} catch (IOException e) {
													e.printStackTrace();
												}
												currentBlock.setType(mats);
												currentBlock.setData(finDir);
												//player.sendMessage("Mats: " + mats.name().toString() + " data: " + finDir);
												logBlock(player.getName().toString(), currentBlock.getLocation(), currentBlock.getTypeId(), currentBlock.getData() );
												}
												}else{
												//player.sendMessage("You are not allowed to place blocks in this plot!");
												plotBuild = false;
											}
										}else{
											//player.sendMessage("You are not allowed to place blocks in this region!");
											regionBlockBuild = false;
										}
									}else{
										//player.sendMessage("You are not allowed to build in this region!");
										regionBuild = false;
	
									}

								}
	
							}
				


						}
					}
				}
				else if(dir.equals("east")){
					for (int jr : range(0, pieces[curdigit].length - 1)){
						//player.sendMessage(Integer.toString(jr));
						//loop for array part
						for(int let : range(0, pieces[curdigit][jr].length())){
							//player.sendMessage(Integer.toString(let));
							//player.sendMessage(pieces[curdigit][jr]);
							if(let < pieces[curdigit][jr].length()){
								String letnow = pieces[curdigit][jr].substring(let, let + 1);

								//player.sendMessage(letnow);
								int x1 = loc.getBlockX() + 1;
								int y1 = loc.getBlockY() ;
								int z1 = loc.getBlockZ() ;


								finDir = 0;
								if(mat.equals("wood")){
									mats = Material.WOOD;
								}
								if(mat.equals("nether")){
									mats = Material.NETHER_BRICK;
								}
								if(mat.equals("cobble")){
									mats = Material.COBBLESTONE;
								}
								if(mat.equals("brick")){
									mats = Material.BRICK;
								}
								if(mat.equals("stone")){
									mats = Material.SMOOTH_BRICK;
								}
								if(mat.equals("sandstone")){
									mats = Material.SANDSTONE;
								}
								if(mat.equals("quartz")){
									mats = Material.QUARTZ_BLOCK;
								}
								if(!(letnow.equals(" "))){
									if(letnow.equals("#")){
										//player.sendMessage("#");
										if(font.equals("fz")){

											if(mat.equals("wood")){
												mats = Material.WOOD_STAIRS;
											}
											if(mat.equals("brick")){

												mats = Material.BRICK_STAIRS;
											}
											if(mat.equals("cobble")){

												mats = Material.COBBLESTONE_STAIRS;
											}
											if(mat.equals("stone")){

												mats = Material.SMOOTH_STAIRS;
											}
											if(mat.equals("nether")){

												mats = Material.NETHER_BRICK_STAIRS;
											}
											if(mat.equals("sandstone")){

												mats = Material.SANDSTONE_STAIRS;
											}
											if(mat.equals("quartz")){

												mats = Material.QUARTZ_STAIRS;
											}

											finDir = 2;
										}
										else{
										if(mat.equals("wood")){
											mats = Material.WOOD;
										}
										if(mat.equals("brick")){

											mats = Material.BRICK;
										}
										if(mat.equals("cobble")){

											mats = Material.COBBLESTONE;
										}
										if(mat.equals("stone")){

											mats = Material.SMOOTH_BRICK;
										}
										if(mat.equals("nether")){

											mats = Material.NETHER_BRICK;
										}
										if(mat.equals("sandstone")){

											mats = Material.SANDSTONE;
										}
										if(mat.equals("quartz")){

											mats = Material.QUARTZ_BLOCK;
										}
										}
									}
									else if(letnow.equals("!")){
										//player.sendMessage("!");

										if(mat.equals("wood")){
											mats = Material.WOOD_STAIRS;
										}
										else if(mat.equals("brick")){

											mats = Material.BRICK_STAIRS;
										}
										else if(mat.equals("cobble")){

											mats = Material.COBBLESTONE_STAIRS;
										}
										else if(mat.equals("stone")){

											mats = Material.SMOOTH_STAIRS;
										}
										else if(mat.equals("nether")){

											mats = Material.NETHER_BRICK_STAIRS;
										}
										else if(mat.equals("sandstone")){

											mats = Material.SANDSTONE_STAIRS;
										}
										else if(mat.equals("quartz")){

											mats = Material.QUARTZ_STAIRS;
										}
										else{
											mats =Material.WOOD_STAIRS;
										}

										finDir = 2;
									}
									else if(letnow.equals("@")){
										//player.sendMessage("@");

										if(mat.equals("wood")){
											mats = Material.WOOD_STAIRS;
										}
										else if(mat.equals("brick")){

											mats = Material.BRICK_STAIRS;
										}
										else if(mat.equals("cobble")){

											mats = Material.COBBLESTONE_STAIRS;
										}
										else if(mat.equals("stone")){

											mats = Material.SMOOTH_STAIRS;
										}
										else if(mat.equals("nether")){

											mats = Material.NETHER_BRICK_STAIRS;
										}
										else if(mat.equals("sandstone")){

											mats = Material.SANDSTONE_STAIRS;
										}
										else if(mat.equals("quartz")){

											mats = Material.QUARTZ_STAIRS;
										}
										else{
											mats =Material.WOOD_STAIRS;
										}
										finDir = 3;
									}
									else if(letnow.equals("%")){
										//player.sendMessage("%");

										if(mat.equals("wood")){
											mats = Material.WOOD_STAIRS;
										}
										else if(mat.equals("brick")){

											mats = Material.BRICK_STAIRS;
										}
										else if(mat.equals("cobble")){

											mats = Material.COBBLESTONE_STAIRS;
										}
										else if(mat.equals("stone")){

											mats = Material.SMOOTH_STAIRS;
										}
										else if(mat.equals("nether")){

											mats = Material.NETHER_BRICK_STAIRS;
										}
										else if(mat.equals("sandstone")){

											mats = Material.SANDSTONE_STAIRS;
										}
										else if(mat.equals("quartz")){

											mats = Material.QUARTZ_STAIRS;
										}
										else{
											mats =Material.WOOD_STAIRS;
										}
										finDir = 6;

									}
									else if(letnow.equals("^")){
										//player.sendMessage("^");

										if(mat.equals("wood")){
											mats = Material.WOOD_STAIRS;
										}
										else if(mat.equals("brick")){

											mats = Material.BRICK_STAIRS;
										}
										else if(mat.equals("cobble")){

											mats = Material.COBBLESTONE_STAIRS;
										}
										else if(mat.equals("stone")){

											mats = Material.SMOOTH_STAIRS;
										}
										else if(mat.equals("nether")){

											mats = Material.NETHER_BRICK_STAIRS;
										}
										else if(mat.equals("sandstone")){

											mats = Material.SANDSTONE_STAIRS;
										}
										else if(mat.equals("quartz")){

											mats = Material.QUARTZ_STAIRS;
										}
										else{
											mats =Material.WOOD_STAIRS;
										}
										finDir = 7;

									}
									else if(letnow.equals("_")){
										//player.sendMessage("_");

										if(mat.equals("wood")){
											mats = Material.WOOD;
										}
										else if(mat.equals("nether")){
											mats = Material.NETHER_BRICK;
										}
										else if(mat.equals("cobble")){
											mats = Material.COBBLESTONE;
										}
										else if(mat.equals("brick")){
											mats = Material.BRICK;
										}
										else if(mat.equals("stone")){
											mats = Material.SMOOTH_BRICK;
										}
										else if(mat.equals("sandstone")){
											mats = Material.SANDSTONE;
										}
										else if(mat.equals("quartz")){
											mats = Material.QUARTZ_BLOCK;
										}
										else{
											mats =Material.WOOD;
										}
										finDir = 0;
									}
									else if(letnow.equals("-")){
										//player.sendMessage("-");
										if(mat.equals("wood")){
											finDir = 10;
										}
										else if(mat.equals("brick")){
											finDir = 12;
										}
										else if(mat.equals("cobble")){
											finDir = 11;
										}
										else if(mat.equals("stone")){
											finDir = 13;
										}
										else if(mat.equals("nether")){
											finDir = 14;
										}
										else if(mat.equals("sandstone")){
											finDir = 9;
										}
										else if(mat.equals("quartz")){
											finDir = 15;
										}
										else{
											finDir = 10;
											mats =Material.STEP;
										}

										mats = Material.STEP;
									}
									else if(letnow.equals("~")){
										//player.sendMessage("~");
										if(mat.equals("wood")){
											finDir = 2;
										}
										else if(mat.equals("brick")){
											finDir = 4;
										}
										else if(mat.equals("cobble")){
											finDir = 3;
										}
										else if(mat.equals("stone")){
											finDir = 5;
										}
										else if(mat.equals("nether")){
											finDir = 6;
										}
										else if(mat.equals("sandstone")){
											finDir = 1;
										}
										else if(mat.equals("quartz")){
											finDir = 7;
										}
										else{
											finDir = 2;
											mats =Material.STEP;
										}

										mats = Material.STEP;
									}

									World world = loc.getWorld();


									// Get the block that we are currently looping over.
									Block currentBlock;// = world.getBlockAt(x1, y1 + 4 - jr, z1 - start + let);
									if(font.equals("sm") || font.equals("pv")){

										currentBlock = world.getBlockAt(x1, y1 + 3 - jr, z1 - smstart + let);
	
									}
									else{
									currentBlock = world.getBlockAt(x1, y1 + 4 - jr, z1 - start + let);
									}



									if(canBuildHere(player.getLocation(), player)){
										if(canBuildHere(currentBlock.getLocation(), player)){
											if(isPlotAllowed(player, currentBlock) == true){
												if(font.equals("sm")){

													try {
														data.writeToFile(Integer.toString(x1) 
																+ "," + Integer.toString(y1 + 3 - jr) 
																+ "," + Integer.toString(z1 - smstart + let) 
																+ "," + currentBlock.getType().toString());
													} catch (IOException e) {
														e.printStackTrace();
													}
													currentBlock.setType(mats);
													currentBlock.setData(finDir);
													//player.sendMessage("Mats: " + mats.name().toString() + " data: " + finDir);
													logBlock(player.getName().toString(), currentBlock.getLocation(), currentBlock.getTypeId(), currentBlock.getData() );
													
												}
												else if(font.equals("pv")){

													curplayer.sendBlockChange(currentBlock.getLocation(), mats, finDir);
													player.sendBlockChange(currentBlock.getLocation(), mats, finDir);
												}
												
												else{
												try {
													data.writeToFile(Integer.toString(x1) 
															+ "," + Integer.toString(y1 + 4 - jr) 
															+ "," + Integer.toString(z1 - start + let) 
															+ "," + currentBlock.getType().toString());
												} catch (IOException e) {
													e.printStackTrace();
												}
												currentBlock.setType(mats);
												currentBlock.setData(finDir);
												//player.sendMessage("Mats: " + mats.name().toString() + " data: " + finDir);
												logBlock(player.getName().toString(), currentBlock.getLocation(), currentBlock.getTypeId(), currentBlock.getData() );
												}
										}else{
												//player.sendMessage("You are not allowed to place blocks in this plot!");
												plotBuild = false;
											}
										}else{
											//player.sendMessage("You are not allowed to place blocks in this region!");
											regionBlockBuild = false;
										}
									}else{
										//player.sendMessage("You are not allowed to build in this region!");
										regionBuild = false;
								
									}



								}
						
							}
				


						}
					
				}
				}
				start = start - cnt;
				smstart = smstart - cnt4;



			
			}
	}

		if (regionBuild == false){
			player.sendMessage("You are not allowed to build in this region!");
		}
		if (regionBlockBuild == false){
			player.sendMessage("You are not allowed to place blocks in this region!");
		}
		if (plotBuild == false){
			player.sendMessage("You are not allowed to place blocks outside your plot!");
		}


		return true;
	}
	public static boolean logBlock(String playerName, Location locate, int blockType, byte blockData ){
		try{
			CoreProtectAPI CoreProtect = new CoreProtect().getAPI();
		}
		catch(NoClassDefFoundError e){
			return true;
		}
		CoreProtectAPI CoreProtect = new CoreProtect().getAPI();
		if (CoreProtect!=null){
			@SuppressWarnings("unused")
			boolean success = CoreProtect.logPlacement(playerName, locate, blockType, blockData);        
			return true;

		}
		return false;
	}

	public static boolean canBuildHere(Location loc, Player playerName){

		try{
			GlobalRegionManager worldGuard = WorldGuardPlugin.inst().getGlobalRegionManager();
		}
		catch(NoClassDefFoundError e)
		{
			return true;
		}
		GlobalRegionManager worldGuard = WorldGuardPlugin.inst().getGlobalRegionManager();
		if (worldGuard!=null){
			boolean success = worldGuard.canBuild(playerName, loc);
			return success;
		}
		return false;
	}

	public static boolean isPlotAllowed(Player player, Block block){

		Block b = block;
		try{
			PlotManager.isPlotWorld(b);
		}
		catch(NoClassDefFoundError e){
			return true;
		}
		if(PlotManager.isPlotWorld(b))
		{
			Player p = player;
			boolean canbuild = PlotMe.cPerms(p, "plotme.admin.buildanywhere");
			String id = PlotManager.getPlotId(b.getLocation());

			if(id.equalsIgnoreCase(""))
			{
				if(!canbuild)
				{
					//p.sendMessage(PlotMe.caption("ErrCannotBuild"));

					return false;
				}
				return true;
			}
			else
			{
				Plot plot = PlotManager.getPlotById(p,id);

				if (plot == null)
				{
					if(!canbuild)
					{
						//p.sendMessage(PlotMe.caption("ErrCannotBuild"));

						return false;
					}
					return true;
				}
				else if(!plot.isAllowed(p.getName()))
				{
					if(!canbuild)
					{
						//p.sendMessage(PlotMe.caption("ErrCannotBuild"));

						return false;
					}
					return true;
				}
				else
				{
					plot.resetExpire(PlotManager.getMap(b).DaysToExpiration);
				}

			}
			return true;
		}
		return true;
	}



	private WorldGuardPlugin getWorldGuard() {
		Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");


		if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
			return null;
		}

		return (WorldGuardPlugin) plugin;
	}

}			    		
/*
 * Version 1.0 
 * By K0Gs
 */

