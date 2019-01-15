package bomberMan;

import org.newdawn.slick.SlickException;

public class power extends gameSprites{

	public power(float x,float y) throws SlickException {
		super("assets/battery.png");
		// TODO Auto-generated constructor stub
		this.X=x;
		this.Y=y;
	}

	public void setX(float x) {this.X=x;}

}
