/**
	For testing purposes only.
 */
public class CollisionTester {
	
	private Ball b;
	private Brick1 bk;
	private CollisionDetection det;
	
	public CollisionTester(){
		det = new CollisionDetection();
		
		//Test 1. Ball is outside brick and heading NorthEast
		b = new Ball(120, 200, 2, 1, -1);
		bk = new Brick1(100,100,50,50);
		System.out.println("Test 1: false = " + det.BallAndBrick(b, b.getXDir(), b.getYDir(), bk) + " and dir X = " + b.getXDir() + ", Y = " + b.getYDir());
		
		//Test 2. Ball is outside brick and heading NorthWest
		b = new Ball(130, 200, 2, -1, -1);
		System.out.println("Test 2: false = " + det.BallAndBrick(b, b.getXDir(), b.getYDir(), bk) + " and dir X = " + b.getXDir() + ", Y = " + b.getYDir());
		
		//Test 3. Ball is outside brick and heading SouthEast
		b = new Ball(120, 50, 2, 1, 1);
		System.out.println("Test 3: false = " + det.BallAndBrick(b, b.getXDir(), b.getYDir(), bk) + " and dir X = " + b.getXDir() + ", Y = " + b.getYDir());
		
		//Test 4. Ball is outside brick and heading SouthWest
		b = new Ball(130, 50, 2, -1, 1);
		System.out.println("Test 4: false = " + det.BallAndBrick(b, b.getXDir(), b.getYDir(), bk) + " and dir X = " + b.getXDir() + ", Y = " + b.getYDir());
		
		System.out.println();
		
		//Test 5. Ball is inside brick and heading NorthEast
		b = new Ball(125, 145, 2, 1, -1);
		System.out.println("Test 5: true = " + det.BallAndBrick(b, b.getXDir(), b.getYDir(), bk) + " and dir X = " + b.getXDir() + ", Y = " + b.getYDir());
		
		//Test 6. Ball is inside brick and heading NorthEast
		b = new Ball(105, 125, 2, 1, -1);
		System.out.println("Test 6: true = " + det.BallAndBrick(b, b.getXDir(), b.getYDir(), bk) + " and dir X = " + b.getXDir() + ", Y = " + b.getYDir());
		
		System.out.println();
		
		//Test 7. Ball is inside brick and heading NorthWest
		b = new Ball(125, 145, 2, -1, -1);
		System.out.println("Test 7: true = " + det.BallAndBrick(b, b.getXDir(), b.getYDir(), bk) + " and dir X = " + b.getXDir() + ", Y = " + b.getYDir());
		
		//Test 8. Ball is inside brick and heading NorthWest
		b = new Ball(145, 125, 2, -1, -1);
		System.out.println("Test 8: true = " + det.BallAndBrick(b, b.getXDir(), b.getYDir(), bk) + " and dir X = " + b.getXDir() + ", Y = " + b.getYDir());
		
		System.out.println();
		
		//Test 9. Ball is inside brick and heading SouthEast
		b = new Ball(125, 105, 2, 1, 1);
		System.out.println("Test 9: true = " + det.BallAndBrick(b, b.getXDir(), b.getYDir(), bk) + " and dir X = " + b.getXDir() + ", Y = " + b.getYDir());
		
		//Test 10. Ball is inside brick and heading SouthEast
		b = new Ball(105, 125, 2, 1, 1);
		System.out.println("Test 10: true = " + det.BallAndBrick(b, b.getXDir(), b.getYDir(), bk) + " and dir X = " + b.getXDir() + ", Y = " + b.getYDir());
		
		System.out.println();
		
		//Test 11. Ball is inside brick and heading SouthWest
		b = new Ball(125, 105, 2, -1, 1);
		System.out.println("Test 11: true = " + det.BallAndBrick(b, b.getXDir(), b.getYDir(), bk) + " and dir X = " + b.getXDir() + ", Y = " + b.getYDir());
		
		//Test 12. Ball is inside brick and heading SouthWest
		b = new Ball(145, 125, 2, -1, 1);
		System.out.println("Test 12: true = " + det.BallAndBrick(b, b.getXDir(), b.getYDir(), bk) + " and dir X = " + b.getXDir() + ", Y = " + b.getYDir());
		
		
	}
	
	public static void main(String[] args) 
	{
		CollisionTester tester = new CollisionTester();
	}
}
