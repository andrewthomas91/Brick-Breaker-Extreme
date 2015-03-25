import java.awt.Toolkit;
import java.io.IOException;


public class PowerUpInverse extends PowerUp {
	
	PowerUpInverse(Game g){
		super(g);
	}
	
	PowerUpInverse(int x, int y, int r, int dx, int dy, int life, Game g){
		super(x,y,dx,dy,life,g);
		setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "powerupInvert35.png"));
	}
	
	public void action() {
		SoundEffect.LEVEL.play();
		getGame().switchInverse();
	}

	public void removeAction() {
		getGame().switchInverse();
	}
}
