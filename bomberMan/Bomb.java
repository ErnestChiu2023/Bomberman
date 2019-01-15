package bomberMan;

import org.newdawn.slick.SlickException;

public class Bomb extends gameSprites{

	public Bomb(float x,float y) throws SlickException {
		super("assets/bomb.png");
		// TODO Auto-generated constructor stub
		this.X=x;
		this.Y=y;
	}

	public void setX(float x) {this.X=x;}

}
