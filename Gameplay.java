package Game;

import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;

public class Gameplay extends startup_Menu implements ActionListener, KeyListener{
	//buttons for next, instructions, stats, backpack, roster, hero #1, hero #2, hero #3
	private JButton next, h1, h2, h3, clear;
	protected static Color b = null; //color object that holds the color set from the startup_Menu method
	protected JTextField n; //text field for the user to enter their name
	protected String name = ""; //String value that holds the name of the user
	
	//Falcon Image
	ImageIcon falcon = new ImageIcon("Falcon_1.png");
	Image f1 = falcon.getImage();
	Image f2 = f1.getScaledInstance(229, 197, java.awt.Image.SCALE_SMOOTH);
	ImageIcon falcon2 = new ImageIcon(f2);
	//Hulk Image
	ImageIcon hulk = new ImageIcon("Hulk_1.png");
	Image hu1 = hulk.getImage();
	Image hu2 = hu1.getScaledInstance(200, 218, java.awt.Image.SCALE_SMOOTH);
	ImageIcon hulk2 = new ImageIcon(hu2);
	//Star-Lord
	ImageIcon starl = new ImageIcon("StarLord_1.png");
	Image s1 = starl.getImage();
	Image s2 = s1.getScaledInstance(154, 198, java.awt.Image.SCALE_SMOOTH);
	ImageIcon starl2 = new ImageIcon(s2);
	
	Stats a; //the stats object (used for the whole game) that contains stats for the characters
	UStats k = new UStats(); //user stat object (used for the whole game) that contains stats for the user

	//Constructor 
	//pre: color object 
	public Gameplay(Color m){
		super(line);
		b = m; //takes in the color from startup_Menu
		clear(); //calls clear method
		name(); //calls the name method
	}
	//displays the screen for the user to input their name
	public void name(){
		setSize(700, 400);
		getContentPane().setLayout(null);
		getContentPane().setBackground(b);
		//asks the user to input their name
		JLabel name = new JLabel("Enter your name:", SwingConstants.CENTER);
		name.setBounds(250, 150, 200, 20);
		//text field to enter name
		n = new JTextField(15);
		n.setBounds(250, 180, 200, 20);
		n.addKeyListener(this);
		//next button to go to the next screen (arrow next button)
		next = new JButton(arrow1); 
		next.setBounds(620, 300, 50, 50);
		button(next);
		next.addActionListener(this);
		//clear button to delete what the user typed if they made a mistake
		clear = new JButton("Clear"); 
		clear.setBounds(300, 220, 100, 30);
		clear.setBackground(Color.WHITE);
		clear.addActionListener(this);
		
		add(name);
		add(n);
		add(clear);
		add(next);
		setResizable(false);
		setVisible(true);
		setLocation(330, 120); //positions the jframe on the computer screen
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
	}
	//displays the choosing first hero screen (user will choose one of three heroes as their first hero)
	public void firsth(){
		setSize(700, 400);
		getContentPane().setLayout(null);
		getContentPane().setBackground(b);
		//tells the user to pick one of the three heroes
		JLabel fh = new JLabel("Choose one of these three heroes:");
		fh.setBounds(255, 50, 200, 20);
		//Hero #1 Button (Falcon)
		h1 = new JButton(falcon2);
		h1.setBounds(40, 100, 229, 197);
		h1.addActionListener(this);
		button(h1);
		//Hero #2 Button (Hulk)
		h2 = new JButton(hulk2);
		h2.setBounds(285, 100, 200, 218);
		h2.addActionListener(this);
		button(h2);
		//Hero #3 Button (Star Lord)
		h3 = new JButton(starl2);
		h3.setBounds(500, 100, 154, 198);
		h3.addActionListener(this);
		button(h3);

		add(fh);
		add(h1);
		add(h2);
		add(h3);
	}
	
	//creates the map by calling the map class
	//pre: Stats object that will be used in the map class
	public void map(Stats a){
		new Map(a, k); //calling the map class
		dispose(); //deletes the frame
	}
	
	public void actionPerformed(ActionEvent event){ 
		//if the next button is pressed the first hero choosing screen is displayed
		if(event.getSource() == next){
			clear();
			firsth(); //calls the first hero method
		}
		//if the clear button is pressed the text entered will be deleted if the user made a mistake
		if(event.getSource() == clear){
			name = ""; //resets the name string
			n.setText(""); //clears anything typed in the text field
		} 
		//if the h1 button is pressed the falcon hero will be the user's first hero and the game will start
		//it will create the Stats object (for characters), it will call the map method, and will create the sidebar
		if(event.getSource() == h1){
			clear();
			a = new Stats("Falcon"); //creates a hero object
			map(a); //calls the map method with the new Stats object created
			new SideBar(name, a, k); //calls the sidebar class to display the sidebar
		}
		//if the h1 button is pressed the hulk hero will be the user's first hero and the game will start
		//it will create the Stats object (for characters), it will call the map method, and will create the sidebar
		if(event.getSource() == h2){
			clear();
			a = new Stats("Hulk"); //creates a hero object
			map(a); //calls the map method
			new SideBar(name, a, k); //calls the sidebar class
		}
		//if the h1 button is pressed the star lord hero will be the user's first hero and the game will start
		//it will create the Stats object (for characters), it will call the map method, and will create the sidebar
		if(event.getSource() == h3){
			clear();
			a = new Stats("Star Lord"); //creates a hero object
			map(a); //calls the map method 
			new SideBar(name, a, k); //calls the sidebar class 
		}
	}
	//method is responsible for anything typed in the text field 
	//pre: the int value of the key pressed, char value of the key pressed, the string value containing the text entered
	//post: returns the string value 
	public String text(int i, char k, String n){
		//anything besides the caps lock, shift, backspace or enter is pressed will be added to the string value
		if(i != KeyEvent.VK_CAPS_LOCK && i != KeyEvent.VK_SHIFT && i != KeyEvent.VK_BACK_SPACE && i != KeyEvent.VK_ENTER){
			n = n + k;
		}
		//if backspace is pressed the last char value of the string will be deleted
		else if(k == KeyEvent.VK_BACK_SPACE && n.length() > 0){
			n = n.substring(0, n.length()-1);
		}
		return n;
	}
	public void keyPressed(KeyEvent event){
		char k = event.getKeyChar();
		int i = event.getKeyCode();
		name = text(i, k, name); //calls the text class
	}
	public void keyTyped(KeyEvent event){
		
	}
	public void keyReleased(KeyEvent event){
	
	}
}
