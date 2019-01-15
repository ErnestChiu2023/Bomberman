package bomberMan;

import org.newdawn.slick.SlickException;

public class Block extends gameSprites{

	public Block(float x,float y) throws SlickException {
		super("assets/block.png");
		// TODO Auto-generated constructor stub
		this.X=x;
		this.Y=y;
	}

	public void setX(float x) {this.X=x;}

}
