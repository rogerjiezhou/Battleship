package Roger;


import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;



public class Server {

	    private ServerSocket ss;
	    private Socket s;
	    private DataInputStream in;
	    private DataOutputStream out;
	    private byte [] I = new byte[100];
	    private byte [] O = new byte[100];
	    private static int count = 0;
	    private static int sum = 0;
	    private static int check = 0;
	    private int self = 0;
	    private int feedback;
	    private Interface myBoard;
	    private boolean win = false;

	    public Server(){
	    	
	    	myBoard = new Interface();
	    }
	    
	    
	    public void startServer(){
	        try {
	                ss = new ServerSocket(10008);
	                System.out.println("Server is starting...");
	                JOptionPane.showMessageDialog(null, "Waiting for Client","Server is starting...", JOptionPane.PLAIN_MESSAGE);	
	                s = ss.accept();
	                JOptionPane.showMessageDialog(null, "Connect Successfully","Client Found", JOptionPane.PLAIN_MESSAGE);
	                
	                out = new DataOutputStream(s.getOutputStream());	                
	                in = new DataInputStream(s.getInputStream());
	                in.read(I);
	                
	                Enemy E = new Enemy(out, I);	                	            
	               
	                out.write(O);
	                out.flush();
	                
	                new Thread()
	                {
	            		public void run()
	            		{
	            			 while(!win)
	            	            {
	            	            	try {
	    								feedback = in.readInt();
	    								
	    								if(myBoard.board[feedback].checkShip() == true)
	    								{
	    									myBoard.board[feedback].setHit();
	    									check++;
	    									if(check == self)
	    									{
	    										win = true;
	    										JOptionPane.showMessageDialog(null, "You Lose !","Sorry", JOptionPane.PLAIN_MESSAGE);
	    			        	            	try {
	    											sleep(750);
	    										} catch (InterruptedException e) {
	    											// TODO Auto-generated catch block
	    											e.printStackTrace();
	    										}
	    										out.close();
	    										in.close();
	    										s.close();
	    									}
	    								}
	    								else
	    									myBoard.board[feedback].setMiss();
	    							} catch (IOException e) {
	    								// TODO Auto-generated catch block
	    								e.printStackTrace();
	    							}
	            	            	JOptionPane.showMessageDialog(null, "Your Turn !","Your Turn", JOptionPane.PLAIN_MESSAGE);
	            	            	try {
										sleep(750);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
	            	            	System.out.println("Client: "+feedback + win);
	            	            }     
	            		}
	                }.start();

	        } catch (IOException ie) {
	            ie.printStackTrace();
	        }
	    }
	    	    
	    class Interface extends JFrame{
	    	
	    	int BOARDSIZE = 10;
	    	int count = 0;
	    	PathButton [] board = new PathButton[BOARDSIZE*BOARDSIZE];
	    	JPanel P = new JPanel();	
	    		
	    	public Interface()
	    	{		
	    		this.setTitle("968212 JIE ZHOU  BATTLESHIP");

	    		this.setBounds(500, 25, 900, 900);
	    		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	    		this.setContentPane(P);
	    		P.setLayout(new GridLayout(BOARDSIZE,BOARDSIZE));

	    		for(int i = 0;i < (BOARDSIZE*BOARDSIZE); i++)
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
	    		JMenuItem start = new JMenuItem("Create Game");
	    		JMenuItem reset = new JMenuItem("Reset");
	    		run.add(start);
	    		run.add(reset);
	    		this.setJMenuBar(MenuBar);
	    		
	    		aircraft.addActionListener(new ActionListener(){
	    			@Override
	    			public void actionPerformed(ActionEvent e) {
	    				for(int i = 0;i < (BOARDSIZE*BOARDSIZE); i++)
	    				{
	    					board[i].setSetting(true, 5);
	    				}
	    				
	    			}				
	    		});
	    		battleship.addActionListener(new ActionListener(){
	    			@Override
	    			public void actionPerformed(ActionEvent e) {
	    				for(int i = 0;i < (BOARDSIZE*BOARDSIZE); i++)
	    				{
	    					board[i].setSetting(true, 4);
	    				}
	    				
	    			}				
	    		});
	    		submarine.addActionListener(new ActionListener(){
	    			@Override
	    			public void actionPerformed(ActionEvent e) {
	    				for(int i = 0;i < (BOARDSIZE*BOARDSIZE); i++)
	    				{
	    					board[i].setSetting(true, 3);
	    				}
	    				
	    			}				
	    		});
	    		cruiser.addActionListener(new ActionListener(){
	    			@Override
	    			public void actionPerformed(ActionEvent e) {
	    				for(int i = 0;i < (BOARDSIZE*BOARDSIZE); i++)
	    				{
	    					board[i].setSetting(true, 3);
	    				}
	    				
	    			}				
	    		});
	    		patrol.addActionListener(new ActionListener(){
	    			@Override
	    			public void actionPerformed(ActionEvent e) {
	    				for(int i = 0;i < (BOARDSIZE*BOARDSIZE); i++)
	    				{
	    					board[i].setSetting(true, 2);
	    				}
	    				
	    			}				
	    		});
	    		start.addActionListener(new ActionListener(){
	    			@Override
	    			public void actionPerformed(ActionEvent e) {
//	    				for(int i = 0;i < (MAZESIZE*MAZESIZE); i++)
//	    				{
//	    					System.out.print(board[i].checkShip()+" ");
//	    					if(i%MAZESIZE == 9)
//	    						System.out.println();
//	    						
//	    				}
	    				for(int i = 0;i < (BOARDSIZE*BOARDSIZE); i++)
	    				{
	    					board[i].setSetting(false, 0);
	    					if(board[i].checkShip() == true)
	    					{
	    						O[i] = 1;
	    						self++;
	    					}
	    					else
	    						O[i] = 0;
//	    					if(i% 10 == 0)
//	    						System.out.println();
//	    					System.out.print(O[i]+" ");	    					
	    				}
	    				System.out.print(sum);	 
	    				startServer();	    				
	    			}				
	    		});
	    		reset.addActionListener(new ActionListener(){
	    			@Override
	    			public void actionPerformed(ActionEvent e) {
	    				for(int i = 0;i < (BOARDSIZE*BOARDSIZE); i++)
	    				{
	    					board[i].reset();
	    				}	    				
	    			}				
	    		});
	    		
	    		this.setVisible(true);
	    	}	       
	    }
	    
	    class Enemy extends JFrame
	    {
	    	int BOARDSIZE = 10;
	    	CheckButton [] enemyBoard = new CheckButton[BOARDSIZE*BOARDSIZE];
	    	JPanel P2 = new JPanel();
	    	DataOutputStream out1;
	    	byte[]enemy;
	 	
	    	public Enemy(DataOutputStream o, byte[]in)
	    	{		
	    		this.setTitle("968212 JIE ZHOU  ENEMYBOARD");

	    		this.setBounds(500, 25, 900, 900);
	    		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	    		this.setContentPane(P2);
	    		P2.setLayout(new GridLayout(BOARDSIZE,BOARDSIZE));
	    		out1 = o;
	    		enemy = new byte[BOARDSIZE*BOARDSIZE];
	    		for(int i = 0;i < (BOARDSIZE*BOARDSIZE); i++)
	    		{
	    			enemy[i] = in[i];
	    		}
	    		this.setVisible(true);
	    		for(int i = 0;i < (BOARDSIZE*BOARDSIZE); i++)
	    		{
	    			enemyBoard[i] = new CheckButton(i,out1,enemy[i]);
	    			this.getContentPane().add(enemyBoard[i]);
	    		}
	    	}
	    }
	    
	    class CheckButton extends JButton implements MouseListener, MouseMotionListener{

	    	boolean isShip;
	    	Image hit;
	    	Image bg;
	    	Image miss;
	    	int number;
	    	DataOutputStream out2;
	    	
	    	public CheckButton(int order, DataOutputStream o, byte input)
	    	{		
	    		try {
	    			bg = ImageIO.read(getClass().getResource("ocean.png"));
	    			hit = ImageIO.read(getClass().getResource("explosion.png"));
	    			miss = ImageIO.read(getClass().getResource("miss.png"));
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	    		this.setIcon(new ImageIcon(bg));
	    		number = order;
	    		out2 = o;
	    		
	    		if(input == 1)
	    		{
	    			isShip = true;
	    			sum++;
	    		}
	    		else
	    			isShip =false;
	   
	    		addMouseListener(this);
	    		addMouseMotionListener(this);	    		
	    	}

	    	@Override
	    	public void mouseDragged(MouseEvent e) {
	    		// TODO Auto-generated method stub
	    		
	    	}

	    	@Override
	    	public void mouseMoved(MouseEvent e) {
	    		// TODO Auto-generated method stub
	    		
	    	}

	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		
				try {
					out2 = new DataOutputStream(s.getOutputStream());
					out2.writeInt(number);
					out2.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(isShip == true) {
					this.setIcon(new ImageIcon(hit));
					count++;
					if(count == sum)
					{
						JOptionPane.showMessageDialog(null, "You Win !","Congratulation", JOptionPane.PLAIN_MESSAGE);
						win = true;
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						try {
							out2.close();
							in.close();
							s.close();
							ss.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				}
	    		else
	    			this.setIcon(new ImageIcon(miss));

	    			
	    	}
	    	@Override
	    	public void mousePressed(MouseEvent e) {
	    		// TODO Auto-generated method stub
	    		
	    	}

	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		
	    	}

	    	@Override
	    	public void mouseEntered(MouseEvent e) {
	    		// TODO Auto-generated method stub
	    		
	    	}

	    	@Override
	    	public void mouseExited(MouseEvent e) {
	    		// TODO Auto-generated method stub
	    		
	    	}
	    }
	    
	    
	    public static void main(String[] args) throws Exception {
	        new Server();
	    }
	}

