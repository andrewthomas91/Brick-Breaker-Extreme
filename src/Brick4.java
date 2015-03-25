import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;


public class Brick4 extends Brick {
	
	/**
	Default constructor for the Brick4 object
 */
	Brick4(){
		super();
	}
	
	/**
	Constructor for the Brick4 object
	@param x The X Coordinate of the brick.
	@param y The Y Coordinate of the brick.
	@param w The width of the brick.
	@param h The height of the brick.
*/
	Brick4(int x, int y, int w, int h){
		super(x, y, w, h, 4);
		setPointValue(40);
		setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "purpleBrick.png"));
	}
}
