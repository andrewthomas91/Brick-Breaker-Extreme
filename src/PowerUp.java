import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

public abstract class PowerUp{
	private int xCord, yCord, directionY,directionX,height,width,lifeTime;
	private boolean active = false;
	private boolean collected = false;
	private Game game;
	String imgPath = System.getProperty("user.dir") + File.separator + "Images" + File.separator;
	private Image icon;
	private CollisionDetection det;
	
	/**
	Constructor for the PowerUp object
	@param g The Game object.
 */
		PowerUp(Game g){
			game = g;
			xCord = 0;
			yCord = 0;
			directionY = 1;
			directionX = 0;
			height = 20;
			width = 20;
			lifeTime = 100;
			det = new CollisionDetection();
		}
		
		/**
		Constructor for the PowerUp object
		@param x The x coordinate.
		@param y The y coordinate.
		@param dx The direction of the powerup along the x axis.
		@param dy The direction of the powerup along the y axis.
		@param time The life time of the powerup once it is active.
		@param g The Game object.
	 */
		PowerUp(int x, int y, int dx, int dy, int time, Game g){
			game = g;
			xCord = x;
			yCord = y;
			height = 20;
			width = 20;
			directionY = dy;
			directionX = dx;
			lifeTime = time;
			det = new CollisionDetection();
		}
		
		/**
		Moves the powerup down the screen.
		Checks to see if it is collected via the bottom paddle.
		Returns true if it is collected.
		@param bottomPaddle
		@param topPaddle
		@return True if the powerup was collected or false if it wasn't.
	 */
		public boolean move(Paddle bottomPaddle, Paddle topPaddle){
			if (det.PowerupAndPaddleTopBottom(this, bottomPaddle, topPaddle)){
				collected = true;
				icon = null;
				xCord = 0;
				yCord = 0;
				directionY = 0;
				directionX = 0;
				action();
				if(lifeTime > 0)
					active = true;
				return true;
			}
			else
				yCord += directionY * 2;
			return false;
		}
	
	    public abstract void action();
	    
	    public abstract void removeAction();
			
	    /**
		Gets the icon.
		@return The icon.
	 */
		public Image getIcon() {
			return icon;
		}
		
		 /**
		Checks if the powerup is offscreen.
		@return True if the powerup is offscreen or false if it isn't.
	 */
		public boolean OffScreen() {
			if(yCord>570 ||yCord<30)
				return true;
			else
			    return false;
		}	
		
		 /**
		Sets the icon.
		@param i The icon.
	 */
		public void setIcon(Image i){
			icon = i;
		}

		/**
		Gets the x coordinate.
		@return The x coordinate.
	 */
		public int getX() {
			return xCord;
		}

		/**
		Gets the height.
		@return The height.
	 */
		public int getHeight() {
			return height;
		}
		
		/**
		Gets the width.
		@return The width.
	 */
		public int getWidth(){
			return width;
		}

		/**
		Gets the y coordinate.
		@return The y coordinate.
	 */
		public int getY() {
			return yCord;
		}
		
		/**
		Gets the game.
		@return The game.
	 */
		public Game getGame(){
			return game;
		}
		
		/**
		Gets the life time.
		@return The life time.
	 */
		public int getLifeTime(){
			return lifeTime;
		}
		
		/**
		Decreases the time by one.
		If the life time is zero makes this powerup no longer active.
	 */
		public void TimePass(){
			lifeTime--;
			if(lifeTime == 0)
				active = false;
		}
		
		/**
		Gets the active boolean.
		@return The active boolean.
	 */
		public boolean getActive(){
			return active;
		}
		
		/**
		Gets the collected boolean.
		@return The collected boolean.
	 */
		public boolean getCollected(){
			return collected;
		}
		
		/**
		Gets the y direction powerup is traveling.
		@return The y direction.
	 */
		public int directionY(){
			return directionY;
		}
}
