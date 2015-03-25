import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

public class UnbreakableBrick extends Brick {

	/**
	Default constructor for the UnbreakableBrick object
 */
	UnbreakableBrick(){
		super();
	}
	
	/**
	Constructor for the UnbreakableBrick object
	@param x The X Coordinate of the brick.
	@param y The Y Coordinate of the brick.
	@param w The width of the brick.
	@param h The height of the brick.
*/
	UnbreakableBrick(int x, int y, int w, int h){
		super(x, y, w, h, -1);
		setPointValue(0);
		setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "unbreakableBrick.png"));
	}
}
