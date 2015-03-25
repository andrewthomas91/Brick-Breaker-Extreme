import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;


public class Background {
	private ArrayList<Image> backgrounds = new ArrayList<Image>();
	Image lightBlue, dino, brickBackground, sunset, ucsb, lake, winter, winterTwo, fall, darkSky;
	String imgPath = System.getProperty("user.dir") + File.separator + "Images" + File.separator;
	private boolean shuffle = true;
	private static Random r = new Random();
	
	/**
		Default constructor for the Background object
	 */
	public Background(){
		lightBlue = Toolkit.getDefaultToolkit().getImage(imgPath + "lightBlue.jpg");
		dino = Toolkit.getDefaultToolkit().getImage(imgPath + "dinoF14.jpg");
		brickBackground = Toolkit.getDefaultToolkit().getImage(imgPath + "brickBackground.jpg");
		
		sunset = Toolkit.getDefaultToolkit().getImage(imgPath + "sunset.jpg");
		ucsb = Toolkit.getDefaultToolkit().getImage(imgPath + "ucsb.jpg");
		lake = Toolkit.getDefaultToolkit().getImage(imgPath + "lake.jpg");
		winter = Toolkit.getDefaultToolkit().getImage(imgPath + "Winter.jpg");
		winterTwo = Toolkit.getDefaultToolkit().getImage(imgPath + "Winter2.jpeg");
		fall = Toolkit.getDefaultToolkit().getImage(imgPath + "Fall.jpg");
		darkSky = Toolkit.getDefaultToolkit().getImage(imgPath + "DarkSky.jpg");
			
		backgrounds.add(sunset);
		backgrounds.add(ucsb);
		backgrounds.add(lake);
		backgrounds.add(winter);
		backgrounds.add(darkSky);
		backgrounds.add(winterTwo);
		backgrounds.add(fall);
	}
	
	/**
		Uses the modern version of the Fisher–Yates shuffle algorithm to shuffle the background images
	 */
	public void shuffleBackgrounds() {
		int j;
		Image temp;
		
		for(int i = (backgrounds.size() - 1); i > 0; i--){
			j = r.nextInt(i);
			temp = backgrounds.get(j);
			backgrounds.set(j, backgrounds.get(i));
			backgrounds.set(i, temp);
		}
	}

	/**
		Gets the correct image corresponding to the level the player is currently on
	 	@param level The level the player is currently on
		@return the background image
	 */
	public Image getBackground(int level){
		if(level == -2)
			return lightBlue;
		if(level == -1)
			return dino;
		if(level == 0)
			return brickBackground;
		
		level--;
		level = level % backgrounds.size();
		if(level == 1)
			shuffle = true;
		if(level == 0 && shuffle){
			shuffleBackgrounds();
			shuffle = false;
		}
		if(level < backgrounds.size())
			return backgrounds.get(level);

		return winter;
		
	}
}
