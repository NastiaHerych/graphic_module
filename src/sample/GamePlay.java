package sample;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class GamePlay extends JPanel implements KeyListener, ActionListener  {
	private boolean play = false;
	private int score = 0;
	
	private int totalBricks = 48;
	
	private Timer timer;
	private int delay = 8;
	
	private int playerX = 310;
	
	private int ballposX = 300;
	private int ballposY = 400;

	private int ballposX_2 = 280;
	private int ballposY_2 = 370;

	private int ballposX_3 = 320;
	private int ballposY_3 = 370;

	private int ballXdir = -1;
	private int ballYdir = -2;
	
	private MapGenerator map;
	
	public GamePlay() {
		map = new MapGenerator(6, 8);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		
		
		
	}
	public void paint(Graphics g) {
		
		g.setColor(Color.pink);
		g.fillRect(1, 1, 692, 592);
		
		map.draw((Graphics2D)g);
		
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		g.setColor(Color.blue);
		g.fillRect(playerX, 550, 100, 8);
		
		g.setColor(Color.gray);
		g.fillOval(ballposX, ballposY, 20, 20);

		g.setColor(Color.gray);
		g.fillOval(ballposX_2, ballposY_2, 20, 20);

		g.setColor(Color.gray);
		g.fillOval(ballposX_3, ballposY_3, 20, 20);
		
		g.setColor(Color.black);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString("" + score, 590, 30);
		
		
		if (totalBricks <= 0) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.black);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("You Won, Score: " + score, 190, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to Restart.", 230, 350);	
			
		}
		
		if(ballposY > 570 || ballposY_2 > 570) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Game Over, Score: " + score, 190, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to Restart.", 230, 350);	
		} 
		
		
		
		g.dispose();
	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		timer.start();
		if(play) {
			// Ball - Pedal  interaction 
			if(new Rectangle(ballposX,  ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				ballYdir = - ballYdir;
			}
			if(new Rectangle(ballposX_2,  ballposY_2, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				ballYdir = - ballYdir;
			}
			if(new Rectangle(ballposX_3,  ballposY_3, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				ballYdir = - ballYdir;
			}
			for( int i = 0; i<map.map.length; i++) {
				for(int j = 0; j<map.map[0].length; j++) {
					if(map.map[i][j] > 0) {
						int brickX = j*map.brickWidth + 80;
						int brickY = i*map.brickHeight + 50;
						int brickWidth= map.brickWidth;
						int brickHeight = map.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20,20);
						Rectangle ballRect_2 = new Rectangle(ballposX_2, ballposY_2, 20,20);
						Rectangle ballRect_3 = new Rectangle(ballposX_3, ballposY_3, 20,20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect) || ballRect_2.intersects(brickRect) || ballRect_3.intersects(brickRect) ) {
							map.setBrickValue(0, i, j);
							totalBricks--;
							score+=5;
							
							if(ballposX + 19 <= brickRect.x || ballposX +1 >= brickRect.x + brickRect.width) 
								ballXdir = -ballXdir;
							 else {
								ballYdir = -ballYdir;
							}

							if(ballposX_2 + 19 <= brickRect.x || ballposX_2 +1 >= brickRect.x + brickRect.width)
								ballXdir = -ballXdir;
							else {
								ballYdir = -ballYdir;
							}

							if(ballposX_3 + 19 <= brickRect.x || ballposX_3 +1 >= brickRect.x + brickRect.width)
								ballXdir = -ballXdir;
							else {
								ballYdir = -ballYdir;
							}
						}


						
					}
					
				}
			}
			
			
			ballposX += ballXdir;
			ballposY += ballYdir;
			ballposX_2 += ballXdir;
			ballposY_2 += ballYdir;
			ballposX_3 += ballXdir;
			ballposY_3 += ballYdir;
			if(ballposX < 0 || ballposX_2 < 0 || ballposX_3 < 0) {
				ballXdir = -ballXdir;
			}
			if(ballposY < 0 || ballposY_2 < 0 || ballposY_3 < 0) {
				ballYdir = -ballYdir;
			}
			if(ballposX > 670 || ballposX_2 > 670 || ballposX_3 > 670) {
				ballXdir = -ballXdir;  
			
			}
			
		}
		
		
		repaint();

	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX >= 600) {
				playerX = 600;
			} else {
				moveRight();
					
			}
		}
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX < 10) {
				playerX = 10;
			} else {
				moveLeft();
					
			}
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play) {
				play = true;
				ballposX = 300;
				ballposY = 400;
				ballposX_2 = 280;
				ballposY_2 = 370;
				ballposX_3 = 320;
				ballposY_3 = 370;
				ballXdir = -1;
				ballYdir = -2;
				score = 0;
				totalBricks = 25;
				map = new MapGenerator(5,5);
				
				repaint();
			}
		}
		
	}	
		public void moveRight() {
			play = true;
			playerX += 20;
		}
		public void moveLeft() {
			play = true;
			playerX -= 20;
		}
		
	

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}