import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
	Runs the game using threading, double buffering, and graphics.
*/
public class Game extends JFrame implements Runnable
{	
	private Display display;
	private Ball ball;//The ball
	private Paddle bottomPaddle, topPaddle;
	private LevelGenerator level;
	private HighScoreList list;
	
	private ArrayList<Brick> bricks = new ArrayList<Brick>();
	private ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
	
	private int flashingCounter = 1;
	private int levelNum = 1;
	private int inverse = 1;
	private int scoreMultiplier = 1;
	private int scoreTotal, scoreLevel, bonus, lives;
	private String userName;
	
	private boolean newGame = true;
	private boolean flashingBall = false;
	private boolean gameOver = true;
	private boolean isPaused = false;
	private boolean lostLife = false;
	private boolean levelComplete = false;
	private boolean highScoreScreen = false;
	private boolean mute = false;
	
	private static Random r = new Random();
	Image offscreenImage;//The image off screen that is used for double buffering
	Graphics offscr;//What is drawn onto that is then used to create the off screen image	
	
	Thread bounceBall = null;//Thread variable which will be taking care of the ball animation
	
	/**
	Default constructor for the Game object
	 */
	public Game()
	{
		this.setSize(600,630);//600 pixels wide by 630 pixels high
		this.setTitle("Brick Breaker Extreme");//Title of application
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.white);
		this.setVisible(true);	
		offscreenImage = createImage(this.getWidth(), this.getHeight());//Creates the off screen image using this frames dimensions
		offscr = offscreenImage.getGraphics();
		
		KeyListener handler = new MyHandler();//Key Listener that is used to listen for the enter key and the left and right arrow keys
	    this.addKeyListener(handler);//Adds the listener to this frame
		MouseListener mouse = new MouseListener();
		this.addMouseMotionListener(mouse);
		
		
		SoundEffect.init();
		bottomPaddle = new Paddle();
		topPaddle = new Paddle(30);
		ball = new Ball();
		level = new LevelGenerator();
		list = new HighScoreList();
		display = new Display(offscreenImage);				
		makeBricks();
		
		String str = JOptionPane.showInputDialog(null, "Enter user name:", "", 1);
		 if(str != null){
			 JOptionPane.showMessageDialog(null, "You entered: " + str, "", 1);
			 userName = str;
		 }
		 else{
			 JOptionPane.showMessageDialog(null, "No name entered. Using name of top score holder.", "", 1);
			 userName = list.getScores().get(0).getPlayer();
		 }
		
		bounceBall = new Thread(this);
		bounceBall.start();//Starts the bounceBall thread, which tells the ball to move and updates the graphics
	}

	/**
	Paint method. Draws the screen.
	@param g The graphics used to draw the paddle
	 */
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;	
		g2.drawImage(offscreenImage, 0, 0, this);//Draws the off screen image to the screen (double buffering)
	}
	
	/**
	run method for the thread. Always loops while the program is running.
	 */
	public void run()
	{		
		while(true){//This thread loops until the application is closed
			System.out.println("");
			
			if(newGame){
				lives = 5;
				scoreTotal = 0;
				scoreLevel = 0;
				if(!highScoreScreen){
					offscr = display.drawBackground(-1);
					offscr = display.playerSelect(userName);//Asks Player for 1 or 2 player Mode
				}
				this.repaint();
				try{
					Thread.sleep(50);//Sleeps for 20 milliseconds
				}
				catch(Exception e){
					System.out.println("Error in running thread " + e);
				}
			}
			else{
				while(!gameOver && !levelComplete && !lostLife)
				{		
					offscr = display.clear();//Clears the screen.
					offscr = display.drawBackground(levelNum);
				
					lostLife = ball.move(bottomPaddle, topPaddle, bricks);
						
					if(!isPaused){
						for(int i = 0; i < powerUps.size(); i++){
						
							powerUps.get(i).move(bottomPaddle,topPaddle);
								
							if(powerUps.get(i).getActive()){
								powerUps.get(i).TimePass();
							}
								
							else if(powerUps.get(i).getCollected()){
								powerUps.get(i).removeAction();
								powerUps.remove(i);	
							}
						}
					}
				
					if (lostLife){
						lives--;
						powerUps.clear();
						inverse = 1;
						flashingCounter = 1;
						flashingBall = false;
					}
					
					if(lives == 0)
						gameOver = true;
				
					levelComplete = true;
					for(int x = 0; x < bricks.size(); x++){
						if(bricks.get(x).getLives() > 0)
							levelComplete = false;
					}
				
					if(flashingBall){
						if((flashingCounter / 25) % 2 == 1){	
							offscr = display.drawBall(ball);
						}
						flashingCounter++;
					}
					else{
						flashingCounter = 1;
						offscr = display.drawBall(ball);
					}
					offscr = display.drawInfo(ball, lives, scoreTotal + scoreLevel, levelNum);	
					offscr = display.drawPaddle(bottomPaddle);
					offscr = display.drawPaddle(topPaddle);
					offscr = display.drawBricks(bricks);
					
					for(int i = 0; i < powerUps.size(); i++){
						offscr = display.drawPowerup(powerUps.get(i));
					}
				
					if (isPaused)
						offscr = display.drawPauseMessage();
				
					if(lostLife){
						offscr = display.drawLostLife(lives);
						SoundEffect.LIFE.play();
					}
					this.repaint();//Calls the repaint method
				
					for (int i = 0; i < bricks.size(); i++){
						if((bricks.get(i)).getPowerUp()){
							int s = r.nextInt(6);
							switch(s){
							case 0:
								powerUps.add(new PowerUpInverse((bricks.get(i)).getX(), (bricks.get(i)).getY(),30,1,1,300,this));
								break;
							case 1:
								powerUps.add(new PowerUpSmallPaddle((bricks.get(i)).getX(), (bricks.get(i)).getY(),1,1,300,this,bottomPaddle,topPaddle));
								break;
							case 2:
								powerUps.add(new PowerUpBigPaddle((bricks.get(i)).getX(), (bricks.get(i)).getY(),1,1,300,this, bottomPaddle, topPaddle));
								break;
							case 3:
								powerUps.add(new PowerUpAddLife((bricks.get(i)).getX(), (bricks.get(i)).getY(),30,1,1,0,this));
								break;
							case 4:
								powerUps.add(new PowerUpDoubleScore((bricks.get(i)).getX(), (bricks.get(i)).getY(),30,1,1,500,this));
								break;
							case 5:
								powerUps.add(new PowerUpFlashingBall((bricks.get(i)).getX(), (bricks.get(i)).getY(),30,1,1,300,this));		
								break;
							}
							bricks.get(i).setPowerUp(false);
						}
						if(!((Brick) bricks.get(i)).getAlive()){
							scoreLevel += (bricks.get(i).getPointValue() * scoreMultiplier);
							bricks.remove(i);	
						}
					}
				
					try{
						Thread.sleep(20);//Sleeps for 20 milliseconds
					}
					catch(Exception e){
						System.out.println("Error in running thread " + e);
					}
					if(gameOver){//If the game is over, lets the user know how to start a new game
						SoundEffect.EXPLODE.play();
						flashingCounter = 1;
						flashingBall = false;
						powerUps.clear();
						list.insertHighScore(userName,scoreTotal);
						offscr = display.drawGameOverMessage(scoreTotal + scoreLevel);
						level.shuffleLevels();
						this.repaint();//Calls the repaint method
					
					}
					if(levelComplete){//If the level is over, lets the user know how to start a new game
						SoundEffect.LEVEL.play();
						flashingCounter = 1;
						flashingBall = false;
						powerUps.clear();
						bricks.clear();
						if(ball.getScore() != 0){
							double hits = ball.getScore() / 10.0;
							double efficentBonus = scoreLevel / hits;
							double lifeAndLevelBonus = scoreLevel * (levelNum + lives);
							bonus = (int) Math.round(efficentBonus + lifeAndLevelBonus);
							scoreTotal += bonus + scoreLevel;
							scoreLevel = 0;
						}
						offscr = display.drawLevelCompleteMessage(levelNum, bonus);
						this.repaint();//Calls the repaint method
					}
				
				}
			}
		}
	}

	/**
	Makes the bricks to be used for the current level.
	 */
	public void makeBricks(){
		level.generateLevel(levelNum);
		for(int index = 1; index < 65; index++){
			if(level.getValue(index) == 1){
				bricks.add(new Brick1(level.getXCord(index),level.getYCord(index),55,55));
			}	
			
			if(level.getValue(index) == 2){
				bricks.add(new Brick2(level.getXCord(index),level.getYCord(index),55,55));
			}
			
			if(level.getValue(index) == 3){
				bricks.add(new Brick3(level.getXCord(index),level.getYCord(index),55,55));
			}
			
			if(level.getValue(index) == 4){
				bricks.add(new Brick4(level.getXCord(index),level.getYCord(index),55,55));
			}
			
			if(level.getValue(index) == 5){
				bricks.add(new UnbreakableBrick(level.getXCord(index),level.getYCord(index),50,50));
			}
		}
	}
	
	/**
	Sets the number of lives the player has remaining.
	@param l The number of lives.
	 */
	public void setLives(int l){
		lives = l;
	}
	
	/**
	Switches the inverse variable by multiplying it by 1.
	 */
	public void switchInverse(){
		inverse = (inverse * -1);
	}
	
	/**
	Sets the score multiplier.
	@param i The score multiplier.
	 */
	public void setScoreMultiplier(int i) {
		scoreMultiplier = i;
	}
	
	/**
	Sets the flashingball boolean.
	@param f Either false or true.
	 */
	public void setFlashingBall(boolean f){
		flashingBall = f;		
	}
	
	/**
	Gets the number of lives the player has remaining.
	@return The number of lives the player has remaining.
	 */
	public int getLives(){
		return lives;
	}
	
	/**
	The MyHandler object which implements KeyListener. Used to listen for user input to move the paddle and start a new game.
	 */
	private class MyHandler implements KeyListener { 	
		
    	public void actionPerformed(ActionEvent e) {
    	}
	
    	/**
    	Method that is called when a key is pressed.
    	@param e The key that was pressed. (e.getKeyCode() returns the key code of the pressed key)
    	 */
    	public void keyPressed(KeyEvent e) {//A key was pressed
    		if (e.getKeyCode()==68){//d key kills you for testing only remove in final version1
    			levelComplete = true;
    		}
    		if (e.getKeyCode()==39)//Right arrow key code
    		{
    			if(!gameOver && !isPaused){//Only moves the paddle if there is a game going
    				bottomPaddle.move(1*inverse);
    				topPaddle.move(1*inverse);
    			}
    		}
    		
    		if (e.getKeyCode()==37)//Left arrow key code
    		{
    			if(!gameOver && !isPaused){
    				bottomPaddle.move(-1*inverse);
    				topPaddle.move(-1*inverse);
				}
    		}
    		if (e.getKeyCode()==80 && !gameOver && !levelComplete && !lostLife)//p key code
    		{
    			if (!isPaused){
    				isPaused = true;
    				ball.stop();
    			}
    			else {
    				isPaused = false;
    				ball.resume();
    			}
    		}
    		
    		 if (e.getKeyCode()==49)//1 key code
 		    {
    			 if(gameOver && !highScoreScreen){
    				 newGame = false;
    	    	     gameOver = false;
    			 }
    			 if(highScoreScreen){
    				 String str = JOptionPane.showInputDialog(null, "Enter user name:", "", 1);
    				 if(str != null){
    					 JOptionPane.showMessageDialog(null, "You entered: " + str, "", 1);
    					 userName = str;
    				 }
    				 else
    					 JOptionPane.showMessageDialog(null, "No name entered.", "", 1);
    			 }
 		    }
    		
    		if (e.getKeyCode()==51)//3 key code
    		{
    			if(mute){
    				SoundEffect.volume = SoundEffect.Volume.LOW;  // un-mute
    				mute = false;
    			}
    			else{
    				SoundEffect.volume = SoundEffect.Volume.MUTE;  // mute
    				mute = true;
    			}
    		}
    		    	  	
    		if (e.getKeyCode()==10 && !isPaused)//Enter key code
    		{
    			if(gameOver || levelComplete || lostLife){//Only starts a new game if there is none currently going on
    				ball.newGame();//Resets variables for a new game
    				if(gameOver){
    					ball.setScore(0);
    					newGame = true;
    					levelNum = 1;
    					bricks.clear();
    					makeBricks();
    					offscr = display.clear();
    				}
    				if(levelComplete){
    					levelComplete = false;
    					levelNum++;
    					makeBricks();
    					ball.setScore(0);
    				}
    				if(lostLife)
    					lostLife = false;
    				
    			}	
    			if(highScoreScreen){
    				highScoreScreen = false;
    				offscr = display.drawBackground(-1);
					offscr = display.playerSelect(userName);//Asks Player for 1 or 2 player Mode 
    			}
    			else{
    				if(gameOver){
    					offscr = display.highScoreScreen(list.getScores());
    					highScoreScreen = true;
    				}
    			}
    		}
    	}

    	public void keyReleased(KeyEvent arg0) {//Not used in this program
    		
    	}

    	public void keyTyped(KeyEvent arg0) {//Not used in this program
    			
    	}    
    }
	
	/**
	 * 
	 * The MouseListener object which implements MouseMotionListener. Used to listen for user mouse movement to move the paddles.
	 *
	 */
	private class MouseListener implements MouseMotionListener{
		   private int oldX;
		   private int oldY;

		   public void mouseClicked(MouseEvent e){
		   }
		   
		   /**
	    	Method that is called when the mouse is moved.
	    	@param e The mouse that was moved.
	    	 */
	    	public void mouseMoved(MouseEvent e) {
		        int x = e.getX();
		        int y = e.getY();
		        
				if (x>oldX+5){
		        	if(!gameOver && !isPaused){
	    				bottomPaddle.moveMouse(1*inverse, x);
	    				topPaddle.moveMouse(1*inverse, x);
	    				oldX=x;
	    			    
					}
		        }
		        if (x<oldX-5){
		        	if(!gameOver && !isPaused){
	    				bottomPaddle.moveMouse(-1*inverse, x);
	    				topPaddle.moveMouse(-1*inverse, x);
	    				oldX=x;
					}
		        }		     
	    	}

			@Override
			public void mouseDragged(MouseEvent e) {

				
			}
			
	    }
}


    
