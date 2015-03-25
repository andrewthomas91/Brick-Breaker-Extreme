import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Brick{
	
	//Ints used for the x and y coordinates and the width and height of the brick
	private int xCord, yCord, width, height, lives, pointValue;
	private boolean alive, powerup;
	private Image icon;
	String imgPath = System.getProperty("user.dir") + File.separator + "Images" + File.separator;
	private static Random r = new Random();
	
	/**
		Default constructor for the Brick object
	 */
	public Brick(){
		//Default values for the brick
		xCord = 100;
		yCord = 100;
		width = 45;
		height = 20;
		lives = 1;
		alive = true;
		powerup = false;
	}
	
	/**
		Constructor for the Brick object
		@param x The X Coordinate of the brick.
		@param y The Y Coordinate of the brick.
		@param w The width of the brick.
		@param h The height of the brick.
		@param L The number of lives this brick has.
 */
	public Brick(int x, int y, int w, int h, int L){
		//Default values for the brick
		xCord = x;
		yCord = y;
		width = w;
		height = h;
		lives = L;
		alive = true;
		powerup = false;
	}
	
	/**
		Hurts the brick by removing a life from it.
		If the brick is destroyed there is a 20% chance of generating a powerup.
	 */
	public void hurtBrick(){		
		if(lives > 1){
			lives--;
			SoundEffect.WALL.play();
		}
		else{
			if(r.nextInt(10) < 2)
				powerup = true;
			alive = false;
			SoundEffect.CRUMBLE.play();
		}
		updateIcon();
	}
	
	/**
		Updates the brick's icon depending on its number of remaining lives.
	 */
	public void updateIcon(){
		if(lives == 1){
			setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "redBrick.png"));
		}
		if(lives == 2){
			setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "blueBrick.png"));
		}
		if(lives == 3){
			setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "greenBrick.png"));
		}
	}
	
	/**
		Prints the X and Y Coordinates of the brick.
	 */
	public void print(){
		System.out.println(xCord + ", " + yCord);
	}
	
	/**
	 * Sets the point value
	 * @param pv
	 */
	public void setPointValue(int pv){
		pointValue = pv;
	}
	
	/**
	 * Sets the icon
	 * @param i
	 */
	public void setIcon(Image i){
		icon = i;
	}
	
	/**
	 * Sets the powerup boolean
	 * @param b
	 */
	public void setPowerUp(boolean b){
		powerup = b;
	}
	
	/**
		Gets the point value.
		@return the point value.
	 */
	public int getPointValue(){
		return pointValue;
	}
	
	/**
		Gets the powerup boolean.
		@return the powerup boolean.
	 */
	public boolean getPowerUp(){
		return powerup;
	}
	
	/**
		Gets the x coordinate of the brick.
		@return the x coordinate of the brick
	 */
	public int getX(){
		return xCord;
	}
	
	/**
	Gets the y coordinate of the brick.
	@return the y coordinate of the brick
	 */
	public int getY(){
		return yCord;
	}
	
	/**
	Gets the width of the brick.
	@return the width of the brick
	 */
	public int getW(){
		return width;
	}
	
	/**
	Gets the height of the brick.
	@return the height of the brick
	 */
	public int getH(){
		return height;
	}

	/**
	Gets the number of lives.
	@return the number of lives.
 */
	public int getLives(){
		return lives;
	}
	
	/**
	Gets the alive boolean.
	@return the alive boolean.
 */
	public boolean getAlive() {
		return alive;
	}
	
	/**
	Gets the icon.
	@return the icon.
 */
	public Image getIcon(){
		return icon;
	}
}