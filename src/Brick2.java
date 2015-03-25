import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;


public class Brick2 extends Brick {
	
	/**
	Default constructor for the Brick2 object
 */
	Brick2(){
		super();
	}
	
	/**
	Constructor for the Brick2 object
	@param x The X Coordinate of the brick.
	@param y The Y Coordinate of the brick.
	@param w The width of the brick.
	@param h The height of the brick.
*/
	Brick2(int x, int y, int w, int h){
		super(x, y, w, h, 2);
		setPointValue(20);
		setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "blueBrick.png"));
	}
}
