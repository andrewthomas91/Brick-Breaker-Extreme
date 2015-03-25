import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;


public class Brick1 extends Brick {
	
	/**
	Default constructor for the Brick1 object
 */
	Brick1(){
		super();
	}
	
	/**
	Constructor for the Brick1 object
	@param x The X Coordinate of the brick.
	@param y The Y Coordinate of the brick.
	@param w The width of the brick.
	@param h The height of the brick.
*/
	Brick1(int x, int y, int w, int h){
		super(x, y, w, h, 1);
		setPointValue(10);
		setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "redBrick.png"));
	}
}
