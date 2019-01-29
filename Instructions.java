package Game;

import java.io.*;
import java.util.Scanner;

public class Instructions {
	
	private String line;
	public Instructions(String line){
		line = "";
	}
	public String getInstruction() throws IOException{
		line = "<html>"
				+ "1. You will have the option to pick one hero out of three to begin the game<br/>"
				+ "2. Use the up, down, left and right keys in order to move the character around the map<br/>"
				+ "3. As you move around the map you will come in contact will heroes, villains and shops<br/>"
				+ "Battles:<br/>"
				+ " 	- there are two different types of battles, Hero battles and Villain battles<br/>"
				+ "		- in battles you can use the short attack infinite times, but you can only use the charge attack when the counter reaches 6<br/>"
				+ "Hero:<br/>"
				+ "		- you will battle heroes using one character in order to beat them<br/>"
				+ "		- after beating the hero you will be given the option to keep or discard the hero<br/>"
				+ "		- after beating the hero you get 100 xp, one candy item and $25<br/>"
				+ "Villain:<br/>"
				+ "		- you will battle these villains using one hero from your roster<br/>"
				+ "		- after beating the villain you get 150 xp, one candy item and $35<br/>"
				+ "Shops:<br/>"
				+ "		- you will also have the option to enter shops where you can buy candy items for $100<br/>"
				+ "4. The 'Stats' button will allow you to see your personal stats<br/>"
				+ "5. The 'Backpack' button will allow you to apply candy items to your hero<br/>"
				+ "6. The 'Roster' button will allow you to see all of your heroes in your roster along with their stats<br/>"
				+ "Candy Items:<br/>"
				+ "		- candy item will increase one of three stats (Attack, Defense, Stamina) by 0.5<br/>"
				+ "		- the highest value for all three stats is 15<br/>"
				+ "7. Use the arrow buttons on screen to go next or back on certain game screens<br/>"
				+ "8. You can change the background colour of the screens by going into the settings page</html>";
		return line;
	}
}
