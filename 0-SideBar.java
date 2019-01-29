package Game;

import java.awt.Color;
import javax.swing.*;

import java.awt.event.*;
public class SideBar extends Gameplay implements ActionListener{
	//buttons for next, instructions, stats, backpack, roster
	private JButton instructions, stats, backpack, roster;
	private JButton candy; //candy using button
	private JButton clear, at, de, st; //clear (clears the string value), attack, defense, stamina for increasing hero stats
	private JTextField candytf; //entering heroes in text field
	private JLabel chk, candyD; //checking hero message, display candy total
	private int check = 0; //used to find out if the user successfully entered the heroes name (used for adding stats using candies)
	Stats a; //Stats object 
	UStats k; //User stats object
	private String name; //String value holds the user's name
	private String heroU = ""; //String value holds the heroe's name entered by the user
	
	//Constructor
	//pre: String value (name), Stats object, User stats object
	public SideBar(String n, Stats a1, UStats k1){
		super(b);
		a = a1; //takes in the stats object 
		k = k1; //takes in the user stats object
		name = n; //takes in the user's name
		clear(); //calls the clear method
		main(); //calls the main method 
	}
	//displays the main sidebar screen 
	public void main(){
		setSize(225, 400);
		getContentPane().setLayout(null);
		getContentPane().setBackground(b);
		//instructions button to display the instructions
		instructions = new JButton("Instructions");
		instructions.setBounds(100, 250, 105, 45);
		instructions.addActionListener(this);
		instructions.setBackground(Color.WHITE);
		//stats button to display the user's stats screen
		stats = new JButton("Stats");
		stats.setBounds(10, 250, 80, 45);
		stats.addActionListener(this);
		stats.setBackground(Color.WHITE);
		//backpack button to display the backpack screen
		backpack = new JButton("Backpack");
		backpack.setBounds(10, 300, 100, 45);
		backpack.addActionListener(this);
		backpack.setBackground(Color.WHITE);
		//roster button to display the user's roster 
		roster = new JButton("Roster");
		roster.setBounds(120, 300, 85, 45);
		roster.addActionListener(this);
		roster.setBackground(Color.WHITE);
		
		add(stats);
		add(instructions);
		add(backpack);
		add(roster);
		setResizable(false);
		setVisible(true);
		setLocation(100, 120); //positions the jframe on the computer screen
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
	}
	//displays the user's heroes + hero stats
	public void rosterD(){
		setSize(700, 580);
		getContentPane().setLayout(null);
		getContentPane().setBackground(b);
		//displays the total candies the user has
		JLabel candyD = new JLabel("Candies: "+k.getCandies());
		candyD.setBounds(20, 20, 100, 20);
		//displays the general line to help the user understand the stats
		JLabel infor = new JLabel("Hero Name:  Attack  Defense  Stamina");
		infor.setBounds(20, 60, 250, 20);
		//displays the names of all heroes
		String l = hero_D();
		JLabel heroD = new JLabel(l);
		heroD.setBounds(20, 90, 700, 400);
		//back button
		back = new JButton(backa1);
		back.setBounds(325, 490, 50, 50);
		button(back);
		back.addActionListener(this);
		
		add(candyD);
		add(infor);
		add(heroD);
		add(back);

	}
	//displays the user's stats
	public void stats(){
		setSize(225, 400);
		getContentPane().setLayout(null);
		getContentPane().setBackground(b);
		//displays the user's name
		JLabel na = new JLabel("Name: "+name);
		na.setBounds(10, 20, 200, 20);
		//displays the user's level
		JLabel level = new JLabel("Level: "+k.getLevel());
		level.setBounds(10, 50, 50, 20);
		//displays the user's xp total
		JLabel xpD = new JLabel("XP: "+k.getXp());
		xpD.setBounds(10, 70, 50, 20);
		//displays hero battle
		JLabel hbattle = new JLabel("HERO BATTLES", SwingConstants.CENTER);
		hbattle.setBounds(62, 100, 100, 20);
		//displays # of heroes fought
		JLabel hf = new JLabel("# of Hero Battles Fought: "+k.getHeroF());
		hf.setBounds(10, 125, 170, 20);
		//displays # of hero battles won
		JLabel hw = new JLabel("# of Hero Battles Won: "+k.getHeroW());
		hw.setBounds(10, 145, 150, 20);
		//displays # of hero battles lost
		JLabel hl = new JLabel("# of Hero Battles Lost: "+(k.getHeroF()-k.getHeroW()));
		hl.setBounds(10, 165, 150, 20);
		//displays villain battle
		JLabel vbattle = new JLabel("VILLAIN BATTLES", SwingConstants.CENTER);
		vbattle.setBounds(62, 195, 100, 20);
		//displays # of villains fought
		JLabel vf = new JLabel("# of Villain Battles Fought: "+k.getVillainF());
		vf.setBounds(10, 215, 170, 20);
		//displays # of villain battles won
		JLabel vw = new JLabel("# of Villain Battles Won: "+k.getVillainW());
		vw.setBounds(10, 235, 150, 20);
		//displays # of vilain battles lost
		JLabel vl = new JLabel("# of Villain Battles Lost: "+(k.getVillainF()-k.getVillainW()));
		vl.setBounds(10, 255, 150, 20);
		//displays money
		JLabel mon = new JLabel("MONEY", SwingConstants.CENTER);
		mon.setBounds(62, 285, 100, 20);
		//displays the total coin amount
		JLabel coin = new JLabel("Coin: $"+k.getCoin());
		coin.setBounds(10, 305, 100, 20);
		//back button
		back = new JButton(backa1);
		back.setBounds(87, 320, 50, 50);
		button(back);
		back.addActionListener(this);

		add(na);
		add(level);
		add(xpD);
		add(hbattle);
		add(hf);
		add(hw);
		add(hl);
		add(vbattle);
		add(vf);
		add(vw);
		add(vl);
		add(mon);
		add(coin);
		add(back);
	}
	//display's the user's backpack where the user can apply candies to each of their heroes
	public void backpack(){
		setSize(700, 580);
		getContentPane().setLayout(null);
		getContentPane().setBackground(b);
		//resets the heroU value
		heroU = "";
		//displays the total candies
		candyD = new JLabel("Candies: "+k.getCandies());
		candyD.setBounds(20, 20, 100, 20);
		//displays the general line to help the user understand the stats
		JLabel infor = new JLabel("Hero Name:  Attack  Defense  Stamina");
		infor.setBounds(20, 60, 250, 20);
		//displays the names of all heroes
		String l = hero_D();
		JLabel heroD = new JLabel(l);
		heroD.setBounds(20, 90, 700, 400);
		//displays the instructions for applying candies to heroes
		JLabel candyI = new JLabel("<html>Use these text field to enter a hero to use the candies on<br/>"+"Use the A D S to specify which stat to increase", SwingConstants.CENTER);
		candyI.setBounds(320, 150, 300, 60);
		//use candy button
		candy = new JButton("Use Candies");
		candy.setBounds(375, 280, 150, 30);
		candy.setBackground(Color.WHITE);
		candy.addActionListener(this);
		
		//entering hero's name text field
		candytf = new JTextField(15);
		candytf.setBounds(350, 250, 200, 20);
		candytf.addKeyListener(this); 
		//clear button clears the string value entered in the text field
		clear = new JButton("Clear");
		clear.setBounds(400, 210, 100, 30); 
		clear.setBackground(Color.WHITE);
		clear.addActionListener(this);
		//attack button
		at = new JButton("A");
		at.setBounds(370, 315, 50, 30);
		at.setBackground(Color.WHITE);
		at.addActionListener(this);
		//defense button
		de = new JButton("D");
		de.setBounds(425, 315, 50, 30);
		de.setBackground(Color.WHITE);
		de.addActionListener(this);
		//stamina button
		st = new JButton("S");
		st.setBounds(480, 315, 50, 30);
		st.setBackground(Color.WHITE);
		st.addActionListener(this);
		//checking message
		chk = new JLabel("", SwingConstants.CENTER);
		chk.setBounds(305, 345, 300, 40);
		//back button
		back = new JButton(backa1);
		back.setBounds(325, 490, 50, 50);
		button(back);
		back.addActionListener(this);

		add(candyD);
		add(infor);
		add(heroD);
		add(candyI);
		add(candy);
		add(candytf);
		add(clear);
		add(at);
		add(de);
		add(st);
		add(chk);
		add(back);
	}
	//returns the names of all heroes along with their stats in a vertical format
	public String hero_D(){
		int k;
		String l = "<html>";
		//runs through all 25 heroes
		for(int i = 0; i < 25; i++){
			k = a.BHero(i); //checks if the user has this hero
			//prints the name of the hero along with their stats
			if(k == 0){
				l = l+a.getHName(i)+":  "+a.getUattack(i)+" "+a.getUdefense(i)+" "+a.getUstamina(i)+"<br/>";
			}
			//prints questions marks to hide the uncaught hero's name
			else{
				l = l+"?????<br/>";
			}
		}
		return l; 
	}
	public void actionPerformed(ActionEvent event){
		//if the instructions button is pressed the instructions are displayed
		if(event.getSource() == instructions){
			clear();
			instructions(); 
		}
		//if the back button is pressed the main sidebar is displayed
		if(event.getSource() == back){
			clear();
			main();
		}
		//if the roster button is pressed the roster page is displayed
		if(event.getSource() == roster){
			clear();
			rosterD();
		}
		//if the stats button is pressed the stats page of the user is displayed
		if(event.getSource() == stats){
			clear();
			stats();
		}
		//if the backpack button is pressed the backpack page is displayed
		if(event.getSource() == backpack){
			clear();
			backpack();
		}
		//if the clear button is pressed the heroU string value is reset to empty
		if(event.getSource() == clear){
			heroU = "";
			candytf.setText("");
		}
		//if the candy button is pressed the name of the hero entered to apply candies will be checked to see if the user has this hero
		if(event.getSource() == candy){
			int i = a.BHero(heroU); //return a int value to indicate if the user has the hero or not
			if(i == 0){ //if i = 0 it means the user doesn't have this hero or their entry is incorrect
				chk.setText("No hero was found. Try entering another hero."); //displays this message to the user
			}
			else{ //if i doesn't = 0 it means the user has the hero 
				check = 1;
				chk.setText("Hero was found."); //displays this message to the user
			}
		}
		//if the at is pressed and the user has the hero, the attack value of the hero is less than 15 and the user has candies then the attack stat will go up for that hero
		if(event.getSource() == at && check == 1 && a.getUattack(a.getUHero(heroU)) < 15 && k.getCandies() > 0){
			a.addAtt(a.getUHero(heroU), ((a.getUattack(a.getUHero(heroU)))+0.5)); //adding 0.5 to the attack stat
			chk.setText("<html>Heroes stats went up.<br/>"+"Go back to successfully add the stats.");
			k.subCandies(); //subtracts from the candy total
			candyD.setText("Candies: "+k.getCandies()); //displays candy totals
		
		}
		//if the de is pressed and the user has the hero, the defense value of the hero is less than 15 and the user has candies then the defense stat will go up for that hero
		if(event.getSource() == de && check == 1 && a.getUdefense(a.getUHero(heroU)) < 15 && k.getCandies() > 0){
			a.addDef(a.getUHero(heroU), ((a.getUdefense(a.getUHero(heroU)))+0.5)); //addes 0.5 to the defense stat
			chk.setText("<html>Heroes stats went up.<br/>"+"Go back to successfully add the stats.");
			k.subCandies(); 
			candyD.setText("Candies: "+k.getCandies()); 

		}
		//if the st is pressed and the user has the hero, the stamina value of the hero is less than 15 and the user has candies then the stamina stat will go up for that hero
		if(event.getSource() == st && check == 1 && a.getUstamina(a.getUHero(heroU)) < 15 && k.getCandies() > 0){
			a.addSta(a.getUHero(heroU), ((a.getUstamina(a.getUHero(heroU)))+0.5)); //adds 0.5 to the stamina stat
			chk.setText("<html>Heroes stats went up.<br/>"+"Go back to successfully add the stats.");
			k.subCandies(); 
			candyD.setText("Candies: "+k.getCandies());

		}
		//if the backpack or at or de or st button is pressed and the user has 0 candies, then a message will appear
		else if((event.getSource() == backpack || event.getSource() == at || event.getSource() == de || event.getSource() == st) && k.getCandies() == 0){
			chk.setText("<html>ERROR. You don't have enough candies"); //displays this message
		}
		//if the hero entered has attack of 15 then a message will appear
		else if(a.getUattack(a.getUHero(heroU)) == 15){
			chk.setText("<html>ERROR. You have reached the limit for that attack stat"); //displays this message
		}
		//if the hero entered has defense of 15 then a message will appear
		else if(a.getUdefense(a.getUHero(heroU)) == 15){
			chk.setText("<html>ERROR. You have reached the limit for that defense stat"); //displays this message
		}
		//if the hero entered has stamina of 15 then a message will appear
		else if(a.getUstamina(a.getUHero(heroU)) == 15){
			chk.setText("<html>ERROR. You have reached the limit for that stamina stat"); //displays this message
		}
	}
	public void keyPressed(KeyEvent event){
		char k = event.getKeyChar();
		int i = event.getKeyCode();
		heroU = text(i, k, heroU); //calls the text method 
	}
	public void keyTyped(KeyEvent event){
		
	}
	public void keyReleased(KeyEvent event){
	
	}
	
}
