import java.awt.Toolkit;
import java.io.IOException;


public class PowerUpFlashingBall extends PowerUp {
	
	PowerUpFlashingBall(Game g){
		super(g);
	}
	
	PowerUpFlashingBall(int x, int y, int r, int dx, int dy, int life, Game g){
		super(x,y,dx,dy,life,g);
		setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "powerupFlashingBall35.png"));
	}
	
	public void action() {
		SoundEffect.LEVEL.play();
		getGame().setFlashingBall(true);
	}

	public void removeAction() {
		getGame().setFlashingBall(false);
	}
}
