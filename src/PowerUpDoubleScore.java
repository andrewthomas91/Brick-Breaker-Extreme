import java.awt.Toolkit;
import java.io.IOException;


public class PowerUpDoubleScore extends PowerUp {
	
	PowerUpDoubleScore(Game g){
		super(g);
	}
	
	PowerUpDoubleScore(int x, int y, int r, int dx, int dy, int life, Game g){
		super(x,y,dx,dy,life,g);
		setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "powerupMultiplier35.png"));
	}
	
	public void action() {
		SoundEffect.LEVEL.play();
		getGame().setScoreMultiplier(2);
	}

	public void removeAction() {
		getGame().setScoreMultiplier(1);
	}
}
