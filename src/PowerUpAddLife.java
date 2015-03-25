import java.awt.Toolkit;
import java.io.IOException;


public class PowerUpAddLife extends PowerUp {
	
	PowerUpAddLife(Game g){
		super(g);
	}
	
	PowerUpAddLife(int x, int y, int r, int dx, int dy, int life, Game g){
		super(x,y,dx,dy,life,g);
		setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "powerupLife35.png"));
	}
	
	public void action() {
		getGame().setLives(getGame().getLives()+1);
	}

	public void removeAction() {	
	}
}
