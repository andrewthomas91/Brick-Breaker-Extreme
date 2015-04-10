import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

public class Paddle{
	
	//Ints used for the x and y coordinates and the width and height of the paddle
	private double xCord, xCordOld, yCord, width, height, yCordOld;;
	String imgPath = System.getProperty("user.dir") + File.separator + "Images" + File.separator;
	private Image icon;
	
	/**
	Default constructor for the Paddle object
	 */
	public Paddle(){
		//Default values for the paddle
		xCord = 200;
		yCord = 570;
		width = 60;
		height = 8;
		setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "paddle.png"));
	}
	
	/**
	Constructor for the Paddle object
	@param y The Y coordinate.
	 */
	public Paddle(int y){
		//Default values for the paddle
		xCord = 200;
		yCord = y;
		width = 60;
		height = 8;
		setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "paddle.png"));
	}
	
	/**
	Constructor for the Paddle object
	@param x The x coordinate.
	@param y The Y coordinate.
	@param pic The name of the picture file.
	 */
	public Paddle(int x, int y, String pic){
		//Default values for the paddle
		xCord = x;
		yCord = y;
		width = 8;
		height = 60;
		setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + pic));
	}
	
	/**
	Updates the paddle's x coordinate when called depending on the direction sent as the parameter.
	@param direction The direction to move the paddle. A positive number to go to the right or a negative number to go to the left.
 */
	public void move(int direction){
		if ((direction > 0 && xCord < 540) || (direction < 0 && xCord > 0)){//The paddle can't move past the bounds of the screen
			xCordOld = xCord;
			xCord += direction * 20;//The paddle moves 20 pixels to either the left or right every time this method is called
		}
	}
	
	/**
	Updates the paddle's x coordinate when called depending on the direction sent as the parameter.
	@param direction The direction to move the paddle. A positive number to go to the right or a negative number to go to the left.
 */
	public void moveMouse(int direction, int mouseXCoord){
		if ((direction > 0 && xCord < 540) || (direction < 0 && xCord > 0)){//The paddle can't move past the bounds of the screen
			xCordOld = xCord;
			xCord = mouseXCoord;
//			xCord += direction * 10;//The paddle moves 20 pixels to either the left or right every time this method is called
		}
	}
	
	/**
	Updates the paddle's y coordinate when called depending on the direction sent as the parameter.
	@param direction The direction to move the paddle. A positive number to go to the right or a negative number to go to the left.
 */
	public void moveY(int direction){
		if ((direction > 0 && yCord < 540) || (direction < 0 && yCord > 10)){//The paddle can't move past the bounds of the screen
			yCordOld = yCord;
			yCord += direction * 20;//The paddle moves 20 pixels to either the left or right every time this method is called
		}
	}
	
	/**
	Gets the x coordinate of the paddle.
	@return The x coordinate of the paddle
	 */
	public double getX(){
		return xCord;
	}
	
	/**
	Gets the y coordinate of the paddle.
	@return The y coordinate of the paddle
	 */
	public double getY(){
		return yCord;
	}
	
	/**
	Sets the x coordinate of the paddle.
	 */
	public void setX(int x){
		xCord = x;
	}
	
	/**
	Sets the y coordinate of the paddle.
	 */
	public void setY(int y){
		yCord  =y;
	}
	
	/**
	Gets the old x coordinate of the paddle.
	@return the old x coordinate of the paddle
	 */
	public double getXOld(){
		return xCordOld;
	}
	
	/**
	Gets the old y coordinate of the paddle.
	@return the old y coordinate of the paddle
	 */
	public double getYOld(){
		return yCordOld;
	}
	
	/**
	Gets the width of the paddle.
	@return the width of the paddle
	 */
	public double getW(){
		return width;
	}
	
	/**
	Sets the width of the paddle.
	@return the width of the paddle
	 */
	public void setW(int w){
		width = w;
	}
	
	/**
	Gets the height of the paddle.
	@return the height of the paddle
	 */
	public double getH(){
		return height;
	}
	
	/**
	Sets the height of the paddle.
	@return the height of the paddle
	 */
	public void setH(int h){
		height = h;
	}
	
	/**
	Sets the icon.
	 */
	public void setIcon(Image i){
		icon = i;
	}
	
	/**
	Gets the icon.
	 */
	public Image getIcon(){
		return icon;
	}
}