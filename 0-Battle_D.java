package Game;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class Battle_D extends Gameplay implements ActionListener, KeyListener {
	//buttons: next (start screen), nextC (hero choose screen), clear (clear textfield), nextB (used after the battle ends), nextV (used after villain present)
	JButton next, nextC, clear, nextB, nextV;
	JButton shortm, chargem; //short and charge attack buttons
	JButton yes, no; //clicked if user wants or doesn't want to add this hero to their roster
	JLabel chk; //displays if the user enter was correct or not
	JLabel charge, chcount; //displays the user if the can use the charge move, and the charge counter
	JLabel vic; //displays who won the battle
	JTextField tf1; //text field for the user to enter the hero they want to user 
	String heroU = ""; //String variable to contain what the user types
	//health bar
	//user, opponent
	JPanel Uhb, Ohb;
	//coordinates from map
	private int r;
	private int c;
	//map
	private int [][] map;
	//OPPONENT
	Stats opp; //opponent hero object
	private int oppNum; //opponent's number 
	private String oppName; //opponent's name
	private double Oatt; //opponent's attack value
	private double Odef; //opponent's defense value
	private double Osta; //opponent's stamina value
	private double Ostab; //opponent's stamina value (used in battle)
	private ImageIcon Oimg; //opponent's image
	//USER
	protected static Stats user; //user hero object
	private int uNum; //user's number 
	private String uName; //user's name
	private double Uatt; //user's attack value
	private double Udef; //user's defense value
	private double Usta; //user's stamina value
	private double Ustab; //user's stamina value (used in battle)
	private ImageIcon Uimg; //user's image

	private int ch; //holds the # of times the short attack is used
	private UStats k; //user stats object
	
	
	//constructor 
	//pre: two int values, int 2d array, stats object, ustats object
	public Battle_D(int y, int x, int map1[][], Stats a, UStats k1){
		super(b);
		//takes in the position of the user
		r = y; 
		c = x;
		map = map1;
		k = k1;
		if(map[r][c] >= 28){ //checks if the opponent is a villain
			oppNum = map[r][c] - 28; //-28 because the first villain has a # of 28 on the map
			opp = new Stats();
			Oimg = opp.getVillainImage(oppNum); 
			k.addVillainF(); //adds to # of villain battle total
		}
		else{ //if the opponent is a hero
			oppNum = map[r][c] - 3; //-3 because the first hero has a # of 3 on the map
			opp = new Stats();
			Oimg = opp.getHeroImage(oppNum);
			k.addHeroF(); //adds to # of hero battle total
		}
		user = a;
		clear();
		start_screen(); //calls the start sreen method
	}
	//displays the start screen of the battle phase tells the user a hero/villain has appeared
	public void start_screen(){
		setSize(700, 400);
		getContentPane().setLayout(null);
		getContentPane().setBackground(b);
		JLabel tell;
		if(map[r][c] >= 28){ //if the opponent is a villain
			oppName = opp.getVName(oppNum);
			System.out.println(oppName);
			//tells the user a villain has appeared
			tell = new JLabel("A "+oppName+" villain has appeared", SwingConstants.CENTER);
			tell.setBounds(100, 70, 500, 20);
		}
		else{ //if the opponent is a hero
			oppName = opp.getHName(oppNum);
			//tells the user a hero has appeared
			tell = new JLabel("A "+oppName+" hero has appeared", SwingConstants.CENTER);
			tell.setBounds(100, 70, 500, 20);
		}
		//opponent's image
		JLabel Him = new JLabel(Oimg);
		Him.setBounds(((700-Oimg.getIconWidth())/2), 110, Oimg.getIconWidth(), Oimg.getIconHeight());
		//next button
		next = new JButton(arrow1);
		next.setBounds(620, 300, 50, 50);
		button(next);
		next.addActionListener(this);
		
		add(tell);
		add(Him);
		add(next);
		setResizable(false);
		setVisible(true);
		setLocation(330, 120);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
	}
	//displays the hero choosing screen (for the user)
	public void hero_choose(){
		setSize(700, 400);
		getContentPane().setLayout(null);
		getContentPane().setBackground(b);
		//tells the user to type in the hero they want to use
		JLabel choose = new JLabel("Type in a hero you want to use in battle:", SwingConstants.CENTER);
		choose.setBounds(200, 100, 300, 20);
		//information (about how to search a hero)
		JLabel search = new JLabel("<html>IMPORTANT:<br/>"+"- Use the CLEAR button to delete your entry<br/>"+"- Use the ARROW button to begin your battle<br/>"
									+"- Use the roster page to help type in the hero's name", SwingConstants.CENTER);
		search.setBounds(200, 110, 300, 100);
		//next button
		nextC = new JButton(arrow1);
		nextC.setBounds(620, 300, 50, 50);
		button(nextC);
		nextC.addActionListener(this);
		//clear button to clear the text entered
		clear = new JButton("Clear");
		clear.setBounds(300, 210, 100, 30);
		clear.addActionListener(this);
		//Checking message
		chk = new JLabel("",SwingConstants.CENTER);
		chk.setBounds(100, 300, 500, 20);
		//text field for user to enter hero name
		tf1 = new JTextField(15);
		tf1.addKeyListener(this);
		tf1.setBounds(300, 250, 100, 20);
		//tells the user when they can use the charge button
		JLabel ifn = new JLabel("IMPORTANT --> You can only use the charge attack when the counter reaches 6", SwingConstants.CENTER);
		ifn.setBounds(125, 300, 450, 20);
		
		add(choose);
		add(tf1);
		add(search);
		add(nextC);
		add(clear);
		add(chk);
		add(ifn);
	}
	//displays the battle screen
	public void battle_screen(){
		setSize(700, 500);
		getContentPane().setLayout(null);
		getContentPane().setBackground(b);
		//user's hero name
		JLabel u = new JLabel(uName, SwingConstants.CENTER);
		u.setBounds(150, 70, 100, 20);
		//user's hero image
		JLabel Uim = new JLabel(Uimg);
		Uim.setBounds(((700-Uimg.getIconWidth())/4), 90, Uimg.getIconWidth(), Uimg.getIconHeight());
		//user's health bar
		Uhb = new JPanel();
		Uhb.setBackground(Color.GREEN);
		Uhb.setBounds(100, 260, 230, 20);
	
		//opponent's hero name
		JLabel o = new JLabel(oppName, SwingConstants.CENTER);
		o.setBounds(400, 70, 200, 20);
		//opponent's hero image
		JLabel Him = new JLabel(Oimg);
		Him.setBounds((3*(700-Oimg.getIconWidth())/4), 90, Oimg.getIconWidth(), Oimg.getIconHeight());
		//opponent's health bar
		Ohb = new JPanel();
		Ohb.setBackground(Color.GREEN);
		Ohb.setBounds(400, 260, 230, 20);
		
		//Attack buttons
		//short
		shortm = new JButton("Short Attack");
		shortm.setBounds(140, 300, 150, 30);
		shortm.addActionListener(this);
		//charge
		chargem = new JButton("Charge Attack");
		chargem.setBounds(140, 340, 150, 30);
		chargem.addActionListener(this);
		//charge attack counter
		chcount = new JLabel("Charge Attack Counter: "+ch);
		chcount.setBounds(420, 350, 150, 20);
		//charge attack message
		charge = new JLabel("", SwingConstants.CENTER);
		charge.setBounds(100, 400, 500, 20);
		//victory message
		vic = new JLabel("", SwingConstants.CENTER);
		vic.setFont(sta);
		vic.setBounds(100, 320, 500, 50);
		//next button 
		//user won --> to hero adding screen/ villain presenting screen
		//user lost --> back to the map
		nextB = new JButton(arrow1);
		nextB.setBounds(550, 320, 50, 50);
		nextB.setVisible(false);
		button(nextB);
		nextB.addActionListener(this);
		
		add(u);
		add(o);
		add(Uim);
		add(Him);
		add(chcount);
		add(vic);
		add(Uhb);
		add(Ohb);
		add(charge);
		add(shortm);
		add(chargem);
		add(nextB);
	}
	//hero presentation screen
	public void hero_present(){
		setSize(700, 400);
		getContentPane().setLayout(null);
		getContentPane().setBackground(b);
		//asks the user if they want this hero in their roster
		JLabel choose = new JLabel("Do you want this hero, if yes click Yes", SwingConstants.CENTER);
		choose.setBounds(100, 80, 500, 20);
		//opponent's hero image
		JLabel Him = new JLabel(Oimg);
		Him.setBounds(((700-Oimg.getIconWidth())/2), 130, Oimg.getIconWidth(), Oimg.getIconHeight());
		//tells user the stats of the hero added to their roster
		JLabel stat = new JLabel("<html>Attack: "+Oatt+"<br/>Defense: "+Odef+"<br/>Stamina: "+Osta);
		stat.setBounds(100, 170, 150, 50);
		//tells user the stats they have gained
		JLabel stat1 = new JLabel("<html>Gained:<br/>"+"XP: 100<br/>"+"Candies: 1<br/>"+"Coin: $25");
		stat1.setBounds(540, 160, 150, 70);
		//yes button to add hero into roster and display map
		yes = new JButton("Yes");
		yes.setBounds(245, 310, 100, 30);
		yes.addActionListener(this);
		//no button to display map
		no = new JButton("No");
		no.setBounds(355, 310, 100, 30);
		no.addActionListener(this);
	
		add(choose);
		add(Him);
		add(stat);
		add(stat1);
		add(yes);
		add(no);
	}
	//villain presentation screen
	public void villain_present(){
		setSize(700, 400);
		getContentPane().setLayout(null);
		getContentPane().setBackground(b);
		//tells the user how to get back to the map
		JLabel choose = new JLabel("Click the arrow button to go back to the map", SwingConstants.CENTER);
		choose.setBounds(100, 80, 500, 20);
		//opponent's hero image
		JLabel Him = new JLabel(Oimg);
		Him.setBounds(((700-Oimg.getIconWidth())/2), 130, Oimg.getIconWidth(), Oimg.getIconHeight());
		//tells user the stats of the hero added to their roster
		JLabel stat = new JLabel("<html>Attack: "+Oatt+"<br/>Defense: "+Odef+"<br/>Stamina: "+Osta);
		stat.setBounds(100, 170, 150, 50);
		//tells user the stats they have gained
		JLabel stat1 = new JLabel("<html>Gained:<br/>"+"XP: 150<br/>"+"Candies: 1<br/>"+"Coin: $35");
		stat1.setBounds(540, 160, 150, 70);
		//next (Choose) button
		nextV = new JButton(arrow1);
		nextV.setBounds(620, 300, 50, 50);
		button(nextV);
		nextV.addActionListener(this);
		
		add(choose);
		add(Him);
		add(stat);
		add(stat1);
		add(nextV);
	}
	
	//performs the attack for the opponent
	public void oppAttack(){
		double a1;
		double a2;
		double a3;
		//opponent's attack after user's short attack
		if(Ostab <= 0){ //if opponent has no health after the short attack
			//everything disappears and the next button is displayed
			shortm.setVisible(false);
			chargem.setVisible(false);
			chcount.setVisible(false);
			charge.setVisible(false);
			nextB.setVisible(true);
			vic.setText("YOU WON"); //displays that the user has won
		}
		else{ //if opponent still has health
			//sets up the opponents attack value
			if(map[r][c] < 28){ //checks if the opponent is a hero
				a1 = Oatt*.30; //30% of Oatt
				a2 = (Oatt*.20)*(Udef/100.00); //minus the Udef/100
				a3 = a1 - a2;
			}
			else{ //checks if the opponent is a villain
				a1 = Oatt*.40; //40% of Oatt
				a2 = (Oatt*.20)*(Udef/100.00); //minus the Udef/100
				a3 = a1 - a2;
			}
			Ustab = Ustab - a3; //subtracts from user's health
			Uhb.setBounds(100, 260, ((int)((Ustab/(Usta*3))*230)), 20); //updates the user's health bar

			//if user has no health left
			if(Ustab <= 0){
				shortm.setVisible(false);
				chargem.setVisible(false);
				chcount.setVisible(false);
				charge.setVisible(false);
				nextB.setVisible(true);
				vic.setText("YOU LOST"); //displays that the user has lost
			}
		}
	}
	public void actionPerformed(ActionEvent event){
		//if the next button is pressed the hero choosing screen is displayed
		if(event.getSource() == next){
			clear();
			hero_choose(); //calls the hero choosing method
		}
		//if the clear button is pressed the string holding the entry will be deleted along with the text inside the text field
		if(event.getSource() == clear){
			heroU = "";
			tf1.setText("");
		}
		//if the nextC button is pressed the hero name entered will be checked to see if it correct or not/ if the user has that hero or not
		if(event.getSource() == nextC){
			int i = user.BHero(heroU); //checks the entry
			if(i == 0){ //if entry is wrong
				chk.setText("No hero was found. Try entering another hero."); //displays this message
			}
			else{ //if entry is correct
				chk.setText("Hero was found."); //displays this text
				clear();
				uNum = user.getUHero(heroU); //tell hero class which hero user is using
				uName = user.getUHeroName(uNum); //gets the official name of the hero user is using
				//getting opponent's stats
				Oatt = opp.getHattack();
				Odef = opp.getHdefense();
				Osta = opp.getHstamina();
				Ostab = Osta*3; //increase the health used in game by 3 time for longer battles 
				//getting user's stats
				Uatt = user.getUattack(uNum);
				Udef = user.getUdefense(uNum);
				Usta = user.getUstamina(uNum);
				Ustab = Usta*3; //increases the health used in game by 3 times for longer battle
				Uimg = user.getHeroImage(uNum);
				
				battle_screen(); //calls the battle screen method
			}
		}
		//if nextB button is pressed and either the user or opponent has no health left
		if(event.getSource() == nextB && (Ustab <= 0 || Ostab <= 0)){
			if(Ostab <= 0){ //user wins (hero)
				clear();
				if(map[r][c] <= 27){ //if opponent is a hero
					k.addXp(100); //adds 100 points to the xp total
					k.addCandies(); //adds to the candies total
					k.addCoin(25);
					k.addHeroW(); //adds to the hero battle won total
					hero_present(); //presents the user with the hero (calls the method)
				}
				else{ //if opponent is a villain
					k.addXp(150); //adds 150 points to the xp total
					k.addCandies(); //adds to the candies total
					k.addCoin(35);
					k.addVillainW(); //adds to the hero battle won total
					villain_present(); //calls the villain present method
				}
			}
			else{ //user loses
				dispose();
				new Map(r-7,c-13, map); //reprints the map
				k.addXp(20); //adds 20 points to the xp total
			}
		}
		//if yes button is pressed the hero will be added to the user's roster or will replace the older hero in the roster already 
		//map will be reprinted
		if(event.getSource() == yes){
			dispose();
			//adds the hero's stats 
			user.addHero(oppNum);
			user.addAtt(oppNum, Oatt);
			user.addDef(oppNum, Odef);
			user.addSta(oppNum, Osta);
			new Map(r-7,c-13, map); //reprints the map
		}
		//if no button is pressed the hero will not be added to the user's roster and map will be reprinted
		if(event.getSource() == no){
			dispose();
			new Map(r-7,c-13, map); //reprints the map
		}
		//if the nextV button is pressed the map will be reprinted 
		if(event.getSource() == nextV){
			dispose();
			new Map(r-7,c-13, map); //reprints the map
		}
		//if the shortm button is pressed and both the user and opponent still have health
		if(event.getSource() == shortm && Ustab > 0 && Ostab > 0){
			ch++; //adds one to short attack counter
			chcount.setText("Charge Attack Counter: "+ch); //updates the charge counter
			//sets up user's attack value
			double a1 = Uatt*.20; //20% of Uatt
			double a2 = (Uatt*.20)*(Odef/100.00); //minus the Odef/100
			double a3 = a1 - a2;
			Ostab = Ostab - a3; //subtracting from opponent's health
			Ohb.setBounds(400, 260, ((int)((Ostab/(Osta*3))*230)), 20); //updates the opponent's health bar
			
			oppAttack(); //calls the opponent attack method
		}
		
		if(event.getSource() == chargem && Ustab > 0 && Ostab > 0){
			double a1 = 0;
			double a2 = 0;
			double a3 = 0;
			if(ch >= 6){ //if the counter has reached 6
				ch = 0; //resets the short attack counter
				chcount.setText("Charge Attack Counter: "+ch);
				//subtracts from the opponents health 
				//short attack = 45% of Uatt minus the Odef/100 
				a1 = Uatt*.45; //20% of Uatt
				a2 = (Uatt*.20)*(Odef/100.00); //minus the Odef/100
				a3 = a1 - a2;
				Ostab = Ostab - a3; 
				Ohb.setBounds(400, 260, ((int)((Ostab/(Osta*3))*230)), 20); //displays the opponent's health bar
			}
			else{ //if the counter hasn't reached 6
				charge.setText("Can't use the charge attack yet"); //diplay this message
			}
			oppAttack(); //calls the opponent attack method
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
