package Roger;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Interface extends JFrame{
	
	static int MAZESIZE = 10;
	static int column = MAZESIZE;
	static int count = 0;
	static PathButton [] board = new PathButton[MAZESIZE*MAZESIZE];
	JPanel P = new JPanel();	
		
	public Interface()
	{		
		this.setTitle("968212 JIE ZHOU  BATTLESHIP");

		this.setBounds(500, 25, 900, 900);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setContentPane(P);
		P.setLayout(new GridLayout(MAZESIZE,MAZESIZE));

		for(int i = 0;i < (MAZESIZE*MAZESIZE); i++)
		{
			board[i] = new PathButton(i, board);
			this.getContentPane().add(board[i]);
		}
			
		JMenuBar MenuBar = new JMenuBar();
		JMenu set = new JMenu("Set Ships");
		JMenu run = new JMenu("Run");
		MenuBar.add(set);
		JMenuItem aircraft = new JMenuItem("Aircraft");
		JMenuItem battleship = new JMenuItem("Battleship");
		JMenuItem submarine = new JMenuItem("Submarine");
		JMenuItem cruiser = new JMenuItem("Cruiser");
		JMenuItem patrol = new JMenuItem("Patrol");
		set.add(aircraft);
		set.add(battleship);
		set.add(submarine);
		set.add(cruiser);
		set.add(patrol);
		MenuBar.add(run);
		JMenuItem start = new JMenuItem("Start Game");
		JMenuItem reset = new JMenuItem("Reset");
		run.add(start);
		run.add(reset);
		this.setJMenuBar(MenuBar);
		
		aircraft.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0;i < (MAZESIZE*MAZESIZE); i++)
				{
					board[i].setSetting(true, 5);
				}
				
			}				
		});
		battleship.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0;i < (MAZESIZE*MAZESIZE); i++)
				{
					board[i].setSetting(true, 4);
				}
				
			}				
		});
		submarine.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0;i < (MAZESIZE*MAZESIZE); i++)
				{
					board[i].setSetting(true, 3);
				}
				
			}				
		});
		cruiser.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0;i < (MAZESIZE*MAZESIZE); i++)
				{
					board[i].setSetting(true, 3);
				}
				
			}				
		});
		patrol.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0;i < (MAZESIZE*MAZESIZE); i++)
				{
					board[i].setSetting(true, 2);
				}
				
			}				
		});
		start.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0;i < (MAZESIZE*MAZESIZE); i++)
				{
					System.out.print(board[i].checkShip()+" ");
					if(i%MAZESIZE == 9)
						System.out.println();
						
				}
//				new Enemy();
				
			}				
		});
		reset.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0;i < (MAZESIZE*MAZESIZE); i++)
				{
					board[i].reset();
				}
				
			}				
		});
		
		this.setVisible(true);
	}
	
    class Enemy extends JFrame
    {
    	PathButton [] enemyBoard = new PathButton[MAZESIZE*MAZESIZE];
    	JPanel P2 = new JPanel();
 	
    	public Enemy()
    	{		
    		this.setTitle("968212 JIE ZHOU  ENEMYBOARD");

    		this.setBounds(500, 25, 900, 900);
    		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    		this.setContentPane(P2);
    		P2.setLayout(new GridLayout(MAZESIZE,MAZESIZE));
    		this.setVisible(true);
    		for(int i = 0;i < (MAZESIZE*MAZESIZE); i++)
    		{
    			enemyBoard[i] = new PathButton(i, enemyBoard);
    			this.getContentPane().add(enemyBoard[i]);
    		}
    	}
    }

//	public static void main(String arg[])
//	{
//		new Interface();
//	}


}
