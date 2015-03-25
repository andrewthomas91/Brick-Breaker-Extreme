import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HighScoreList{
	ArrayList<HighScore> scores = new ArrayList<HighScore>();
	File directory = new File (".");
	
	/**
	Default constructor for the HighScoreList object.
	 */
	public HighScoreList(){
		 try{
			 FileReader fr = new FileReader(directory.getCanonicalPath() + "/src/HighScoreList.txt");
		     BufferedReader br = new BufferedReader(fr);
		     String strLine;
		     int lineCount = 0;
		     int score = 0;
			 String player = "";
			  
			 while ((strLine = br.readLine()) != null){
				 if(lineCount != 0){
					 player = strLine.substring(strLine.indexOf(45)+1, strLine.lastIndexOf(45));
					 Integer num = new Integer(strLine.substring(strLine.lastIndexOf(45)+1));
					 score = num.intValue();
					 scores.add(new HighScore(player, score));
				 }
				 lineCount++;
			 }
			 br.close();
		 }
		 catch (Exception e){
			 System.err.println("Error: " + e.getMessage());
		 }
	}
	
	/**
	Inserts a new high score to the list if it equal to or higher then any of the top ten score.
	Places the score on the list accordingly.
	@param player The player's name.
	@param score The player's score.
	 */
	public void insertHighScore(String player, int score){
		if(score > scores.get(scores.size()-1).getScore()){
			for(int x = scores.size()-2; x >= 0; x--){
				if(score < scores.get(x).getScore()){
					scores.add(x+1, new HighScore(player,score));
					break;
				}
				if(score == scores.get(x).getScore()){
					scores.add(x, new HighScore(player,score));
					break;
				}
				if(score > scores.get(x).getScore() && x == 0){
					scores.add(x, new HighScore(player,score));
					break;
				}
			}
			scores.remove(scores.size()-1);
		}
		updateList();
	}
	
	/**
	Updates the .txt file that stores the top ten high scores. 
	 */
	public void updateList(){
		try
	    {
	       FileWriter fw = new FileWriter(directory.getCanonicalPath() + "/src/HighScoreList.txt", false );
	       BufferedWriter bw = new BufferedWriter(fw);
	 
	       bw.write("Top 10 High Scores");
	       bw.newLine();
	       for(int x = 0; x < scores.size(); x++){
	    	   bw.write((x+1) + "-" + scores.get(x).getPlayer() + "-" + scores.get(x).getScore());
	    	   bw.newLine();
	       }	 
	       bw.close( );
	    }
	 
	    catch( IOException ioexception )
	    {
	      ioexception.printStackTrace( );
	    }
	}
	
	/**
	Prints the high scores.
	 */
	public void print(){
		for(int x = 0; x < scores.size(); x++){
			System.out.println(scores.get(x).getPlayer() + " " + scores.get(x).getScore());
		}
	}
	
	/**
	Gets the arraylist of high scores.
	@return The arraylist of high scores.
	 */
	public ArrayList<HighScore> getScores(){
		return scores;
	}
}