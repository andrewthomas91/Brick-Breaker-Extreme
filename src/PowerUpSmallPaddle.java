import java.awt.Toolkit;
import java.io.IOException;
public class PowerUpSmallPaddle  extends PowerUp {
	
	Paddle pad1,pad2,pad3,pad4;
	
	PowerUpSmallPaddle(Game g){
		super(g);
	}
	
	PowerUpSmallPaddle(int x, int y, int dx, int dy, int life, Game g, Paddle p1, Paddle p2, Paddle p3, Paddle p4){
		super(x,y,dx,dy,life,g);
		pad1 = p1;
		pad2 = p2;
		pad3 = p3;
		pad4 = p4;
		setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "powerupSmall35.png"));
	}
	
	public void action() {
		SoundEffect.LEVEL.play();
		pad1.setW(30);
		pad2.setW(30);
		if(getGame().getOnePlayer()==false){
			pad3.setW(30);
			pad4.setW(30);
			
		}
		pad1.setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "paddleSmall.png"));
		pad2.setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "paddleSmall.png"));
		if (getGame().getOnePlayer()==false){
			pad3.setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "vPaddleSmall.png"));
			pad4.setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "vPaddleSmall.png"));
		}
	}

	public void removeAction() {	
		pad1.setW(60);
		pad2.setW(60);
		if(getGame().getOnePlayer()==false){
			pad3.setW(60);
			pad4.setW(60);
		}
		pad1.setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "paddle.png"));
		pad2.setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "paddle.png"));
		if (getGame().getOnePlayer()==false){
			pad3.setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "paddle.png"));
			pad4.setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "paddle.png"));
		}
	}
}

