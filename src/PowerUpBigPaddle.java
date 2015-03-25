import java.awt.Toolkit;
import java.io.IOException;
public class PowerUpBigPaddle extends PowerUp {
	
	Paddle pad1,pad2,pad3,pad4;
	
	PowerUpBigPaddle(Game g){
		super(g);
	}
	
	PowerUpBigPaddle(int x, int y, int dx, int dy, int life, Game g, Paddle p1, Paddle p2){
		super(x,y,dx,dy,life,g);
		pad1 = p1;
		pad2 = p2;
		setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "powerupBig35.png"));
	}
	
	
	PowerUpBigPaddle(int x, int y, int dx, int dy, int life, Game g, Paddle p1, Paddle p2, Paddle p3, Paddle p4){
		super(x,y,dx,dy,life,g);
		pad1 = p1;
		pad2 = p2;
		pad3 = p3;
		pad4 = p4;
		setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "powerupBig35.png"));
	}
	
	public void action() {
		SoundEffect.LEVEL.play();
		pad1.setW(120);
		pad2.setW(120);
		if(getGame().getOnePlayer()==false){
			pad3.setW(120);
			pad4.setW(120);
			
		}
		pad1.setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "paddleBig.png"));
		pad2.setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "paddleBig.png"));
		if (getGame().getOnePlayer()==false){
			pad3.setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "vPaddleBig.png"));
			pad4.setIcon(Toolkit.getDefaultToolkit().getImage(imgPath + "vPaddleBig.png"));
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

