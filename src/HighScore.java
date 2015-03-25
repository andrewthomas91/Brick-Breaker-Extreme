public class HighScore {
	String player;
	int score;
	
	/**
	Default constructor for the HighScore object.
	 */
	public HighScore(){
		player = "player";
		score = 100;
	}
	
	/**
	Constructor for the HighScore object.
	@param p The player's name.
	@param s The player's score.
	 */
	public HighScore(String p, int s){
		player = p;
		score = s;
	}
	
	public void setScore(int s){
		score = s;
	}
	
	public void setPlayer(String p){
		player = p;
	}
	
	public int getScore(){
		return score;
	}
	
	public String getPlayer(){
		return player;
	}
}
