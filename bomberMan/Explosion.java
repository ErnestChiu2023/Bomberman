package bomberMan;

import org.newdawn.slick.SlickException;

public class Explosion  extends gameSprites{

	public Explosion(float x,float y) throws SlickException {
		super("assets/boom.png");
		// TODO Auto-generated constructor stub
		this.X=x;
		this.Y=y;
	}

	public void setX(float x) {this.X=x;}

}