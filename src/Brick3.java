import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;


public class Brick3 extends Brick {
	
	/**
	Default constructor for the Brick3 object
 */
	Brick3(){
		super();
	}
	
	/**
	Constructor for the Brick3 object
	@param x The X Coordinate of the brick.
	@param y The Y Coordinate of the brick.
	@param w The width of the brick.
	@param h The height of the brick.
*/
	Brick3(int x, int y, int w, int h){
		super(x, y, w, h, 3);
		setPointValue(30);
		setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "greenBrick.png"));
	}
}
