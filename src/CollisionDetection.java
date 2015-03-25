import java.util.Random;

public class CollisionDetection {
	int BX,BY,TLX,TLY,TRX,TRY,BLX,BLY,BRX,BRY;//For Ball and Brick
	boolean collision;
	private static Random r = new Random();//Used to generate a random xCord for the balls starting location
	
	/**
	Default constructor for the CollisionDetection object
 */
	CollisionDetection(){
		collision = false;
	}
	
	/**
	Checks for a collision between a ball object and a brick object.
	@param b The ball.
	@param xDir The direction of the ball along the X axis.
	@param yDir The direction of the ball along the Y axis.
	@param bk The Brick.
 */
	public boolean BallAndBrick(Ball b, int xDir, int yDir, Brick bk){

		collision = false;
		BX = (int) (b.getX()+(b.getRadius()*0.5));
		BY = (int) (b.getY()+(b.getRadius()*0.5));
		TLX = bk.getX();
		TLY = bk.getY();
		TRX = bk.getX()+bk.getW();
		TRY = bk.getY();
		BLX = bk.getX();
		BLY = bk.getY()+bk.getH();
		BRX = bk.getX()+bk.getW();
		BRY = bk.getY()+bk.getH();
		
		if(xDir > 0 && yDir < 0){//Ball is traveling North East
			if(BY > TLY && BX > TLX){
				for(int i = 0; i < bk.getW(); i++){
					collision = (BY < (BLY - i) && BX < (BLX + i));
					if(collision){
						b.setDircetions(-1,1);
						return true;
					}
				}
			}
			if(BY < BRY && BX < BRX){
				for(int i = 0; i < bk.getW(); i++){
					collision = (BY > (BLY - i) && BX > (BLX + i));
					if(collision){
						b.setDircetions(1,-1);
						return true;
					}
				}
			}
		}
		
		if(xDir < 0 && yDir < 0){//Ball is traveling North West
			if(BY > TRY && BX < TRX){
				for(int i = 0; i < bk.getW(); i++){
					collision = (BY < (BRY - i) && BX > (BRX - i));
					if(collision){
						b.setDircetions(-1,1);
						return true;
					}
					
				}
			}
			if(BY < BLY && BX > BLX){
				for(int i = 0; i < bk.getW(); i++){
					collision = (BY > (BRY - i) && BX < (BRX - i));
					if(collision){
						b.setDircetions(1,-1);
						return true;
					}
					
				}
			}
		}
		
		if(xDir > 0 && yDir > 0){//Ball is traveling South East
			if(BY > TRY && BX < TRX){
				for(int i = 0; i < bk.getW(); i++){
					collision = (BY < (BRY - i) && BX > (BRX - i));
					if(collision){
						b.setDircetions(1,-1);
						return true;
					}
					
				}
			}
			if(BY < BLY && BX > BLX){
				for(int i = 0; i < bk.getW(); i++){
					collision = (BY > (BRY - i) && BX < (BRX - i));
					if(collision){
						b.setDircetions(-1,1);
						return true;
					}
					
				}
			}
		}
		
		if(xDir < 0 && yDir > 0){//Ball is traveling South West
			if(BY > TRY && BX < TRX){
				for(int i = 0; i < bk.getW(); i++){
					collision = (BY < (BRY - i) && BX > (BRX - i));
					if(collision){
						b.setDircetions(1,-1);
						return true;
					}
					
				}
			}
			if(BY < BLY && BX > BLX){
				for(int i = 0; i < bk.getW(); i++){
					collision = (BY > (BRY - i) && BX < (BRX - i));
					if(collision){
						b.setDircetions(-1,1);
						return true;
					}
					
				}
			}
		}
		return false;
	}
	
	/**
	Checks for a collision between a ball object and the top and bottom paddle objects.
	@param b The ball.
	@param yDir The direction of the ball along the Y axis.
	@param pad The bottom paddle.
	@param pad2 The top paddle.
 */
	public boolean BallAndPaddleTopBottom(Ball b, int yDir, Paddle pad, Paddle pad2){
		if(yDir < 0){//Ball is traveling North
			BX = (int) (b.getX()+(b.getRadius()*0.5));
			BY = b.getY();
			TLY = pad2.getY();
			BLX = pad2.getX();
			BLY = pad2.getY()+pad2.getH();
			BRX = pad2.getX()+pad2.getW();
			if(BX < BRX +(b.getRadius()*0.5) && BX > BLX -(b.getRadius()*0.5)) 
				if(BY < BLY && BY > TLY)
					return true;
		}
		
		if(yDir > 0){//Ball is traveling South
			BX = (int) (b.getX()+(b.getRadius()*0.5));
			BY = (int) (b.getY()+(b.getRadius()));
			TLY = pad.getY();
			BLX = pad.getX();
			BLY = pad.getY()+pad.getH();
			BRX = pad.getX()+pad.getW();
			if(BX < BRX +(b.getRadius()*0.5) && BX > BLX -(b.getRadius()*0.5))
				if(BY > TLY && BY < BLY)
					return true;
		}
		return false;
	}
	
	/**
	Checks for a collision between a ball object and the left and right paddle objects.
	@param b The ball.
	@param xDir The direction of the ball along the X axis.
	@param pad3 The right paddle.
	@param pad4 The left paddle.
 */
	public boolean BallAndPaddleLeftRight(Ball b, int xDir, Paddle pad3, Paddle pad4){
		if(xDir > 0){//Ball is traveling East
			BX = (int) (b.getX()+(b.getRadius()));
			BY = (int) (b.getY()+(b.getRadius()*0.5));
			TLY = pad3.getY();
			BLY = pad3.getY()+pad3.getH();
			TRX = pad3.getX()+pad3.getW();
			TLX = pad3.getX();
			if(BY < BLY +(b.getRadius()*0.5) && BY > TLY -(b.getRadius()*0.5))
				if(BX > TLX && BX < TRX)
					return true;
		}
		
		if(xDir < 0){//Ball is traveling West
			BX = b.getX();
			BY = (int) (b.getY()+(b.getRadius()*0.5));
			TLY = pad4.getY();
			BLY = pad4.getY()+pad4.getH();
			TRX = pad4.getX()+pad4.getW();
			TLX = pad4.getX();
			if(BY < BLY +(b.getRadius()*0.5) && BY > TLY -(b.getRadius()*0.5))
				if(BX < TRX && BX > TLX)
					return true;
		}
		return false;
	}

	/**
	Checks for a collision between a powerup object and the top and bottom paddle objects.
	@param b The ball.
	@param yDir The direction of the powerup along the Y axis.
	@param pad The bottom paddle.
	@param pad2 The top paddle.
 */
	public boolean PowerupAndPaddleTopBottom(PowerUp b, int yDir, Paddle pad, Paddle pad2){
		if(yDir < 0){//Ball is traveling North
			BX = b.getX();
			BY = b.getY();
			TLY = pad2.getY();
			BLX = pad2.getX();
			BLY = pad2.getY()+pad2.getH();
			BRX = pad2.getX()+pad2.getW();
			if(BX < BRX && (BX + b.getWidth()) > BLX) 
				if(BY < BLY && BY > TLY)
					return true;
		}
		
		if(yDir > 0){//Ball is traveling South
			BX = b.getX();
			BY = b.getY() + b.getHeight();
			TLY = pad.getY();
			BLX = pad.getX();
			BLY = pad.getY()+pad.getH();
			BRX = pad.getX()+pad.getW();
			if(BX < BRX && (BX + b.getWidth()) > BLX)
				if(BY > TLY && BY < BLY)
					return true;
		}
		return false;
	}
	
	/**
	Calculates the direction the ball will take after bouncing off the paddle.
	@param b The ball.
	@param pad The paddle.
	@return The new X direction for the ball. Will either be -3, -2, -1, 1, 2, or 3.
 */
	public int Angle(Ball b, Paddle pad){
		int angle = b.getX() - pad.getX();//Used to calculate the direction the ball will take after bouncing off the paddle
		//Five regions on the paddle. Middle one will pick either -1 or 1
		if(angle < 7)
			return -3;
		if(angle >= 7 && angle < 20)
			return -2;
		if(angle >= 20 && angle < 40){
			if (r.nextInt(2) == 0)
				return -1;
			else
				return 1;
		}
		if(angle >= 40 && angle < 53)
			return 2;
		if(angle >= 53)
			return 3;
		return 0;
	}
}