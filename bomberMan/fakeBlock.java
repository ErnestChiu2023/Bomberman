package bomberMan;

import org.newdawn.slick.SlickException;

public class fakeBlock extends gameSprites{

	public fakeBlock(float x,float y) throws SlickException {
		super("assets/brokenBlock.png");
		// TODO Auto-generated constructor stub
		this.X=x;
		this.Y=y;
	}

	public void setX(float x) {this.X=x;}

}
