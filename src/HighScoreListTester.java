/**
	For testing purposes only.
 */
public class HighScoreListTester {
	
	private HighScoreList list;
	
	public HighScoreListTester(){
		list = new HighScoreList();
		list.print();
		System.out.println("Running insert on Bob - 75");
		list.insertHighScore("Bob", 75);
		list.print();
		System.out.println("Running insert on Taylor - 200");
		list.insertHighScore("Taylor", 200);
		list.print();
		System.out.println("Running insert on Jack - 10");
		list.insertHighScore("Jack", 10);
		list.print();
		System.out.println("Running insert on Sharon - 90");
		list.insertHighScore("Sharon", 90);
		list.print();
	}
	
	public static void main(String[] args) 
	{
		HighScoreListTester tester = new HighScoreListTester();
	}
}