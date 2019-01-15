package bomberMan;

import org.newdawn.slick.SlickException;


public class Background extends gameSprites{

	public Background(float x,float y) throws SlickException {
		super("assets/bombermanBackground.png");	//call parent constructor to set the image of this bird
		//initialize its inherited properties
		//initialize our road's properties
		this.X=x;
		this.Y=y;
		/*I also want me sprite to posess movement/speed.*/
	}
	
	
	public void setX(float x){this.X=x;}


	
	/*Control speed limits in setSpeed.*/

}
