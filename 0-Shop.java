package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Shop extends Gameplay implements ActionListener{
	
	JButton buy, back; //buy, back
	JLabel candyA, moneyA, chk; //candy amount, money amount, checking message 
	UStats k; //User stats object
	private int r; //row
	private int c; //col
	private int [][] map; //number map
	
	//constructor
	//pre: UStats object, two int values, int 2d array
	public Shop(UStats k1, int r1, int c1, int [][] map1){
		super(b);
		clear(); //calls the clear method
		k = k1; //takes in the UStats object
		//r and c to later call the map class to help reprint the map
		r = r1; //takes in the row number
		c = c1; //takes in the column number
		map = map1; //takes in the map array
		counter(); //calls the counter method
	}
	
	//displays the shop interface where the user can buy candies
	public void counter(){
		setSize(400, 400);
		getContentPane().setLayout(null);
		getContentPane().setBackground(b);
		//displays total candy amount
		candyA = new JLabel("Candies: "+k.getCandies());
		candyA.setBounds(10, 30, 100, 20);
		//displays total money amount
		moneyA = new JLabel("Coins: "+k.getCoin());
		moneyA.setBounds(10, 50, 100, 20);
		//information for the user before buying 
		JLabel buyI = new JLabel("<html>Click the 'Buy' button to buy the candy<br/>"+
								"<br/>Cost: $100", SwingConstants.CENTER);
		buyI.setBounds(100, 90, 200, 70);
		//Buy button
		buy = new JButton("Buy");
		buy.setBounds(150, 170, 100, 30);
		buy.addActionListener(this);
		//checking message
		chk = new JLabel("", SwingConstants.CENTER);
		chk.setBounds(100, 200, 210, 50);
		//back button
		back = new JButton(backa1);
		back.setBounds(325, 310, 50, 50);
		button(back);
		back.addActionListener(this);
		
		add(candyA);
		add(moneyA);
		add(buyI);
		add(buy);
		add(chk);
		add(back);
		
		setResizable(false);
		setVisible(true);
		setLocation(330, 120);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);

	}
	
	public void actionPerformed(ActionEvent event){
		//if the buy button is pressed and the user has $100 or more they can buy a candy
		if(event.getSource() == buy && k.getCoin() >= 100){
			k.addCoin(-100); //subtracts $100
			k.addCandies(); //adds one candy to the total
			candyA.setText("Candies: "+k.getCandies()); //redisplays candy total
			moneyA.setText("Coins: "+k.getCoin()); //redisplays money total
			k.addXp(20); //adds 20 points to the xp total
		}
		//if the user has less than $100
		if(k.getCoin() < 100){
			chk.setText("ERROR. You don't have enough coins"); //display this message
		}
		//if back button is pressed the map is reprinted
		if(event.getSource() == back){
			dispose(); //deletes the frame
			new Map(r-7,c-13, map, 1); //reprints the map by calling the map class
		}
	}
}
