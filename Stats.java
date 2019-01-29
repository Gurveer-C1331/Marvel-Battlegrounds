package Game;
import javax.swing.*;
import java.awt.event.*;
public class Stats{
	//Array of hero names
	private String [] heroNam = {"Ant man", "Black Panther", "Black Widow", "Captain America Classic", "Captain America WS", "Captain America WWII", "Daredevil", "Deadpool", "Doctor Strange", "Falcon", "Hulk", "Iron Man Mk 42", "Iron Man Mk 5", "Iron Man Av", "Loki", "Captain Marvel", "Nova", "Punisher", "Spider Gwen", "Spiderman BS", "Spiderman Classic", "Spiderman Superior", "Star Lord", "Thor", "Winter Solider"};
	//Array of villain names
	private String [] vilNam = {"Abomination", "Bullseye", "Carnage", "Doctor Doom", "Doctor Octopus", "Dormammu", "Electro", "Green Goblin", "Hela", "Hydro Man", "Kingpin", "Mysterio", "Red Skull", "Shocker", "Surtur", "Thanos", "Ultron", "Venom", "Vulture", "Yellowjacket"};
	//Array for hero stats (attack, defense, stamina)
	//Indicates if user has the hero --> 0 row
	//Attack --> 1 row
	//Defense --> 2 row
	//Stamina --> 3 row
	double [][] heroStat = new double [4][heroNam.length]; 
	int [][] map; //represents the map
	//Hero
	double attH; //attack
	double defH; //defense
	double staH; //stamina
	//Hero (User)
	double attU; //attack
	double defU; //defense
	double staU; //stamina
	
	//constructor used when user encounters a hero or villain on the map
	public Stats(){

	}
	
	//constructor used when user picks his/her first hero
	//pre: String value
	public Stats(String b){
		int k = 0; //used to find the proper position to add stats in heroStat array
		//finds the hero using the name given
		for(int i = 0; i < heroNam.length; i++){
			if(heroNam[i] == b){ //if name given is found in the heroName array
				k = i;
			}
		} 
		heroStat[0][k] = 1; //adds one to indicate user now has this hero
		//generates stats
		int at = (int)(Math.random()*(9-3+1)+3); //attack
		int de = (int)(Math.random()*(9-3+1)+3); //defense
		int st = (int)(Math.random()*(9-3+1)+3); //stamina
		//adds the information into the heroStat array
		addAtt(k, at);
		addDef(k, de);
		addSta(k, st);
	}
	//used to check if the user has this hero (used when printing vertical list of heroes in Backpack and Roster page)
	//pre: int value
	//post: returns a int value
	public int BHero(int a){
		int k = -1;
		//checks if the user has the hero
		if(heroStat[0][a] == 1){
			k = 0; 
		}
		else{
			k = 1;
		}
		return k;
	}
	//used to check if the user has the hero they entered
	//pre: String value
	//post: returns int value
	public int BHero(String a){
		int k = -1;
		int check = 0;
		//checks to see if the user has the hero they entered
		for(int i = 0; i < heroNam.length; i++){
			if(heroNam[i].equalsIgnoreCase(a) && heroStat[0][i] == 1){
				k = i;
			}
		} 
		//if no hero is found check is 0
		if(k == -1){
			check = 0;
		}
		else{
			check = 1;
		}
		return check;
	}
	//returns the name of the hero the user has encountered 
	//pre: int value
	//post: returns String value
	public String getHName(int n){
		String name = heroNam[n]; //finds name in the heroNam array
		return name;
	}
	//returns the name of the villain the user has encountered 
	//pre: int value
	//post: returns String value
	public String getVName(int n){
		String name = vilNam[n]; //finds the name in the villain name array
		return name;
		}
	//returns the user's hero's name
	//pre: int value
	//post: returns String value
	public String getUHeroName(int Uh){
		String l = heroNam[Uh]; //finds the name in the heroName array
		return l;
	}
	//returns the ImageIcon of the hero 
	//pre: int value
	//post: returns ImageIcon of hero
	public ImageIcon getHeroImage(int n){
		return heroimage[n];
	}
	//returns the ImageIcon of the villain 
	//pre: int value
	//post: returns ImageIcon of villain
	public ImageIcon getVillainImage(int n){
		return villainimage[n];
	}
	//returns the integer number (representing the hero) of the hero the user has selected 
	//pre: String value
	//post: returns int value
	public int getUHero(String Uname){
		int Uh = 0;
		//finds the hero in the heroName array
		for(int i = 0; i < heroNam.length; i++){
			if(heroNam[i].equalsIgnoreCase(Uname)){
				Uh = i;
			}
		}
		return Uh;
	}
	
	//GETTING THE STATS OF THE HEROES IN BATTLE
	//returns the attack of the hero the user has encountered
	//post: return int value
	public double getHattack(){
		attH = (int)(Math.random()*(15-1+1)+1); //generates a number from 1 to 15
		return attH;
	}
	//returns the attack of the hero the user is using 
	//pre: int value
	//post: returns int value
	public double getUattack(int Uh){
		attU = heroStat[1][Uh];
		return attU;
	}
	//returns the defense of the hero the user has encountered
	//post: int value
	public double getHdefense(){
		defH = (int)(Math.random()*(15-1+1)+1); //generates a number from 1 to 15
		return defH;
	}
	//returns the defense of the hero the user is using 
	//pre: int value
	//post: returns int value
	public double getUdefense(int Uh){
		defU = heroStat[2][Uh];
		return defU;
	}
	//returns the stamina of the hero the user has encountered
	//post: int value
	public double getHstamina(){
		staH = (int)(Math.random()*(15-1+1)+1); //generates a number from 1 to 15
		return staH;
	}
	//gets the stamina of the hero the user is using 
	//pre: int value
	//post: returns int value
	public double getUstamina(int Uh){
		staU = heroStat[3][Uh];
		return staU;
	}
	
	//ADDING THE STATS ONLY AFTER THE USER HAS WON THE BATTLE 
	//adding one in the array to indicate the user has this hero
	//pre: int value
	public void addHero(int Hh){
		heroStat[0][Hh] = 1;
	}
	//adding the attack stats 
	//pre: int value
	public void addAtt(int Hh, double n){
		heroStat[1][Hh] = n;
	}
	//adding the defense stats 
	//pre: int value
	public void addDef(int Hh, double n){
		heroStat[2][Hh] = n;
	}
	//adding the stamina stats 
	//pre: int value
	public void addSta(int Hh, double n){
		heroStat[3][Hh] = n;
	}
	
	
	//IMAGES OF THE HEROES
	ImageIcon hero0 = new ImageIcon("Ant-man.png");
	ImageIcon hero1 = new ImageIcon("Black_Panther.png");
	ImageIcon hero2 = new ImageIcon("Black_Widow.png");
	ImageIcon hero3 = new ImageIcon("CaptainAmerica_Classic.png");
	ImageIcon hero4 = new ImageIcon("CaptainAmerica_WinterSolider.png");
	ImageIcon hero5 = new ImageIcon("CaptainAmerica_WWII.png");
	ImageIcon hero6 = new ImageIcon("Daredevil.png");
	ImageIcon hero7 = new ImageIcon("Deadpool.png");
	ImageIcon hero8 = new ImageIcon("DrStrange.png");
	ImageIcon hero9 = new ImageIcon("Falcon.png");
	ImageIcon hero10 = new ImageIcon("Hulk.png");
	ImageIcon hero11 = new ImageIcon("IronMan_Mark42.png");
	ImageIcon hero12 = new ImageIcon("IronMan_Mark5.png");
	ImageIcon hero13 = new ImageIcon("IronMan_Avengers.png");
	ImageIcon hero14 = new ImageIcon("Loki.png");
	ImageIcon hero15 = new ImageIcon("CaptainMarvel.png");
	ImageIcon hero16 = new ImageIcon("Nova.png");
	ImageIcon hero17 = new ImageIcon("Punisher.png");
	ImageIcon hero18 = new ImageIcon("Spider-Gwen.png");
	ImageIcon hero19 = new ImageIcon("Spider-Man_BlackSuit.png");
	ImageIcon hero20 = new ImageIcon("Spider-Man_Classic.png");
	ImageIcon hero21 = new ImageIcon("Superior_SpiderMan.png");
	ImageIcon hero22 = new ImageIcon("StarLord.png");
	ImageIcon hero23 = new ImageIcon("Thor.png");
	ImageIcon hero24 = new ImageIcon("Winter_Soldier.png");
	//array of all hero images
	ImageIcon [] heroimage = {hero0, hero1, hero2, hero3, hero4, hero5, hero6, hero7, hero8, hero9, hero10, hero11, hero12, hero13, hero14, hero15, hero16, hero17, hero18, hero19, hero20, hero21, hero22, hero23, hero24};
	
	
	//IMAGES OF THE VILLAINS
	ImageIcon villain0 = new ImageIcon("Abomination.png");
	ImageIcon villain1 = new ImageIcon("Bullseye.png");
	ImageIcon villain2 = new ImageIcon("Carnage.png");
	ImageIcon villain3 = new ImageIcon("Doctor_Doom.png");
	ImageIcon villain4 = new ImageIcon("Doctor_Octopus.png");
	ImageIcon villain5 = new ImageIcon("dormammu.png");
	ImageIcon villain6 = new ImageIcon("Electro.png");
	ImageIcon villain7 = new ImageIcon("GreenGoblin.png");
	ImageIcon villain8 = new ImageIcon("Hela.png");
	ImageIcon villain9 = new ImageIcon("hydro-man.png");
	ImageIcon villain10 = new ImageIcon("Kingpin.png");
	ImageIcon villain11 = new ImageIcon("Mysterio.png");
	ImageIcon villain12 = new ImageIcon("RedSkull.png");
	ImageIcon villain13 = new ImageIcon("Shocker_Classic.png");
	ImageIcon villain14 = new ImageIcon("Surtur.png");
	ImageIcon villain15 = new ImageIcon("Thanos.png");
	ImageIcon villain16 = new ImageIcon("ULTRON.png");
	ImageIcon villain17 = new ImageIcon("Venom.png");
	ImageIcon villain18 = new ImageIcon("vulture.png");
	ImageIcon villain19 = new ImageIcon("Yellowjacket.png");
	//array of all villain images
	ImageIcon [] villainimage = {villain0, villain1, villain2, villain3, villain4, villain5, villain6, villain7, villain8, villain9, villain10, villain11, villain12, villain13, villain14, villain15, villain16, villain17, villain18, villain19};

	
}
