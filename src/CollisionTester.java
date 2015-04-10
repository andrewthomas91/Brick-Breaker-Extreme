/**
	For testing purposes only.
 */
public class CollisionTester {
	
	private Ball b;
	private Brick1 bk;
	private CollisionDetection det;
	private int testCase = 0;
	private int failures = 0;
	
	public CollisionTester(){
		det = new CollisionDetection();
		
		//Test 1. Ball is outside brick and heading NorthEast
		b = new Ball(120, 200, 2, 1, -1);
		bk = new Brick1(100,100,50,50);
		run();
		check(det.BallAndBrick(b, bk), false);	
	
		//Test 2. Ball is outside brick and heading NorthWest
		b = new Ball(130, 200, 2, -1, -1);
		run();
		check(det.BallAndBrick(b, bk), false);	
		
		//Test 3. Ball is outside brick and heading SouthEast
		b = new Ball(120, 50, 2, 1, 1);
		run();
		check(det.BallAndBrick(b, bk), false);	
		
		//Test 4. Ball is outside brick and heading SouthWest
		b = new Ball(130, 50, 2, -1, 1);
		run();
		check(det.BallAndBrick(b, bk), false);	
				
		//Test 5. Ball is inside brick and heading NorthEast
		b = new Ball(125, 145, 2, 1, -1);
		run();
		check(det.BallAndBrick(b, bk), true);
		
		//Test 6. Ball is inside brick and heading NorthEast
		b = new Ball(105, 125, 2, 1, -1);
		run();
		check(det.BallAndBrick(b, bk), true);	
				
		//Test 7. Ball is inside brick and heading NorthWest
		b = new Ball(125, 145, 2, -1, -1);
		run();
		check(det.BallAndBrick(b, bk), true);	
		
		//Test 8. Ball is inside brick and heading NorthWest
		b = new Ball(145, 125, 2, -1, -1);
		run();
		check(det.BallAndBrick(b,  bk), true);	
				
		//Test 9. Ball is inside brick and heading SouthEast
		b = new Ball(125, 105, 2, 1, 1);
		run();
		check(det.BallAndBrick(b, bk), true);	
		
		//Test 10. Ball is inside brick and heading SouthEast
		b = new Ball(105, 125, 2, 1, 1);
		run();
		check(det.BallAndBrick(b, bk), true);	
				
		//Test 11. Ball is inside brick and heading SouthWest
		b = new Ball(125, 105, 2, -1, 1);
		run();
		check(det.BallAndBrick(b, bk), true);	
		
		//Test 12. Ball is inside brick and heading SouthWest
		b = new Ball(145, 125, 2, -1, 1);
		run();
		check(det.BallAndBrick(b, bk), true);	
		
		if(failures == 0)
			System.out.println("All Tests Passed");
	}
	
	private boolean check(boolean checkThis, boolean equalsThis) {
		if(checkThis == equalsThis) {
			return true;
		}
		else {
			failures++;
			System.out.println("Test: " + testCase + " Failed");
			return false;
		}
	}
	
	private void run()  {
		testCase++;
		System.out.println("Test: " + testCase + " Running");
	}
	
	public static void main(String[] args) 
	{
		CollisionTester tester = new CollisionTester();
	}
}
