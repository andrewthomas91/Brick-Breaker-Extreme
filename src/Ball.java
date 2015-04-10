import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Ball{
	private int yCord, radius, directionY, speed, score, speedOld;
	double xCord, directionX, directionXOld;
	private static Random r = new Random();
	private CollisionDetection det;
	private Image icon;
	String imgPath = System.getProperty("user.dir") + File.separator + "Images" + File.separator;
	
	/**
    	Default constructor for the Ball object
  	 */
	public Ball(){		
		det = new CollisionDetection();
		xCord = r.nextInt(366);//An int between 0-365
		xCord += 10;//Ten is added to this value to get it away from the left wall. Making random between 10-375
		yCord = 540;//Starting y coordinate for the ball
		radius = 10;//Radius of the ball
		score = 0;
		//The initial x direction of the ball is chosen at random
		directionY = -1;
		if (r.nextInt(2) == 0)
			directionX = -1;
		else
			directionX = 1;
		//Speed of the ball. The ball moves this many pixels per 20 milliseconds.
		speed = 4;
		setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "ball.png"));
	}
	
	/**
		Constructor for the Ball object
		@param x The X Coordinate of the ball.
		@param y The Y Coordinate of the ball.
		@param r The radius of the ball.
		@param dirX The direction in the X axis the ball is moving.
		@param dirY The direction in the Y axis the ball is moving.
	 */
	public Ball(int x, int y, int r, int dirX, int dirY){		
		det = new CollisionDetection();
		xCord = x;
		yCord = y;
		radius = r;
		score = 0;
		directionY = dirY;
		directionX = dirX;
		speed = 4;
		setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "ball.png"));
	}
	
	/**
		Resets the Balls instance variables to represent a new game situation.
	 */
	public void newGame(){
		xCord = r.nextInt(376);
		xCord += 10;
		yCord = 540;
		directionY = -1;
		if (r.nextInt(2) == 0)
			directionX = -1;
		else
			directionX = 1;
		speed = 4;
	}
	
	/**
		Stops the ball from moving by setting its speed and directionX to zero.
	 */
	public void stop(){
		speedOld = speed;
		speed = 0;
		directionXOld = directionX;
		directionX = 0;
	}
	
	/**
		Resumes the ball's movement by setting its speed and directionX back to their previous states.
	 */
	public void resume(){
		directionX = directionXOld;
		speed = speedOld;
	}
	
	/**
	Updates the ball's x and y coordinates when called. Single Player move function. Also updates the x and y directions accordingly. Checks the game over condition.
	@param bottomPaddle The Paddle object being used in this game. Used to check if the ball bounces off it.
	@param topPaddle The second Paddle object being used in this game.
	@param bricks The bricks currently on the screen and being used by the game.
	@return true if the game is still going, or false if the game is over.
 */
	public boolean move(Paddle bottomPaddle, Paddle topPaddle, ArrayList<Brick> bricks){
		boolean collisionDetected = false;
		for(int i = 0; i < bricks.size(); i++){
			if((bricks.get(i)).getAlive()){
				switch(det.BallAndBrickDirection(this, bricks.get(i))) {
					case 1: directionY = directionY * -1; collisionDetected = true; break;
					case 2: directionY = directionY * -1; collisionDetected = true; break;
					case 3: directionX = directionX * -1; collisionDetected = true; break;
					case 4: directionX = directionX * -1; collisionDetected = true; break;
					case 5: break;
				}
				if(collisionDetected) {
					if(bricks.get(i).getLives() > 0)
						bricks.get(i).hurtBrick();
					if(bricks.get(i).getLives() == -1)
						SoundEffect.WALL.play();
					collisionDetected = false;
				}
			}
		}
		
		if (xCord <= 0 || xCord >= 585){//If the ball has hit the left or right wall the x direction is changed
			SoundEffect.WALL.play();
			directionX = directionX * -1;
			if(xCord <= 0)
				xCord = 1;
			if(xCord >= 585)
				xCord = 584;
		}
		if (yCord >= 585 && (bottomPaddle.getX() - 20 <= xCord || bottomPaddle.getX() + 20 >= xCord))//If the ball has missed the paddle the game is ended by returning false
			return true;
		if (yCord <= 29 && (topPaddle.getX() - 20 <= xCord || topPaddle.getX() + 20 >= xCord))//If the ball has missed the paddle the game is ended by returning false
			return true;
		
		if (det.BallAndPaddleTopBottom(this, bottomPaddle, topPaddle)){//If the ball hits either of the paddle does the following
			SoundEffect.PADDLE.play();
			score++;
			if(score%10 == 0)//Increments the speed every five points
				speed++;
			directionX = det.Angle(this, bottomPaddle);
			directionY = directionY * -1;//Changes the y direction of the ball
		}
			
		//Updates the coordinates using the current directions (x and y) and the speed of the ball
		xCord += directionX;
		yCord += directionY * speed;
		return false;//Returns true meaning the game has not ended
	}
			
	/**
	 * Sets the X & Y directions
	 * @param directionX, directionY
	 */
	public void setDircetions(int dirX, int dirY){
		directionX = directionX * dirX;
		directionY = directionY * dirY;
	}
	
	/**
	 * Sets the Value of y
	 * @param x
	 */
	public void setY(int y){
		yCord = y;
	}
	
	/**
	 * Sets the Value of X
	 * @param x
	 */
	public void setX(int x){
		xCord = x;
	}
	
	/**
	 * Sets the Icon
	 * @param image
	 */
	public void setIcon(Image i){
		icon = i;
	}
	
	/**
	 * Sets the Score
	 * @param score
	 */
	public void setScore(int s){
		score = s;
	}
	
	/**
	Gets the x coordinate of the ball.
	@return the x coordinate of the ball
	 */
	public double getX(){
		return xCord;
	}
	
	/**
	Gets the x coordinate of the ball.
	@return the x coordinate of the ball
	 */
	public int getXAsInt(){
		return (int) (xCord);
	}
	
	/**
	Gets the y coordinate of the ball.
	@return the y coordinate of the ball
	 */
	public int getY(){
		return yCord;
	}
	
	/**
	 * Gets the Direction of X
	 * @return directionX
	 */
	public double getXDir(){
		return directionX;
	}
	
	/**
	 * Gets the Direction of X
	 * @return directionX
	 */
	public int getXDirAsInt(){
		return (int) (directionX);
	}
	
	/**
	 * Gets the Direction of Y
	 * @return directionY
	 */
	public int getYDir(){
		return directionY;
	}
	
	/**
	Gets the radius of the ball.
	@return the radius of the ball
	 */
	public int getRadius(){
		return radius;
	}
	
	/**
	Gets the game's score.
	@return the game's score
	 */
	public int getScore(){
		return score;
	}
	
	/**
	Gets the ball's speed.
	@return the ball's speed
	 */
	public int getSpeed(){
		return speed;
	}
	
	/**
	Gets the ball's old speed.
	@return the ball's old speed
	 */
	public int getSpeedOld(){
		return speedOld;
	}

	/**
	 * Gets the Image
	 * @return Image
	 */
	public Image getIcon() {
		return icon;
	}
}
