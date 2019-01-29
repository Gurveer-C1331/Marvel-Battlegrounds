package Game;

public class UStats {
	
	//holds all the stats of the user
	//XP --> row 0
	//Level --> row 1
	//Candies --> row 2
	//# of Hero Fought --> row 3
	//# of Hero Battles Won --> row 4
	//# of VillainsFought --> row 5
	//# of Villain Battles Won --> row 6
	//coins --> row 7
	int [] stat = new int [8];
	
	//constructor
	public UStats(){
		
	}
	//returns the xp total
	//post: returns int value
	public int getXp(){
		return stat[0];
	}
	//adds to the xp total
	//pre: int value
	public void addXp(int n){
		stat[0] = stat[0] + n;
		level_C(stat[0]); //calls the level method to calculate the level
	}
	//calculates the level of the user based on the xp total
	//pre: int value
	public void level_C(int x){
		while(x >= (Math.pow(2, getLevel())*100)){ //when user levels up
			stat[1]++; //adds one to level
			addCandies(); //user gets one candy when leveling up
			addCoin(50); //user gets $50 when leveling up
		}
	}
	//returns the level of the user
	//post: returns int value
	public int getLevel(){
		return stat[1];
	}
	//returns the # of candies 
	//post: returns int value
	public int getCandies(){
		return stat[2];
	}
	//adds one to the candy total
	public void addCandies(){
		stat[2]++;
	}
	//subtracts one to the candy total
	public void subCandies(){
		stat[2]--;
	}
	//returns the # of heroes fought
	//post: returns int value
	public int getHeroF(){
		return stat[3];
	}
	//adds to the total of heroes fought 
	public void addHeroF(){
		stat[3]++;
	}
	//returns the # of hero battles won
	//post: returns int value
	public int getHeroW(){
		return stat[4];
	}
	//adds to the total of hero battles won
	public void addHeroW(){
		stat[4]++;
	}
	//returns the # of villains fought 
	//post: returns int value
	public int getVillainF(){
		return stat[5];
	}
	//adds to the total of villains fought
	public void addVillainF(){
		stat[5]++;
	}
	//returns the # of villain battles won
	//post: returns int value
	public int getVillainW(){
		return stat[6];
	}
	//adds to the total of villain battles won
	public void addVillainW(){
		stat[6]++;
	}
	//returns the amount of coins the user has
	//post: returns int value
	public int getCoin(){
		return stat[7];
	}
	//adds to the total amount of coins
	public void addCoin(int n){
		stat[7] = stat[7] + n;
	}
	
	
	
}
