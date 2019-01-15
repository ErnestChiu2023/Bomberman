package bomberMan;

import org.newdawn.slick.SlickException;


public class Player  extends gameSprites{
	private final float MAXSPEED=25;
	private float speed;
	private Bomb[] Capacity=new Bomb[4];
	private int power = 2;
	private String name=null;
	public Player(float x,float y, String link) throws SlickException {
		super(link);	//call parent constructor to set the image of this bird
		//initialize its inherited properties
		//initialize our road's properties
		if (link.equals("assets/Bomberman1.png"))
		{
			name="player1";
		}
		if(link.equals("assets/Bomberman2.png"))
		{
			name="player2";
		}
		this.X=x;
		this.Y=y;
	}
	public Player(float x,float y, String link, String name) throws SlickException {
		super(link);	//call parent constructor to set the image of this bird
		//initialize its inherited properties
		//initialize our road's properties
		this.name=name;
		this.X=x;
		this.Y=y;
	}
	


	public void setX(float x){this.X=x;}
	
	public void powerUp()
	{
		power++;
	}
	public int showPower()
	{
		return power;
	}
	/*Control speed limits in setSpeed.*/


}
