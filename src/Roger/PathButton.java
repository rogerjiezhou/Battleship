package Roger;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class PathButton extends JButton implements MouseListener, MouseMotionListener{

	int Path;
	boolean setting;
	boolean isShip;
	boolean isHit;
	Image img;
	Image bg;
	Image hit;
	Image miss;
	int number;
    PathButton [] Button;
    int shipLength;
	
	public PathButton(int order, PathButton []button)
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
		Button = new PathButton[button.length];
		Button = button;
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
		if(setting == true)
		{
			if(SwingUtilities.isLeftMouseButton(e))
			{
				if(!isShip)
				{
					if(number % 10 < (10 - shipLength + 1))
					{
						try {
							img = ImageIO.read(getClass().getResource("head4.png"));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						this.setIcon(new ImageIcon(img));
						this.isShip = true;
						try {
							img = ImageIO.read(getClass().getResource("body2.png"));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						for(int i = number + 1 ; i < number + shipLength - 1 ; i++)
						{
							Button[i].setIcon(new ImageIcon(img));
							Button[i].setShip(true);				
						}
						try {
							img = ImageIO.read(getClass().getResource("head2.png"));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						Button[number + shipLength - 1].setIcon(new ImageIcon(img));
						Button[number + shipLength - 1].setShip(true);				
					}
				}
				else
				{
					this.setIcon(new ImageIcon(bg));
					isShip = false;
				}
	
			}
			else if(SwingUtilities.isRightMouseButton(e))
			{
				if(!isShip)
				{				    
					if(number < 10*(10 - shipLength + 1))
					{
						try {
							img = ImageIO.read(getClass().getResource("head1.png"));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						this.setIcon(new ImageIcon(img));
						this.isShip = true;
						try {
							img = ImageIO.read(getClass().getResource("body1.png"));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						for(int i = number + 10 ; i < number + 10*(shipLength-1)  ; i+=10)
						{
							Button[i].setIcon(new ImageIcon(img));
							Button[i].setShip(true);				
						}
						try {
							img = ImageIO.read(getClass().getResource("head3.png"));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						Button[number + 10*(shipLength-1)].setIcon(new ImageIcon(img));
						Button[number + 10*(shipLength-1)].setShip(true);	
					}		
				}
			}
		}

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
	
	public void setSetting(boolean set, int length)
	{
		setting = set;
		shipLength = length;
	}
	
	public void setShip(boolean set)
	{
		isShip = set;
	}
	public void setHit()
	{
		isHit = true;
		this.setIcon(new ImageIcon(hit));
	}
	public void setMiss()
	{
		this.setIcon(new ImageIcon(miss));
	}
	public boolean checkShip()
	{
		return isShip;
	}

	public void reset()
	{
		isShip = false;
		this.setIcon(new ImageIcon(bg));
	}

}
