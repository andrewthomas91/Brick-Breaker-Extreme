import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

public class LevelGenerator {
	
	private int[] brickGrid = new int[65];
	private ArrayList<Integer> levels = new ArrayList<Integer>();
	private static Random r = new Random();
	
	/**
	Default constructor for the Level Generator object
	 */
	public LevelGenerator(){
		brickGrid[0] = 0;//The first index of the array is always zero to avoid "off by one" errors
		for(int i = 0; i < 15; i++){
			levels.add(i+1);
		}
		shuffleLevels();
	}
	
	/**
	Uses the modern version of the Fisher–Yates shuffle algorithm to shuffle the levels.
 */
	public void shuffleLevels() {
		int j, temp;
		
		for(int i = (levels.size() - 1); i > 0; i--){
			j = r.nextInt(i);
			temp = levels.get(j);
			levels.set(j, levels.get(i));
			levels.set(i, temp);
		}
	}
	
	/**
	Generates a random level of bricks.
 */
	public void randomLevel(){
		brickGrid[0] = 0;//The first index of the array is always zero to avoid "off by one" errors
		
		for (int x = 1; x < 65; x++){
			brickGrid[x] = r.nextInt(2);
		}
	}
	
	/**
	Generates a new level to play.
	Levels are represented by an 8 by 8 matrix.
	Once all the levels have been played they are re-shuffled and ran through again.
 */
	public void generateLevel(int level){
		if((level-1) % levels.size() == 0)
			shuffleLevels();
		level = levels.get((level-1) % levels.size());
		
		if(level == 1){
			int[] levelGrid = {0,
							   0, 0, 0, 0, 0, 0, 0, 0,
			                   0, 0, 0, 0, 0, 0, 0, 0,
			                   0, 0, 2, 1, 2, 0, 0, 0,
			                   0, 0, 1, 1, 1, 0, 0, 0,
			                   0, 1, 5, 5, 5, 1, 0, 0,
			                   0, 0, 0, 3, 0, 0, 0, 0,
			                   0, 0, 0, 0, 0, 0, 0, 0,
			                   0, 0, 0, 0, 0, 0, 0, 0};
			
			for (int x = 1; x < 65; x++){
				brickGrid[x] = levelGrid[x];
			}
		}
		if(level == 2){
			int[] levelGrid = {0,
							   0, 0, 0, 0, 0, 0, 0, 0,
			                   0, 0, 2, 0, 0, 2, 0, 0,
			                   0, 0, 0, 0, 0, 0, 0, 0,
			                   0, 0, 0, 5, 5, 0, 0, 0,
			                   1, 0, 0, 0, 0, 0, 0, 1,
			                   0, 1, 0, 0, 0, 0, 1, 0,
			                   0, 0, 1, 1, 1, 1, 0, 0,
			                   0, 0, 0, 0, 0, 0, 0, 0};
			
			for (int x = 1; x < 65; x++){
				brickGrid[x] = levelGrid[x];
			}
		}
		if(level == 3){
			int[] levelGrid = {0,
							   0, 0, 0, 0, 0, 0, 0, 0,
			                   0, 1, 1, 1, 1, 1, 1, 0,
			                   0, 1, 0, 0, 0, 0, 1, 0,
			                   0, 1, 0, 2, 3, 0, 1, 0,
			                   0, 1, 0, 3, 2, 0, 1, 0,
			                   0, 1, 0, 0, 0, 0, 1, 0,
			                   0, 1, 1, 1, 1, 1, 1, 0,
			                   0, 0, 0, 0, 0, 0, 0, 0};
			
			for (int x = 1; x < 65; x++){
				brickGrid[x] = levelGrid[x];
			}
		}
		
		if(level == 4){
			int[] levelGrid = {0,
							   0, 0, 0, 0, 0, 0, 0, 0,
			                   0, 0, 1, 0, 0, 0, 0, 0,
			                   0, 1, 0, 1, 0, 0, 0, 0,
			                   1, 0, 0, 0, 1, 0, 1, 0,
			                   1, 1, 1, 1, 1, 1, 1, 1,
			                   1, 0, 0, 0, 1, 0, 1, 0,
			                   1, 0, 0, 0, 1, 0, 0, 0,
			                   1, 0, 0, 0, 1, 0, 0, 0};
			
			for (int x = 1; x < 65; x++){
				brickGrid[x] = levelGrid[x];
			}
		}
		
		if(level == 5){
			int[] levelGrid = {0,
							   0, 0, 0, 1, 0, 0, 0, 0,
			                   0, 0, 1, 2, 1, 0, 0, 0,
			                   0, 1, 0, 3, 0, 1, 0, 0,
			                   1, 2, 3, 0, 3, 2, 1, 0,
			                   0, 1, 0, 3, 0, 1, 0, 0,
			                   0, 0, 1, 2, 1, 0, 0, 0,
			                   0, 0, 0, 1, 0, 0, 0, 0,
			                   0, 0, 0, 0, 0, 0, 0, 0};
			
			for (int x = 1; x < 65; x++){
				brickGrid[x] = levelGrid[x];
			}
		}
		
		if(level == 6){
			int[] levelGrid = {0,
							   0, 0, 1, 0, 0, 1, 0, 0,
			                   0, 0, 1, 0, 0, 1, 0, 0,
			                   0, 0, 1, 0, 0, 1, 0, 0,
			                   0, 0, 2, 3, 3, 2, 0, 0,
			                   0, 0, 1, 0, 0, 1, 0, 0,
			                   0, 0, 1, 0, 0, 1, 0, 0,
			                   0, 0, 1, 0, 0, 1, 0, 0,
			                   0, 0, 0, 0, 0, 0, 0, 0};
			
			for (int x = 1; x < 65; x++){
				brickGrid[x] = levelGrid[x];
			}
		}
		
		if(level == 7){
			int[] levelGrid = {0,
							   1, 0, 0, 0, 0, 0, 0, 1,
			                   0, 1, 0, 0, 0, 0, 1, 0,
			                   0, 0, 1, 5, 0, 1, 0, 0,
			                   0, 0, 0, 2, 2, 0, 0, 0,
			                   0, 0, 0, 2, 2, 0, 0, 0,
			                   0, 0, 1, 0, 0, 1, 0, 0,
			                   0, 1, 0, 0, 0, 0, 1, 0,
			                   1, 0, 0, 0, 0, 0, 0, 1};
			
			for (int x = 1; x < 65; x++){
				brickGrid[x] = levelGrid[x];
			}
		}
		
		if(level == 8){
			int[] levelGrid = {0,
							   5, 5, 5, 0, 0, 5, 5, 5,
			                   5, 0, 2, 2, 2, 2, 0, 5,
			                   0, 0, 2, 3, 1, 2, 0, 0,
			                   0, 0, 2, 1, 3, 2, 0, 0,
			                   5, 0, 2, 2, 2, 2, 0, 5,
			                   5, 0, 0, 0, 0, 0, 0, 5,
			                   5, 5, 5, 0, 0, 5, 5, 5,
			                   0, 0, 0, 0, 0, 0, 0, 0};
			
			for (int x = 1; x < 65; x++){
				brickGrid[x] = levelGrid[x];
			}
		}
		
		if(level == 9){
			int[] levelGrid = {0,
							   0, 0, 0, 0, 0, 0, 0, 0,
			                   0, 1, 0, 0, 0, 0, 1, 0,
			                   1, 0, 0, 0, 0, 0, 0, 1,
			                   0, 1, 0, 0, 0, 0, 1, 0,
			                   1, 0, 0, 0, 0, 0, 0, 1,
			                   0, 1, 0, 0, 0, 0, 1, 0,
			                   1, 0, 0, 0, 0, 0, 0, 1,
			                   0, 0, 0, 0, 0, 0, 0, 0};
			
			for (int x = 1; x < 65; x++){
				brickGrid[x] = levelGrid[x];
			}
		}
		
		if(level == 10){
			int[] levelGrid = {0,
							   0, 0, 0, 1, 0, 0, 0, 0,
			                   0, 0, 1, 2, 1, 0, 0, 0,
			                   0, 1, 2, 2, 2, 1, 0, 0,
			                   0, 0, 0, 1, 0, 0, 0, 0,
			                   0, 0, 0, 1, 0, 0, 0, 0,
			                   0, 0, 0, 1, 0, 0, 0, 0,
			                   0, 0, 0, 0, 0, 0, 0, 0,
			                   0, 0, 0, 0, 0, 0, 0, 0};
			
			for (int x = 1; x < 65; x++){
				brickGrid[x] = levelGrid[x];
			}
		}
		
		if(level == 11){
			int[] levelGrid = {0,
							   0, 0, 0, 0, 0, 0, 0, 0,
			                   0, 5, 0, 1, 1, 0, 5, 0,
			                   0, 5, 0, 2, 2, 0, 5, 0,
			                   0, 5, 0, 1, 1, 0, 5, 0,
			                   0, 5, 0, 2, 2, 0, 5, 0,
			                   0, 5, 0, 1, 1, 0, 5, 0,
			                   0, 0, 0, 0, 0, 0, 0, 0,
			                   0, 0, 0, 0, 0, 0, 0, 0};
			
			for (int x = 1; x < 65; x++){
				brickGrid[x] = levelGrid[x];
			}
		}
		
		if(level == 12){
			int[] levelGrid = {0,
							   0, 0, 0, 0, 0, 0, 0, 0,
			                   0, 0, 0, 0, 0, 0, 0, 0,
			                   1, 0, 1, 0, 1, 0, 1, 0,
			                   0, 1, 0, 1, 0, 1, 0, 1,
			                   1, 0, 1, 0, 1, 0, 1, 0,
			                   0, 0, 0, 0, 0, 0, 0, 0,
			                   0, 0, 0, 0, 0, 0, 0, 0,
			                   0, 0, 0, 0, 0, 0, 0, 0};
			
			for (int x = 1; x < 65; x++){
				brickGrid[x] = levelGrid[x];
			}
		}
		
		if(level == 13){
			int[] levelGrid = {0,
							   0, 0, 0, 1, 0, 0, 0, 0,
			                   0, 2, 0, 1, 0, 2, 0, 0,
			                   0, 0, 2, 0, 2, 0, 0, 0,
			                   0, 0, 3, 0, 3, 0, 0, 0,
			                   0, 0, 1, 0, 1, 0, 0, 0,
			                   0, 0, 0, 0, 0, 0, 0, 0,
			                   0, 0, 0, 0, 0, 0, 0, 0,
			                   0, 0, 0, 0, 0, 0, 0, 0};
			
			for (int x = 1; x < 65; x++){
				brickGrid[x] = levelGrid[x];
			}
		}
		
		if(level == 14){
			int[] levelGrid = {0,
							   0, 0, 0, 0, 0, 0, 0, 0,
			                   0, 0, 0, 0, 0, 0, 0, 0,
			                   0, 0, 1, 1, 0, 2, 0, 0,
			                   0, 0, 1, 5, 5, 0, 0, 0,
			                   0, 0, 0, 5, 5, 1, 0, 0,
			                   0, 0, 2, 0, 1, 1, 0, 0,
			                   0, 0, 0, 0, 0, 0, 0, 0,
			                   0, 0, 0, 0, 0, 0, 0, 0};
			
			for (int x = 1; x < 65; x++){
				brickGrid[x] = levelGrid[x];
			}
		}
		
		if(level == 15){
			int[] levelGrid = {0,
							   0, 0, 0, 0, 0, 0, 0, 0,
			                   1, 1, 1, 1, 1, 1, 1, 1,
			                   0, 0, 0, 0, 0, 0, 0, 0,
			                   1, 1, 1, 1, 1, 1, 1, 1,
			                   0, 0, 0, 0, 0, 0, 0, 0,
			                   1, 1, 1, 0, 0, 1, 1, 1,
			                   0, 0, 0, 0, 0, 0, 0, 0,
			                   0, 0, 0, 0, 0, 0, 0, 0};
			
			for (int x = 1; x < 65; x++){
				brickGrid[x] = levelGrid[x];
			}
		}
	}
	
	/**
	Gets the value at the given index in the brickGrid array.
	@param index The index within the brickGrid array.
	@return The value at the given index.
 */
	public int getValue(int index){
		if(index >= 0 && index <= 64)
			return brickGrid[index];
		else
			return 0;
	}
	
	/**
	prints out a visual ascii representation of the current level of bricks.
 */
	public void print(){
		for (int x = 1; x < 65; x++){
			System.out.print(brickGrid[x] + " ");
			if(x%8 == 0)
				System.out.println();
		}
		System.out.println();
		System.out.println();
	}
	
	/**
	Gets the XCord of where to place the brick on the game screen.
	@param index The index in the array of bricks.
	@return The corresponding XCord of where to place that brick.
 */
	public int getXCord(int index){
		if(index % 8 == 1)
			return 100;
		if(index % 8 == 2)
			return 150;
		if(index % 8 == 3)
			return 200;
		if(index % 8 == 4)
			return 250;
		if(index % 8 == 5)
			return 300;
		if(index % 8 == 6)
			return 350;
		if(index % 8 == 7)
			return 400;
		if(index % 8 == 0)
			return 450;
		return 0;
	}
	
	/**
	Gets the YCord of where to place the brick on the game screen.
	@param index The index in the array of bricks.
	@return The corresponding YCord of where to place that brick.
 */
	public int getYCord(int index){
		if(index <= 8)
			return 100;
		if(index > 8 && index <= 16)
			return 150;
		if(index > 16 && index <= 24)
			return 200;
		if(index > 24 && index <= 32)
			return 250;
		if(index > 32 && index <= 40)
			return 300;
		if(index > 40 && index <= 48)
			return 350;
		if(index > 48 && index <= 56)
			return 400;
		if(index > 56)
			return 450;
		return 0;
	}

}
