import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Display extends JFrame{	
	Color ballColor = Color.red;//The ball color (initially black).
	Graphics offscr;//What is drawn onto that is then used to create the off screen image	
	Image offscreenImage;//The image off screen that is used for double buffering
	Image icon;
	Background bg;
	Font boldFont;
	Font boldHuge;
	
	/**
	Constructor for the Display object.
	@param i The image of the jFrame.
 */
	public Display(Image i){	
		boldFont = new Font("SansSerif", Font.BOLD, 20);
		boldHuge = new Font("SansSerif", Font.BOLD, 40);
		offscreenImage = i;
		offscr = offscreenImage.getGraphics();
		offscr.setFont(boldFont);
		bg = new Background();
	}
	
	/**
	Draws the background image.
	@param level The level the player is on.
 */
	public Graphics drawBackground(int level){
		icon = bg.getBackground(level);
		offscr.drawImage(icon, 0, 0, null);
		if(level > 0){
			icon = bg.getBackground(0);
			offscr.drawImage(icon, 0, 600, null);	
		}
		return offscr;
	}
	
	/**
	Draws the high score screen
	@param scores The arraylist of high scores
 */
	public Graphics highScoreScreen(ArrayList<HighScore> scores){
		clear();
		icon = bg.getBackground(-2);
		offscr.drawImage(icon, 0, 0, null);
		offscr.setColor(Color.black);
		offscr.drawString("Top 10 High Scores", 190, 90);
		for(int x = 0; x < scores.size(); x++){
			offscr.drawString((x+1) + ". " + scores.get(x).getPlayer() + " - " + scores.get(x).getScore(), 190, 90+((x+1)*35));
		}
		offscr.drawString("Press 1 to change your user name", 50, 500);
		return offscr;
	}
	
	/**
	Draws the game over message.
	@param score The score the player got.
 */
	public Graphics drawGameOverMessage(int score){
		clear();
		bg.shuffleBackgrounds();
		icon = bg.getBackground(-2);
		offscr.drawImage(icon, 0, 0, null);
		offscr.setFont(boldHuge);
		offscr.setColor(Color.black);
		offscr.drawString("Game Over!", 190, 90);
		offscr.drawString("Press enter to", 175, 180);
		offscr.drawString("start a new game", 150, 215);
		offscr.drawString("Score = " + score, 180, 350);
		offscr.setFont(boldFont);
		return offscr;
	}
	
	/**
	Draws the pause game message.
 */
	public Graphics drawPauseMessage(){
		offscr.setColor(ballColor);
		offscr.drawString("Game Paused", 100, 60);
		offscr.drawString("Press P to resume game.", 100, 90);
		return offscr;
	}
	
	/**
	Draws the lost life message.
	@param lives The number of lives the player has left.
 */
	public Graphics drawLostLife(int lives){
		offscr.setColor(ballColor);
		offscr.drawString("You Lost a Life. You have " + lives + " lives left.", 100, 60);
		offscr.drawString("Press enter to continue.", 100, 90);
		return offscr;
	}
	
	/**
	Draws the level complete message.
	@param levelNum The level the player is now on.
	@param bonus The number of bonus points the player got on the last level.
 */
	public Graphics drawLevelCompleteMessage(int levelNum, int bonus){
		offscr.setColor(ballColor);
		offscr.drawString("Level " + levelNum + " completed.", 100, 60);
		offscr.drawString("You got " + bonus + " bonus points for that level!", 100, 90);
		offscr.drawString("Press enter to start next level.", 100, 120);
		
		return offscr;
	}
	
	/**
	Draws the game info.
	@param b The ball.
	@param lives The number of lives the player has left.
	@param score The player's score.
	@param level The level the player is currently on.
 */
	public Graphics drawInfo(Ball b, int lives, int score, int level){
		offscr.setColor(Color.black);
		offscr.drawString("Hits: " + b.getScore(), 30, 620);
		offscr.drawString("Lives: " + lives, 170, 620);
		offscr.drawString("Score: " + score, 310, 620);
		offscr.drawString("Level: " + level, 450, 620);
		return offscr;
	}
	
	/**
	Draws the ball.
	@param b The ball.
 */
	public Graphics drawBall(Ball b){
		offscr.drawImage(b.getIcon(), b.getXAsInt() - b.getRadius(), b.getY() - b.getRadius(), null);
		offscr.drawOval(b.getXAsInt(), b.getY(), 1, 1);
		offscr.drawLine(b.getXAsInt(), b.getY(), b.getXAsInt(), b.getY() + b.getRadius());
		offscr.drawLine(b.getXAsInt(), b.getY(), b.getXAsInt(), b.getY() - b.getRadius());
		offscr.drawLine(b.getXAsInt(), b.getY(), b.getXAsInt() + b.getRadius(), b.getY());
		offscr.drawLine(b.getXAsInt(), b.getY(), b.getXAsInt() - b.getRadius(), b.getY());
		return offscr;
	}
	
	/**
	Draws the Powerup.
	@param p The powerup.
 */
	public Graphics drawPowerup(PowerUp p){
		offscr.drawImage(p.getIcon(), p.getX(), p.getY(), null);
		return offscr;
	}
		
	/**
	Sets the ball color variable.
	@param c The Color.
 */
	public void setBallColor(Color c){
		ballColor = c;
	}
	
	/**
	Draws the Paddle.
	@param p the paddle.
 */
	public Graphics drawPaddle(Paddle p){
		offscr.drawImage(p.getIcon(), (int) p.getX(), (int) p.getY(), null);
		return offscr;
	}
	
	/**
	Draws the Bricks.
	@param bricks The bricks.
 */
	public Graphics drawBricks(ArrayList<Brick> bricks){
		for (int i = 0; i < bricks.size(); i++){
			offscr.drawImage(bricks.get(i).getIcon(), bricks.get(i).getX(), bricks.get(i).getY(), null);
			offscr.drawOval(bricks.get(i).getX(), bricks.get(i).getY(), 5, 5);
		}	
		return offscr;
	}	

	/**
	Clears the screen.
 */
	public Graphics clear(){
		offscr.setColor(Color.white);
		offscr.fillRect(0, 0, 600, 630);
		return offscr;
	}

	/**
	Draws the player select message.
	@param name The player's name.
 */
	public Graphics playerSelect(String name) {
		offscr.setFont(boldHuge);
		offscr.setColor(Color.red);
		offscr.drawString("Press 1 for single player", 50, 200);
		offscr.drawString("Press 2 for multiplayer", 50, 270);
		offscr.drawString("Press 3 to toggle sound", 50, 340);
		offscr.drawString("" + name, 230, 80);
		offscr.setFont(boldFont);
		return offscr;
	}
}

