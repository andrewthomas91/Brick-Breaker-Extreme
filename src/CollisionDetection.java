import java.util.Random;

public class CollisionDetection {
	int ballX, ballY, 
	brickTopLeftX, brickTopLeftY, 
	brickTopRightX, brickTopRightY, 
	brickBottomLeftX, brickBottomLeftY,
	brickBottomRightX, brickBottomRightY;
	boolean collision;
	private static Random random = new Random();//Used to generate a random xCord for the balls starting location
	
	/**
	Default constructor for the CollisionDetection object
 */
	CollisionDetection(){
		collision = false;
	}
	
	/**
	Checks for a collision between a ball object and a brick object.
	@param ball
	@param brick
 */
	public boolean BallAndBrick(Ball ball, Brick brick){	
		if(ball.getX() - ball.getRadius() < brick.getX() + brick.getW() &&
		   ball.getX() + ball.getRadius() > brick.getX() &&
		   ball.getY() - ball.getRadius() < brick.getY() + brick.getH() &&
		   ball.getY() + ball.getRadius() > brick.getY()) {
		   return true;
		}
		return false;
	}
	
	/**
	Checks for a collision between a ball object and a brick object.
	@param ball
	@param brick
 */
	public int BallAndBrickDirection(Ball ball, Brick brick){	
		if(BallAndBrick(ball, brick)){
			if(ball.getY() - ball.getRadius() < brick.getY() + brick.getH()) {
				return 1;
			}
			if(ball.getY() + ball.getRadius() > brick.getY()) {
				return 2;
			}
			if(ball.getX() - ball.getRadius() < brick.getX() + brick.getW()) {
				return 3;
			}
			if(ball.getX() + ball.getRadius() > brick.getX()) {
				return 4;
			}
		}
		return 5;
	}
	
	/**
	Checks for a collision between a ball object and the top and bottom paddle objects.
	@param ball
	@param topPaddle
	@param topPaddle
 */
	public boolean BallAndPaddleTopBottom(Ball ball, Paddle bottomPaddle, Paddle topPaddle){
		if(ball.getYDir() < 0){//Ball is traveling North
			if(ball.getX() - ball.getRadius() < topPaddle.getX() + topPaddle.getW() &&
			   ball.getX() + ball.getRadius() > topPaddle.getX() &&
			   ball.getY() - ball.getRadius() < topPaddle.getY() + topPaddle.getH() &&
			   ball.getY() + ball.getRadius() > topPaddle.getY()) {
				return true;
			}
		}
		
		if(ball.getYDir() > 0){//Ball is traveling South
			if(ball.getX() - ball.getRadius() < bottomPaddle.getX() + bottomPaddle.getW() &&
			   ball.getX() + ball.getRadius() > bottomPaddle.getX() &&
			   ball.getY() - ball.getRadius() < bottomPaddle.getY() + bottomPaddle.getH() &&
			   ball.getY() + ball.getRadius() > bottomPaddle.getY()) {
				return true;
			}
		}
		return false;
	}

	/**
	Checks for a collision between a powerup object and the top and bottom paddle objects.
	@param powerUp
	@param bottomPaddle
	@param topPaddle
 */
	public boolean PowerupAndPaddleTopBottom(PowerUp powerUp, Paddle bottomPaddle, Paddle topPaddle){
		if(powerUp.directionY() < 0){//Ball is traveling North
			if(powerUp.getX() < topPaddle.getX() + topPaddle.getW() &&
					powerUp.getX() + powerUp.getWidth() > topPaddle.getX() &&
					powerUp.getY() < topPaddle.getY() + topPaddle.getH() &&
					powerUp.getY() + powerUp.getHeight() > topPaddle.getY()) {
				return true;
			}
		}
		
		if(powerUp.directionY() > 0){//Ball is traveling South
			if(powerUp.getX() < bottomPaddle.getX() + bottomPaddle.getW() &&
					powerUp.getX() + powerUp.getWidth() > bottomPaddle.getX() &&
					powerUp.getY() < bottomPaddle.getY() + bottomPaddle.getH() &&
					powerUp.getY() + powerUp.getHeight() > bottomPaddle.getY()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	Calculates the direction the ball will take after bouncing off the paddle.
	@param b The ball.
	@param pad The paddle.
	@return The new X direction for the ball. Will either be -3, -2, -1, 1, 2, or 3.
 */
	public double Angle(Ball b, Paddle pad){
		double offset = b.getX() - (pad.getX() + (pad.getW() / 2));
		double angle = offset / (pad.getW() / 2);
		System.out.println(offset + " " + angle + " " + angle * 3);
		return angle * 3;
	}
}