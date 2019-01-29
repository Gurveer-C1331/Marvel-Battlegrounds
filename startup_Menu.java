package Game;
/* Gurveer Singh Chahal  
 * Dec. 19, 2018
 * Startup Menu
 */
import javax.swing.*;  
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class startup_Menu extends JFrame implements ActionListener{

	private JButton start, instruction; //start game button, instructions displaying button
	protected JButton back; //going back button
	private JButton setting; //displaying setting button
	//colour option buttons
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	//logo image (Marvel BattleGrounds Logo)
	ImageIcon im1 = new ImageIcon("Logo.png");
	Image marv1 = im1.getImage();
	Image marv2 = marv1.getScaledInstance(225, 152, java.awt.Image.SCALE_SMOOTH);
	ImageIcon im2 = new ImageIcon(marv2);
	
	//next arrow image
	ImageIcon arrow = new ImageIcon("arrow.png");
	Image a1 = arrow.getImage();
	Image a2 = a1.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	ImageIcon arrow1 = new ImageIcon(a2);
	//back arrow image
	ImageIcon backa = new ImageIcon("back.png");
	Image ba1 = backa.getImage();
	Image ba2 = ba1.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	ImageIcon backa1 = new ImageIcon(ba2);

	//String value for the instructions text
	protected static String line;
	//fonts
	Font set = new Font("Headliner NO. 45", Font.PLAIN, 30);
	Font sta = new Font("Headliner NO. 45", Font.PLAIN, 45);
	Font col = new Font("Golbold", Font.PLAIN, 20);
	//colours for background (colour options)
	public Color lred = new Color(255,128,128);
	public Color lblue = new Color(205,255,251);
	public Color lyel = new Color(255,255,138);
	//represents the main colour choice of the whole game
	Color m;

	//Constructor
	//pre: String value
	public startup_Menu(String l){
		line = l; //takes in the string value from the main
		startup(); //calls the startup menu method
	}
	//Displays the start menu of the game
	public void startup(){
		setSize(700, 400);
		getContentPane().setLayout(null);
		
		//Sart button to start the game
		start = new JButton("Start");
		start.setFont(sta);
		start.setBounds(285, 205, 130, 45);
		start.addActionListener(this);
		button(start);
		//Instruction button to display the instructions
		instruction = new JButton("Instructions");
		instruction.setFont(set);
		instruction.addActionListener(this);
		instruction.setBounds(250, 250, 200, 30);
		button(instruction);
		//Setting button to display the settings 
		setting = new JButton("Settings");
		setting.setFont(set);
		setting.setBounds(0, 320, 120, 36);
		setting.addActionListener(this);
		button(setting);

		//jlabel containing the game's logo
		JLabel logo = new JLabel(im2);
		logo.setBounds(238, 20, 225, 152);

		add(logo);
		add(start);
		add(instruction);
		add(setting);
		
		setVisible(true);
		setResizable(false);
		setLocation(330, 120); //positions the jframe on the computer screen
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
	}
	//method removes the button's default background (parameters: JButton)
	//pre: JButton
	public void button(JButton b){
		b.setOpaque(false);
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
		b.setForeground(Color.BLACK);
	}
	//Displays the instructions panel
	public void instructions(){
		setSize(700, 510);
		//Back button to go back to the main menu
		back = new JButton(backa1);
		back.setFont(set);
		back.setBounds(325, 420, 50, 50);
		back.addActionListener(this);
		button(back);
		
		//instructions display
		JLabel in = new JLabel(line);
		in.setBounds(70, 0, 600, 430);
		
		add(in);
		add(back);
	
	}
	//Displays the setting panel
	public void settings(){
		setSize(700,400);
		//Back button to go back to the main menu
		back = new JButton(backa1);
		back.setFont(set);
		back.setBounds(325, 310, 50, 50);
		back.addActionListener(this);
		button(back);
		//asks the user to pick a background colour
		JLabel ask = new JLabel("Pick a background colour:");
		ask.setBounds(277, 85, 150, 30);
		//background buttons 
		//Light Red
		b1 = new JButton("Light Red");
		b1.setBounds(290, 120, 120, 30);
		b1.addActionListener(this);
		//Light Blue
		b2 = new JButton("Light Blue");
		b2.setBounds(290, 155, 120, 30);
		b2.addActionListener(this);
		//Light Yellow
		b3 = new JButton("Light Yellow");
		b3.setBounds(290, 190, 120, 30);
		b3.addActionListener(this);
		//White
		b4 = new JButton("White");
		b4.setBounds(290, 225, 120, 30);
		b4.addActionListener(this);
		//set the background of the buttons to white
		b1.setBackground(Color.WHITE);
		b2.setBackground(Color.WHITE);
		b3.setBackground(Color.WHITE);
		b4.setBackground(Color.WHITE);

		add(ask);
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(back);
	}
	//method go into the gameplay class to start the game
	public void gameplay(){
		new Gameplay(m); //calls the gameplay class
		dispose();//deletes the frame
	}
	//clears all the components of the current jpanel
	public void clear(){
		getContentPane().removeAll();
		getContentPane().repaint();
	
	}
	public void actionPerformed(ActionEvent event){
		//if the start button is pressed the game will move into the gameplay phase
		if(event.getSource() == start){
			clear();
			gameplay();
		}
		//if the instructions button is pressed the instructions will appear
		if(event.getSource() == instruction){
			clear();
			instructions();
		}
		//if the back button is pressed the game will go back to the start menu
		if(event.getSource() == back){
			clear();
			startup();
		}
		//if the setting button is pressed the settings will appear
		if(event.getSource() == setting){
			clear();
			settings();
		}
		//if one of the background colour button is pressed the background colour will be set
		if(event.getSource() == b1){
			getContentPane().setBackground(lred);
			m = lred;
		}
		if(event.getSource() == b2){
			getContentPane().setBackground(lblue);
			m = lblue;
		}
		if(event.getSource() == b3){
			getContentPane().setBackground(lyel);
			m = lyel;
		}
		if(event.getSource() == b4){
			getContentPane().setBackground(Color.WHITE);
			m = Color.WHITE;
		}
		
	}
}
