package Game;
import java.awt.Color; 
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
public class Map extends Gameplay implements ActionListener, KeyListener{
	
	private JButton next;//used in the pop up when a hero/villain appears 
	protected int row = 1014; //row of the map
	protected int column = 126; //column of the map
	int [][] map = new int [row][column]; //number filled map
	//grass image
	ImageIcon grass = new ImageIcon("grass.png"); 
	Image g1 = grass.getImage();
	Image g2 = g1.getScaledInstance(26, 26, java.awt.Image.SCALE_SMOOTH);
	ImageIcon grass2 = new ImageIcon(g2);
	//bush image
	ImageIcon bush = new ImageIcon("bush.png");
	Image b1 = bush.getImage();
	Image b2 = b1.getScaledInstance(26, 26, java.awt.Image.SCALE_SMOOTH);
	ImageIcon bush2 = new ImageIcon(b2);
	//water image
	ImageIcon water = new ImageIcon("water.png");
	Image w1 = water.getImage();
	Image w2 = w1.getScaledInstance(26, 26, java.awt.Image.SCALE_SMOOTH);
	ImageIcon water2 = new ImageIcon(w2);
	//shop image
	ImageIcon shop = new ImageIcon("shop.png");
	Image s1 = shop.getImage();
	Image s2 = s1.getScaledInstance(26, 26, java.awt.Image.SCALE_SMOOTH);
	ImageIcon shop2 = new ImageIcon(s2);
	
	ImageIcon [][] imap = new ImageIcon[row][column]; //image filled map
	JLabel position = new JLabel();//Indicates where the user is on the map
	JPanel mapp = new JPanel(new GridLayout(14, 0,0,0));//JPanel for the map with a grid layout for the tiles
	//used to track the user's position on the map
	int r = 0;
	int c = 0;
	static Stats a; //stats object
	static UStats k; //user stats object
	
    //**FOR THIS PART OF THE GAME I USED THE MAP & TILE EXAMPLE ON MOODLE TO HELP ME MAKE THE MAP & HAVE IT MOVE AROUND**
    
	//constructor 
	//pre: stats object, Ustats object
	public Map(Stats a1, UStats k1){
		super(b);
		a = a1; //takes in the stats object
		k = k1; //takes in the ustats object
		clear(); //clears all the components
		Map_display(); //calls the main displaying map method (new map making)
	}
	//constructor used when battles are done
	//pre: two int value, int 2d array
	public Map(int y, int x, int [][] map1){
		super(b);
		clear();
		//takes in the current position of the user
		r = y; 
		c = x;
		map = map1;
		map[r+7][c+13] = 0; //removes the character from the map the user has just battle
		Map_d(); //calls the displaying map method 
	}
	//constructor used after going into the shops (doesn't make the shops disappear)
	//pre: two int value, int 2d array, int value to help override the other constructor
	public Map(int y, int x, int [][] map1, int i){
		super(b);
		clear();
		//takes in the current position of the user
		r = y;
		c = x;
		map = map1;
		Map_d(); //calls the displaying map method
	}

	//creates the map for the first time and displays the Image map on the frame
	public void Map_display(){
		setSize(700, 400);
		createMap(); //creating the number map
		create_Map(imap, mapp); //creating the image map and printing it on a JPanel
		mapp.setBounds(-5, 6, 700, 360);
		mapp.addKeyListener(this);
		mapp.setFocusable(true);
		//red square representing the character on the map
		position.setBackground(Color.red);
		position.setOpaque(true);
		position.setBounds(345,184,26,26);
		
		add(position);
		add(mapp);
		setResizable(false);
		setVisible(true);
		setLocation(330, 120);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
	}
	//recreates the image map using the original number map
	public void Map_d(){
		setSize(700, 400);
		create_Map(imap, mapp); //recreating the image map and printing it on a JPanel

		mapp.setBounds(-5, 6, 700, 360);
		mapp.addKeyListener(this);
		mapp.setFocusable(true);
		
		//red square
		position.setBackground(Color.red);
		position.setOpaque(true);
		position.setBounds(345,184,26,26);
				
		add(position);
		add(mapp);
		setResizable(false);
		setVisible(true);
		setLocation(330, 120);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
	}

	//creates the image map and displays it on the JPanel shown in the JFrame
	//pre: ImageIcon 2d array and JPanel
	public void create_Map(ImageIcon [][] imap, JPanel mapp){
		
		for(int r = 0; r < row; r++){
			for(int c = 0; c < column; c++){
				if(map[r][c] == 1){ //if element = 1
					imap[r][c] = bush2; //bush is added
				}
				else if(map[r][c] == 200){ //if element = 200
					imap[r][c] = water2; //water is added
				}
				else if(map[r][c] == 2){ //if element = 2
					imap[r][c] = shop2; //shop is added
				}
				else{ 
					imap[r][c] = grass2; //grass is added
				}
			}
		}
		//row --> 14; column --> 26
		//Printing tiles to fit the JPanel
		for(int i = r; i < r + 14; i ++){
			for(int k = c; k < c + 26; k ++){
				mapp.add(new JLabel(imap[i][k]));
			}
		}
	}
	JFrame f = new JFrame();
	//displays the dialog box to the user
	public void dia_Battle(int y, int x){
		f.setSize(700, 200);
		getContentPane().setLayout(null);
		getContentPane().setBackground(b);
		clear();
		JLabel tell;
		if(map[y][x] < 28){
			tell = new JLabel("A hero has appeared", SwingConstants.CENTER);
		}
		else{
			tell = new JLabel("A villain has appeared", SwingConstants.CENTER);
		}
		tell.setBounds(250, 70, 200, 20);
		//next button
		next = new JButton(arrow1);
		next.setBounds(325, 120, 50, 50);
		button(next);
		next.addActionListener(this);
		
		add(tell);
		add(next);
		f.add(getContentPane());
		f.setResizable(false);
		f.setVisible(true);
		f.setLocation(330, 250);
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
		
	}
	//checks the current position of the character
	//pre: two int values
	public void checkpos(int y, int x){
		if(map[y][x] == 2){ //if current position is equal to 2 
			new Shop(k, y, x, map); //calls the shop call to go inside the shop
			dispose(); //removes the frame
		}
		
		else if(map[y][x] >= 3 && map[y][x] <= 27){ //if current position is equal (between 3 and 27)
			dia_Battle(y, x);
		}
			
		else if(map[y][x] >= 28 && map[y][x] <= 47){ //if current position is equal (between 28 and 47)	
			dia_Battle(y, x);
		}
		
		else{ //otherwise reprints the map if current location of the user is empty
			mapp.removeAll();//Remove all the tiles	
			//Printing tiles to fit the JPanel using the new values of r and c
			for(int i = r; i < r + 14; i ++){
				for(int k = c; k < c + 26; k ++){
					mapp.add(new JLabel(imap[i][k]));
				}
			}
			setVisible(true);
			repaint();
		}
	}
	public void keyPressed(KeyEvent event) {
		int key = event.getKeyCode();
		if(key == KeyEvent.VK_RIGHT){
			//Restrictions on where the sprite can go 
			//if character's c+1 position is not touching the water and the c+1 location doesn't contain a bush
			if(c+1 <= column-27 && map[r+7][c+13+1] != 1){
				c++; 
			}
		}
		//Repeated for other 3 directions
		if(key == KeyEvent.VK_LEFT){
			//if character's c-1 position is not touching the water and the c-1 location doesn't contain a bush
			if(c-1 >= 0 && map[r+7][c+13-1] != 1){
				c--;
			}
		}
		if(key == KeyEvent.VK_DOWN){
			//if character's r+1 position is not touching the water and the r+1 location doesn't contain a bush
			if(r+1 <= row-15 && map[r+7+1][c+13] != 1){
				r++;
			}
		}
		if(key == KeyEvent.VK_UP){
			//if character's r-1 position is not touching the water and the r-1 location doesn't contain a bush
			if(r-1 >= 0 && map[r+7-1][c+13] != 1){
				r--;
			}	
		}
		checkpos(r+7, c+13); //calls the check position method based on the new value of r and c

	}
	public void keyReleased(KeyEvent event) {

	}
	public void keyTyped(KeyEvent event) {
	}
	
	public void actionPerformed(ActionEvent event){
		//if the next button is pressed the pop up and the map will be closed and the battle screen will play
		if(event.getSource() == next){
			new Battle_D(r+7, c+13, map, a, k); //calls the battle class to start the hero battle
			dispose();
			f.dispose();
		}
	}
	//creates the map that is filled with numbers
	//0 --> empty spaces
	//1 --> blocked spaces
	//2 --> shops
	//3 - 27 --> heroes
	//28 - 47 --> villains 
	//200 --> water
	public void createMap(){
		for(int r = 0; r < row; r++){
			for(int c = 0; c < column; c++){
				//creates a border of water around the map (first 7 rows and last 7 rows)
				if((r >= 0 && r <= 6)||(r >= 1007 && r <= 1014)){
					map[r][c] = 200;
				}
				//creates a border of water around the map (first 13 columns and last 13 columns)
				else if((c >= 0 && c <= 12)||(c >= 113 && c <= 126)){
					map[r][c] = 200;
				}
				//creating a 5x5 empty square (top left corner)
				else if(r >= 7 && r <= 12 && c >= 13 && c <= 18){
					map[r][c] = 0;
				}
				//rest of the map is filled with random numbers (o to 100)
				else{
					map[r][c] = (int)(Math.random()*(100-0+1)+0);
				}
			}
		}
		//finalizes the number map
		for(int r = 0; r < row; r++){
			for(int c = 0; c < column; c++){
				//empty spaces if element is between 0 and 61
				if(map[r][c] >= 0 && map[r][c] <= 61){
					map[r][c] = 0;
				}
				//blocked spaces if element is between 62 and 86
				if(map[r][c] >= 62 && map[r][c] <= 86){
					map[r][c] = 1;
				}
				//fill with heroes if element is between 87 and 94
				if(map[r][c] >= 87 && map[r][c] <= 94){
					getHero(r, c); //calls hero method to specify which hero to add
				}
				//fill with villains if element is betwen 95 and 98
				if(map[r][c] >= 95 && map[r][c] <= 98){
					getVillain(r, c); //calls villain method to specify which villain to add
				}
				//fill with shops if element is between 99 and 100
				if(map[r][c] >= 99 && map[r][c] <= 100){
					map[r][c] = 2;
				}
			}
		}
	}
	//adds a number from 3 to 27 to fill the map with heroes
	//pre: two int values
	public void getHero(int r, int c){
		int a = (int)(Math.random()*(25-1+1)+1);
		//Ant-man
		if(a == 1){
			map[r][c] = 3;
		}
		//Black Panther
		if(a == 2){
			map[r][c] = 4;
		}
		//Black Widow
		if(a == 3){
			map[r][c] = 5;
		}
		//Captain America (Classic)
		if(a == 4){
			map[r][c] = 6;
		}
		//Captain America (Winter Soldier)
		if(a == 5){
			map[r][c] = 7;
		}
		//Captain America (WWII)
		if(a == 6){
			map[r][c] = 8;
		}
		//Daredevil
		if(a == 7){
			map[r][c] = 9;
		}
		//Deadpool
		if(a == 8){
			map[r][c] = 10;
		}
		//Doctor Strange
		if(a == 9){
			map[r][c] = 11;
		}
		//Falcon
		if(a == 10){
			map[r][c] = 12;
		}
		//Hulk
		if(a == 11){
			map[r][c] = 13;
		}
		//Iron Man (Mark 42)
		if(a == 12){
			map[r][c] = 14;
		}
		//Iron Man (Mark 5)
		if(a == 13){
			map[r][c] = 15;
		}
		//Iron Man (Avengers)
		if(a == 14){
			map[r][c] = 16;
		}
		//Loki
		if(a == 15){
			map[r][c] = 17;
		}
		//Captain Marvel
		if(a == 16){
			map[r][c] = 18;
		}
		//Nova
		if(a == 17){
			map[r][c] = 19;
		}
		//Punisher
		if(a == 18){
			map[r][c] = 20;
		}
		//Spider-Gwen
		if(a == 19){
			map[r][c] = 21;
		}
		//Spider-Man (Black Suit)
		if(a == 20){
			map[r][c] = 22;
		}
		//Spider-Man (Classic)
		if(a == 21){
			map[r][c] = 23;
		}
		//Spider-Man (Superior)
		if(a == 22){
			map[r][c] = 24;
		}
		//Star-Lord
		if(a == 23){
			map[r][c] = 25;
		}
		//Thor
		if(a == 24){
			map[r][c] = 26;
		}
		//Winter-Soldier
		if(a == 25){
			map[r][c] = 27;
		}
	}
	//adds a number from 28 to 47 to fill the map with villains
	//pre: two int values
	public void getVillain(int r, int c){
		int a = (int)(Math.random()*(20-1+1)+1);
		//Abomination
		if(a == 1){
			map[r][c] = 28;
		}
		//Bullseye
		if(a == 2){
			map[r][c] = 29;
		}
		//Carnage
		if(a == 3){
			map[r][c] = 30;
		}
		//Doctor Doom
		if(a == 4){
			map[r][c] = 31;
		}
		//Doctor Octopus
		if(a == 5){
			map[r][c] = 32;
		}
		//Dormammu
		if(a == 6){
			map[r][c] = 33;
		}
		//Electro
		if(a == 7){
			map[r][c] = 34;
		}
		//Green-Goblin
		if(a == 8){
			map[r][c] = 35;
		}
		//Hela
		if(a == 9){
			map[r][c] = 36;
		}
		//Hydro-Man
		if(a == 10){
			map[r][c] = 37;
		}
		//Kingpin
		if(a == 11){
			map[r][c] = 38;
		}
		//Mysterio
		if(a == 12){
			map[r][c] = 39;
		}
		//Red Skull
		if(a == 13){
			map[r][c] = 40;
		}
		//Shocker
		if(a == 14){
			map[r][c] = 41;
		}
		//Surtur
		if(a == 15){
			map[r][c] = 42;
		}
		//Thanos
		if(a == 16){
			map[r][c] = 43;
		}
		//Ultron
		if(a == 17){
			map[r][c] = 44;
		}
		//Venom
		if(a == 18){
			map[r][c] = 45;
		}
		//Vulture
		if(a == 19){
			map[r][c] = 46;
		}
		//Yellowjacket
		if(a == 20){
			map[r][c] = 47;
		}
	}
}
